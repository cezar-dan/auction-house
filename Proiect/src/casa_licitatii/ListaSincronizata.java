package casa_licitatii;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clasa ce modeleaza o lista sincronizata ce rezolva problema
 * producer-consumer.
 * <p>
 * Ofera o interfata simplificata de lista, mascand detaliile de implementare
 * ale claselor ArrayList, Lock si Condition. Clasa aceasta reprezinta, astfel,
 * un exemplu de design pattern facade.
 * <p>
 * Este un exemplu de utilizare a genericitatii in proiect.
 * @param <E> - tipul de element din lista.
 */
public class ListaSincronizata<E> {
  static final int CAPACITATE = 5;
  ArrayList<E> tablou = new ArrayList<>();
  Lock lock = new ReentrantLock();
  Condition neVid = lock.newCondition();
  Condition nePlin = lock.newCondition();

  /**
   * Adauga elementul dat in lista, daca aceasta nu este plina.
   * In cazul in care lista este plina, asteapta pana cand un element este
   * sters din alt fir de executie.
   * @param element - elementul de adaugat.
   */
  public void adauga(E element) {
    lock.lock();
    try {
      while (tablou.size() == CAPACITATE) {
        nePlin.await();
      }
      tablou.add(element);
      neVid.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  /**
   * Sterge elementul dat in lista, daca aceasta nu este goala.
   * In cazul in care lista este goala, asteapta pana cand un element este
   * adaugat din alt fir de executie.
   * @param element - elementul de sters.
   */
  public void sterge(E element) {
    lock.lock();
    try {
      while (tablou.isEmpty()) {
        neVid.await();
      }
      tablou.remove(element);
      nePlin.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  /**
   * Intoarce referinta catre elementul aflat la pozitia idx.
   * @param idx - pozitia elementului dorit.
   * @return - referinta catre elementul aflat la pozitia idx.
   */
  public E intoarce(int idx) {
    lock.lock();
    try {
      while (tablou.isEmpty()) {
        neVid.await();
      }
      return tablou.get(idx);
    } catch (InterruptedException e) {
      e.printStackTrace();
      return null;
    } finally {
      lock.unlock();
    }
  }

  /**
   * Intoarce numarul de elemente din lista.
   * @return - numarul de elemente din lista.
   */
  public int dimensiune() {
    lock.lock();
    try {
      return tablou.size();
    } finally {
      lock.unlock();
    }
  }
}
