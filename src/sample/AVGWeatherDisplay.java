package sample;

import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * Klasa AVGWeatherDisplay obliczajaca i wyswietlajsca srednie wartosci parametrow pogody.
 * @author Julia Michalak
 */
public class AVGWeatherDisplay implements Observer {

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
     * Pole tekstowe sredniej wartosci temperatury.
     */
    private TextField AVGTemp;

    /**
     * Pole tekstowe sredniej wartosci cisnienia.
     */
    private TextField AVGPres;

    /**
     * Pole tekstowe sredniej wartosci wilgotnosci.
     */
    private TextField AVGHum;

    /**
     * Pole tekstowe sredniej wartosci temperatury minimalnej.
     */
    private TextField AVGTempMin;

    /**
     * Pole tekstowe sredniej wartosci temperatury maksymalnej.
     */
    private TextField AVGTempMax;

    /**
     * Ilość pomiarow.
     */
    private int ilosc = 0;

    /**
     * Tworzy obiekt AVDWeatherDisplay z zadanymi parametrami.
     * @param AVGTemp
     * @param AVGPres
     * @param AVGHum
     * @param AVGTempMin
     * @param AVGTempMax
     * @param ilosc
     */
    public AVGWeatherDisplay(TextField AVGTemp, TextField AVGPres, TextField AVGHum, TextField AVGTempMin, TextField AVGTempMax, int ilosc) {
        this.AVGTemp = AVGTemp;
        this.AVGPres = AVGPres;
        this.AVGHum = AVGHum;
        this.AVGTempMin = AVGTempMin;
        this.AVGTempMax = AVGTempMax;
        this.ilosc = ilosc;
    }

    /**
     * Metoda oblicza srednie i przekazuje wartosci do wyswietlenia.
     * @param t
     * @param p
     * @param h
     * @param tMax
     * @param tMin
     */
    @Override
    public void update(double t, double p, double h, double tMax, double tMin) {

        Platform.runLater(()->{
            ilosc++;
            tValues.add(t);
            pValues.add(p);
            hValues.add(h);
            tMaxValues.add(tMax);
            tMinValues.add(tMin);

            double sumaTemp = 0;
            double sumaHum = 0;
            double sumaPres = 0;
            double sumaTempMin = 0;
            double sumaTempMax = 0;

            for (int i = 0; i < tValues.size(); i++) {
                sumaTemp += tValues.get(i);
                sumaPres += pValues.get(i);
                sumaHum += hValues.get(i);
                sumaTempMax += tMaxValues.get(i);
                sumaTempMin += tMinValues.get(i);
            }

            double sredniaTemp = sumaTemp/tValues.size();
            double sreniaPres = sumaPres/tValues.size();
            double sredniaHum = sumaHum/tValues.size();
            double sredniaTempMin = sumaTempMin/tValues.size();
            double sredniaTempMax = sumaTempMax/tValues.size();

            AVGTemp.clear();
            AVGTemp.appendText(String.valueOf(sredniaTemp));
            AVGPres.clear();
            AVGPres.appendText(String.valueOf(sreniaPres));
            AVGHum.clear();
            AVGHum.appendText(String.valueOf(sredniaHum));
            AVGTempMax.clear();
            AVGTempMax.appendText(String.valueOf(sredniaTempMax));
            AVGTempMin.clear();
            AVGTempMin.appendText(String.valueOf(sredniaTempMin));
        });
    }
}
