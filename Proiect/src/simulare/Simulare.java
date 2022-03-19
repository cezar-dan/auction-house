package simulare;

import casa_licitatii.CasaDeLicitatii;
import casa_licitatii.clienti.Client;
import casa_licitatii.produse.Produs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clasa ce modeleaza rularea unei simulari automate a procesului de licitatie.
 * <p>
 * Doar o singura instanta de Simulare poate exista in program, clasa
 * implementand design pattern-ul Singleton.
 * <p>
 * Clasa implementeaza interfata "Runnable", putand incepe simularea pe un
 * fir de executie separat.
 */
public class Simulare implements Runnable {
  private static Simulare instance;

  /**
   * Constructorul de baza este marcat ca fiind privat pentru a asigura
   * functionalitatea de Singleton a clasei. Instanta de Simulare poate fi
   * accesata doar prin intermediul metodei getInstance().
   */
  private Simulare() {

  }

  /**
   * Singura modalitate prin care se poate instantia un obiect.
   * <p>
   * Metoda aceasta asigura faptul ca o singura instanta de Simulare va fi
   * creata in intregul program.
   * @return - unica instanta de Simulare.
   */
  public static Simulare getInstance() {
    if (Simulare.instance == null) {
      Simulare.instance = new Simulare();
    }
    return Simulare.instance;
  }

  /**
   * Incepe simularea casei de licitatii.
   * <p>
   * Metoda creeaza doua fire de executie prin care modeleaza un sistem de tip
   * Producer-Consumer. Astfel, un thread va fi pornit pentru administrator,
   * care va avea rolul de Producer, incercand sa adauge in produse in lista
   * pana la epuizarea lor, iar celalalt thread va executa simularea in sine,
   * prin care clientii liciteaza pentru produsele din casa pana se vand toate.
   * Pe acest fir de executie, la vanzarea unui produs broker-ul il sterge
   * din lista, jucand rolul de Consumer.
   * @param sleepMode - true/false daca se doreste utilizarea sleep-urilor sau
   *                  sau nu.
   */
  public void incepeSimularea(boolean sleepMode) {
    SafeSleep.pornit = sleepMode;
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(CasaDeLicitatii.getInstance().getAdministrator());
    executor.execute(this);
    executor.shutdown();
  }

  /**
   * Ruleaza simularea casei de licitatii.
   * <p>
   * Simularea ruleaza pana cand lista de produse din casa se goleste, iar
   * administratorul nu mai are ce produse sa adauge in aceasta. Practic,
   * simularea se opreste cand se vand toate produsele.
   * <p>
   * Clientii sunt luati pe rand, iar fiecare dintre ei solicita o licitatie
   * pentru un produs aleator din lista, oferind totodata un buget maxim pentru
   * licitatia respectiva. Daca in urma unei solicitari, o licitatie atinge
   * numarul de participanti necesari, aceasta incepe.
   */
  @Override
  public void run() {
    CasaDeLicitatii casa = CasaDeLicitatii.getInstance();
    while (true) {
      for (Client client : casa.getClienti()) {
        SafeSleep.safesleep(1000);
        int randPos = (int) (Math.random() * casa.getProduse().dimensiune());
        Produs produs = casa.getProduse().intoarce(randPos);
        client.vizualizeazaProdus(produs.getId());
        SafeSleep.safesleep(1000);
        double bugetMaxim = produs.getPretMinim() * (1 + Math.random() * 2);
        client.solicitaLicitatie(produs, bugetMaxim);
        if (casa.getAdministrator().aScrisTot()
                && casa.getProduse().dimensiune() == 0) {
          System.out.println("\nToate produsele au fost vandute!\nCasa a" +
                  " strans: " + casa.getProfit());
          return;
        }
      }
    }
  }
}
