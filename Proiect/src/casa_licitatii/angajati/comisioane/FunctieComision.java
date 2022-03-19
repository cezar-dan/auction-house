package casa_licitatii.angajati.comisioane;

/**
 * Interfata ce modeleaza o functie de comision.
 * <p>
 * Este un exemplu de design pattern Strategy, metoda "calculeaza" putand
 * fi implementata in diferite moduri de diferiti algoritmi.
 */
public interface FunctieComision {
  double calculeaza(double pret);
}
