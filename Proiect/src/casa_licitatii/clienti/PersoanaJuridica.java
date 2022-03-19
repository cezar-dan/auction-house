package casa_licitatii.clienti;

/**
 * Un tip de client definit de tipul de companie si capitalul social.
 */
public class PersoanaJuridica extends Client{
  TipCompanie companie;
  double capitalSocial;

  /**
   * Construieste un client de tip persoana juridica.
   * @param nume - numele clientului.
   * @param adresa - adresa clientului.
   * @param companie - tipul de companie.
   * @param capital - capitalul companiei.
   */
  public PersoanaJuridica(String nume, String adresa, TipCompanie companie,
                          double capital) {
    super(nume, adresa);
    this.companie = companie;
    this.capitalSocial = capital;
  }
}
