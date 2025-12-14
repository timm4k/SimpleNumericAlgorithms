package core;

import java.util.ArrayList;
import java.util.List;

public final class NumericAlgorithms {

  public static BigInt gcd(BigInt a, BigInt b) {
    while (!b.isZero()) {
      BigInt r = a.mod(b);
      a = b;
      b = r;
    }
    return a;
  }

  public static BigInt gcd(BigInt a, BigInt b, BigInt c) {
    return gcd(gcd(a, b), c);
  }

  public static BigInt lcm(BigInt a, BigInt b) {
    return a.multiply(b).divide(gcd(a, b));
  }

  public static BigInt addMod(BigInt a, BigInt b, BigInt m) {
    return a.add(b).mod(m);
  }

  public static BigInt subMod(BigInt a, BigInt b, BigInt m) {
    if (a.compareTo(b) >= 0) return a.subtract(b).mod(m);
    return a.add(m).subtract(b).mod(m);
  }

  public static BigInt mulMod(BigInt a, BigInt b, BigInt m) {
    return a.multiply(b).mod(m);
  }

  public static BigInt powMod(BigInt a, long e, BigInt m) {
    BigInt res = BigInt.one();
    a = a.mod(m);
    while (e > 0) {
      if ((e & 1) == 1) res = mulMod(res, a, m);
      a = mulMod(a, a, m);
      e >>= 1;
    }
    return res;
  }

  public static List<Long> factorizeLong(long n) {
    List<Long> f = new ArrayList<>();
    for (long d = 2; d * d <= n; d++) {
      while (n % d == 0) {
        f.add(d);
        n /= d;
      }
    }
    if (n > 1) f.add(n);
    return f;
  }
}
