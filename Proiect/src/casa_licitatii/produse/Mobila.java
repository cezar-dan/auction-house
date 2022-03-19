package casa_licitatii.produse;

/**
 * Un tip de produs vandut in casa de licitatii.
 * <p>
 * Un produs mobilier este definit prin tip si material folosit.
 */
public class Mobila extends Produs {
  private String tip;
  private String material;

  /**
   * Seteaza tipul de mobila.
   * @param tip - tipul de mobila.
   */
  public void setTip(String tip) {
    this.tip = tip;
  }

  /**
   * Seteaza materialul.
   * @param material - material.
   */
  public void setMaterial(String material) {
    this.material = material;
  }

  /**
   * Intoarce informatii referitoare la mobila.
   * @return - informatii referitoare la mobila.
   */
  @Override
  public String toString() {
    return  "Mobila {" +
            "tip='" + tip + '\'' +
            ", material='" + material + '\'' +
            super.toString() + '}';
  }
}
