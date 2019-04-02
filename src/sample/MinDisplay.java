package sample;

import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * Klasa MinDisplay szuka minimalnych wartosci parametrow pogody i wyswietla je.
 * @author Julia Michalak
 */
public class MinDisplay implements Observer {

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
     * Tablica wartosci temperatur.
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
     * Tablica wartosci temperatur maksymalnych.
     */
    private ArrayList<Double> tMaxValues = new ArrayList<>();

    /**
     * Tablica wartosci temperatur minimalnych.
     */
    private ArrayList<Double> tMinValues = new ArrayList<>();

    /**
     * Tworzy obiekt MinDisplay z zadanymi parametrami.
     * @param minTemp
     * @param minPres
     * @param minHum
     * @param minTempMin
     * @param minTempMax
     */
    public MinDisplay(TextField minTemp, TextField minPres, TextField minHum, TextField minTempMin, TextField minTempMax) {
        this.minTemp = minTemp;
        this.minPres = minPres;
        this.minHum = minHum;
        this.minTempMin = minTempMin;
        this.minTempMax = minTempMax;
    }

    /**
     * Metoda szuka wartosci minimalnych parametrow pogody i przekazuje wartosci do wyswietlenia.
     * @param t
     * @param p
     * @param h
     * @param tMax
     * @param tMin
     */
    @Override
    public void update(double t, double p, double h, double tMax, double tMin) {

        Platform.runLater(()-> {
            tValues.add(t);
            pValues.add(p);
            hValues.add(h);
            tMaxValues.add(tMax);
            tMinValues.add(tMin);

            double mintempMin = tValues.get(0);
            double presMin = pValues.get(0);
            double humMin = hValues.get(0);
            double tempMinMin = tMinValues.get(0);
            double tempMaxMin = tMaxValues.get(0);

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
        });
    }
}
