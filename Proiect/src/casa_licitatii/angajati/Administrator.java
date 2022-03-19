package casa_licitatii.angajati;

import casa_licitatii.CasaDeLicitatii;
import casa_licitatii.produse.*;
import simulare.SafeSleep;

import java.util.*;

/**
 * Clasa ce modeleaza intrebuintarile unui administrator.
 * <p>
 * Clasa implementeaza interfata "Runnable", un administrator trebuind sa
 * adauge elemente in lista din casa, in timp ce clientii le vizualizeaza
 * si brokerii le sterg.
 * <p>
 * Astfel, va fi rulat un fir de executie in care administratorul va incerca
 * sa adauge elemente in lista din casa, iar concomitent se va rula un fir
 * de executie ce va contine simularea vanzarii produselor.
 * <p>
 * Campul "aTerminat" codifica daca administratorul mai are de adaugat produse
 * in lista din casa, sau a epuizat stocul.
 */
public class Administrator extends Angajat implements Runnable {
  private boolean aTerminat = false;

  /**
   * Construieste un administrator cu un anumit nume.
   * @param nume - numele administratorului.
   */
  public Administrator(String nume) {
    this.nume = nume;
  }

  /**
   * Adauga un produs in lista de produse din casa de licitatii.
   * @param produs - produs de adaugat.
   */
  private void adaugaProdus(Produs produs) {
    CasaDeLicitatii casa = CasaDeLicitatii.getInstance();
    casa.getProduse().adauga(produs);
  }

  /**
   * Verifica daca a epuizat stocul de produse.
   * @return - true/false daca mai are de adaugat elemente sau nu.
   */
  public boolean aScrisTot() {
    return aTerminat;
  }

  /**
   * Metoda ce defineste comportamentul administratorului la inceputul unui fir
   * de executie.
   * <p>
   * Administratorul joaca rolul de Producer, incercand sa adauge mereu produse
   * noi in lista.
   */
  @Override
  public void run() {
    ReaderProduse reader = new ReaderProduseCSV();
    List<Produs> produse = reader.citesteProduse("input.txt");
    Collections.shuffle(produse);
    for (Produs produs : produse) {
      System.out.println("Administratorul a adaugat produsul: " + produs);
      adaugaProdus(produs);
      SafeSleep.safesleep(1000 + (int)(Math.random() * 100));
    }
    aTerminat = true;
  }
}
