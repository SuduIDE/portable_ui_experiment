package org.sudu.experiments.parser.common.stack.graph;

import org.junit.jupiter.api.Test;
import org.sudu.experiments.parser.common.stack.graph.edge.Edge;
import org.sudu.experiments.parser.common.stack.graph.node.StackNode;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Stack;

public class StackGraphTest {


  /*
  Check example from: https://drops.dagstuhl.de/opus/volltexte/2023/17778/pdf/OASIcs-EVCS-2023-8.pdf
   */
  @Test
  public void testResolve() {
    StackGraph graph = new StackGraph();
    graph.edges = new IdentityHashMap<>();
    graph.symbolStack = new Stack<>();
    graph.path = new ArrayList<>();

    var push1 = new StackNode("x", -1, StackNode.PUSH_NODE);
    var push2 = new StackNode(".", -1, StackNode.PUSH_NODE);
    var push3 = new StackNode("()", -1, StackNode.PUSH_NODE);
    var push4 = new StackNode("B", -1, StackNode.PUSH_NODE);
    addEdge(graph, push1, List.of(push2));
    addEdge(graph, push2, List.of(push3));
    addEdge(graph, push3, List.of(push4));

    var push5 = new StackNode("x", -1, StackNode.PUSH_NODE);
    var push6 = new StackNode(".", -1, StackNode.PUSH_NODE);
    var push7 = new StackNode("B", -1, StackNode.PUSH_NODE);
    addEdge(graph, push5, List.of(push6));
    addEdge(graph, push6, List.of(push7));

    var scope33 = new StackNode(null, -1, StackNode.SCOPE_NODE);
    var scope32 = new StackNode(null, -1, StackNode.SCOPE_NODE);
    var scope31 = new StackNode(null, -1, StackNode.SCOPE_NODE);
    addEdge(graph, push4, List.of(scope33));
    addEdge(graph, push7, List.of(scope32));
    addEdge(graph, scope33, List.of(scope32));
    addEdge(graph, scope32, List.of(scope31));

    var pop1 = new StackNode("B", -1, StackNode.POP_NODE);
    var pop2 = new StackNode("()", -1, StackNode.POP_NODE);
    var pop3 = new StackNode(".", -1, StackNode.POP_NODE);
    var pop4 = new StackNode(".", -1, StackNode.POP_NODE);
    addEdge(graph, scope31, List.of(pop1));
    addEdge(graph, pop1, List.of(pop2, pop3));
    addEdge(graph, pop2, List.of(pop4));

    var scope40 = new StackNode(null, -1, StackNode.SCOPE_NODE);
    var scope41 = new StackNode(null, -1, StackNode.SCOPE_NODE);
    addEdge(graph, pop3, List.of(scope40));
    addEdge(graph, pop4, List.of(scope41));
    addEdge(graph, scope41, List.of(scope40));

    var push8 = new StackNode(".", -1, StackNode.PUSH_NODE);
    var push9 = new StackNode(".", -1, StackNode.PUSH_NODE);
    var push10 = new StackNode("()", -1, StackNode.PUSH_NODE);
    var push11 = new StackNode("A", -1, StackNode.PUSH_NODE);
    addEdge(graph, scope40, List.of(push8));
    addEdge(graph, scope41, List.of(push9));
    addEdge(graph, push8, List.of(push11));
    addEdge(graph, push9, List.of(push10));
    addEdge(graph, push10, List.of(push11));

    var scope30 = new StackNode(null, -1, StackNode.SCOPE_NODE);
    addEdge(graph, push11, List.of(scope30));
    addEdge(graph, scope31, List.of(scope30));

    var push12 = new StackNode(".", -1, StackNode.PUSH_NODE);
    var push13 = new StackNode("a", -1, StackNode.PUSH_NODE);
    addEdge(graph, scope30, List.of(push12));
    addEdge(graph, push12, List.of(push13));

    var root1 = new StackNode(null, -1, StackNode.ROOT_NODE);
    var root2 = new StackNode(null, -1, StackNode.ROOT_NODE);
    addEdge(graph, push13, List.of(root1));
    addEdge(graph, root1, List.of(root2));

    var pop9 = new StackNode("a", -1, StackNode.POP_NODE);
    var pop10 = new StackNode(".", -1, StackNode.POP_NODE);
    var scope10 = new StackNode(null, -1, StackNode.SCOPE_NODE);
    var pop11 = new StackNode("A", -1, StackNode.POP_NODE);
    addEdge(graph, root2, List.of(pop9));
    addEdge(graph, pop9, List.of(pop10));
    addEdge(graph, pop10, List.of(scope10));
    addEdge(graph, scope10, List.of(pop11));

    var pop12 = new StackNode("()", -1, StackNode.POP_NODE);
    var pop13 = new StackNode(".", -1, StackNode.POP_NODE);
    var pop14 = new StackNode(".", -1, StackNode.POP_NODE);
    addEdge(graph, pop11, List.of(pop12, pop13));
    addEdge(graph, pop12, List.of(pop14));

    var scope21 = new StackNode(null, -1, StackNode.SCOPE_NODE);
    var scope20 = new StackNode(null, -1, StackNode.SCOPE_NODE);
    addEdge(graph, pop13, List.of(scope20));
    addEdge(graph, pop14, List.of(scope21));
    addEdge(graph, scope21, List.of(scope20));

    var pop15 = new StackNode("x", -1, StackNode.POP_NODE);
    addEdge(graph, scope20, List.of(pop15));

    graph.lift(push1);
    System.out.println();
  }

  public void addEdge(StackGraph graph, StackNode node, List<StackNode> to) {
    graph.edges.putIfAbsent(node, new ArrayList<>());
    graph.edges.get(node).addAll(to.stream().map(Edge::new).toList());
  }

}
