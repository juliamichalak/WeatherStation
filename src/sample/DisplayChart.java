package sample;

import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Klasa DisplayChart wyswietlajca wykresy parametrow pogody.
 * @author Julia Michalak
 */
public class DisplayChart implements Observer {

    /**
     * Obiekt klasy Wykres, wykres temperatury.
     */
    private Wykres wykresTemp = new Wykres();

    /**
     * Obiekt klasy Wykres, wykres cisnienia.
     */
    private Wykres wykresPres = new Wykres();

    /**
     * Obiekt klasy Wykres, wykres wilgotnosci.
     */
    private Wykres wykresHum = new Wykres();

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
     * Ilosc pomiarow.
     */
    private int ilosc;

    /**
     * Tworzy obiekt DisplayChart z zadanymi parametrami.
     * @param charTemp
     * @param charPres
     * @param charHum
     * @param time1
     * @param time2
     * @param time3
     * @param values1
     * @param values2
     * @param values3
     * @param ilosc
     */
    public DisplayChart(LineChart charTemp, LineChart charPres, LineChart charHum, NumberAxis time1, NumberAxis time2, NumberAxis time3, NumberAxis values1, NumberAxis values2, NumberAxis values3, int ilosc) {
        this.charTemp = charTemp;
        this.charPres = charPres;
        this.charHum = charHum;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.values1 = values1;
        this.values2 = values2;
        this.values3 = values3;
        this.ilosc = ilosc;
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
            ilosc++;

            wykresTemp.addxValues(ilosc);
            wykresTemp.addyValues(t);

            wykresPres.addxValues(ilosc);
            wykresPres.addyValues(p);

            wykresHum.addxValues(ilosc);
            wykresHum.addyValues(h);

            series1.getData().clear();
            series2.getData().clear();
            series3.getData().clear();

            for (int i = 0; i < wykresTemp.getxValues().size(); i++) {
                series1.getData().add(new XYChart.Data(wykresTemp.getxValues().get(i),
                        wykresTemp.getyValues().get(i)));
                series2.getData().add(new XYChart.Data(wykresPres.getxValues().get(i),
                        wykresPres.getyValues().get(i)));
                series3.getData().add(new XYChart.Data(wykresHum.getxValues().get(i),
                        wykresHum.getyValues().get(i)));
            }

            charTemp.getData().add(series1);
            charPres.getData().add(series2);
            charHum.getData().add(series3);
            time1.setAutoRanging(true);
            time2.setAutoRanging(true);
            time3.setAutoRanging(true);
            values1.setAutoRanging(true);
            values2.setAutoRanging(true);
            values3.setAutoRanging(true);
        });
    }
}
