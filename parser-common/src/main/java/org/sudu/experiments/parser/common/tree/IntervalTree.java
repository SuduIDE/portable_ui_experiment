package org.sudu.experiments.parser.common.tree;

import org.sudu.experiments.parser.Interval;
import org.sudu.experiments.parser.ParserConstants;
import org.sudu.experiments.parser.common.graph.node.ScopeNode;

import java.util.*;

public class IntervalTree {

  public final IntervalNode root;
  private boolean updateFlag = false;

  private static final Comparator<IntervalNode> INTERVAL_NODE_CMP = (a, b) -> compare(a.interval, b.interval);

  private static int compare(Interval a, Interval b) {
    return a.start == b.start
        ? Integer.compare(b.stop, a.stop)
        : Integer.compare(a.start, b.start);
  }

  public IntervalTree(Interval interval) {
    this(new IntervalNode(interval));
  }

  public IntervalTree(IntervalNode node) {
    this.root = node;
  }

  public static IntervalTree singleInterval(int start, int stop, int intervalType) {
    return new IntervalTree(new Interval(start, stop, intervalType));
  }

  public IntervalNode getReparseNode() {
    return getReparseNode(root);
  }

  private IntervalNode getReparseNode(IntervalNode curNode) {
    if (curNode.needReparse) return curNode;
    for (var subNode: curNode.children) {
      var result = getReparseNode(subNode);
      if (result != null) return result;
    }
    return null;
  }

  public void replaceInterval(Interval from, IntervalNode newTree) {
    var replaceNode = replaceIntervalRec(root, from);
    if (replaceNode == null) return;

    List<ScopeNode> newScopes = newTree.scope != null ? newTree.scope.children : List.of();
    var newNodes = newTree.children;

    if (replaceNode == root) {
      if (this.root.scope == null) {
        this.root.scope = newTree.scope;
      }
      newNodes.forEach(it -> it.parent = root);
      newScopes.forEach(it -> it.parent = root.scope);
      this.root.children = newNodes;
      if (this.root.scope != null) this.root.scope.children = newScopes;
      this.root.needReparse = false;
      return;
    }
    replaceIntervalNode(replaceNode, newNodes);
    replaceScopeNode(replaceNode, newScopes);
  }

  private static void replaceIntervalNode(IntervalNode replaceNode, List<IntervalNode> newNodes) {
    if (!newNodes.isEmpty() && replaceNode.parent != null) {
      var parent = replaceNode.parent;
      newNodes.forEach(it -> it.parent = parent);
      int nodeInd = parent.children.indexOf(replaceNode);
      if (nodeInd == -1) {
        parent.children.addAll(newNodes);
      } else {
        parent.children.remove(nodeInd);
        parent.children.addAll(nodeInd, newNodes);
      }
    }
  }

  public void replaceScopeNode(IntervalNode replaceNode, List<ScopeNode> newScopes) {
    if (replaceNode.scope == null) return;
    var parentScope = replaceNode.scope.parent;
    newScopes.forEach(it -> it.parent = parentScope);
    int scopeInd = parentScope.children.indexOf(replaceNode.scope);
    if (scopeInd == -1) {
      parentScope.children.addAll(newScopes);
    } else {
      parentScope.children.remove(scopeInd);
      parentScope.children.addAll(scopeInd, newScopes);
    }
  }

  private IntervalNode replaceIntervalRec(IntervalNode curNode, Interval oldNode) {
    if (curNode.interval.bordersEqual(oldNode)) {
      for (var subInterval: curNode.children) {
        var result = replaceIntervalRec(subInterval, oldNode);
        if (result != null) return result;
      }
      return curNode;
    } else {
      for (var subInterval: curNode.children) {
        if (subInterval.containsInterval(oldNode.start, oldNode.stop)) {
          var result = replaceIntervalRec(subInterval, oldNode);
          if (result != null) return result;
        }
      }
      return null;
    }
  }

  public List<Interval> toList() {
    List<Interval> result = new ArrayList<>();
    toListRec(root, result);
    return result;
  }

  private void toListRec(IntervalNode curNode, List<Interval> result) {
    result.add(curNode.interval);
    for (var subInterval: curNode.children)
      toListRec(subInterval, result);
  }

  public void makeInsertDiff(int start, int size) {
    if (root.children.size() == 0) {
      root.updateStop(start + size);
      root.needReparse = true;
    } else {
      updateFlag = false;
      makeInsertDiff(root, start, size);
    }
  }

  private void makeInsertDiff(IntervalNode curNode, int start, int size) {
    if (curNode.getStop() < start) return;  // Interval stop lies to the left of start of insertion
    if (curNode.getStart() > start) {       // Interval start lies to the right of insertion
      curNode.updateStart(size);
      curNode.updateStop(size);
      for (var subInterval: curNode.children)
        makeInsertDiff(subInterval, start, size);
    } else if (curNode.between(start) || (!updateFlag && curNode.getStop() == start)) {    // Interval contains insertion point
      curNode.updateStop(size);
      if (curNode.getStart() == start && updateFlag) curNode.updateStart(size);

      for (var subInterval: curNode.children) makeInsertDiff(subInterval, start, size);

      if (!updateFlag) {
        curNode.needReparse = true;
        updateFlag = true;
      }
    }
  }

  public void makeDeleteDiff(int start, int size) {
    updateFlag = false;
    makeDeleteDiff(root, start, size);
  }

  private void makeDeleteDiff(IntervalNode curNode, int start, int size) {
    if (curNode.getStop() < start) return;            // delete interval lies to the left of current
    else if (curNode.getStart() > start + size) {     // delete interval lies to the right of current
      curNode.updateStart(-size);
      curNode.updateStop(-size);
      for (var subInterval: curNode.children)
        makeDeleteDiff(subInterval, start, size);
      curNode.children = updateChildren(curNode.children);
    } else if (curNode.containsInInterval(start, start + size)) {   // cur node contains in delete interval
      if (curNode == root) {
        curNode.setStart(0);
        curNode.setStop(0);
        if (curNode.scope != null) curNode.scope.children.clear();
      } else {
        curNode.setStart(-1);
        curNode.setStop(-1);
        if (curNode.scope != null) {
          curNode.scope.removeInParent();
          curNode.scope = null;
        }
      }
      curNode.children.clear();
      curNode.needReparse = true;
      updateFlag = true;
    } else {
      boolean containsStart = curNode.between(start);
      boolean containsEnd = curNode.between(start + size);
      boolean noChildren = curNode.children.size() == 0;

      if (containsStart && containsEnd) {
        curNode.updateStop(-size);
      } else if (containsStart) {
        curNode.setStop(start);
      } else if (containsEnd) {
        curNode.setStart(start);
        curNode.updateStop(-size);
      } else return;
      if (noChildren || checkNeedReparse(curNode, start)) curNode.needReparse = true;
      else {
        for (var subInterval: curNode.children)
          makeDeleteDiff(subInterval, start, size);
      }
      curNode.children = updateChildren(curNode.children);
    }
  }

  private boolean checkNeedReparse(IntervalNode curNode, int start) {
    if (curNode.children.size() == 0) return true;
    if (curNode.getType() == ParserConstants.IntervalTypes.Java.TYPE_DECL) {
      var first = curNode.children.get(0);
      return first.getStart() > start;
    }
    return false;
  }

  private List<IntervalNode> updateChildren(List<IntervalNode> children) {
    var result = new ArrayList<IntervalNode>();
    IntervalNode curNode = null;

    children.sort(INTERVAL_NODE_CMP);
    for (var interval: children) {
      if (interval.getStart() == interval.getStop()) continue;
      if (!interval.needReparse) {
        if (curNode != null) {
          result.add(curNode);
          curNode = null;
        }
        result.add(interval);
      } else {
        if (curNode == null) curNode = interval;
        else {
          curNode = curNode.merge(interval);
          if (interval.scope != null) interval.scope.removeInParent();
        }
      }
    }
    if (curNode != null) result.add(curNode);
    return result;
  }

  public void printIntervals(String source) {
    printStructure(source);
    System.out.println();
  }

  public void printStructure(String source) {
    printStructure(0, root, source);
  }

  private void printIntervals(String source, IntervalNode curNode) {
    System.out.println("NeedReparse: " + curNode.needReparse);
    System.out.println("Int: " + curNode.interval);
    System.out.println("________");
    System.out.println(source.substring(curNode.getStart(), curNode.getStop()));
    System.out.println("___________________________________");
    for (var subNode: curNode.children) {
      printIntervals(source, subNode);
    }
  }

  private void printStructure(int depth, IntervalNode curNode, String source) {
    String intervalString;
    if (curNode.getStop() - curNode.getStart() < 43) {
      intervalString = source.substring(curNode.getStart(), curNode.getStop());
    } else {
      intervalString = source.substring(curNode.getStart(), curNode.getStart() + 20) + "..." + source.substring(curNode.getStop() - 20, curNode.getStop());
    }
    intervalString = intervalString.replace("\n", "\\n");
    System.out.println(depth + " " + curNode + "\t" + intervalString);
    for (var subNode: curNode.children) {
      printStructure(depth + 1, subNode, source);
    }
  }

}

