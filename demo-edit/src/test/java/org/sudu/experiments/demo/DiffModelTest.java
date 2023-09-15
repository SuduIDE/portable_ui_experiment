package org.sudu.experiments.demo;

import org.junit.jupiter.api.Test;
import org.sudu.experiments.demo.worker.diff.DiffInfo;
import org.sudu.experiments.demo.worker.diff.DiffUtils;
import org.sudu.experiments.demo.worker.parser.JavaParser;
import org.sudu.experiments.demo.worker.parser.ParserUtils;
import org.sudu.experiments.diff.DiffModel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DiffModelTest {

  public static void main(String[] args) {
    new DiffModelTest().compareDocuments(true);
  }

  @Test
  public void compareDocuments() {
    compareDocuments(false);
  }

  public void compareDocuments(boolean printResults) {
    var docL = parse(readFile("ClassR.java"));
    var docR = parse(readFile("ClassL.java"));

    DiffModel model = new DiffModel();
    char[] charsL = docL.getChars();
    int[] intsL = DiffUtils.makeIntervals(docL);
    char[] charsR = docR.getChars();
    int[] intsR = DiffUtils.makeIntervals(docR);

    int[] res = model.findDiffs(charsL, intsL, charsR, intsR);

    DiffInfo info = DiffUtils.readDiffInfo(res);
    if (printResults) DiffUtils.printInfo(info, docL, docR);
  }

  private Document parse(String text) {
    List<Object> result = new ArrayList<>();
    char[] chars = text.toCharArray();
    int[] ints;
    JavaParser.parse(chars, result);
    ints = (int[]) result.get(0);
    return ParserUtils.makeDocument(ints, chars);
  }

  private String readFile(String filename) {
    try {
      var url = getClass().getClassLoader().getResource(filename);
      if (url == null) throw new IllegalArgumentException("Illegal resource name: " + filename);
      return Files.readString(Path.of(url.toURI()));
    } catch (URISyntaxException | IOException e) {
      throw new RuntimeException(e);
    }
  }

}
