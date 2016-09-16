package de.tle.hr.servicelane;

import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int length = in.nextInt();
    int rounds = in.nextInt();

    int[] freeway = new int[length];
    for (int i = 0; i < length; i++) {
      freeway[i] = in.nextInt();
    }

    for (int round = 0; round <= rounds; round++) {
      int start = in.nextInt();
      int end = in.nextInt();
      int result = Integer.MAX_VALUE;
      for(int i = start; i <= end; i++) {
        if (freeway[i] < result) result = freeway[i];
      }
      System.out.println(result);
    }
  }
}
