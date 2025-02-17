package org.sudu.experiments.encoding;

public interface GbkEncoding {
  interface Table {
    char[] charToGbk = init();
  }

  static char[] init() {
    char[] chars = new char[0x10000];
    byte[] gbkCodes = allGbkCodes();
    String s = TextDecoder.decodeGbk(gbkCodes);
    if (s.length() * 2 != gbkCodes.length)
      throw new RuntimeException("unexpected TextDecoder.decodeGbk length");
    for (int i = 0; i + i < gbkCodes.length; i++) {
      int lb = gbkCodes[i + i] & 255, hb = gbkCodes[i + i + 1] & 255;
      char cp = s.charAt(i), src = (char) (lb + hb * 256);
      chars[cp] = src;
    }
    return chars;
  }

  static byte[] encode(char[] s) {
    var charToGbk = Table.charToGbk;
    int p = 0;
    byte[] data = new byte[byteLength(s)];
    for (char c : s)
      p = putChar(p, data, c, charToGbk);
    return data;
  }

  static byte[] encode(String s) {
    var charToGbk = Table.charToGbk;
    int p = 0;
    byte[] data = new byte[byteLength(s)];
    for (int i = 0, l = s.length(); i < l; ++i)
      p = putChar(p, data, s.charAt(i), charToGbk);
    return data;
  }

  static int bytesForChar(char c, char[] charToGbk) {
    return c > 127 && charToGbk[c] != 0 ? 2 : 1;
  }

  static int putChar(int p, byte[] data, char c, char[] charToGbk) {
    if (c > 127) {
      char gbkCode = charToGbk[c];
      if (gbkCode != 0) {
        if (p + 1 < data.length) {
          data[p++] = (byte) gbkCode;
          data[p++] = (byte) (gbkCode >> 8);
        }
      } else {
        if (p < data.length) data[p++] = '?';
      }
    } else {
      if (p < data.length) data[p++] = (byte) c;
    }
    return p;
  }

  static int byteLength(char[] s) {
    var charToGbk = Table.charToGbk;
    int n = 0;
    for (char c : s) {
      n += bytesForChar(c, charToGbk);
    }
    return n;
  }

  static int byteLength(String s) {
    var charToGbk = Table.charToGbk;
    int n = 0;
    for (int i = 0, l = s.length(); i < l; ++i) {
      char c = s.charAt(i);
      n += bytesForChar(c, charToGbk);
    }
    return n;
  }

  static byte[] allGbkCodes() {
    // [126] x [190]
    byte[] b = new byte[126 * 190 * 2];
    // 1st [81–FE]
    for (byte b1 = -127; b1 <= -2; b1++) {
      int y = b1 + 127;
      for (int i = 0; i < 190; i++) {
        b[i * 2 + y * 190 * 2] = b1;
      }
    }

    // 2nd [40–7E] + [80-FE]
    byte b2 = 0x40;
    for (; b2 <= 0x7e; b2++) {
      int x = b2 - 0x40;
      for (int i = 0; i < 126; i++)
        b[i * 190 * 2 + x * 2 + 1] = b2;
    }
    for (b2 = -128; b2 <= -2; b2++) {
      int x = b2 + 0x40 + 127;
      for (int i = 0; i < 126; i++)
        b[i * 190 * 2 + x * 2 + 1] = b2;
    }
    return b;
  }
}
