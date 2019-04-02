package sample;

import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * Klasa MaxDisplay szuka maksymalnych wartosci parametrow pogody i wyswietla je.
 * @author Julia Michalak
 */
public class MaxDisplay implements Observer {

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
     * Tablica wartosci temperatury maksymalnej.
     */
    private ArrayList<Double> tMaxValues = new ArrayList<>();

    /**
     * Tablica wartosci temperatury minimalnej.
     */
    private ArrayList<Double> tMinValues = new ArrayList<>();

    /**
     * Tworzy obiekt MaxDisplay z zadanymi parametrami.
     * @param maxTemp
     * @param maxPres
     * @param maxHum
     * @param maxTempMin
     * @param maxTempMax
     */
    public MaxDisplay(TextField maxTemp, TextField maxPres, TextField maxHum, TextField maxTempMin, TextField maxTempMax) {
        this.maxTemp = maxTemp;
        this.maxPres = maxPres;
        this.maxHum = maxHum;
        this.maxTempMin = maxTempMin;
        this.maxTempMax = maxTempMax;
    }

    /**
     * Metoda szuka wartosci maksymalnych parametrow pogody i przekazuje wartosci do wyswietlenia.
     * @param t
     * @param p
     * @param h
     * @param tMax
     * @param tMin
     */
    @Override
    public void update(double t, double p, double h, double tMax, double tMin) {
        Platform.runLater(()->{
            tValues.add(t);
            pValues.add(p);
            hValues.add(h);
            tMaxValues.add(tMax);
            tMinValues.add(tMin);

            double maxtempMax = tValues.get(0);
            double presMax = pValues.get(0);
            double humMax = hValues.get(0);
            double tempMinMax = tMinValues.get(0);
            double tempMaxMax = tMaxValues.get(0);

            for (int i = 1; i < tValues.size(); i++){
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
        });
    }
}
