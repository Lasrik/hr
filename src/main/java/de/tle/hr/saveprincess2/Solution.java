package de.tle.hr.saveprincess2;

import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int size = in.nextInt();

    int botLine = in.nextInt();
    int botCol = in.nextInt();

    int prinLine = 0;
    int prinCol = 0;

    for (int i = 0; i < size; i++) {
      String line = in.next();
      for (int j = 0; j < line.length(); j++) {
        char c = line.charAt(j);
        if (c == 'p') {
          prinLine = i;
          prinCol = j;
        }
      }
    }

    if (botLine > prinLine) {
      System.out.println("UP");
    } else if (botLine < prinLine) {
      System.out.println("DOWN");
    } else {
      if (botCol > prinCol) {
        System.out.println("LEFT");
      } else {
        System.out.println("RIGHT");
      }
    }
  }
}
