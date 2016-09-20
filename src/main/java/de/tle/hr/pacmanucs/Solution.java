package de.tle.hr.pacmanucs;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Queue;

public class Solution {

  static List<Point> bfs(int rows, int cols, int pacman_r, int pacman_c, int food_r, int food_c, char[][] grid) {
    Queue<Point> queue = new LinkedList<>();
    boolean[][] visited = new boolean[rows][cols];
    List<Point> expanded = new LinkedList<>();
    Map<Point, Point> parents = new HashMap<>();

    queue.add(new Point(pacman_r, pacman_c));
    visited[pacman_r][pacman_c] = true;

    while (!queue.isEmpty()) {
      Point current = queue.remove();
      expanded.add(current);
      if (grid[current.x][current.y] == '.') {
        return path(pacman_r, pacman_c, parents, current);
      }

      // UP
      if (current.x > 0
        && !visited[current.x - 1][current.y]
        && grid[current.x - 1][current.y] != '%') {
        visited[current.x - 1][current.y] = true;
        Point p = new Point(current.x - 1, current.y);
        queue.add(p);
        parents.put(p, current);
      }

      // LEFT
      if (current.y > 0
        && !visited[current.x][current.y - 1]
        && grid[current.x][current.y - 1] != '%') {
        visited[current.x][current.y - 1] = true;
        Point p = new Point(current.x, current.y - 1);
        queue.add(p);
        parents.put(p, current);
      }

      // RIGHT
      if (current.y + 1 < cols
        && !visited[current.x][current.y + 1]
        && grid[current.x][current.y + 1] != '%') {
        visited[current.x][current.y + 1] = true;
        Point p = new Point(current.x, current.y + 1);
        queue.add(p);
        parents.put(p, current);
      }

      // DOWN
      if (current.x + 1 < rows
        && !visited[current.x + 1][current.y]
        && grid[current.x + 1][current.y] != '%') {
        visited[current.x + 1][current.y] = true;
        Point p = new Point(current.x + 1, current.y);
        queue.add(p);
        parents.put(p, current);
      }
    }

    // Kein Weg vom Pacman zum Food ..
    return null;
  }

  static List<Point> path(int pacman_r, int pacman_c, Map<Point, Point> parents, Point current) {
    List<Point> path = new LinkedList<>();
    path.add(current);
    do {
      current = parents.get(current);
      path.add(current);
    } while (current.x != pacman_r || current.y != pacman_c);

    return path;
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

    List<Point> path = bfs(rows, cols, pacman_r, pacman_c, food_r, food_c, grid);
    if (path != null) {
      System.out.println(path.size() - 1);
      Collections.reverse(path);
      for (Point point : path) {
        System.out.println(point.x + " " + point.y);
      }
    } else {
      System.out.println("Kein Weg gefunden!");
    }
  }
}