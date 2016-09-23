package de.tle.hr.npuzzle;

import java.util.*;

public class Solution {

  public static void main(String[] args) {
    Board start = readInput();

    Queue<Board> open = new PriorityQueue<>();
    Map<Board, Board> ancestor = new HashMap<>();
    open.add(start);
    ancestor.put(start, null);

    int boards = 0;
    while (!open.isEmpty()) {
      Board current = open.remove();
      boards++;
      if (current.isSolved()) {
        List<Board> solution = new LinkedList<>();
        while (current != null) {
          solution.add(current);
          current = ancestor.get(current);
        }
        System.out.println(solution.size() - 1);
        Collections.reverse(solution);
        for (Board board : solution) {
          if (board.move != null) {
            System.out.println(board.move);
          }
        }
        return;
      }

      List<Board> possibleMoves = current.possibleMoves();
      //System.out.println(current);
      for (Board move : possibleMoves) {
        if (!ancestor.containsKey(move)) {
          open.add(move);
          ancestor.put(move, current);
//          System.out.println(move);
        }
      }
//      System.out.println("---------");
    }

    System.err.println("ERROR! " + boards);
  }

  private static Board readInput() {
    Scanner in = new Scanner(System.in);
    int boardSize = in.nextInt();
    int[][] board = new int[boardSize][boardSize];
    Tile space = new Tile(0, 0);

    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        board[row][col] = in.nextInt();
        if (board[row][col] == 0) {
          space = new Tile(row, col);
        }
      }
    }

    return new Board(0, boardSize, board, space);
  }

  enum Move {
    UP, DOWN, RIGHT, LEFT
  }

  static class Board implements Comparable<Board> {
    private int distance = 0;
    private int boardSize;
    private int[][] board;
    private Tile space;
    private Move move = null;

    public Board(int distance, int boardSize, int[][] board, Tile space) {
      this.distance = distance;
      this.boardSize = boardSize;
      this.board = board;
      this.space = space;
    }

    public Board(int boardSize) {
      this.boardSize = boardSize;
      this.board = new int[boardSize][boardSize];
      int count = 0;
      for (int row = 0; row < boardSize; row++) {
        for (int col = 0; col < boardSize; col++) {
          board[row][col] = count;
          count++;
        }
      }
      space = new Tile(0, 0);
    }

    Board(Board other) {
      this.distance = other.distance + 1;
      this.boardSize = other.boardSize;
      this.board = new int[boardSize][boardSize];
      for (int row = 0; row < boardSize; row++) {
        System.arraycopy(other.board[row], 0, this.board[row], 0, boardSize);
      }
      this.space = new Tile(other.space.row, other.space.col);
    }

    public List<Board> possibleMoves() {
      List<Board> result = new LinkedList<>();
      for (Move move : Move.values()) {
        if (isValid(move)) {
          Board b = new Board(this);
          b.move(move);
          result.add(b);
        }
      }
      return result;
    }

    public boolean isValid(Move move) {
      switch (move) {
        case UP:
          return space.row > 0;
        case DOWN:
          return space.row < boardSize - 1;
        case LEFT:
          return space.col > 0;
        case RIGHT:
          return space.col < boardSize - 1;
      }
      return false;
    }

    public void move(Move move) {
      this.move = move;
      Tile newSpace = null;
      switch (move) {
        case UP:
          newSpace = new Tile(space.row - 1, space.col);
          break;
        case DOWN:
          newSpace = new Tile(space.row + 1, space.col);
          break;
        case LEFT:
          newSpace = new Tile(space.row, space.col - 1);
          break;
        case RIGHT:
          newSpace = new Tile(space.row, space.col + 1);
          break;
      }

      board[space.row][space.col] = board[newSpace.row][newSpace.col];
      board[newSpace.row][newSpace.col] = 0;
      space = newSpace;
    }

    public int misplaced() {
      int count = 0;
      for (int row = 0; row < boardSize; row++) {
        for (int col = 0; col < boardSize; col++) {
          if (board[row][col] > 0 && board[row][col] != (row * boardSize) + col) {
            count++;
          }
        }
      }

      return count;
    }

    public int manhattan() {
      int sum = 0;
      for (int row = 0; row < boardSize; row++) {
        for (int col = 0; col < boardSize; col++) {
          int value = board[row][col];
          if (value > 0 && value != (row * boardSize) + col) {
            sum += Math.abs(row - (value / boardSize));
            sum += Math.abs(col - (value % boardSize));
          }
        }
      }
      return sum;
    }

    public int weight() {
      return distance + manhattan() + misplaced();
    }

    public String toString() {
      String result = move + " -> Distance: " + distance + ". Misplaced: " + misplaced() + ". Manhattan: " + manhattan();
      return result + "\n" + Arrays.deepToString(board).replaceAll("\\],", "\n").replaceAll("[\\[\\] ]", "");
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Board board1 = (Board) o;

      return Arrays.deepEquals(board, board1.board);
    }

    @Override
    public int hashCode() {
      return Arrays.deepHashCode(board);
    }

    @Override
    public int compareTo(Board o) {
      int comp = this.weight() - o.weight();
      if (comp == 0) {
        return this.distance - o.distance;
      }

      return this.weight() - o.weight();
    }

    public boolean isSolved() {
      return misplaced() == 0;
    }
  }

  static class Tile {
    public int row;
    public int col;

    public Tile(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
}
