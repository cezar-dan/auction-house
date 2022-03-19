package casa_licitatii.clienti;

/**
 * Un tip de client definit de data nasterii.
 */
public class PersoanaFizica extends Client {
  String dataNastere;

  /**
   * Construieste un client de tip persoana fizica.
   * @param nume - numele clientului.
   * @param adresa - adresa clientului.
   * @param dataNastere - data nasterii clientului.
   */
  public PersoanaFizica(String nume, String adresa, String dataNastere) {
    super(nume, adresa);
    this.dataNastere = dataNastere;
  }
}
