package casa_licitatii.produse;

/**
 * Implementare concreta a lui "BuilderProdus".
 */
public class BuilderBijuterie extends BuilderProdus<Bijuterie,
        BuilderBijuterie> {

  /**
   * Seteaza materialul bijuteriei.
   * @param material - materialul bijuteriei.
   */
  public BuilderBijuterie cuMaterial(String material) {
    clasaConcreta.setMaterial(material);
    return this;
  }

  /**
   * Seteaza daca bijuteria aia piatra pretioasa sau nu.
   * @param piatraPretioasa - informatie legata de piatra pretioasa.
   */
  public BuilderBijuterie cuPiatraPretioasa(String piatraPretioasa) {
    boolean piatra;
    piatra = "da".equals(piatraPretioasa);
    clasaConcreta.setPiatraPretioasa(piatra);
    return this;
  }

  /**
   * Intoarce o instanta de tipul Bijuterie.
   * @return - instanta de tipul Bijuterie.
   */
  @Override
  Bijuterie getClasaConcreta() {
    return new Bijuterie();
  }

  /**
   * Intoarce o instanta de tipul BuilderBijuterie.
   * @return - instanta de tipul BuilderBijuterie.
   */
  @Override
  BuilderBijuterie getBuilderConcret() {
    return this;
  }
}
