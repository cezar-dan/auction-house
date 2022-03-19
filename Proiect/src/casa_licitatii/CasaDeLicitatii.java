package casa_licitatii;

import casa_licitatii.angajati.Administrator;
import casa_licitatii.angajati.Broker;
import casa_licitatii.clienti.Client;
import casa_licitatii.produse.Produs;
import simulare.SafeSleep;

import java.util.*;

/**
 * Clasa ce modeleaza casa de licitatii.
 * <p>
 * O casa de licitatii este intretinuta de un administrator, poate tine cont
 * de profitul obtinut, si contine o lista de produse, una de clienti, una de
 * brokeri si una de licitatii.
 * <p>
 * Doar o singura instanta de CasaDeLicitatii poate exista in program, clasa
 * implementand design pattern-ul Singleton.
 */
public class CasaDeLicitatii {
  private static CasaDeLicitatii instance;
  private final ListaSincronizata<Produs> produse = new ListaSincronizata<>();
  private final List<Client> clienti = new ArrayList<>();
  private final List<Broker> brokeri = new ArrayList<>();
  private final List<Licitatie> licitatii = new ArrayList<>();
  private Administrator administrator;
  private double profit = 0;

  private CasaDeLicitatii() {

  }

  /**
   * Metoda ce intoarce referinta catre lista de produse.
   * @return - referinta catre lista de produse.
   */
  public ListaSincronizata<Produs> getProduse() {
    return produse;
  }

  /**
   * Metoda ce intoarce referinta catre lista de clienti.
   * @return - referinta catre lista de clienti.
   */
  public List<Client> getClienti () {
    return clienti;
  }

  /**
   * Metoda ce intoarce referinta catre administrator.
   * @return - referinta catre administrator.
   */
  public Administrator getAdministrator() {
    return administrator;
  }

  /**
   * Metoda ce seteaza administratorul.
   */
  public void setAdministrator(Administrator administrator) {
    this.administrator = administrator;
  }

  /**
   * Metoda ce intoarce profitul casei.
   * @return - profitul casei.
   */
  public double getProfit() {
    return profit;
  }

  public static CasaDeLicitatii getInstance() {
    if (CasaDeLicitatii.instance == null) {
      CasaDeLicitatii.instance = new CasaDeLicitatii();
    }
    return CasaDeLicitatii.instance;
  }

  /**
   * Gaseste un produs in lista de produse dupa id-ul produsului.
   * @param idProdus - id-ul produsului cautat.
   * @return - referinta catre produsul gasit / null daca nu exista in lista.
   */
  public Produs gasesteProdus(int idProdus) {
    produse.lock.lock();
    try {
      return produse.tablou.stream()
              .filter(produs -> idProdus == produs.getId())
              .findAny()
              .orElse(null);
    } finally {
      produse.lock.unlock();
    }
  }

  /**
   * Gaseste o licitatie in lista de licitatii dupa id-ul produsului scos la
   * vanzare.
   * @param idProdus - id-ul produsului cautat.
   * @return - referinta catre licitatia gasita / null daca nu exista in lista.
   */
  private Licitatie gasesteLicitatie(int idProdus) {
    return licitatii.stream()
            .filter(licitatie -> idProdus == licitatie.getIdProdus())
            .findAny()
            .orElse(null);
  }

  /**
   * Adauga un client in lista de clienti.
   * @param client - client de adaugat.
   */
  public void adaugaClient(Client client) {
    clienti.add(client);
  }

  /**
   * Adauga un broker in lista de brokeri.
   * @param broker - broker de adaugat.
   */
  public void adaugaBroker(Broker broker) {
    brokeri.add(broker);
  }

  /**
   * Adauga o suma la profitul casei.
   * @param pret - suma de augat.
   */
  public void adaugaLaProfit(double pret) {
    profit += pret;
  }

  /**
   * Asociaza aleator un brokur unui client pentru o anumita licitatie.
   * @param licitatie - licitatia pentru care broker-ul va media clientul.
   * @param client - clientul care va fi mediat.
   */
  private void asociazaBroker(Licitatie licitatie, Client client) {
    int randPos = (int) (Math.random() * brokeri.size());
    Broker broker = brokeri.get(randPos);
    broker.preiaClient(licitatie, client);
  }

  /**
   * Preia solicitarea de licitatie a unui client pentru un anumit produs.
   * <p>
   * Daca o licitatie pentru produsul respectiv nu a fost solicitata deja,
   * o noua licitatie este creata si adaugata in lista de licitatii. Se
   * asociaza apoi un broker clientului care solicita licitatia.
   * <p>
   * In cazul in care licitatia a atins numarul minim de participanti necesari,
   * aceasta incepe.
   * @param client - clientul care solicita licitatia.
   * @param produs - produsul pentru care clientul doreste sa liciteze.
   */
  public void preiaSolicitareLicitatie(Client client, Produs produs) {
    Licitatie licitatie = gasesteLicitatie(produs.getId());
    if (licitatie == null) {
      licitatie = new Licitatie(produs.getId());
      licitatii.add(licitatie);
    }
    asociazaBroker(licitatie, client);
    if (licitatie.poateIncepe()) {
      SafeSleep.safesleep(1000);
      System.out.println("\nINCEPE LICITATIA pentru produsul " + produs
              + "\n");
      ruleazaLicitatie(produs);
    }
  }

  /**
   * Ruleaza licitatia in totalitate, notificand brokerii de start, apoi
   * simuland toti pasii licitatiei, anuntand castigatorul si incheiand
   * pe urma toate legaturile dintre participanti.
   * @param produs - produsul pentru care se tine licitatia.
   */
  private void ruleazaLicitatie(Produs produs) {
    Licitatie licitatie = gasesteLicitatie(produs.getId());
    SafeSleep.safesleep(1000);
    licitatie.afiseazaParticipanti();
    notificaBrokeriStart(licitatie);
    incheieLicitatia(licitatie, simuleazaLicitatie(licitatie));
  }

  /**
   * Notifica toti brokerii participanti la licitatie de inceperea ei.
   * @param licitatie - licitatie care incepe.
   */
  private void notificaBrokeriStart(Licitatie licitatie) {
    for (Broker broker : licitatie.getBrokeri()) {
      broker.setLicitatieCurenta(licitatie);
    }
  }

  /**
   * Simuleaza pasii de executie ai licitatiei si intoarce clientul care a
   * oferit cel mai bun pret.
   * <p>
   * In fiecare runda, clientii ofera un pret pentru produs, iar la final se
   * stabileste un castigator. Pretul maxim oferit per runda este stocat
   * in licitatie, pentru ca brokerii sa il transmita mai departe clientilor
   * pentru a stabili strategia de licitatie.
   * @param licitatie - licitatia care este simulata.
   * @return - referinta catre clientul care a oferit cel mai bun pret.
   */
  private Client simuleazaLicitatie(Licitatie licitatie) {
    Client castigator = null;
    while (!licitatie.amTerminat()) {
      SafeSleep.safesleep(500);
      System.out.println("\nRUNDA " + (licitatie.getNrPasiCurent() + 1));
      double maxPret;
      Map<Client, Double> oferte = new HashMap<>();
      for (Broker broker : licitatie.getBrokeri()) {
        oferte.putAll(broker.solicitaOfertaClienti());
      }
      castigator = stabilesteCastigator(oferte);
      maxPret = oferte.get(castigator);
      licitatie.setOfertaMaxima(maxPret);
      licitatie.treciLaPasulUrmator();
    }
    return castigator;
  }

  /**
   * Stabileste un castigator dintr-o mapare de clienti-oferte.
   * <p>
   * Este ales clientul care a oferit suma cea mai mare. In cazul in care doi
   * clienti au oferit aceeasi suma, castigator este cel care a castigat mai
   * multe licitatii.
   * @param oferte - mapare de clienti-oferte.
   * @return - referinta catre clientul care a oferit cel mai mult.
   */
  private Client stabilesteCastigator(Map<Client, Double> oferte) {
    double maxim = 0;
    Client castigator = null;
    for (Map.Entry<Client, Double> entry : oferte.entrySet()) {
      if (entry.getValue() > maxim) {
        maxim = entry.getValue();
        castigator = entry.getKey();
      } else if (entry.getValue() == maxim && castigator != null) {
        if (entry.getKey().getNrLicitatiiCastigate() >
                castigator.getNrLicitatiiCastigate()) {
          maxim = entry.getValue();
          castigator = entry.getKey();
        }
      }
    }
    return castigator;
  }

  /**
   * Incheie licitatia, stabileste daca a fost depasit pretul minim de vanzare
   * a produsului si intrerupe comunicarea intre participanti.
   * @param licitatie - licitatia care se incheie.
   * @param castigatorPosibil - clientul care a oferit cel mai bun pret.
   */
  private void incheieLicitatia(Licitatie licitatie, Client castigatorPosibil) {
    System.out.println("\nLICITATIA S-A TERMINAT\n");
    Client castigator = null;
    SafeSleep.safesleep(1000);
    double pretMin = gasesteProdus(licitatie.getIdProdus()).getPretMinim();
    if (licitatie.getOfertaMaxima() >= pretMin) {
      System.out.println("Licitatia a fost castigata de " +
              castigatorPosibil.getNume() + ", care a oferit " +
              licitatie.getOfertaMaxima() + "\n");
      castigator = castigatorPosibil;
    } else {
      System.out.println("Pragul minim nu a fost atins, produsul este " +
              "pastrat pentru o licitatie viitoare\n");
    }
    for (Broker broker : licitatie.getBrokeri()) {
      broker.anuntaCastigator(castigator);
      broker.incheieComunicare();
    }
    licitatii.remove(licitatie);
  }
}
