package casa_licitatii.produse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Implementare concreta a unui "ReaderProduse" - citire din fisiere CSV.
 */
public class ReaderProduseCSV implements ReaderProduse {
  /**
   * Modalitate de citire a produselor din fisiere CSV.
   * @param numeFisier - nume fisier din care se face citirea.
   * @return - lista de produse citite.
   */
  public List<Produs> citesteProduse(String numeFisier) {
    List<Produs> produse = new ArrayList<>();
    try (Scanner scan = new Scanner(new File(numeFisier))) {
      while (scan.hasNextLine()) {
        String data = scan.nextLine();
        List<String> prodData = Arrays.asList(data.split(","));
        Produs produs;
        switch (prodData.get(0)) {
          case "mobila" -> produs = new BuilderMobila()
                  .buildProdusGeneric(prodData)
                  .cuTip(prodData.get(5))
                  .cuMaterial(prodData.get(6))
                  .build();
          case "tablou" -> produs = new BuilderTablou()
                  .buildProdusGeneric(prodData)
                  .cuNumePictor(prodData.get(5))
                  .cuTipCulori(prodData.get(6))
                  .build();
          default -> produs = new BuilderBijuterie()
                  .buildProdusGeneric(prodData)
                  .cuMaterial(prodData.get(5))
                  .cuPiatraPretioasa(prodData.get(6))
                  .build();
        }
        produse.add(produs);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return produse;
  }
}
