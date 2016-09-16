package de.tle.hr.xor;

import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int l = in.nextInt();
    int r = in.nextInt();

    int result = 0;
    for (int a = l; a <= r; a++) {
      for (int b = a; b <= r; b++) {
        int xor = a ^ b;
        if (xor > result) result = xor;
      }
    }

    System.out.println(result);
  }
}
