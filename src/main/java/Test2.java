public class Test2 {

	/**
	 * @param args add by zxx ,Dec 9, 2008
	 */
	public static void main(String[] args) {
		System.out.println(new Test2().test());
	}

	static int test() {
		int x = 1;
		try {
			return x;
		} finally {
			++x;
		}
	}

}
