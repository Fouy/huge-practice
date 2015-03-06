
/**
 * @author Jack Zhang
 * @version vb1.0
 * @Email virgoboy2004@163.com
 * @Date 2012-5-20
 */
public class Test1 {
	public static synchronized void staticX() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + "staticX.......................");
		}
	}

	public synchronized void x() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + "x.......................");
		}
	}

	public static void main(String[] args) {
		final Test1 test1 = new Test1();
		
//		for (int i = 0; i < 3; i++) {
//			new Thread(new Runnable() {
//				public void run() {
//					try {
//						test1.x();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}, "a" + i).start();
//		}

		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				public void run() {
					try {
						Test1.staticX();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "b" + i).start();
		}

	}
}
