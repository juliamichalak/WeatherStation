package sample;

/**
 * Interface Observable dokonuje operacji na obserwatorach.
 * @author Julia Michalak
 */
public interface Observable {

    /**
     * Dodaje obserwatora do listy.
     * @param observer
     */
    void addObserver(Observer observer);

    /**
     * Usuwa obserwatora z listy.
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * Uaktualnia informacje u obserwatorow.
     * @throws IllegalAccessException
     */
    void updateObservers() throws IllegalAccessException;
}
