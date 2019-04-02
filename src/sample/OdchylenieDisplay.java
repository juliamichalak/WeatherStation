package sample;

import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * Klasa OdchylenieDisplay oblicza odchylenie parametrow pogody i wyswietla je.
 * @author Julia Michalak
 */
public class OdchylenieDisplay implements Observer {

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
     * Ilosc pomiarow.
     */
    private int ilosc;

    /**
     * Tworzy obiekt OdchylenieDisplay z zadanymi parametrami.
     * @param ODCHTemp
     * @param ODCHPres
     * @param ODCHHum
     * @param ODCHTempMin
     * @param ODCHTempMax
     * @param ilosc
     */
    public OdchylenieDisplay(TextField ODCHTemp, TextField ODCHPres, TextField ODCHHum, TextField ODCHTempMin, TextField ODCHTempMax, int ilosc) {
        this.ODCHTemp = ODCHTemp;
        this.ODCHPres = ODCHPres;
        this.ODCHHum = ODCHHum;
        this.ODCHTempMin = ODCHTempMin;
        this.ODCHTempMax = ODCHTempMax;
        this.ilosc = ilosc;
    }

    /**
     * Metoda oblicza odchelenia parametrow pogody i przekazuje je do wyswietlenia.
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
        });
    }
}
