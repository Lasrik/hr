package de.tle.hr.botcleanv2;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

  public static final int SIZE = 5;

  public static CMD next_move(int currentRow, int currentCol, char[][] board) {
    if (board[currentRow][currentCol] == 'd') {
      return CMD.CLEAN;
    }

    Point dust = findNearest(currentRow, currentCol, board, 'd');
    if (dust != null) {
      return move_to(currentRow, currentCol, (int) dust.getX(), (int) dust.getY());
    }

    Point hidden = findNearest(currentRow, currentCol, board, 'o');
    if (hidden != null) {
      return move_to(currentRow, currentCol, hidden.x, hidden.y);
    }

    if (currentRow < 1) return CMD.DOWN;
    if (currentRow >= 4) return CMD.UP;
    if (currentCol < 1) return CMD.RIGHT;
    if (currentCol >= 4) return CMD.LEFT;

    return Math.random() > 0.5 ? CMD.DOWN : CMD.RIGHT;
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

  public static Point findNearest(int currentRow, int currentCol, char[][] board, char what) {
    double minDist = Double.MAX_VALUE;
    Point bot = new Point(currentRow, currentCol);
    Point result = null;
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        if (board[row][col] == what) {
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

  public static void main(String[] args) throws IOException {
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

    Path path = Paths.get("./board.txt");
    if (Files.isReadable(path)) {
      List<String> lines = Files.readAllLines(path, Charset.forName("utf-8"));
      for (int row = 0; row < SIZE; row++) {
        String line = lines.get(row);
        System.err.println(line);
        for (int col = 0; col < SIZE; col++) {
          if (board[row][col] == 'o') {
            board[row][col] = line.charAt(col);
          }
        }
      }
    }

    List<String> lines = new ArrayList<>(SIZE);
    for (int row = 0; row < SIZE; row++) {
      lines.add(String.copyValueOf(board[row]));
    }
    Files.write(path, lines);

    CMD cmd = next_move(posR, posC, board);
    System.out.println(cmd);
  }

  public enum CMD {
    LEFT, RIGHT, UP, DOWN, CLEAN
  }
}
