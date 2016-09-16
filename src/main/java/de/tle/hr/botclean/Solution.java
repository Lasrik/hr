package de.tle.hr.botclean;

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

    return move_to(currentRow, currentCol, (int) dust.getX(), (int) dust.getY());
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
    for (int offset = 1; offset < board.length; offset++) {
      for (int row = max(0, currentRow - offset); row <= min(4, currentRow + offset); row++) {
        for (int col = max(0, currentCol - offset); col <= min(4, currentCol + offset); col++) {
          if (board[row][col] == 'd') {
            double dist = bot.distance(row, col);
            if (dist < minDist) {
              minDist = dist;
              result = new Point(row, col);
            }
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
