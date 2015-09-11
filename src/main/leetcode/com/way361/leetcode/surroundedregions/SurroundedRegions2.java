package com.way361.leetcode.surroundedregions;

import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegions2 {
	int m, n;
	char[][] board;
	/** 保存广度遍历位置 */
	Queue<Integer> queue = new LinkedList<Integer>();

	public static void main(String[] args) {
		char[][] board = {{'X','X','X','X','X'}, 
					      {'X','O','O','X','O'},
					      {'X','X','O','X','X'},
					      {'X','O','X','X','O'}};
		System.out.println("before convert :");
		printBoard(board);
		
		new SurroundedRegions2().solve(board);
		
		System.out.println("after convert :");
		printBoard(board);
	}
	
	public void solve(char[][] board) {
		if (board == null || board.length == 0)
			return;
		this.board = board;
		m = board.length;
		n = board[0].length;

		for (int j = 0; j < n; j++) {
			bfs(0, j);
			bfs(m - 1, j);
		}

		for (int i = 1; i < m - 1; i++) {
			bfs(i, 0);
			bfs(i, n - 1);
		}

		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'O')
					board[i][j] = 'X';
				else if (board[i][j] == 'D')
					board[i][j] = 'O';
			}
	}
	
	/**
	 * 广度优先遍历
	 * @param x
	 * @param y
	 */
	void bfs(int x, int y) {
		fill(x, y);
		
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			int i = curr / m;
			int j = curr % m;
			
			fill(i - 1, j);
			fill(i + 1, j);
			fill(i, j - 1);
			fill(i, j + 1);
		}
	}
	
	/**
	 * 将该位置的O改为D，并且标识该位置，以供广度遍历
	 * @param x
	 * @param y
	 */
	void fill(int x, int y) {
		if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] != 'O')
			return;
		queue.offer(x * m + y);
		board[x][y] = 'D';
	}
	
	/**
	 * 打印
	 * @param board
	 */
	private static void printBoard(char[][] board) {
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

}
