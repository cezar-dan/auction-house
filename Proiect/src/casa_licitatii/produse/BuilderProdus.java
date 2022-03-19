package casa_licitatii.produse;

import java.util.List;

/**
 * Clasa ce implementeaza un builder pentru produse abstracte.
 * <p>
 * Clasa aceasta este un exemplu de design pattern builder. Este totodata si
 * un exemplu de utilizare a genericitatii.
 * @param <T> - tipul produsului.
 * @param <B> - tipul de builder.
 */
public abstract class BuilderProdus <T extends Produs, B extends BuilderProdus>
{
  T clasaConcreta;
  B builderConcret;

  /**
   * Intoarce o instanta de tipul clasaConcreta.
   * @return - instanta de tipul clasaConcreta.
   */
  abstract T getClasaConcreta();

  /**
   * Intoarce o instanta de tipul builderConcret.
   * @return - instanta de tipul builderConcret.
   */
  abstract B getBuilderConcret();

  /**
   * Constructor pentru builder.
   */
  BuilderProdus() {
    clasaConcreta = getClasaConcreta();
    builderConcret = getBuilderConcret();
  }


  /**
   * Seteaza id-ul produsului.
   * @param id - id-ul produsului.
   */
  public B cuId(String id) {
    clasaConcreta.setId(Integer.parseInt(id));
    return builderConcret;
  }

  /**
   * Seteaza numele produsului.
   * @param nume - id-ul produsului.
   */
  public B cuNume(String nume) {
    clasaConcreta.setNume(nume);
    return builderConcret;
  }

  /**
   * Seteaza pretul minim al produsului.
   * @param pretMinim - pretul minim al produsului.
   */
  public B cuPretMinim(String pretMinim) {
    clasaConcreta.setPretMinim(Double.parseDouble(pretMinim));
    return  builderConcret;
  }

  /**
   * Seteaza pretul anul de fabricare al produsului.
   * @param an - anul de fabricare al produsului.
   */
  public B cuAn(String an) {
    clasaConcreta.setAn(Integer.parseInt(an));
    return builderConcret;
  }

  /**
   * Construieste campurile generice ale produsul pe baza unui String de date.
   * @param data - camp de date.
   */
  @SuppressWarnings("unchecked")
  public B buildProdusGeneric(List<String> data) {
    return (B) builderConcret
            .cuId(data.get(1))
            .cuNume(data.get(2))
            .cuPretMinim(data.get(3))
            .cuAn(data.get(4));
  }

  /**
   * Incheie lantul de metode, returnand instanta creata.
   * @return - instanta creata.
   */
  public T build() {
    return clasaConcreta;
  }
}
