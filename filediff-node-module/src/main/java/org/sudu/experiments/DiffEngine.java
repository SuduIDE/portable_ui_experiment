package org.sudu.experiments;

import org.sudu.experiments.js.*;
import org.sudu.experiments.js.node.*;
import org.sudu.experiments.update.DiffModelChannelUpdater;
import org.sudu.experiments.diff.folder.ItemFolderDiffModel;
import org.sudu.experiments.update.FileDiffChannelUpdater;
import org.sudu.experiments.update.FileEditChannelUpdater;
import org.teavm.jso.core.JSString;

public class DiffEngine implements DiffEngineJs {
  public static final boolean debug = false;

  final NodeWorkersPool pool;

  DiffEngine(JsArray<NodeWorker> worker) {
    pool = new NodeWorkersPool(worker);
  }

  @Override
  public void dispose() {
    JsHelper.consoleInfo("DiffEngine.dispose");
    pool.terminateAll();
    SshPool.terminate();
  }

  @Override
  public JsFolderDiffSession startFolderDiff(
      JsFolderInput leftPath, JsFolderInput rightPath, Channel channel
  ) {
    LoggingJs.info("Starting folder diff");
    boolean scanFileContent = true;
    DirectoryHandle leftDir = JsFolderInput.directoryHandle(leftPath);
    DirectoryHandle rightDir = JsFolderInput.directoryHandle(rightPath);

    if (leftDir == null)
      throw new IllegalArgumentException(
          "illegal leftPath argument " + JsHelper.jsToString(leftPath));
    if (rightDir == null)
      throw new IllegalArgumentException(
          "illegal rightPath argument " + JsHelper.jsToString(rightPath));

    LoggingJs.info("  DiffEngine Left: ".concat(leftDir.toString()));
    LoggingJs.info("  DiffEngine Right: ".concat(rightPath.toString()));

    ItemFolderDiffModel root = new ItemFolderDiffModel(null, "");
    root.setItems(leftDir, rightDir);

    DiffModelChannelUpdater updater = new DiffModelChannelUpdater(
        root,
        scanFileContent,
        pool, channel
    );
    updater.beginCompare();
    return new JsFolderDiffSession0(updater);
  }

  public JsFileDiffSession startFileDiff(
      JsFileInput leftInput, JsFileInput rightInput,
      Channel channel,
      JsFolderDiffSession parent
  ) {
    LoggingJs.info("Starting new file diff ...");

    boolean isLeftFile = JsFileInput.isPath(leftInput);
    boolean isRightFile = JsFileInput.isPath(rightInput);
    boolean isLeftText = !isLeftFile && JsFileInput.isContent(leftInput);
    boolean isRightText = !isLeftFile && JsFileInput.isContent(rightInput);

    FileHandle leftHandle = isLeftFile ?
        JsFileInput.fileHandle(leftInput, true) : null;
    FileHandle rightHandle = isRightFile ?
        JsFileInput.fileHandle(rightInput, true) : null;

    boolean validatedLeft = isLeftFile ? leftHandle != null : isLeftText;
    boolean validatedRight = isRightFile ? rightHandle != null : isRightText;

    if (!validatedLeft)
      LoggingJs.error(JsHelper.concat(
          "startFileDiff: left input is invalid ", leftInput));

    if (!validatedRight)
      LoggingJs.error(JsHelper.concat(
          "startFileDiff: right input is invalid ", rightInput));

    if (!validatedLeft || !validatedRight)
      return null;

    LoggingJs.info("  parent is FolderDiffSession: " +
        (parent instanceof JsFolderDiffSession0));

    DiffModelChannelUpdater parentUpdater =
        JsHelper.jsIf(parent) ? ((JsFolderDiffSession0) parent).updater : null;

    FileDiffChannelUpdater updater
        = new FileDiffChannelUpdater(channel, parentUpdater, pool);

    if (isLeftFile) {
      LoggingJs.info("  left file: ".concat(leftHandle.toString()));
      updater.compareLeft(leftHandle);
    } else {
      JSString leftStr = JsFileInput.getContent(leftInput);
      LoggingJs.info("  left is content, length = " + leftStr.getLength());
      updater.sendFileRead(true, leftStr, null);
    }

    if (isRightFile) {
      LoggingJs.info("  right file: ".concat(rightHandle.toString()));
      updater.compareRight(rightHandle);
    } else {
      JSString rightStr = JsFileInput.getContent(rightInput);
      LoggingJs.info("  right is content, length = " + rightStr.getLength());
      updater.sendFileRead(false, rightStr, null);
    }
    return new JsFileDiffSession0();
  }

  @Override
  public JsFileDiffSession startFileEdit(JSString input, Channel channel) {
    JsHelper.consoleInfo("Starting file edit ...");

    boolean isFile = JsFileInput.isPath(input);

    JSString str = isFile
        ? JsFileInput.path(input)
        : JsFileInput.getContent(input);

    JsHelper.consoleInfo("  input: ", str);

    FileEditChannelUpdater updater = new FileEditChannelUpdater(channel);
    if (isFile) {
      FileHandle handle = new NodeFileHandle(str);
      updater.beginCompare(handle);
    } else {
      updater.sendMessage(str, null);
    }
    return new JsFileDiffSession0();
  }

  @Override
  public JsDiffTestApi testApi() {
    return debug ? new DiffTestApi(pool) : null;
  }
}
