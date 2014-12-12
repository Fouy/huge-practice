package com.way361.leetcode.wordladdertwo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.way361.structure.list.sequence.SeqList;

/**
 * 图的邻接表表示
 * @author xuefeihu
 *
 */
public class MyAdjListGraph<T> {
	public List<MyVertex<T>> vertexList;	//顶点顺序表
	
	public MyAdjListGraph (int size){
		size = size < 10 ? 10 : size;
		this.vertexList = new ArrayList<MyVertex<T>>(size);
	}
	
	/**
	 * 向图中插入顶点
	 * @param x 顶点数据
	 * @return 顶点个数
	 */
	public int insertVertex(T x){
		this.vertexList.add(new MyVertex<T>(x));
		return this.vertexList.size()-1;
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 */
	public void insertEdge(int i, int j){
		if(i>=0 && i<vertexList.size() && j>=0 && j<vertexList.size() && i!=j){
			MyEdge edge = new MyEdge(i, j);
			LinkedList<MyEdge> adjLink = vertexList.get(i).adjLink;
			adjLink.add(new MyEdge(i, j));
			
			
		}
	}
	
	
}

/**
 * 图的顶点元素
 */
class MyVertex<T> {
	public T data;		//顶点的数据
	public LinkedList<MyEdge> adjLink;	//顶点邻接表表示的边 
	
	public MyVertex(T data) {
		this.data = data;
		this.adjLink = new LinkedList<MyEdge>();
	}

	@Override
	public String toString() {
		return "MyVertex [data=" + data + ", adjLink=" + adjLink + "]";
	}
	
}

/**
 * 图的边
 */
class MyEdge implements Comparable<MyEdge> {
	public int start;	//开始顶点号
	public int dest;	//指向顶点号

	public MyEdge(int start, int dest) {
		this.start = start;
		this.dest = dest;
	}

	@Override
	public String toString() {
		return "MyEdge [start=" + start + ", dest=" + dest + "]";
	}

	@Override
	public int compareTo(MyEdge e) {
		if(this.start != e.start)
			return this.start - e.start;
		return this.dest - e.dest;
	}
	
}



