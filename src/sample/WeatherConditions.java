package sample;

/**
 * Klasa WeatherConditions sluzy do przekazywania parametrow pogody.
 * @author Julia Michalak
 */
public class WeatherConditions {

    /**
     * Wartosc temperatury.
     */
    private double Temperature;

    /**
     * Wartosc cisnienia.
     */
    private double Pressure;

    /**
     * Wartosc wilgotnosci.
     */
    private double Humidity;

    /**
     * Wartosc temperatury minimalnej.
     */
    private double TempMin;

    /**
     * Wartosc temperatury maksymalnej.
     */
    private double TempMax;

    /**
     * Tworzy obiekt WeatherConditions z zadanymi parametrami.
     * @param temperature
     * @param pressure
     * @param humidity
     * @param tempMin
     * @param tempMax
     */
    public WeatherConditions(double temperature, double pressure, double humidity, double tempMin, double tempMax) {
        Temperature = temperature;
        Pressure = pressure;
        Humidity = humidity;
        TempMin = tempMin;
        TempMax = tempMax;
    }

    /**
     * Zwraca wartosci parametrow pogodowych
     * @return Wartosci parametrow pogodowych.
     */
    @Override
    public String toString() {
        return String.valueOf(Temperature) + String.valueOf(Pressure) + String.valueOf(Humidity) + String.valueOf(TempMin)
                + String.valueOf(TempMax);
    }
}
