package com.way361.leetcode.clonegraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *  LeetCode | Clone Graph
 * @author xuefeihu
 * 
 * 题目：
	Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
	
	OJ's undirected graph serialization:
	Nodes are labeled uniquely.
	
	We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
	As an example, consider the serialized graph {0,1,2 # 1,2 # 2,2}.
	
	The graph has a total of three nodes, and therefore contains three parts as separated by #.
	
	First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
	Second node is labeled as 1. Connect node 1 to node 2.
	Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
	Visually, the graph looks like the following:
	
	       1
	      / \
	     /   \
	    0 --- 2
	         / \
	         \_/
 * 题解：
	这道题考察对图的遍历和利用HashMap拷贝的方法。
	对图的遍历就是两个经典的方法DFS和BFS。BFS经常用Queue实现，DFS经常用递归实现（可改为栈实现）。
	拷贝方法是用用HashMap，key存原始值，value存copy的值，用DFS,BFS方法遍历帮助拷贝neighbors的值。
 *
 */
public class CloneGraph {

	public static void main(String[] args) {
		UndirectedGraphNode srcNode = createGraphNode();
		UndirectedGraphNode resultNode = cloneGraph3(srcNode);
		System.out.println(resultNode);
	}

	/**
	 * 克隆无向图
	 * 方法1： BFS,就是先将头节点入queue，每一次queue出列一个node，然后检查这个node的所有的neighbors，
	 * 如果没visited过，就入队，并更新neighbor。 然后更新新的neighbor列表。
	 * @param graphNode
	 * @return
	 */
	public static UndirectedGraphNode cloneGraph1(UndirectedGraphNode graphNode) {
		// Note: The Solution object is instantiated only once and is reused by each test case.
		if (graphNode == null) {
			return graphNode;
		}
		UndirectedGraphNode result = new UndirectedGraphNode(graphNode.label);
		LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		queue.add(graphNode);
		Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		map.put(graphNode, result);

		while (!queue.isEmpty()) {
			UndirectedGraphNode nodeInQueue = queue.poll();
			ArrayList<UndirectedGraphNode> neighbors = nodeInQueue.neighbors;
			for (int i = 0; i < neighbors.size(); i++) {
				UndirectedGraphNode n1 = neighbors.get(i);
				if (map.containsKey(n1)) {
					map.get(nodeInQueue).neighbors.add(map.get(n1));
				} else {
					UndirectedGraphNode n1clone = new UndirectedGraphNode(n1.label);
					map.get(nodeInQueue).neighbors.add(n1clone);
					map.put(n1, n1clone);
					queue.add(n1);
				}
			}
		}
		return result;
	}
	
	/**
	 * 克隆无向图
	 * 方法2：DFS的递归操作如下，迭代复制neighbors
	 * @param graphNode
	 * @return
	 */
	public static UndirectedGraphNode cloneGraph2(UndirectedGraphNode graphNode) {
		if (graphNode == null)
			return null;

		HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		UndirectedGraphNode head = new UndirectedGraphNode(graphNode.label);
		hm.put(graphNode, head);

		DFS(hm, graphNode);// DFS
		return head;
	}
	
	/**
	 * 深度优先遍历
	 * @param hm
	 * @param graphNode
	 */
	public static void DFS(HashMap<UndirectedGraphNode, UndirectedGraphNode> hm, UndirectedGraphNode graphNode) {
		if (graphNode == null)
			return;

		for (UndirectedGraphNode aneighbor : graphNode.neighbors) {
			if (!hm.containsKey(aneighbor)) {
				UndirectedGraphNode newneighbor = new UndirectedGraphNode(
						aneighbor.label);
				hm.put(aneighbor, newneighbor);
				DFS(hm, aneighbor);// DFS
			}
			hm.get(graphNode).neighbors.add(hm.get(aneighbor));
		}
	}
	
	/**
	 * 克隆无向图
	 * 方法3：DFS的非递归方法，重点是把BFS中的queue换成stack，因为出列方法不一样了，所以遍历的线路就不一样了。
	 * @param graphNode
	 * @return
	 */
	public static UndirectedGraphNode cloneGraph3(UndirectedGraphNode graphNode) {
		if (graphNode == null)
			return null;

		HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		LinkedList<UndirectedGraphNode> stack = new LinkedList<UndirectedGraphNode>();
		UndirectedGraphNode head = new UndirectedGraphNode(graphNode.label);
		hm.put(graphNode, head);
		stack.push(graphNode);

		while (!stack.isEmpty()) {
			UndirectedGraphNode curnode = stack.pop();
			for (UndirectedGraphNode aneighbor : curnode.neighbors) {// check each neighbor
				if (!hm.containsKey(aneighbor)) {// if not visited,then push to stack
					stack.push(aneighbor);
					UndirectedGraphNode newneighbor = new UndirectedGraphNode(
							aneighbor.label);
					hm.put(aneighbor, newneighbor);
				}
				hm.get(curnode).neighbors.add(hm.get(aneighbor));
			}
		}
		return head;
	}
	
	/**
	 * 生成初始无向图
	 * @return
	 */
	private static UndirectedGraphNode createGraphNode() {
		UndirectedGraphNode node0 = new UndirectedGraphNode(0);
		UndirectedGraphNode node1 = new UndirectedGraphNode(1);
		UndirectedGraphNode node2 = new UndirectedGraphNode(2);
		node0.neighbors.add(node1);
		node0.neighbors.add(node2);
		node1.neighbors.add(node2);
		node2.neighbors.add(node2);
		return node0;
	}
	
}

/**
 * 无向图节点
 */
class UndirectedGraphNode {
	public int label;
	public ArrayList<UndirectedGraphNode> neighbors;

	public UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}

//	@Override
//	public String toString() {
//		String temp = "";
//		if(neighbors.size() > 0){
//			for(UndirectedGraphNode neighbor : neighbors){
//				
//			}
//		}
//		return "[label=" + label + ", neighbors=" + neighbors + "]";
//	}
	
}
