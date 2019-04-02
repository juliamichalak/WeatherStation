package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Klasa ReadingFile pobiera dane parametrow pogody z pliku, oblicza srednie, odchylenie, znajduje wartosci minimalne i maksymalne.
 * Wyswietla wszystkie dane i wykresy.
 * @author Julia Michalak
 */
public class ReadingFile {

    /**
     * Tablica ilosci pomiarow.
     */
    private ArrayList<Integer> xValues = new ArrayList<>();

    /**
     * Tablica wartosci temperatury.
     */
    private ArrayList<Double> tValues = new ArrayList<>();

    /**
     * Tablica wartosci cisnienia.
     */
    private ArrayList<Double> pValues = new ArrayList<>();

    /**
     * Tablica wartosci wilgotnosci.
     */
    private ArrayList<Double> hValues = new ArrayList<>();

    /**
     * Tablica wartosci temperatury minimalnej.
     */
    private ArrayList<Double> tMinValues = new ArrayList<>();

    /**
     * Tablica wartosci temperatury maksymalnej.
     */
    private ArrayList<Double> tMaxValues = new ArrayList<>();

    /**
     * Nazwa pliku.
     */
    private String fileName;

    /**
     * Pole tekstowe wartosci temperatury.
     */
    private TextArea txtTemp;

    /**
     * Pole tekstowe wartosci cisnienia.
     */
    private TextArea txtPres;

    /**
     * Pole tekstowe wartosci wilgotnosci.
     */
    private TextArea txtHum;

    /**
     * Pole tekstowe wartosci temperatury minimalnej.
     */
    private TextArea txtTempMin;

    /**
     * Pole tekstowe wartosci temperatury maksymalnej.
     */
    private TextArea txtTempMax;

    /**
     * Pole tekstowe ilosci pomiarow.
     */
    private TextField txtIlosc;

    /**
     * Pole tekstowe sredniej temperatury.
     */
    private TextField AVGTemp;

    /**
     * Pole tekstowe sredniej cisnienia.
     */
    private TextField AVGPres;

    /**
     * Pole tekstowe sredniej wilgotnosci.
     */
    private TextField AVGHum;

    /**
     * Pole tekstowe sredniej temperatury minimalnej.
     */
    private TextField AVGTempMin;

    /**
     * Pole tekstowe sredniej temperatury maksymalnej.
     */
    private TextField AVGTempMax;

    /**
     * Pole tekstowe odchylenia temperatury.
     */
    private TextField ODCHTemp;

    /**
     * Pole tekstowe odchylenia cisnienia.
     */
    private TextField ODCHPres;

    /**
     * Pole tekstowe odchylenia wilgotnosci.
     */
    private TextField ODCHHum;

    /**
     * Pole tekstowe odchylenia temperatury minimalnej.
     */
    private TextField ODCHTempMin;

    /**
     * Pole tekstowe odchylenia temperatury maksymalnej.
     */
    private TextField ODCHTempMax;

    /**
     * Pole tekstowe minimalnej wartosci temperatury.
     */
    private TextField minTemp;

    /**
     * Pole tekstowe minimalnej wartosci cisnienia.
     */
    private TextField minPres;

    /**
     * Pole tekstowe minimalnej wartosci wilgotnosci.
     */
    private TextField minHum;

    /**
     * Pole tekstowe minimalnej wartosci temperatury minimalnej.
     */
    private TextField minTempMin;

    /**
     * Pole tekstowe minimalnej wartosci temperatury maksymalnej.
     */
    private TextField minTempMax;

    /**
     * Pole tekstowe maksymalnej wartosci temperatury.
     */
    private TextField maxTemp;

    /**
     * Pole tekstowe maksymalnej wartosci cisnienia.
     */
    private TextField maxPres;

    /**
     * Pole tekstowe maksymalnej wartosci wilgotnosci.
     */
    private TextField maxHum;

    /**
     * Pole tekstowe maksymalnej wartosci temperatury minimalnej.
     */
    private TextField maxTempMin;

    /**
     * Pole tekstowe maksymalnej wartosci temperatury maksymalnej.
     */
    private TextField maxTempMax;

    /**
     * Seria dla wykresu temperatury.
     */
    private XYChart.Series series1 = new XYChart.Series();

    /**
     * Seria dla wykresu cisnienia.
     */
    private XYChart.Series series2 = new XYChart.Series();

    /**
     * Seria dla wykresu wilgotnosci.
     */
    private XYChart.Series series3 = new XYChart.Series();

    /**
     * Wykres temperatury.
     */
    private LineChart charTemp;

    /**
     * Wykres cisnienia.
     */
    private LineChart charPres;

    /**
     * Wykres wilgotnosci.
     */
    private LineChart charHum;

    /**
     * Os x (ilosc pomiarow) dla wykresu temperatury.
     */
    private NumberAxis time1;

    /**
     * Os x (ilosc pomiarow) dla wykresu cisnienia.
     */
    private NumberAxis time2;

    /**
     * Os x (ilosc pomiarow) dla wykresu wilgotnosci.
     */
    private NumberAxis time3;

    /**
     * Os y (wartosci temperatury) dla wykresu temperatury.
     */
    private NumberAxis values1;

    /**
     * Os y (wartosci cisnienia) dla wykresu cisnienia.
     */
    private NumberAxis values2;

    /**
     * Os y (wartosci wilgotnosci) dla wykresu wilgotnosci.
     */
    private NumberAxis values3;

    /**
     * Tworzy obiekt ReadingFile z zadanymi parametrami.
     * @param fileName
     * @param txtTemp
     * @param txtPres
     * @param txtHum
     * @param txtTempMin
     * @param txtTempMax
     * @param txtIlosc
     * @param AVGTemp
     * @param AVGPres
     * @param AVGHum
     * @param AVGTempMin
     * @param AVGTempMax
     * @param charTemp
     * @param charPres
     * @param charHum
     * @param time1
     * @param time2
     * @param time3
     * @param values1
     * @param values2
     * @param values3
     * @param ODCHTemp
     * @param ODCHPres
     * @param ODCHHum
     * @param ODCHTempMin
     * @param ODCHTempMax
     * @param minTemp
     * @param minPres
     * @param minHum
     * @param minTempMin
     * @param minTempMax
     * @param maxTemp
     * @param maxPres
     * @param maxHum
     * @param maxTempMin
     * @param maxTempMax
     */
    public ReadingFile(String fileName, TextArea txtTemp, TextArea txtPres, TextArea txtHum, TextArea txtTempMin, TextArea txtTempMax, TextField txtIlosc, TextField AVGTemp, TextField AVGPres, TextField AVGHum, TextField AVGTempMin, TextField AVGTempMax, LineChart charTemp, LineChart charPres, LineChart charHum, NumberAxis time1, NumberAxis time2, NumberAxis time3, NumberAxis values1, NumberAxis values2, NumberAxis values3, TextField ODCHTemp, TextField ODCHPres, TextField ODCHHum, TextField ODCHTempMin, TextField ODCHTempMax, TextField minTemp, TextField minPres, TextField minHum, TextField minTempMin, TextField minTempMax, TextField maxTemp, TextField maxPres, TextField maxHum, TextField maxTempMin, TextField maxTempMax) {
        this.txtTemp = txtTemp;
        this.txtPres = txtPres;
        this.txtHum = txtHum;
        this.txtTempMin = txtTempMin;
        this.txtTempMax = txtTempMax;
        this.txtIlosc = txtIlosc;
        this.AVGTemp = AVGTemp;
        this.AVGPres = AVGPres;
        this.AVGHum = AVGHum;
        this.AVGTempMin = AVGTempMin;
        this.AVGTempMax = AVGTempMax;
        this.charTemp = charTemp;
        this.charPres = charPres;
        this.charHum = charHum;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.values1 = values1;
        this.values2 = values2;
        this.values3 = values3;
        this.fileName = fileName;
        this.ODCHTemp = ODCHTemp;
        this.ODCHPres = ODCHPres;
        this.ODCHHum = ODCHHum;
        this.ODCHTempMin = ODCHTempMin;
        this.ODCHTempMax = ODCHTempMax;
        this.minTemp = minTemp;
        this.minPres = minPres;
        this.minHum = minHum;
        this.minTempMin = minTempMin;
        this.minTempMax = minTempMax;
        this.maxTemp = maxTemp;
        this.maxPres = maxPres;
        this.maxHum = maxHum;
        this.maxTempMin = maxTempMin;
        this.maxTempMax = maxTempMax;
    }

    /**
     * Metoda odczytujaca wartosci parametrow pogodowych z pliku.
     */
    public void odczyt(){

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        WeatherConditions[] testWeatherConditions1 = null;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            testWeatherConditions1 = gson.fromJson(bufferedReader, WeatherConditions[].class);
        } catch(
                FileNotFoundException e){
            e.printStackTrace();
        } catch(
                IOException e){
            e.printStackTrace();
        }

        System.out.println("From Json: " + Arrays.toString(testWeatherConditions1) + '\n');
        for (int i = 0; i < testWeatherConditions1.length; i++){

            WeatherConditions weatherConditions1 = testWeatherConditions1[i];
            String test = gson.toJson(weatherConditions1);
            Map r = gson.fromJson(test, Map.class);

            double temperature = (Double) r.get("Temperature");
            double pressure = (Double) r.get("Pressure");
            double humidity = (Double) r.get("Humidity");
            double tempMin = (Double) r.get("TempMin");
            double tempMax = (Double) r.get("TempMax");

            xValues.add(i + 1);
            tValues.add(temperature);
            pValues.add(pressure);
            hValues.add(humidity);
            tMinValues.add(tempMin);
            tMaxValues.add(tempMax);
        }
    }

    /**
     * Metoda obliczajaca statystyczne wartosci parametrow.
     * Wyswietla dane, wykresy i wartosci statystyczne parametrow pogodowych.
     */
    public void wyswietlenie(){

        double sumaTemp = 0;
        double sumaHum = 0;
        double sumaPres = 0;
        double sumaTempMin = 0;
        double sumaTempMax = 0;

        double mintempMin = tValues.get(0);
        double presMin = pValues.get(0);
        double humMin = hValues.get(0);
        double tempMinMin = tMinValues.get(0);
        double tempMaxMin = tMaxValues.get(0);

        double maxtempMax = tValues.get(0);
        double presMax = pValues.get(0);
        double humMax = hValues.get(0);
        double tempMinMax = tMinValues.get(0);
        double tempMaxMax = tMaxValues.get(0);

        series1.getData().clear();
        series2.getData().clear();
        series3.getData().clear();

        for (int i = 0; i < tValues.size(); i++) {
            txtTemp.appendText(String.valueOf(tValues.get(i) + "\n"));
            txtPres.appendText(String.valueOf(pValues.get(i) + "\n"));
            txtHum.appendText(String.valueOf(hValues.get(i) + "\n"));
            txtTempMin.appendText(String.valueOf(tMinValues.get(i) + "\n"));
            txtTempMax.appendText(String.valueOf(tMaxValues.get(i) + "\n"));
            txtIlosc.clear();
            txtIlosc.appendText(String.valueOf(xValues.get(i)));

            sumaTemp += tValues.get(i);
            sumaPres += pValues.get(i);
            sumaHum += hValues.get(i);
            sumaTempMax += tMaxValues.get(i);
            sumaTempMin += tMinValues.get(i);

            series1.getData().add(new XYChart.Data(xValues.get(i),
                    tValues.get(i)));
            series2.getData().add(new XYChart.Data(xValues.get(i),
                    pValues.get(i)));
            series3.getData().add(new XYChart.Data(xValues.get(i),
                    hValues.get(i)));
        }

        for (int i = 1; i < tValues.size(); i++){
            if(mintempMin > tValues.get(i))
                mintempMin = tValues.get(i);
            if(presMin > pValues.get(i))
                presMin = pValues.get(i);
            if(humMin > hValues.get(i))
                humMin = hValues.get(i);
            if(tempMinMin > tMinValues.get(i))
                tempMinMin = tMinValues.get(i);
            if(tempMaxMin > tMaxValues.get(i))
                tempMaxMin = tMaxValues.get(i);
            if(maxtempMax < tValues.get(i))
                maxtempMax = tValues.get(i);
            if(presMax < pValues.get(i))
                presMax = pValues.get(i);
            if(humMax < hValues.get(i))
                humMax = hValues.get(i);
            if(tempMinMax < tMinValues.get(i))
                tempMinMax = tMinValues.get(i);
            if(tempMaxMax < tMaxValues.get(i))
                tempMaxMax = tMaxValues.get(i);
        }

        minTemp.clear();
        minTemp.appendText(String.valueOf(mintempMin));
        minPres.clear();
        minPres.appendText(String.valueOf(presMin));
        minHum.clear();
        minHum.appendText(String.valueOf(humMin));
        minTempMin.clear();
        minTempMin.appendText(String.valueOf(tempMinMin));
        minTempMax.clear();
        minTempMax.appendText(String.valueOf(tempMaxMin));
        maxTemp.clear();
        maxTemp.appendText(String.valueOf(maxtempMax));
        maxPres.clear();
        maxPres.appendText(String.valueOf(presMax));
        maxHum.clear();
        maxHum.appendText(String.valueOf(humMax));
        maxTempMin.clear();
        maxTempMin.appendText(String.valueOf(tempMinMax));
        maxTempMax.clear();
        maxTempMax.appendText(String.valueOf(tempMaxMax));

        double sredniaTemp = sumaTemp/tValues.size();
        double sredniaPres = sumaPres/tValues.size();
        double sredniaHum = sumaHum/tValues.size();
        double sredniaTempMin = sumaTempMin/tValues.size();
        double sredniaTempMax = sumaTempMax/tValues.size();

        double sumaTemp2 = 0;
        double sumaHum2 = 0;
        double sumaPres2 = 0;
        double sumaTempMin2 = 0;
        double sumaTempMax2 = 0;

        for (int i = 0; i < tValues.size(); i++){
            sumaTemp2 += Math.pow(tValues.get(i) - sredniaTemp, 2);
            sumaPres2 += Math.pow(pValues.get(i) - sredniaPres, 2);
            sumaHum2 += Math.pow(hValues.get(i) - sredniaHum, 2);
            sumaTempMin2 += Math.pow(tMinValues.get(i) - sredniaTempMin, 2);
            sumaTempMax2 += Math.pow(tMaxValues.get(i) - sredniaTempMax, 2);
        }

        double odchylenieTemp = Math.sqrt(sumaTemp2/(tValues.size() - 1));
        double odchyleniePres = Math.sqrt(sumaPres2/(pValues.size() - 1));
        double odchylenieHum = Math.sqrt(sumaHum2/(hValues.size() - 1));
        double odchylenieTempMin = Math.sqrt(sumaTempMin2/(tMinValues.size() - 1));
        double odchylenieTempMax = Math.sqrt(sumaTempMax2/(tMaxValues.size() - 1));

        ODCHTemp.clear();
        ODCHPres.clear();
        ODCHHum.clear();
        ODCHTempMin.clear();
        ODCHTempMax.clear();

        ODCHTemp.appendText(String.valueOf(odchylenieTemp));
        ODCHPres.appendText(String.valueOf(odchyleniePres));
        ODCHHum.appendText(String.valueOf(odchylenieHum));
        ODCHTempMax.appendText(String.valueOf(odchylenieTempMax));
        ODCHTempMin.appendText(String.valueOf(odchylenieTempMin));

        AVGTemp.clear();
        AVGTemp.appendText(String.valueOf(sredniaTemp));
        AVGPres.clear();
        AVGPres.appendText(String.valueOf(sredniaPres));
        AVGHum.clear();
        AVGHum.appendText(String.valueOf(sredniaHum));
        AVGTempMax.clear();
        AVGTempMax.appendText(String.valueOf(sredniaTempMax));
        AVGTempMin.clear();
        AVGTempMin.appendText(String.valueOf(sredniaTempMin));

        charTemp.getData().add(series1);
        charPres.getData().add(series2);
        charHum.getData().add(series3);
        time1.setAutoRanging(true);
        time2.setAutoRanging(true);
        time3.setAutoRanging(true);
        values1.setAutoRanging(true);
        values2.setAutoRanging(true);
        values3.setAutoRanging(true);
    }
}
