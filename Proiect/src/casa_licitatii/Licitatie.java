package casa_licitatii;

import casa_licitatii.angajati.Broker;
import casa_licitatii.clienti.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa ce modeleaza o licitatie.
 * <p>
 * O licitatie este definita printr-un id propriu si un id-produs, tine cont de
 * numarul de participanti curent, de numarul de pasi curent si de oferta
 * maxima pana in momentul respectiv, si contine o lista de clienti si de
 * brokeri participanti.
 * O licitatie stabileste de asemenea numarul minim si maxim de participanti
 * necesari pentru incepere, precum si numarul maximi de pasi pentru a vinde
 * produsul.
 */
public class Licitatie {
  private final int id = (int)(Math.random() * 1000000);
  private int nrParticiapntiCurent = 0;
  private final int nrParticipantiMax = (int)(2 + Math.random() * 5);
  private final int nrParticipantiMin = (int)(2 + Math.random() *
          nrParticipantiMax);
  private final int idProdus;
  private final int nrPasiMaxim = (int)(2 + Math.random() * 4);
  private int nrPasiCurent = 0;
  private final List<Client> clienti = new ArrayList<>();
  private final List<Broker> brokeri = new ArrayList<>();
  private double ofertaMaxima = 0;

  /**
   * Construieste o instanta de licitatie pe baza id-ului produsului licitat.
   * @param idProdus - id-ul produsului licitat.
   */
  public Licitatie(int idProdus) {
    this.idProdus = idProdus;
  }

  /**
   * Metoda ce intoarce id-ul produsului licitat.
   * @return - id-ul produsului licitat.
   */
  public int getIdProdus() {
    return idProdus;
  }

  /**
   * Metoda ce intoarce numarul de pasi curent.
   * @return - numarul de pasi curent.
   */
  public int getNrPasiCurent() {
    return nrPasiCurent;
  }

  /**
   * Trece licitatia la urmatorul pas, incrementand campul nrPasiCurent.
   */
  public void treciLaPasulUrmator() {
    this.nrPasiCurent++;
  }

  /**
   * Intoarce referinta catre lista de brokeri participanti la licitatie.
   * @return - lista de brokeri participanti.
   */
  public List<Broker> getBrokeri() {
    return brokeri;
  }

  /**
   * Metoda ce intoarce oferta maxima pana in momentul respectiv.
   * @return - oferta max pana in momentul respectiv.
   */
  public double getOfertaMaxima() {
    return ofertaMaxima;
  }

  /**
   * Metoda ce seteaza oferta maxima.
   * @param pretCurentMaxim - pretul maxim pana in momentul respectiv.
   */
  public void setOfertaMaxima(double pretCurentMaxim) {
    this.ofertaMaxima = pretCurentMaxim;
  }

  /**
   * Inscrie o pereche client-broker in licitatie.
   * @param client - clientul inscris.
   * @param broker - brokerul ce va media clientul.
   */
  public void inscrieClientCuBroker(Client client, Broker broker) {
    nrParticiapntiCurent++;
    clienti.add(client);
    if (!brokeri.contains(broker)) {
      brokeri.add(broker);
    }
  }

  /**
   * Afiseaza numele si bugetul maxim al participantilor inscrisi.
   */
  public void afiseazaParticipanti() {
    System.out.println("La aceasta licitatie participa:\n");
    for (Client client : clienti) {
      System.out.println(client.getNume() + ", cu bugetul de: " +
              client.getBugetPerProdus().get(getIdProdus()));
    }
    System.out.print("\n");
  }

  /**
   * Metoda ce verifica daca licitatia a atins numarul maxim de pasi.
   * @return - true/false daca licitatia trebuie incheiata sau nu.
   */
  public boolean amTerminat() {
    return nrPasiCurent > nrPasiMaxim;
  }

  /**
   * Metoda ce verifica daca licitatia a atins numarul minim de participanti
   * necesar inceperii.
   * @return - true/false daca licitatia poate incepe sau nu.
   */
  public boolean poateIncepe() {
    return nrParticiapntiCurent == nrParticipantiMin;
  }
}
