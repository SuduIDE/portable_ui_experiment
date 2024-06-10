package org.sudu.experiments;

import org.sudu.experiments.diff.DiffModelBuilder;
import org.sudu.experiments.editor.worker.TestJobs;
import org.sudu.experiments.js.*;
import org.sudu.experiments.js.node.Fs;
import org.sudu.experiments.js.node.NodeDirectoryHandle;
import org.teavm.jso.JSObject;
import org.teavm.jso.core.JSNumber;
import org.teavm.jso.core.JSString;
import org.teavm.jso.typedarrays.Int32Array;
import org.teavm.jso.typedarrays.Uint16Array;

import java.util.Arrays;

import static org.sudu.experiments.editor.worker.EditorWorker.array;

public class DiffEngine implements DiffEngineJs {
  final NodeWorkersPool pool;

  DiffEngine(JsArray<NodeWorker> worker) {
    pool = new NodeWorkersPool(worker);
  }

  @Override
  public void dispose() {
    pool.terminateAll();
  }

  @Override
  public Promise<JSString> fib(int n) {
    return Promise.create((postResult, postError) -> {
      pool.sendToWorker(
          result -> {
            int[] intResult = array(result, 0).ints();
            postResult.f(JSString.valueOf(
                "r: " + intResult[0] + ", time: " + intResult[1]
            ));
          }, TestJobs.fibonacci, new int[]{n}
      );
    });
  }

  @Override
  public void startFolderDiff(JSString leftPath, JSString rightPath, Channel channel) {
//    NodeFileHandle fh = new NodeFileHandle(leftPath);
//    NodeFileHandle fh = new NodeFileHandle(rightPath);

    JsHelper.consoleInfo("Starting folder diff ");
    JsHelper.consoleInfo("\t leftPath ", leftPath);
    JsHelper.consoleInfo("\t rightPath ", rightPath);
    channel.setOnMessage(
        m -> {
          JsHelper.consoleInfo("channel onMessage ", m);
          getArray(m);
        }
    );
    JsArray<JSObject> array = JsArray.create();

    {
      String string = "sss";
      int[] data = new int[10];
      char[] dataChar = string.toCharArray();


      /* 0 */array.push(JSNumber.valueOf(20));
      /* 1 */array.push(JSString.valueOf(string));
      /* 2 */array.push(JsMemoryAccess.bufferView(data));
      /* 3 */array.push(JsMemoryAccess.bufferView(dataChar));
    }

    channel.sendMessage(array);

//    new DiffModelBuilder(null, pool, true);

//    getArray(array);
    // JS -> [Mp.alg1] -> Workers -> [WorkerJob] -> [Mp.alg1] -> JS


  }

  void getArray(JsArray<JSObject> array) {
    int i20 = array.get(0).<JSNumber>cast().intValue();
    String sss = array.get(1).<JSString>cast().stringValue();

    int[] intArray = JsMemoryAccess.toJavaArray(
        array.get(2).<Int32Array>cast());
    char[] chars = JsMemoryAccess.toJavaArray(
        array.get(3).<Uint16Array>cast());

    JsHelper.consoleInfo("i = " + i20);
    JsHelper.consoleInfo("sss = " + sss);
    JsHelper.consoleInfo("intArray = " + Arrays.toString(intArray));
    JsHelper.consoleInfo("charArray = " + Arrays.toString(chars));
  }

  @Override
  public void testFS(JSString path, JsFunctions.Runnable onComplete) {
    FsTest.fsTest(path, onComplete);
  }

  @Override
  public void testFS2(JSString path1, JSString path2, JsFunctions.Runnable onComplete) {
    if (notDir(path1) || notDir(path2)) {
      onComplete.f();
      return;
    }

    JsHelper.consoleInfo("testFS2 path1 = ", path1);
    JsHelper.consoleInfo("testFS2 path2 = ", path2);

    NodeDirectoryHandle dir1 = new NodeDirectoryHandle(path1);
    NodeDirectoryHandle dir2 = new NodeDirectoryHandle(path2);

    new FolderDiffTestNode(
        dir1, dir2, pool, onComplete
    ).scan();
  }

  static boolean notDir(JSString path) {
    if (!Fs.isDirectory(path)) {
      JsHelper.consoleError("path is not a directory ", path);
      return true;
    }
    return false;
  }
}