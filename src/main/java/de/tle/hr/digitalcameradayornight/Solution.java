package de.tle.hr.digitalcameradayornight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    long bright = 0;
    long all = 0;

    String line;
    while ((line = in.readLine()) != null) {
      String[] triples = line.split(" ");
      for (String triple : triples) {
        all++;
        int brightness = add(triple);
        if (brightness > 240) {
          bright++;
        }
      }
    }

    double ratio = (double) bright / (double) all;
    if (ratio > 0.55) {
      System.out.println("day");
    } else {
      System.out.println("night");
    }
  }

  private static int add(String triple) {
    String[] values = triple.split(",");
    int result = 0;
    for (String value : values) {
      result += Integer.parseInt(value);
    }

    return result;
  }
}
