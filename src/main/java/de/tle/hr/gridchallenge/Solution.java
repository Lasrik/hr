package de.tle.hr.gridchallenge;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int rounds = in.nextInt();
    for (int round = 0; round < rounds; round++) {
      String[] lines = new String[in.nextInt()];
      for (int i = 0; i < lines.length; i++) {
        char[] chars = in.next().toCharArray();
        Arrays.sort(chars);
        lines[i] = new String(chars);
      }

      if (valid(lines)) {
        System.out.println("YES");
      } else {
        System.out.println("NO");
      }
    }
  }

  private static boolean valid(String[] grid) {
    for (int col = 0; col < grid[0].length(); col++) {
      for (int row = 1; row < grid.length; row++) {
        if (grid[row].charAt(col) < grid[row - 1].charAt(col)) {
          return false;
        }
      }
    }
    return true;
  }
}
