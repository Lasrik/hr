package de.tle.hr.utopiatree;

import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int rounds = in.nextInt();

    for (int round = 0; round < rounds; round++) {
      int height = 1;

      int cycles = in.nextInt();

      for (int cycle = 0; cycle < cycles; cycle++) {
        if (cycle % 2 == 0) {
          height = height * 2;
        } else {
          height = height + 1;
        }
      }

      System.out.println(height);
    }
  }
}
