package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

/**
 * Klasa WeatherBase przechowywuje w listach wartosci parametrow pogody pobrane z serwera.
 * Umozliwia zapis pobranych wartosci do plikow.
 * @author Julia Michalak
 */
public class WeatherBase implements Observer {

    /**
     * Tablica wartosci temperatury.
     */
    ArrayList<Double> tValues = new ArrayList();

    /**
     * Tablica wartosci wilgotnosci.
     */
    ArrayList<Double> hValues = new ArrayList();

    /**
     * Tablica wartosci cisnienia.
     */
    ArrayList<Double> pValues = new ArrayList();

    /**
     * Tablica wartosci temperatury maksymalnej.
     */
    ArrayList<Double> tMaxValues = new ArrayList();

    /**
     * Tablica wartosci temperatury minimalnej.
     */
    ArrayList<Double> tMinValues = new ArrayList();

    /**
     * Metoda zapisujaca zebrane wartosci parametrow do pliku tekstowego o nazwie zadanej parametrem.
     * @param fileName
     */
    public void zapis(String fileName) {

        File file = new File(fileName);
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(file);
            for (int i = 0; i < tValues.size(); i++) {
                printWriter.print(tValues.get(i) + "; ");
                printWriter.print(hValues.get(i) + "; ");
                printWriter.print(pValues.get(i) + "; ");
                printWriter.print(tMaxValues.get(i) + "; ");
                printWriter.print(tMinValues.get(i) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.close();
    }

    /**
     * Metoda zapisujaca zebrane wartosci parametrow do pliku w formacie json o nazwie zadanej parametrem.
     * @param fileName
     */
    public void zapis2(String fileName){

        WeatherConditions[] list = new WeatherConditions[tValues.size()];
        for(int i = 0; i < tValues.size(); i++) {
            double temp = tValues.get(i);
            double pres = pValues.get(i);
            double hum = hValues.get(i);
            double tMax = tMaxValues.get(i);
            double tMin = tMinValues.get(i);
            WeatherConditions a = new WeatherConditions(temp, pres, hum, tMin, tMax);
            list[i] = a;
        }

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        File file1 = new File(fileName);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file1);
                gson.toJson(list, fileWriter);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda uaktualniajaca listy wartosci parametrow pogodowych o zadane wartosci.
     * @param t
     * @param p
     * @param h
     * @param tMax
     * @param tMin
     */
    @Override
    public void update( double t, double p, double h, double tMax, double tMin) {
        tValues.add(t);
        pValues.add(p);
        hValues.add(h);
        tMaxValues.add(tMax);
        tMinValues.add(tMin);
    }
}
