package task3;

public class Consumer extends Thread {
	private BoundedBuffer buffer;

	public Consumer(BoundedBuffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		System.out.println("Starting Consumer Thread");
		for (int i = 0; i<5; i++){
			try {
				int num = buffer.read();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
