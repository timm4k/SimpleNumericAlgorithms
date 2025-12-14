package ui;

import core.BigInt;
import core.NumericAlgorithms;

public final class DemoRunner {

  public static void run() {
    System.out.println(ConsoleColors.BLUE + "==== DEMO ====" + ConsoleColors.RESET);

    BigInt a = new BigInt("36");
    BigInt b = new BigInt("12");
    BigInt c = new BigInt("18");

    print("36 + 12", a.add(b));
    print("36 - 12", a.subtract(b));
    print("36 * 12", a.multiply(b));
    print("36 / 12", a.divide(b));
    print("36^5", a.pow(5));

    print("gcd(36,12)", NumericAlgorithms.gcd(a, b));
    print("gcd(36,12,18)", NumericAlgorithms.gcd(a, b, c));
    print("lcm(36,12)", NumericAlgorithms.lcm(a, b));
  }

  private static void print(String label, BigInt value) {
    System.out.println(ConsoleColors.PURPLE + label + " = " + value + ConsoleColors.RESET);
  }
}
