package com.way361.leetcode.surroundedregions;

/**
 * LeetCode | Surrounded Regions
 * @author xuefeihu
 * 
 * ��Ŀ��
	Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
	A region is captured by flipping all 'O's into 'X's in that surrounded region .
 *˼·��
	һ��ʼ�ҳ��Դ�������һ��һ��ĵ��ƣ����Ǹо�����Ƚ�ӷ�ס����Ժ���ѡ����BFS���㷨����������һȦ�ҵ��ַ�Ϊ
	O����ڣ�������BFS��Ϊ�˲��ظ�������ͬ�ĵ�Ԫ���ҹ�����һ������������ÿ����Ԫ����ʵ�״̬��
  ����1��DFS{com.way361.SurroundedRegions}
  ����2��BFS{com.way361.SurroundedRegions2}
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
	 * ת������
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
	 * ������ȱ���
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
	 * ��ӡ
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
