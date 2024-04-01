package org.sudu.experiments.diff.lcs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Time complexity – O(nm)
 * Space complexity – O(nm)
 */
public class LCS<S> extends BaseLCS<S> {

  public LCS(S[] L, S[] R) {
    super(L, R);
  }

  public int[][] countLCSMatrix() {
    int[][] matrix = new int[L.length + 1][R.length + 1];
    for (int i = 1; i < L.length + 1; i++) {
      for (int j = 1; j < R.length + 1; j++) {
        if (equals(L[i - 1], R[j - 1])) {
          matrix[i][j] = 1 + matrix[i - 1][j - 1];
        } else {
          matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
        }
      }
    }
    return matrix;
  }

  public List<S> findCommon() {
    int[][] matrix = countLCSMatrix();
    int i = L.length, j = R.length;
    LinkedList<S> common = new LinkedList<>();

    while (i > 0 && j > 0) {
      if (equals(L[i - 1], R[j - 1])) {
        common.addFirst(L[i - 1]);
        i--;
        j--;
      } else {
        if (matrix[i - 1][j] > matrix[i][j - 1]) i--;
        else j--;
      }
    }
    return new ArrayList<>(common);
  }
}