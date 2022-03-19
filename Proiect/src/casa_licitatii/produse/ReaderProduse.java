package casa_licitatii.produse;

import java.util.List;

/**
 * Interfata ce ofera posibilitatea implementarii diferitelor moduri de citire
 * a unor produse.
 * <p>
 * Interfata aceasta este un exemplu de design pattern "Adapter".
 */
public interface ReaderProduse {
  /**
   * Modalitate de citire a produselor.
   * @param numeFisier - nume fisier din care se face citirea.
   * @return - lista de produse citite.
   */
  List<Produs> citesteProduse(String numeFisier);
}
