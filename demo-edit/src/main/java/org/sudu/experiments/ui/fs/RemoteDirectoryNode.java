package org.sudu.experiments.ui.fs;

import org.sudu.experiments.ui.FileTreeNode;

public class RemoteDirectoryNode extends RemoteFileTreeNode {

  public int folderCnt;

  public RemoteDirectoryNode(
      String path,
      RemoteHandle handle,
      int depth
  ) {
    super(path, handle, depth);
    iconFolder();
    onClick = this::onClick;
    close();
  }

  public void onClick() {
    if (isOpened()) closeDir();
    else openDir();
    handle.sendModel();
  }

  public void openDir() {
    doOpen();
    var opposite = handle.getOppositeDir(this);
    if (opposite != null) opposite.doOpen();
    if (children.length == 1 && children[0] instanceof RemoteDirectoryNode singleDir) singleDir.openDir();
  }

  public void closeDir() {
    doClose();
    var opposite = handle.getOppositeDir(this);
    if (opposite != null) opposite.doClose();
  }

  public void doOpen() {
    handle.openDir(this);
    handle.updateView();
    super.open();
  }

  public void doClose() {
    handle.closeDir(this);
    handle.updateView();
    super.close();
  }

  public boolean isOpened() {
    return children != FileTreeNode.ch0;
  }

  @Override
  public boolean isClosed() {
    return children == FileTreeNode.ch0;
  }

  public void setChildren(FileTreeNode[] children) {
    this.children = children;
  }

  public RemoteDirectoryNode findSubDir(String path) {
    return (RemoteDirectoryNode) FileTreeNode.bs(children, 0, folderCnt, path);
  }

  public RemoteFileNode findSubFile(String path) {
    return (RemoteFileNode) FileTreeNode.bs(children, folderCnt, childrenLength(), path);
  }

  @Override
  protected void defaultIcon() {
    if (isOpened()) iconFolderOpened();
    else iconFolder();
  }
}