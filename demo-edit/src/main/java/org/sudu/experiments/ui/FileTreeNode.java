package org.sudu.experiments.ui;

import java.util.Comparator;

public class FileTreeNode extends TreeNode {

  public static final FileTreeNode[] ch0 = new FileTreeNode[0];
  public static final Comparator<FileTreeNode> cmp = FileTreeNode::compare;

  FileTreeNode[] children = ch0;

  public FileTreeNode(String v, int d) {
    super(v, d);
  }

  public FileTreeNode(String v, int d, FileTreeNode[] ch) {
    super(v, d);
    setContent(ch);
  }

  public String name() {
    return value();
  }

  static int compare(FileTreeNode o1, FileTreeNode o2) {
    return o1.name().compareTo(o2.name());
  }

  protected void setContent(FileTreeNode[] ch) {
    children = ch;
  }

  protected boolean isOpened() {
    return arrow == allowDown;
  }

  TreeNode[] getModel() {
    TreeNode[] model = new TreeNode[count()];
    int idx = getModel(model, 0);
    if (idx != model.length) throw new RuntimeException();
    return model;
  }

  public int childrenLength() {
    return children.length;
  }

  private int count() {
    int n = 1;
    if (isOpened()) {
      for (FileTreeNode child : children)
        n += child.count();
    }
    return n;
  }

  public int countAll() {
    int n = 1;
    for (FileTreeNode child : children)
      n += child.countAll();
    return n;
  }

  private int getModel(TreeNode[] t, int idx) {
    t[idx++] = this;
    if (isOpened()) {
      for (FileTreeNode child : children) {
        idx = child.getModel(t, idx);
      }
    }
    return idx;
  }

  public void open() {
    arrowDown();
  }

  public void close() {
    arrowRight();
  }

  public Runnable toggle(Runnable update) {
    return () -> {
      if (isOpened()) close();
      else open();
      update.run();
    };
  }

  public void toggleOnCLick(Runnable updateRoot, boolean doubleClick) {
    Runnable toggle = toggle(updateRoot);
    onClickArrow(toggle);
    if (doubleClick) onDblClick(toggle);
    else onClick(toggle);
  }
}