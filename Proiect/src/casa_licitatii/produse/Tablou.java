package casa_licitatii.produse;

/**
 * Un tip de produs vandut in casa de licitatii.
 * <p>
 * Un tablou este definit prin numele pictorului si tipul de culori folosit.
 */
public class Tablou extends Produs {
  private String numePictor;
  private TipCulori culori;

  /**
   * Seteaza numele pictorului.
   * @param numePictor - numele pictorului.
   */
  public void setNumePictor(String numePictor) {
    this.numePictor = numePictor;
  }

  /**
   * Seteaza tipul de culori.
   * @param culori - tipul de culori.
   */
  public void setCulori(TipCulori culori) {
    this.culori = culori;
  }

  /**
   * Intoarce informatii referitoare la tablou.
   * @return - informatii referitoare la tablou.
   */
  @Override
  public String toString() {
    return "Tablou {" +
            "numePictor='" + numePictor + '\'' +
            ", culori=" + culori.toString().toLowerCase() +
            super.toString() +
            '}';
  }
}
