package casa_licitatii.clienti;

import casa_licitatii.CasaDeLicitatii;
import casa_licitatii.produse.Produs;
import simulare.SafeSleep;

import java.util.HashMap;
import java.util.Map;

/**
 * Clasa ce modeleaza comportamentul unui client.
 * <p>
 * Un client este definit de un id, un nume si o adresa, tine cont de numarul
 * de participari si de licitatii castigate si contine o mapare de tip
 * id produs-buget, prin care stabileste cate un buget pentru fiecare produs
 * pentru care doreste sa liciteze.
 */
public abstract class Client {
  private final int id;
  private final String nume;
  private final String adresa;
  private int nrParticipari = 0;
  private int nrLicitatiiCastigate = 0;
  private final Map<Integer, Double> bugetPerProdus = new HashMap<>();

  /**
   * Construieste un client pe baza unui nume si o adresa.
   * @param nume - numele clientului.
   * @param adresa - adresa clientului.
   */
  protected Client(String nume, String adresa) {
    this.nume = nume;
    this.adresa = adresa;
    this.id = (nume + adresa).hashCode() & 0xfffffff;
  }

  /**
   * Metoda ce intoarce numele clientului.
   * @return - numele clientului.
   */
  public String getNume() {
    return nume;
  }

  /**
   * Metoda ce intoarce numarul de licitatii castigate.
   * @return - numarul de licitatii castigate.
   */
  public int getNrLicitatiiCastigate() {
    return nrLicitatiiCastigate;
  }

  /**
   * Metoda ce intoarce referinta la bugetul stabilit per produs.
   * @return - referinta la bugetul stabilit per produs.
   */
  public Map<Integer, Double> getBugetPerProdus() {
    return bugetPerProdus;
  }

  /**
   * Metoda ce intoarce numarul de participari la licitatii.
   * @return - numarul de participari la licitatii.
   */
  public int getNrParticipari() {
    return nrParticipari;
  }

  /**
   * Vizualizeaza un produs din lista de produse a casei.
   * <p>
   * Poate sa o faca in acelasi timp in care administratorul adauga produse.
   * @param idProdus - id-ul produsului de vizualizat.
   */
  public void vizualizeazaProdus(int idProdus) {
    Produs produs = CasaDeLicitatii.getInstance().gasesteProdus(idProdus);
    if (produs == null) {
      System.out.println("Acest produs nu exista.\n");
    } else {
      System.out.println("Clientul " + this.nume + " vizualizeaza produsul: "
              + produs);
    }
  }

  /**
   * Solicita o licitatie pentru un produs pentru care a stabilit un buget
   * maxim.
   * @param produs - produsul pe care il doreste.
   * @param bugetMaxim - bugetul maxim pentru produs.
   */
  public void solicitaLicitatie(Produs produs, double bugetMaxim) {
    if (bugetPerProdus.containsKey(produs.getId())) {
      System.out.println("Clientul " + this.nume + " resolicita o licitatie " +
              "pentru produsul " + produs);
      return;
    }
    bugetPerProdus.put(produs.getId(), bugetMaxim);
    System.out.println("Clientul " + this.nume + " solicita o licitatie pentru"
            + " produsul " + produs);
    CasaDeLicitatii.getInstance().preiaSolicitareLicitatie(this, produs);
  }

  /**
   * Calculeaza un pret de oferit pentru licitatie in functie de id-ul
   * produsului si pretul maxim oferit in runda precedenta.
   * @param idProdus - id-ul produsului pentru care liciteaza.
   * @param maximRundaTrecuta - pretul maxim oferit runda trecuta.
   * @return - pretul oferit de client.
   */
  public double oferaPretCalculat(int idProdus, double maximRundaTrecuta) {
    SafeSleep.safesleep(1000);
    double buget = bugetPerProdus.get(idProdus);
    double oferta = maximRundaTrecuta +
            buget * Math.random() / (3 + Math.random() * 5);
    if (oferta > buget) {
      oferta = buget;
    }
    if (oferta == 0) {
      oferta = buget * 1 / (8 + Math.random() * 4);
    }
    System.out.println("Clientul " + this.nume + " ofera: " + oferta);
    return oferta;
  }

  /**
   * Primeste de la broker informatia referitoare la castigator.
   * @param castigator - castigatorul licitatiei.
   */
  public void primesteCastigator(Client castigator) {
    if (castigator == this) {
      nrLicitatiiCastigate++;
    }
  }

  /**
   * Paraseste licitatia, eliminind produsul din lista bugete.
   * @param idProdus - id-ul produsului de eliminat.
   */
  public void parasesteLicitatie(int idProdus) {
    nrParticipari++;
    getBugetPerProdus().remove(idProdus);
  }
}
