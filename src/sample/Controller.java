package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalTime;

/**
 * Sluzy do porozumiewania sie z uzytkownikiem i przekazania mu szukanych danych.
 * @author Julia Michalak
 */
public class Controller{

    @FXML
    private LineChart<LocalTime, Number> charTemp;
    @FXML
    private NumberAxis time1;
    @FXML
    private NumberAxis values1;
    @FXML
    private LineChart<Number, Number> charPres;
    @FXML
    private NumberAxis time2;
    @FXML
    private NumberAxis values2;
    @FXML
    private LineChart<Number, Number> charHum;
    @FXML
    private NumberAxis time3;
    @FXML
    private NumberAxis values3;
    @FXML
    private TextField txtMiasto;
    @FXML
    private TextField txtCzestotliwosc;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnStop;
    @FXML
    private Button btnStopZapis;
    @FXML
    private Button btnPrzerwij;
    @FXML
    private Button btnWczytaj;
    @FXML
    private TextField txtFileName;
    @FXML
    private TextArea txtTemp;
    @FXML
    private TextArea txtPres;
    @FXML
    private TextArea txtHum;
    @FXML
    private TextArea txtTempMin;
    @FXML
    private TextArea txtTempMax;
    @FXML
    private TextField txtIlosc;
    @FXML
    private TextField avgTemp;
    @FXML
    private TextField avgPres;
    @FXML
    private TextField avgHum;
    @FXML
    private TextField avgTempMin;
    @FXML
    private TextField avgTempMax;
    @FXML
    private TextField odchTemp;
    @FXML
    private TextField odchPres;
    @FXML
    private TextField odchHum;
    @FXML
    private TextField odchTempMin;
    @FXML
    private TextField odchTempMax;
    @FXML
    private TextField minTemp;
    @FXML
    private TextField minPres;
    @FXML
    private TextField minHum;
    @FXML
    private TextField minTempMin;
    @FXML
    private TextField minTempMax;
    @FXML
    private TextField maxTemp;
    @FXML
    private TextField maxPres;
    @FXML
    private TextField maxHum;
    @FXML
    private TextField maxTempMin;
    @FXML
    private TextField maxTempMax;

    private String name;
    private int ilosc = 0;
    public int ilosc2;
    WeatherBase weatherBase = new WeatherBase();
    DisplayInTextFields textFields;
    DisplayChart Charts;
    WeatherStation station;
    AVGWeatherDisplay AVGWeather;
    OdchylenieDisplay odchylenie;
    ReadingFile readingFile;
    MinDisplay minDisplay;
    MaxDisplay maxDisplay;

    int i = 0;

    int stop = 1;

    @FXML
    void btnStartPressed(ActionEvent event) throws IllegalAccessException {

        if (stop == 0){
            weatherBase.tValues.clear();
            weatherBase.pValues.clear();
            weatherBase.hValues.clear();
            weatherBase.tMinValues.clear();
            weatherBase.tMaxValues.clear();

            txtTemp.clear();
            txtPres.clear();
            txtHum.clear();
            txtTempMin.clear();
            txtTempMax.clear();

            charTemp.getData().clear();
            charPres.getData().clear();
            charHum.getData().clear();
        }
        if (i == 0)
            ilosc = 0;

        else if (i ==1)
            ilosc = ilosc2;

        int czestotliwosc = 0;

        if (txtCzestotliwosc.getText().isEmpty())
            txtCzestotliwosc.appendText("10");

        czestotliwosc = 1000 * Integer.parseInt(txtCzestotliwosc.getText());

        if (txtMiasto.getText().isEmpty())
            txtMiasto.appendText("Wroclaw");

        name = String.valueOf(txtMiasto.getText());
        station= new WeatherStation(name,czestotliwosc);
        station.addObserver(weatherBase);
        textFields= new DisplayInTextFields(txtTemp, txtPres, txtHum, txtTempMin, txtTempMax, txtIlosc, ilosc);
        station.addObserver(textFields);
        Charts = new DisplayChart(charTemp, charPres, charHum, time1, time2, time3, values1, values2, values3, ilosc);
        station.addObserver(Charts);
        AVGWeather = new AVGWeatherDisplay(avgTemp, avgPres, avgHum, avgTempMin, avgTempMax, ilosc);
        station.addObserver(AVGWeather);
        odchylenie = new OdchylenieDisplay(odchTemp, odchPres, odchHum, odchTempMin, odchTempMax, ilosc);
        station.addObserver(odchylenie);
        minDisplay = new MinDisplay(minTemp, minPres, minHum, minTempMin, minTempMax);
        station.addObserver(minDisplay);
        maxDisplay = new MaxDisplay(maxTemp, maxPres, maxHum, maxTempMin, maxTempMax);
        station.addObserver(maxDisplay);
        station.start();
    }

    @FXML
    void btnStopPressed(ActionEvent event) throws IllegalAccessException{
        station.stop();
        i = 0;
        stop = 0;
    }

    @FXML
    void btnStopZapisPressed(ActionEvent event) {
        if (txtFileName.getText().isEmpty())
            txtFileName.appendText("WeatherConditions" + txtMiasto.getText());

        name = String.valueOf(txtFileName.getText());

        weatherBase.zapis(name + ".txt");
        weatherBase.zapis2(name + ".json");
        station.stop();
        i = 0;
        stop = 0;

    }

    @FXML
    void btnPrzerwijPressed(ActionEvent event){
        station.interrupt();
        ilosc2 = Integer.parseInt(txtIlosc.getText());
        i = 1;
        stop = 1;
    }

    @FXML
    void btnWczytajPressed(ActionEvent event) {

        if (txtFileName.getText().isEmpty()){
            txtFileName.appendText("przyklad");
            txtMiasto.appendText("New York");
            txtCzestotliwosc.appendText("120");
        }

        weatherBase.tValues.clear();
        weatherBase.pValues.clear();
        weatherBase.hValues.clear();
        weatherBase.tMinValues.clear();
        weatherBase.tMaxValues.clear();

        txtTemp.clear();
        txtPres.clear();
        txtHum.clear();
        txtTempMin.clear();
        txtTempMax.clear();

        charTemp.getData().clear();
        charPres.getData().clear();
        charHum.getData().clear();

        name = String.valueOf(txtFileName.getText());
        name+= ".json";

        readingFile = new ReadingFile(name, txtTemp, txtPres, txtHum, txtTempMin, txtTempMax, txtIlosc, avgTemp, avgPres, avgHum, avgTempMin, avgTempMax, charTemp, charPres, charHum, time1, time2, time3, values1, values2, values3, odchTemp, odchPres, odchHum, odchTempMin, odchTempMax, minTemp, minPres, minHum, minTempMin, minTempMax, maxTemp, maxPres, maxHum, maxTempMin, maxTempMax);
        readingFile.odczyt();
        readingFile.wyswietlenie();

        i = 0;
        stop = 0;
    }
}
