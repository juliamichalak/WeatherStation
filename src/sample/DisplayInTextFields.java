package sample;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Klasa DisplayInTextFields wyswietlajaca wartosci parametrow pogody.
 * @author Julia Michalak
 */
public class DisplayInTextFields implements Observer {

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
     * Ilosc pomiarow.
     */
    private int ilosc;

    /**
     * Tworzy obiekt DisplayInTextFields z zadanymi parametrami.
     * @param txtTemp
     * @param txtPres
     * @param txtHum
     * @param txtTempMin
     * @param txtTempMax
     * @param txtIlosc
     * @param ilosc
     */
    public DisplayInTextFields(TextArea txtTemp, TextArea txtPres, TextArea txtHum, TextArea txtTempMin, TextArea txtTempMax, TextField txtIlosc, int ilosc) {
        this.txtTemp = txtTemp;
        this.txtPres = txtPres;
        this.txtHum = txtHum;
        this.txtTempMin = txtTempMin;
        this.txtTempMax = txtTempMax;
        this.txtIlosc = txtIlosc;
        this.ilosc = ilosc;
    }

    /**
     * Metoda przekazuje wartosci parametrow pogody do wyswietlenia.
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
            txtTemp.appendText(String.valueOf(t + "\n"));
            txtPres.appendText(String.valueOf(p + "\n"));
            txtHum.appendText(String.valueOf(h + "\n"));
            txtTempMin.appendText(String.valueOf(tMin + "\n"));
            txtTempMax.appendText(String.valueOf(tMax + "\n"));
            txtIlosc.clear();
            txtIlosc.appendText(String.valueOf(ilosc));
        });
    }
}
