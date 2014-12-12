package com.way361.leetcode.surroundedregions;

/**
 * LeetCode | Surrounded Regions
 * @author xuefeihu
 * 
 * 题目：
	Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
	A region is captured by flipping all 'O's into 'X's in that surrounded region .
 *思路：
	一开始我尝试从外向里一层一层的递推，但是感觉代码比较臃肿。所以后来选择了BFS的算法，即从最外一圈找到字符为
	O的入口，向内做BFS。为了不重复遍历相同的单元格，我构造了一个矩阵来保存每个单元格访问的状态。
  方法1：DFS{com.way361.SurroundedRegions}
  方法2：BFS{com.way361.SurroundedRegions2}
 */
public class SurroundedRegions {

	int m, n;
	char[][] board;
	
	public static void main(String[] args) {
		char[][] board = {{'X','X','X','X','X'}, 
			      		  {'X','O','O','X','O'},
			      		  {'X','X','O','X','X'},
			      		  {'X','O','X','X','O'}};
		System.out.println("before convert :");
		printBoard(board);
		
		new SurroundedRegions().solve(board);
		
		System.out.println("after convert :");
		printBoard(board);
	}

	/**
	 * 转换矩阵
	 * @param board
	 */
	public void solve(char[][] board) {
		if (board == null || board.length == 0)
			return;
		this.board = board;
		m = board.length;
		n = board[0].length;

		for (int j = 0; j < n; j++) {
			dfs(0, j);
			dfs(m - 1, j);
		}
		System.out.println("after convert1 :");
		printBoard(board);
		
		for (int i = 1; i < m - 1; i++) {
			dfs(i, 0);
			dfs(i, n - 1);
		}
		System.out.println("after convert2 :");
		printBoard(board);
		
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'O')
					board[i][j] = 'X';
				else if (board[i][j] == 'D')
					board[i][j] = 'O';
			}
	}

	/**
	 * 深度优先遍历
	 * @param x
	 * @param y
	 */
	void dfs(int x, int y) {
		if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] != 'O')
			return;
		board[x][y] = 'D';
		dfs(x - 1, y);
		dfs(x + 1, y);
		dfs(x, y - 1);
		dfs(x, y + 1);
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
