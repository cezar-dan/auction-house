package casa_licitatii.produse;

/**
 * Implementare concreta a lui "BuilderProdus".
 */
public class BuilderMobila extends BuilderProdus<Mobila, BuilderMobila> {

  /**
   * Seteaza tipul de mobila.
   * @param tip - tipul de mobila.
   */
  public BuilderMobila cuTip(String tip) {
    clasaConcreta.setTip(tip);
    return this;
  }

  /**
   * Seteaza materialul.
   * @param material - material de setat.
   */
  public BuilderMobila cuMaterial(String material) {
    clasaConcreta.setMaterial(material);
    return this;
  }

  /**
   * Intoarce o instanta de tipul Mobila.
   * @return - instanta de tipul Mobila.
   */
  @Override
  Mobila getClasaConcreta() {
    return new Mobila();
  }

  /**
   * Intoarce o instanta de tipul BuilderMobila.
   * @return - instanta de tipul BuilderMobila.
   */
  @Override
  BuilderMobila getBuilderConcret() {
    return this;
  }
}
