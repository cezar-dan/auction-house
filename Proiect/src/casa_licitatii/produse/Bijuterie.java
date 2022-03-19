package casa_licitatii.produse;

/**
 * Un tip de produs vandut in casa de licitatii.
 * <p>
 * O bijuterie este definita prin material si poate contine sau nu pietre
 * pretioase.
 */
public class Bijuterie extends Produs {
  private String material;
  private boolean piatraPretioasa;

  /**
   * Seteaza materialul bijuteriei.
   * @param material - materialul bijuteriei.
   */
  public void setMaterial(String material) {
    this.material = material;
  }

  /**
   * Specifica daca bijuteria contine sau nu pietre pretioase.
   * @param piatraPretioasa - informatie referitoare la pietrele pretioase.
   */
  public void setPiatraPretioasa(boolean piatraPretioasa) {
    this.piatraPretioasa = piatraPretioasa;
  }

  /**
   * Intoarce informatii referitoare la bijuterie.
   * @return - informatii referitoare la bijuterie.
   */
  @Override
  public String toString() {
    return  "Bijuterie {" +
            "material='" + material + '\'' +
            ", piatraPretioasa=" + piatraPretioasa +
            super.toString() +
            '}';
  }
}
