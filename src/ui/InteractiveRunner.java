package ui;

import core.BigInt;
import core.NumericAlgorithms;

import java.util.Scanner;

public final class InteractiveRunner {

  public static void run() {
    Scanner sc = new Scanner(System.in);
    boolean running = true;

    System.out.println(ConsoleColors.BLUE + "\n==== INTERACTIVE MODE ====" + ConsoleColors.RESET);

    while (running) {
      printMainMenu();
      int choice = readInt(sc);

      switch (choice) {
        case 1 -> basicAdd(sc);
        case 2 -> basicSub(sc);
        case 3 -> basicMul(sc);
        case 4 -> basicDiv(sc);
        case 5 -> basicPow(sc);
        case 6 -> basicGcd(sc);
        case 7 -> basicLcm(sc);
        case 8 -> modMenu(sc);
        case 9 -> running = false;
        default -> System.out.println("Invalid option");
      }
    }
  }

  private static void printMainMenu() {
    System.out.println(ConsoleColors.BLUE +
        "1 - Add\n" +
        "2 - Subtract\n" +
        "3 - Multiply\n" +
        "4 - Divide\n" +
        "5 - Power\n" +
        "6 - GCD\n" +
        "7 - LCM\n" +
        "8 - Modular arithmetic\n" +
        "9 - Exit" +
        ConsoleColors.RESET);
  }

  private static void modMenu(Scanner sc) {
    boolean modRunning = true;

    while (modRunning) {
      System.out.println(ConsoleColors.BLUE +
          "\nMOD OPERATIONS\n" +
          "1 - (a + b) mod m\n" +
          "2 - (a - b) mod m\n" +
          "3 - (a * b) mod m\n" +
          "4 - a^k mod m\n" +
          "5 - Back" +
          ConsoleColors.RESET);

      int choice = readInt(sc);

      switch (choice) {
        case 1 -> modAdd(sc);
        case 2 -> modSub(sc);
        case 3 -> modMul(sc);
        case 4 -> modPow(sc);
        case 5 -> modRunning = false;
        default -> System.out.println("Invalid option");
      }
    }
  }

  private static void basicAdd(Scanner sc) {
    BigInt a = readBigInt(sc, "First number");
    BigInt b = readBigInt(sc, "Second number");
    show(a.add(b));
  }

  private static void basicSub(Scanner sc) {
    BigInt a = readBigInt(sc, "First number");
    BigInt b = readBigInt(sc, "Second number");
    try {
      show(a.subtract(b));
    } catch (Exception e) {
      System.out.println("Result would be negative");
    }
  }

  private static void basicMul(Scanner sc) {
    BigInt a = readBigInt(sc, "First number");
    BigInt b = readBigInt(sc, "Second number");
    show(a.multiply(b));
  }

  private static void basicDiv(Scanner sc) {
    BigInt a = readBigInt(sc, "Dividend");
    BigInt b = readBigInt(sc, "Divisor");
    try {
      show(a.divide(b));
    } catch (Exception e) {
      System.out.println("Division error");
    }
  }

  private static void basicPow(Scanner sc) {
    BigInt a = readBigInt(sc, "Base");
    System.out.print("Exponent: ");
    long e = readLong(sc);
    show(a.pow(e));
  }

  private static void basicGcd(Scanner sc) {
    BigInt a = readBigInt(sc, "First number");
    BigInt b = readBigInt(sc, "Second number");
    show(NumericAlgorithms.gcd(a, b));
  }

  private static void basicLcm(Scanner sc) {
    BigInt a = readBigInt(sc, "First number");
    BigInt b = readBigInt(sc, "Second number");
    show(NumericAlgorithms.lcm(a, b));
  }

  private static void modAdd(Scanner sc) {
    BigInt a = readBigInt(sc, "a");
    BigInt b = readBigInt(sc, "b");
    BigInt m = readBigInt(sc, "m");
    show(NumericAlgorithms.addMod(a, b, m));
  }

  private static void modSub(Scanner sc) {
    BigInt a = readBigInt(sc, "a");
    BigInt b = readBigInt(sc, "b");
    BigInt m = readBigInt(sc, "m");
    show(NumericAlgorithms.subMod(a, b, m));
  }

  private static void modMul(Scanner sc) {
    BigInt a = readBigInt(sc, "a");
    BigInt b = readBigInt(sc, "b");
    BigInt m = readBigInt(sc, "m");
    show(NumericAlgorithms.mulMod(a, b, m));
  }

  private static void modPow(Scanner sc) {
    BigInt a = readBigInt(sc, "a");
    System.out.print("Exponent: ");
    long e = readLong(sc);
    BigInt m = readBigInt(sc, "m");
    show(NumericAlgorithms.powMod(a, e, m));
  }

  private static BigInt readBigInt(Scanner sc, String label) {
    while (true) {
      System.out.print(label + ": ");
      String s = sc.nextLine().trim();
      try {
        return new BigInt(s);
      } catch (Exception e) {
        System.out.println("Invalid number");
      }
    }
  }

  private static int readInt(Scanner sc) {
    while (!sc.hasNextInt()) {
      sc.nextLine();
      System.out.print("Enter integer: ");
    }
    int v = sc.nextInt();
    sc.nextLine();
    return v;
  }

  private static long readLong(Scanner sc) {
    while (!sc.hasNextLong()) {
      sc.nextLine();
      System.out.print("Enter integer: ");
    }
    long v = sc.nextLong();
    sc.nextLine();
    return v;
  }

  private static void show(BigInt r) {
    System.out.println(ConsoleColors.PURPLE + "Result: " + r + ConsoleColors.RESET);
  }
}
