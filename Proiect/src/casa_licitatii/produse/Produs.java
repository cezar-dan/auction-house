package casa_licitatii.produse;

/**
 * Produs vandut in casa de licitatii.
 * <p>
 * Un produs este definit de un id, un nume, un an de fabricare si un pret
 * minim de vanzare. Produsul poate retine pretul cu care a fost vandut.
 */
public abstract class Produs {
  private int id;
  private String nume;
  private double pretVanzare = 0;
  private double pretMinim;
  private int an;

  /**
   * Metoda ce intoarce id-ul produsului.
   * @return - id-ul produsului.
   */
  public int getId() {
    return id;
  }

  /**
   * Metoda ce seteaza id-ul produsului.
   * @param id - id-ul produsului.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Metoda ce seteaza numele produsului.
   * @param nume - numele produsului.
   */
  public void setNume(String nume) {
    this.nume = nume;
  }

  /**
   * Metoda ce intoarce pretul minim al produsului.
   * @return - pretul minim al produsului.
   */
  public double getPretMinim() {
    return pretMinim;
  }

  /**
   * Metoda ce seteaza pretul minim al produsului.
   * @param pretMinim - pretul minim al produsului.
   */
  public void setPretMinim(double pretMinim) {
    this.pretMinim = pretMinim;
  }

  /**
   * Metoda ce seteaza anul de fabricare al produsului.
   * @param an - anul de fabricare al produsului.
   */
  public void setAn(int an) {
    this.an = an;
  }

  /**
   * Intoarce informatii referitoare la produs.
   * @return - informatii referitoare la produs.
   */
  @Override
  public String toString() {
    return  ", id=" + id +
            ", nume='" + nume + '\'' +
            ", pretMinim=" + String.format("%.2f", pretMinim) +
            ", an=" + an;
  }
}
