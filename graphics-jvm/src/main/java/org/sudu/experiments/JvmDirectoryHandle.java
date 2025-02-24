package org.sudu.experiments;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

// JvmDirectoryHandle reads synchronously on worker threads
// and asynchronously on EDT
class JvmDirectoryHandle extends JvmFsHandle implements DirectoryHandle {

  JvmDirectoryHandle(String path, Path root, Executor bgWorker, Executor edt) {
    super(path, root, bgWorker, edt);
  }

  JvmDirectoryHandle(Path path, Path root, Executor bgWorker, Executor edt) {
    super(path, root, bgWorker, edt);
  }

  @Override
  protected JvmFsHandle ctor(Executor edt) {
    return new JvmDirectoryHandle(path, root, bgWorkerHi, edt);
  }

  @Override
  public void read(Reader reader) {
    var visitor = new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        if (exc != null)
          System.err.println(msg(dir, exc, "folder visiting error: "));
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(msg(file, exc, "file visit error: "));
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (attrs.isDirectory()) {
          var dh = new JvmDirectoryHandle(file, root, bgWorkerHi, edt);
          if (isOnWorker()) {
            reader.onDirectory(dh);
          } else {
            sendBack(dh, reader, edt);
          }

        }
        if (attrs.isRegularFile()) {
          var fh = new JvmFileHandle(file, root, bgWorkerHi, edt);
          if (isOnWorker()) {
            reader.onFile(fh);
          } else {
            sendBack(fh, reader, edt);
          }
        }
        return FileVisitResult.CONTINUE;
      }
    };

    if (isOnWorker()) {
      walk(reader, visitor);
    } else {
      bgWorkerHi.execute(() ->
          walk(reader, visitor)
      );
    }
  }

  private void walk(Reader reader, SimpleFileVisitor<Path> visitor) {
    try {
      Files.walkFileTree(
          path, EnumSet.noneOf(FileVisitOption.class), 1, visitor);
      if (isOnWorker()) {
        reader.onComplete();
      } else {
        sendComplete(reader, edt);
      }
    } catch (IOException e) {
      System.err.println("Files.walkFileTree error: " + e.getMessage());
    }
  }

  @Override
  public void remove(Runnable onComplete, Consumer<String> onError) {
    onError.accept("not implemented");
  }

  static String msg(Path dir, IOException exc, String title) {
    return title + exc.getMessage() + ", path = " + dir;
  }

  static void sendComplete(Reader reader, Executor edt) {
    edt.execute(reader::onComplete);
  }

  static void sendBack(JvmFileHandle fh, Reader reader, Executor edt) {
    edt.execute(() -> reader.onFile(fh));
  }

  static void sendBack(JvmDirectoryHandle dh, Reader reader, Executor edt) {
    edt.execute(() -> reader.onDirectory(dh));
  }

}
