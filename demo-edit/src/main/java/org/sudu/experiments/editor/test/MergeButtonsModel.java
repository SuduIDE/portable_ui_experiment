package org.sudu.experiments.editor.test;

import org.sudu.experiments.diff.DiffTypes;
import org.sudu.experiments.diff.folder.FolderDiffModel;
import org.sudu.experiments.editor.worker.diff.DiffInfo;
import org.sudu.experiments.editor.worker.diff.DiffRange;
import org.sudu.experiments.math.Numbers;
import org.sudu.experiments.math.XorShiftRandom;

import java.util.function.BiConsumer;

public class MergeButtonsModel {
  public int[] lines;
  public Runnable[] actions;

  public MergeButtonsModel(int n) {
    actions = new Runnable[n];
    lines = new int[n];
  }

  public static MergeButtonsModel[] getModels(
      DiffInfo diffInfo,
      boolean leftReadonly,
      boolean rightReadonly,
      BiConsumer<DiffRange, Boolean> applyDiff
  ) {
    int n = 0;
    for (var range: diffInfo.ranges) if (range.type != DiffTypes.DEFAULT) n++;

    var left = new MergeButtonsModel(rightReadonly ? 0 : n);
    var right = new MergeButtonsModel(leftReadonly ? 0 : n);
    int i = 0;
    for (var range: diffInfo.ranges) {
      if (range.type == DiffTypes.DEFAULT) continue;
      if (!rightReadonly) {
        left.lines[i] = line(range.fromL, diffInfo.lineDiffsL.length);
        left.actions[i] = () -> applyDiff.accept(range, true);
      }
      if (!leftReadonly) {
        right.lines[i] = line(range.fromR, diffInfo.lineDiffsR.length);
        right.actions[i] = () -> applyDiff.accept(range, false);
      }
      i++;
    }
    return new MergeButtonsModel[]{left, right};
  }

  public static MergeButtonsModel[] getFolderModels(
      DiffInfo diffInfo,
      FolderDiffModel[] leftDiffs,
      FolderDiffModel[] rightDiffs,
      byte[] leftColors,
      byte[] rightColors,
      boolean leftReadonly,
      boolean rightReadonly,
      BiConsumer<FolderDiffModel, Boolean> applyDiff
  ) {
    MergeButtonsModel left, right;

    if (!rightReadonly) {
      int n = 0;
      for (int i = 0; i < diffInfo.lineDiffsL.length; i++) {
        var line = diffInfo.lineDiffsL[i];
        var model = leftDiffs[i];
        if (line.type != DiffTypes.DEFAULT && model.isCompared()) n++;
      }
      left = new MergeButtonsModel(n);
      for (int lineInd = 0, modelInd = 0; lineInd < diffInfo.lineDiffsL.length; lineInd++) {
        var leftLine = diffInfo.lineDiffsL[lineInd];
        var leftModel = leftDiffs[lineInd];
        if (leftLine.type == DiffTypes.DEFAULT || !leftModel.isCompared()) continue;
        left.lines[modelInd] = line(lineInd, diffInfo.lineDiffsL.length);
        left.actions[modelInd] = () -> applyDiff(leftModel, true, applyDiff);
        leftColors[lineInd] = (byte) diffInfo.lineDiffsL[lineInd].type;  //DiffTypes.FOLDER_ALIGN_DIFF_TYPE;
        modelInd++;
      }
    } else {
      left = new MergeButtonsModel(0);
    }

    if (!leftReadonly) {
      int m = 0;
      for (int i = 0; i < diffInfo.lineDiffsR.length; i++) {
        var line = diffInfo.lineDiffsR[i];
        var model = rightDiffs[i];
        if (line.type != DiffTypes.DEFAULT && model.isCompared()) m++;
      }
      right = new MergeButtonsModel(m);
      for (int lineInd = 0, modelInd = 0; lineInd < diffInfo.lineDiffsR.length; lineInd++) {
        var rightLine = diffInfo.lineDiffsR[lineInd];
        var rightModel = rightDiffs[lineInd];
        if (rightLine.type == DiffTypes.DEFAULT || !rightModel.isCompared()) continue;
        right.lines[modelInd] = line(lineInd, diffInfo.lineDiffsR.length);
        right.actions[modelInd] = () -> applyDiff(rightModel, false, applyDiff);
        rightColors[lineInd] = (byte) diffInfo.lineDiffsR[lineInd].type;  //DiffTypes.FOLDER_ALIGN_DIFF_TYPE;
        modelInd++;
      }
    } else {
      right = new MergeButtonsModel(0);
    }
    return new MergeButtonsModel[]{left, right};
  }

  private static void applyDiff(
      FolderDiffModel model,
      boolean left,
      BiConsumer<FolderDiffModel, Boolean> applyDiff
  ) {
    if (applyDiff == null) return;
    applyDiff.accept(model, left);
  }

  private static int line(int line, int docLen) {
    return Numbers.clamp(0, line, docLen - 1);
  }

  public static class TestModel extends MergeButtonsModel {
    public TestModel(int docLines) {
      super(docLines);
      int n = docLines / 4;
      XorShiftRandom rand = new XorShiftRandom();
      int space = docLines / (1 + n);
      for (int i = 0, pi = 0; i < n; i++) {
        lines[i] = pi;
        actions[i] = action(pi);
        pi += 1 + rand.nextInt(space);
      }
    }
    static Runnable action(int pi) {
      return () -> System.out.println("Runnable #" + pi);
    }
  }
}
