package simulare;

import casa_licitatii.angajati.Administrator;
import casa_licitatii.angajati.Broker;
import casa_licitatii.CasaDeLicitatii;
import casa_licitatii.clienti.*;

/**
 * Clasa main a programului.
 */
public class Main {
  /**
   * Metoda main a programului.
   * <p>
   * Pentru a incepe programul, un administrator este creat si asociat casei,
   * iar pe urma diferiti clienti si brokeri sunt adaugati in baza de date
   * a casei. Se incepe apoi simularea licitatiilor prin intermediul clasei
   * Simulare.
   * @param args - lista de argumente date in linia de comanda.
   */
  public static void main(String[] args) {
    CasaDeLicitatii casa = CasaDeLicitatii.getInstance();
    casa.setAdministrator(new Administrator("Jon"));

    casa.adaugaBroker(new Broker("Andi"));
    casa.adaugaBroker(new Broker("Jean"));
    casa.adaugaBroker(new Broker("Marshall"));

    casa.adaugaClient(new PersoanaFizica("Elvis", "CA", "08.01.1935"));
    casa.adaugaClient(new PersoanaFizica("Dave Hester", "Florida", "15.05.1985"));
    casa.adaugaClient(new PersoanaFizica("Darrell Sheets", "Texas", "12.09.1966"));
    casa.adaugaClient(new PersoanaFizica("Jarrod Schulz", "LA", "04.03.1987"));
    casa.adaugaClient(new PersoanaJuridica("Gorillaz", "Ohio", TipCompanie.SRL, 1600.00));
    casa.adaugaClient(new PersoanaJuridica("Ikea", "Oslo", TipCompanie.SA, 999999999.99));
    casa.adaugaClient(new PersoanaJuridica("Dedeman", "Bacau", TipCompanie.SRL, 600000.50));

    Simulare.getInstance().incepeSimularea(false);
  }
}
