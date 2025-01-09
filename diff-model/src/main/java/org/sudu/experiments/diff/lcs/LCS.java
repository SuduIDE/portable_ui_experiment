package org.sudu.experiments.diff.lcs;

import org.sudu.experiments.diff.ranges.BaseRange;
import org.sudu.experiments.diff.ranges.CommonRange;
import org.sudu.experiments.diff.ranges.Diff;
import org.sudu.experiments.math.ArrayOp;

import java.util.ArrayList;
import java.util.List;

public abstract class LCS {

  protected final int[][] L, R;
  protected final int[] leftSync, rightSync;

  public LCS(int[][] L, int[][] R) {
    this(L, R, new int[]{}, new int[]{});
  }

  public LCS(int[][] L, int[][] R, int[] leftSync, int[] rightSync) {
    this.L = L;
    this.R = R;
    leftSync = ArrayOp.add(leftSync, L.length);
    rightSync = ArrayOp.add(rightSync, R.length);

    if (leftSync.length != rightSync.length)
      throw new IllegalArgumentException("Left and right sync points arrays must have the same length");
    this.leftSync = leftSync;
    this.rightSync = rightSync;
  }

  // return values & indices of L sequence
  public abstract int[][] findCommon();

  public <S> List<BaseRange<S>> countRanges(
      S[] objL, S[] objR,
      int start, int endCut
  ) {
    List<BaseRange<S>> ranges = new ArrayList<>();
    if (start != 0) ranges.add(new CommonRange<>(0, 0, start));

    int[][] common = findCommon();
    int commonPtr = 0,
        leftPtr = start,
        rightPtr = start;

    Diff<S> currentDiff = null;
    CommonRange<S> currentCommon = null;

    while (commonPtr < common.length) {
      S cS = objL[common[commonPtr][1]];
      S cL = objL[leftPtr], cR = objR[rightPtr];

      if (equals(cL, cR)) {
        if (currentCommon == null) currentCommon = new CommonRange<>(leftPtr, rightPtr);
        if (currentDiff != null) ranges.add(currentDiff);
        commonPtr++;
        leftPtr++;
        rightPtr++;
        currentCommon.length++;
        currentDiff = null;
        continue;
      }
      if (currentDiff == null) currentDiff = new Diff<>(leftPtr, rightPtr);
      if (currentCommon != null) ranges.add(currentCommon);
      currentCommon = null;

      if (!equals(cS, cL)) {
        currentDiff.diffN.add(objL[leftPtr]);
        leftPtr++;
      }
      if (!equals(cS, cR)) {
        currentDiff.diffM.add(objR[rightPtr]);
        rightPtr++;
      }
    }
    if (currentCommon != null) ranges.add(currentCommon);
    if (currentDiff == null) currentDiff = new Diff<>(leftPtr, rightPtr);

    for (; leftPtr < objL.length - endCut; leftPtr++) {
      S cL = objL[leftPtr];
      currentDiff.diffN.add(cL);
    }
    for (; rightPtr < objR.length - endCut; rightPtr++) {
      S cR = objR[rightPtr];
      currentDiff.diffM.add(cR);
    }
    if (currentDiff != null && currentDiff.isNotEmpty()) ranges.add(currentDiff);
    if (endCut != 0) ranges.add(new CommonRange<>(objL.length - endCut, objR.length - endCut, endCut));
    return ranges;
  }

  protected <S> boolean equals(S a, S b) {
    return a.equals(b);
  }

  protected int valL(int ind) {
    return L[ind][0];
  }

  protected int valR(int ind) {
    return R[ind][0];
  }
}
