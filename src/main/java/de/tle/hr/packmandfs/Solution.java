package de.tle.hr.packmandfs;

import java.awt.*;
import java.util.Scanner;
import java.util.Stack;

public class Solution {

  static void dfs(int rows, int cols, int pacman_r, int pacman_c, int food_r, int food_c, char[][] grid) {
    Stack<Point> stack = new Stack<>();
    boolean[][] visited = new boolean[rows][cols];
    stack.push(new Point(pacman_r, pacman_c));

    int number = 0;

    while (!stack.empty()) {
      Point current = stack.pop();
      visited[current.x][current.y] = true;
      System.out.println(current.x + " " + current.y);
      number++;
      if (grid[current.x][current.y] == '.') {
        System.out.println(number);
        return;
      }

      // UP
      if (current.x > 0) {
        if (!visited[current.x - 1][current.y] && grid[current.x - 1][current.y] != '%') {
          stack.push(new Point(current.x - 1, current.y));
        }
      }

      // LEFT
      if (current.y > 0) {
        if (!visited[current.x][current.y - 1] && grid[current.x][current.y - 1] != '%') {
          stack.push(new Point(current.x, current.y - 1));
        }
      }

      // RIGHT
      if (current.y + 1 < cols) {
        if (!visited[current.x][current.y + 1] && grid[current.x][current.y + 1] != '%') {
          stack.push(new Point(current.x, current.y + 1));
        }
      }

      // DOWN
      if (current.x + 1 < rows) {
        if (!visited[current.x + 1][current.y] && grid[current.x + 1][current.y] != '%') {
          stack.push(new Point(current.x + 1, current.y));
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);


    int pacman_r = in.nextInt();
    int pacman_c = in.nextInt();

    int food_r = in.nextInt();
    int food_c = in.nextInt();

    int rows = in.nextInt();
    int cols = in.nextInt();

    char[][] grid = new char[rows][cols];

    for (int i = 0; i < rows; i++) {
      String line = in.next();
      for (int j = 0; j < line.length(); j++) {
        grid[i][j] = line.charAt(j);
      }
    }

    dfs(rows, cols, pacman_r, pacman_c, food_r, food_c, grid);
  }
}
