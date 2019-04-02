package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/**
 * Klasa WeatherStation pobiera parametry pogody.
 * @author Julia Michalak
 */
public class WeatherStation implements  Runnable {

    /**
     * Reprezentuje watek.
     */
    private Thread worker;
    protected volatile boolean isRunning = false;

    /**
     * Czestotliwosc pobierania danych pogodowych.
     */
    private int interval;

    /**
     * Nazwa miasta.
     */
    private String name;

    /**
     * Lista obserwatorow.
     */
    private volatile ArrayList<Observer> observers = new ArrayList<>();

    /**
     * Reprezentuje mapowanie r1.
     */
    private Map r1;

    /**
     * Typ zapytania dotyczaczy pogody.
     */
    private String requestType;

    /**
     * Jednostka pobieranej pogody
     */
    private String units;

    /**
     * Wartosc temperatury.
     */
    private double temperature;

    /**
     * Wartosc cisnienia.
     */
    private double pressure;

    /**
     * Wartosc wilgotnosci.
     */
    private double humidity;

    /**
     * Wartosc temperatury minimalnej.
     */
    private double tempMin;

    /**
     * Wartosc temperatury maksymalnej.
     */
    private double tempMax;

    /**
     * Metoda dodania obserwatora.
     * @param observer
     */
     public void addObserver(Observer observer) {
         if (!observers.contains(observer)) {
             observers.add(observer)
         ;}
     }

    /**
     * Metoda usuniecia obserwatora.
     * @param observer
     */
     public void removeObserver(Observer observer) {
        if (observers.contains(observer)) observers.remove(observer);
     }

    /**
     * Metoda uaktualniania obserwatora.
     * @throws IllegalAccessException
     */
     public void updateObservers() throws IllegalAccessException {
         for (Observer o : observers) {
             getDataFromServer();
             o.update(temperature, pressure, humidity, tempMax, tempMin);
         }
     }

    /**
     * Metoda startu.
     */
    public void start(){
         worker= new Thread(this,"Weather");
         worker.start();
    }

    /**
     * Metoda przerwania.
     */
    public void interrupt(){
         isRunning = false;
         worker.interrupt();
    }

    /**
     * Metoda zatrzymania.
     */
    public void stop(){
         isRunning = false;
    }

    /**
     * Tworzy obiekt WeatherStation z zadanymi parametrami.
     * @param name
     * @param interval
     * @throws IllegalAccessException
     */
    public WeatherStation(String name, int interval) throws IllegalAccessException {
        this.name = name;
        this.interval=interval;
    }

    /**
     * Pobiera z serwera wartosci parametrow pogody.
     */
    public void getDataFromServer(){

        StringBuffer response = new StringBuffer();

        try {
            URL obj = new URL(setURL("weather", "metric"));
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }

            in.close();

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            Map r = gson.fromJson(String.valueOf(response), Map.class);
            r1 = (Map) r.get("main");
            System.out.println(response);

            temperature = (Double) r1.get("temp");
            pressure = (Double) r1.get("pressure");
            humidity = (Double) r1.get("humidity");
            tempMin = (Double) r1.get("temp_min");
            tempMax = (Double) r1.get("temp_max");

        }catch (MalformedURLException e) {
            System.out.println("bad url");
        } catch (IOException e) {
            System.out.println("Connection failed");
        }
    }

    /**
     * Ustawia adres URL dla zadanych parametrow
     * @param requestType
     * @param units
     * @return adres URL
     */
    private String setURL(String requestType, String units){
        this.requestType = requestType;
        this.units = units;
        return "https://api.openweathermap.org/data/2.5/" + requestType + "?q=" + name + "&units="
                + units + "&APPID=af0e096ff2c0636f653266165732395b";
    }

    /**
     *
     * @return Wartosci parametrow pogody.
     */
    @Override
    public String toString() {
        return "Miasto: "+ name
                + "\nTemperatura: " + String.valueOf(temperature)
                + "\nCisnienie: " + String.valueOf(pressure)
                + "\nWilgotnosc: " + String.valueOf(humidity)
                + "\nTemperatura minimalna: " + String.valueOf(tempMin)
                + "\nTemperatura maksymalna: " + String.valueOf(tempMax);
    }

    /**
     * Metoda odpowiadajaca za czynnosci wykonywane podczas dzialania programu.
     */
    @Override
    public void run() {
        isRunning = true;
        while (isRunning){
            try {
                updateObservers();
                System.out.println();
                Thread.sleep(interval);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Failed to complete operation");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
