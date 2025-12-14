package core;

public final class BigInt implements Comparable<BigInt> {

  private final String value;

  public BigInt(String val) {
    if (val == null || val.isEmpty()) {
      throw new IllegalArgumentException("Empty value");
    }
    if (!val.chars().allMatch(Character::isDigit)) {
      throw new IllegalArgumentException("Invalid number");
    }
    int i = 0;
    while (i < val.length() - 1 && val.charAt(i) == '0') {
      i++;
    }
    this.value = val.substring(i);
  }

  public static BigInt zero() {
    return new BigInt("0");
  }

  public static BigInt one() {
    return new BigInt("1");
  }

  public boolean isZero() {
    return value.equals("0");
  }

  public String toString() {
    return value;
  }

  public int compareTo(BigInt other) {
    if (this.value.length() != other.value.length()) {
      return Integer.compare(this.value.length(), other.value.length());
    }
    return this.value.compareTo(other.value);
  }

  public BigInt add(BigInt other) {
    String a = this.value;
    String b = other.value;
    StringBuilder res = new StringBuilder();
    int i = a.length() - 1;
    int j = b.length() - 1;
    int carry = 0;

    while (i >= 0 || j >= 0 || carry > 0) {
      int da = i >= 0 ? a.charAt(i--) - '0' : 0;
      int db = j >= 0 ? b.charAt(j--) - '0' : 0;
      int sum = da + db + carry;
      res.append(sum % 10);
      carry = sum / 10;
    }
    return new BigInt(res.reverse().toString());
  }

  public BigInt subtract(BigInt other) {
    if (this.compareTo(other) < 0) {
      throw new IllegalArgumentException("Negative result");
    }
    String a = this.value;
    String b = other.value;
    StringBuilder res = new StringBuilder();
    int i = a.length() - 1;
    int j = b.length() - 1;
    int borrow = 0;

    while (i >= 0) {
      int da = a.charAt(i--) - '0' - borrow;
      int db = j >= 0 ? b.charAt(j--) - '0' : 0;
      if (da < db) {
        da += 10;
        borrow = 1;
      } else {
        borrow = 0;
      }
      res.append(da - db);
    }
    return new BigInt(res.reverse().toString().replaceFirst("^0+(?!$)", ""));
  }

  public BigInt multiply(BigInt other) {
    int n = this.value.length();
    int m = other.value.length();
    int[] res = new int[n + m];

    for (int i = n - 1; i >= 0; i--) {
      for (int j = m - 1; j >= 0; j--) {
        int mul = (this.value.charAt(i) - '0') * (other.value.charAt(j) - '0');
        int sum = mul + res[i + j + 1];
        res[i + j + 1] = sum % 10;
        res[i + j] += sum / 10;
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int r : res) {
      if (!(sb.length() == 0 && r == 0)) {
        sb.append(r);
      }
    }
    return new BigInt(sb.length() == 0 ? "0" : sb.toString());
  }

  public BigInt[] divideAndRemainder(BigInt d) {
    if (d.isZero()) throw new ArithmeticException("Division by zero");
    BigInt rem = BigInt.zero();
    StringBuilder q = new StringBuilder();

    for (char c : value.toCharArray()) {
      rem = new BigInt(rem.value + c);
      int cnt = 0;
      while (rem.compareTo(d) >= 0) {
        rem = rem.subtract(d);
        cnt++;
      }
      q.append(cnt);
    }
    return new BigInt[]{
        new BigInt(q.toString().replaceFirst("^0+(?!$)", "")),
        rem
    };
  }

  public BigInt divide(BigInt d) {
    return divideAndRemainder(d)[0];
  }

  public BigInt mod(BigInt d) {
    return divideAndRemainder(d)[1];
  }

  public BigInt pow(long e) {
    BigInt res = BigInt.one();
    BigInt base = this;
    while (e > 0) {
      if ((e & 1) == 1) res = res.multiply(base);
      base = base.multiply(base);
      e >>= 1;
    }
    return res;
  }
}
