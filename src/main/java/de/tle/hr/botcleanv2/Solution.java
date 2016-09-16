package de.tle.hr.botcleanv2;

import java.awt.*;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Solution {

  public static final int SIZE = 5;

  public enum CMD {
    LEFT, RIGHT, UP, DOWN, CLEAN
  }

  public static CMD next_move(int currentRow, int currentCol, char[][] board) {
    if (board[currentRow][currentCol] == 'd') {
      return CMD.CLEAN;
    }

    Point dust = findNearest(currentRow, currentCol, board);
    if (dust != null) {
      return move_to(currentRow, currentCol, (int) dust.getX(), (int) dust.getY());
    }

    if (currentRow < 1) return CMD.DOWN;
    if (currentRow > 3) return CMD.UP;
    if (currentCol < 1) return CMD.RIGHT;
    if (currentCol > 3) return CMD.LEFT;

    return CMD.RIGHT;
  }

  public static CMD move_to(int currentRow, int currentCol, int destRow, int destCol) {
    if (destRow > currentRow) {
      return CMD.DOWN;
    } else if (destRow < currentRow) {
      return CMD.UP;
    } else {
      if (destCol > currentCol) {
        return CMD.RIGHT;
      } else {
        return CMD.LEFT;
      }
    }
  }

  public static Point findNearest(int currentRow, int currentCol, char[][] board) {
    double minDist = Double.MAX_VALUE;
    Point bot = new Point(currentRow, currentCol);
    Point result = null;
    for (int row = max(0, currentRow - 1); row <= min(4, currentRow + 1); row++) {
      for (int col = max(0, currentCol - 1); col <= min(4, currentCol + 1); col++) {
        if (board[row][col] == 'd') {
          double dist = bot.distance(row, col);
          if (dist < minDist) {
            minDist = dist;
            result = new Point(row, col);
          }
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int posR = in.nextInt();
    int posC = in.nextInt();

    char[][] board = new char[SIZE][SIZE];
    for (int row = 0; row < SIZE; row++) {
      String line = in.next();
      for (int col = 0; col < line.length(); col++) {
        board[row][col] = line.charAt(col);
      }
    }

    CMD cmd = next_move(posR, posC, board);
    System.out.println(cmd);
  }
}
