package sample;

import java.util.ArrayList;

/**
 * Klasa Wykres tworzaca listy wartosci x i y do pozniejszego wyswietlenia na wykresie.
 * @author Julia Michalak
 */
public class Wykres {

    /**
     * Tablica wartosci x.
     */
    private ArrayList<Integer> xValues = new ArrayList<>();

    /**
     * Tablica wartosci y.
     */
    private ArrayList<Double> yValues = new ArrayList<Double>();

    /**
     * Zwraca liste wartosci x.
     * @return lista wartosci x
     */
    public ArrayList<Integer> getxValues() {
        return xValues;
    }

    /**
     * Zwraca liste wartosci y.
     * @return lista wartosci y
     */
    public ArrayList<Double> getyValues() {
        return yValues;
    }

    /**
     * Dodaje wartosc parametru do listy wartosci x.
     * @param x
     */
    public void addxValues(int x){
        xValues.add(x);
    }

    /**
     * Dodaje wartosc parametru do listy wartosci y.
     * @param y
     */
    public void addyValues(double y){
        yValues.add(y);
    }
}
