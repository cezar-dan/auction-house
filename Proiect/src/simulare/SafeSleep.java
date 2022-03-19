package simulare;

import static java.lang.Thread.sleep;

public class SafeSleep {

  /**
   * Clasa wrapper ce decoreaza functionalitatea de sleep a firelor de executie.
   * <p>
   * Sleep-urile pot fi activate sau dezactivate in program prin modificarea
   * campului "pornit".
   */
  private SafeSleep() {

  }
  public static boolean pornit = false;

  /**
   * Metoda wrapper ce decoreaza metoda Thread.sleep().
   * <p>
   * Se executa sleep-urile doar daca campul "pornit" este setat pe true.
   * @param time - timpul dat ca parametru lui sleep.
   */
  public static void safesleep(int time) {
    if (!pornit) {
      return;
    }
    try {
      sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
