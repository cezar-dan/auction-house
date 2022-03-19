package casa_licitatii.produse;

/**
 * Implementare concreta a lui "BuilderProdus".
 */
public class BuilderTablou extends BuilderProdus<Tablou, BuilderTablou> {

  /**
   * Seteaza numele pictorului.
   * @param numePictor - numele pictorului.
   */
  public BuilderTablou cuNumePictor(String numePictor) {
    clasaConcreta.setNumePictor(numePictor);
    return this;
  }

  /**
   * Seteaza tipul de culori al tabloului.
   * @param tipString - tip de culori in format String.
   */
  public BuilderTablou cuTipCulori(String tipString) {
    switch(tipString) {
      case "ulei" -> clasaConcreta.setCulori(TipCulori.ULEI);
      case "tempera" -> clasaConcreta.setCulori(TipCulori.TEMPERA);
      case "acrilic" -> clasaConcreta.setCulori(TipCulori.ACRILIC);
      default -> throw new NoSuchColorTypeException();
    }
    return this;
  }

  /**
   * Intoarce o instanta de tipul Tablou.
   * @return - instanta de tipul Tablou.
   */
  @Override
  Tablou getClasaConcreta() {
    return new Tablou();
  }

  /**
   * Intoarce o instanta de tipul BuilderTablou.
   * @return - instanta de tipul BuilderTablou.
   */
  @Override
  BuilderTablou getBuilderConcret() {
    return this;
  }
}
