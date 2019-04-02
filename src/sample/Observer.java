package sample;

/**
 * Interface Observer sluzaczy do uaktualniania danych.
 * @author Julia Michalak
 */
public interface Observer {

    /**
     * Metoda uaktualniajaca o zadanych parametrach.
     * @param t
     * @param p
     * @param h
     * @param tMax
     * @param tMin
     */
    void update(double t, double p, double h, double tMax, double tMin);

}
