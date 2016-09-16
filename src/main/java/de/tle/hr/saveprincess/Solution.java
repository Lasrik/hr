package de.tle.hr.saveprincess;

import java.util.Scanner;

public class Solution {

  private enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int size = in.nextInt();
    boolean bottom = false;
    boolean right = false;

    for (int i = 0; i < size; i++) {
      char[] chars = in.next().toCharArray();
      for (int j = 0; j < chars.length; j++) {
        if (chars[j] == 'p') {
          bottom = i > 0;
          right = j > 0;
        }
      }
    }

    for (int i = 0; i < (size - 1) / 2; i++) {
      System.out.println(bottom ? Direction.DOWN : Direction.UP);
    }

    for (int i = 0; i < (size - 1) / 2; i++) {
      System.out.println(right ? Direction.RIGHT : Direction.LEFT);
    }
  }
}
