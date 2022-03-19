package casa_licitatii.angajati;

import casa_licitatii.*;
import casa_licitatii.clienti.*;
import casa_licitatii.angajati.comisioane.*;
import casa_licitatii.produse.Produs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clasa ce modeleaza intrebuintarile unui broker.
 * <p>
 * Un broker mediaza comunicarea dintre client si casa de licitatii. Acesta
 * contine o mapare de licitatii-lista de clienti mediati la licitatia
 * respectiva, precum si o referinta la licitatia aflata in desfasurare si
 * un mod de calculare a comisionului.
 */
public class Broker extends Angajat {
  private final Map<Licitatie, List<Client>> clientiPerLicitatie =
          new HashMap<>();
  private Licitatie licitatieCurenta;
  private FunctieComision functieComision;

  /**
   * Construieste un broker cu numele dat.
   * @param nume - numele brokerului creat.
   */
  public Broker(String nume) {
    this.nume = nume;
  }

  /**
   * Seteaza licitatia primita ca fiind in desfasurare.
   * @param licitatieCurenta - licitatia care se afla in desfasurare.
   */
  public void setLicitatieCurenta(Licitatie licitatieCurenta) {
    this.licitatieCurenta = licitatieCurenta;
  }

  /**
   * Sterge produsul din lista daca acesta a fost vandut.
   * <p>
   * Aceasta metoda reprezinta modul prin care brokerul isi indeplineste
   * functia de Consmer.
   * @param produs - produsul care a fost vandut si trebuie scos din lista.
   */
  private void stergeProdus(Produs produs) {
    System.out.println("Brokerul " + this.nume + ", a sters produsul: "
            + produs);
    CasaDeLicitatii.getInstance().getProduse().sterge(produs);
  }

  /**
   * Preia un client pentru a-l media la licitatia respectiva, cand aceasta
   * va incepe.
   * @param licitatie - licitatia la care brokerul va colabora cu clientul.
   * @param client - clientul cu care va colabora.
   */
  public void preiaClient(Licitatie licitatie, Client client) {
    licitatie.inscrieClientCuBroker(client, this);
    clientiPerLicitatie.computeIfAbsent(licitatie, k -> new ArrayList<>());
    clientiPerLicitatie.get(licitatie).add(client);
  }

  /**
   * Cere clientilor sa ofere un pret pentru runda respectiva, oferindu-le
   * informatii referitoare la pretul maxim oferit in runda anterioara.
   * @return - mapare de oferte clienti-pret pentru toti clientii mediati
   * de brokerul respectiv.
   */
  public Map<Client, Double> solicitaOfertaClienti() {
    Map<Client, Double> oferte = new HashMap<>();
    List<Client> clienti = clientiPerLicitatie.get(licitatieCurenta);
    for (Client client : clienti) {
      double pret = client.oferaPretCalculat(
              licitatieCurenta.getIdProdus(),
              licitatieCurenta.getOfertaMaxima());
      oferte.put(client, pret);
    }
    return oferte;
  }

  /**
   * Anunta clientii cu care colaboreaza daca au castigat licitatia sau nu.
   * <p>
   * In cazul in care castigatorul licitatiei a colaborat cu acest broker,
   * brokerul isi pastreaza un comision din vanzare si sterge apoi produsul
   * din lista de pruse.
   * @param castigator - castigatorul licitatiei.
   */
  public void anuntaCastigator(Client castigator) {
    List<Client> clienti = clientiPerLicitatie.get(licitatieCurenta);
    for (Client client : clienti) {
      client.primesteCastigator(castigator);
      boolean amCastigat = (client == castigator);
      if (amCastigat) {
        CasaDeLicitatii casa = CasaDeLicitatii.getInstance();
        double pretCastigator = licitatieCurenta.getOfertaMaxima();
        double comision = calculeazaComision(pretCastigator, client);
        System.out.println("Brokerul "+ this.nume + ", a primit un comision" +
                " de tip " + functieComision + ", in valoare de: " + comision);
        casa.adaugaLaProfit(pretCastigator - comision);
        Produs produs = casa.gasesteProdus(licitatieCurenta.getIdProdus());
        stergeProdus(produs);
      }
    }
  }

  /**
   * Calculeaza comisionul de aplicat pe pret, in functie de client.
   * @param pret - pretul cu care a fost licitat produsul.
   * @param client - clientul care a castigat licitatia.
   * @return - valoarea comisionului pastrat de broker.
   */
  private double calculeazaComision(double pret, Client client) {
    int nrParticipari = client.getNrParticipari();
    if (client instanceof PersoanaFizica) {
      if (nrParticipari < 5) {
        functieComision = new ComisionTip1();
      } else {
        functieComision = new ComisionTip2();
      }
    } else if (client instanceof PersoanaJuridica) {
      if (nrParticipari < 25) {
        functieComision = new ComisionTip3();
      } else {
        functieComision = new ComisionTip4();
      }
    }
    return functieComision.calculeaza(pret);
  }

  /**
   * Incheie comunicarea cu toti clientii participanti la licitatie si rupe
   * legatura cu licitatia.
   */
  public void incheieComunicare() {
    List<Client> clienti = clientiPerLicitatie.get(licitatieCurenta);
    for (Client client: clienti) {
      client.parasesteLicitatie(licitatieCurenta.getIdProdus());
    }
    clientiPerLicitatie.remove(licitatieCurenta);
    licitatieCurenta = null;
  }
}
