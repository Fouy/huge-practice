package com.way361.leetcode.copylistwithrandompointer;

import java.util.HashMap;

/**
 * LeetCode | Copy List with Random Pointer
 * @author xuefeihu
 *
 * ��Ŀ��
   		A linked list is given such that each node contains an additional 
   		random pointer which could point to any node in the list or null.
 *		Return a deep copy of the list.
 * ˼·��
 * 		����1�����´����Ľڵ�ƴ�ӵ�ԭ��list�У���random�������֮���ٽ�������Ϊ������
 *		����2������ȿ��������㣬��ʱ���¾�����ڵ����һ��HashMap<RandomListNode, RandomListNode>
 *			�У���һ�ο���random��ʱ��ֱ�Ӵ�map��ȡ���´����������㼴�ɡ�
 */
public class CopyListwithRandomPointer {
	
	public static void main(String[] args) {
		RandomListNode list = createNewList();
		RandomListNode copy = copyRandomList1(list);
		System.out.println(copy);
	}
	
	/** ����1
	 * ��ȿ�������
	 * @param head ͷָ��
	 * @return ������
	 */
	public static RandomListNode copyRandomList1(RandomListNode head) {
		if (head == null)
			return null;
		RandomListNode p = head;

		// copy every node and insert to list
		while (p != null) {
			RandomListNode copy = new RandomListNode(p.label);
			copy.next = p.next;
			p.next = copy;
			p = copy.next;
		}

		// copy random pointer for each new node
		p = head;
		while (p != null) {
			if (p.random != null)
				p.next.random = p.random.next;
			p = p.next.next;
		}

		// break list to two
		p = head;
		RandomListNode newHead = head.next;
		while (p != null) {
			RandomListNode temp = p.next;
			p.next = temp.next;
			if (temp.next != null)
				temp.next = temp.next.next;
			p = p.next;
		}

		return newHead;
	}
	
	/** ����2
	 * ��ȿ�������
	 * @param head ͷָ��
	 * @return ������
	 */
	public RandomListNode copyRandomList2(RandomListNode head) {
		if (head == null)
			return null;
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode newHead = new RandomListNode(head.label);

		RandomListNode p = head;
		RandomListNode q = newHead;
		map.put(head, newHead);
		
		//copy the node to the new list
		p = p.next;
		while (p != null) {
			RandomListNode temp = new RandomListNode(p.label);
			map.put(p, temp);
			q.next = temp;
			q = temp;
			p = p.next;
		}

		//set the node.random from the map
		p = head;
		q = newHead;
		while (p != null) {
			if (p.random != null)
				q.random = map.get(p.random);
			else
				q.random = null;

			p = p.next;
			q = q.next;
		}

		return newHead;
	}
	
	/**
	 * ��ʼ������
	 * @return
	 */
	public static RandomListNode createNewList(){
		RandomListNode head = new RandomListNode(0);
		RandomListNode p = head;
		RandomListNode q = head;
		int position = 0;
		
		for(int i = 1; i < 5; i ++){
			p.next = new RandomListNode(i);
			p = p.next;
		}
		
		p = head;
		for(int i = 0; i < 5; i++){
			position = (int)(Math.random()*10);
			if(position > 0 && position < 10){
				q = head;
				while(q != null){
					if(q.label == position) 
						break;
					q = q.next;
				}
				p.random = q;
			}
			p = p.next;
		}
		return head;
	}
	
}

/**
 * ����ڵ�
 */
class RandomListNode {
	public int label;
	public RandomListNode next, random;

	RandomListNode(int x) {
		this.label = x;
	}
}

