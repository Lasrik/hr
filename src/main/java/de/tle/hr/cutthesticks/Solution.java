package de.tle.hr.cutthesticks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list.add(in.nextInt());
    }

    while (!list.isEmpty()) {
      System.out.println(list.size());
      Collections.sort(list);
      int cut = list.get(0);
      list.removeIf(stick -> stick - cut <= 0);
    }
  }
}