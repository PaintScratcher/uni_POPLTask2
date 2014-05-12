package task2;

import java.util.Arrays;

public class Producer extends Thread {
	int num;
	private BoundedBuffer buffer;

	public Producer(BoundedBuffer buffer, int num) {
		this.buffer = buffer;
		this.num = num;
	}

	public void run() {
		System.out.println("Starting Producer Thread with value: "+Integer.toString(num));
		while (true) {
			try {
				buffer.write(num);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
