package casa_licitatii.angajati.comisioane;

/**
 * Clasa ce implementeaza o functie de comision.
 */
public class ComisionTip4 implements FunctieComision {

  /**
   * Implementeaza concret functia de comision.
   * @param pret - pretul primit.
   * @return - comisionul pastrat.
   */
  @Override
  public double calculeaza(double pret) {
    return pret * 0.1;
  }

  /**
   * Intoarce tipul de comision.
   * @return - tipul de comision.
   */
  @Override
  public String toString() {
    return "C4";
  }
}
