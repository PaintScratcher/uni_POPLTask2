package task2;

import java.util.Arrays;

public class Consumer extends Thread {
	private BoundedBuffer buffer;

	public Consumer(BoundedBuffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		System.out.println("Starting Consumer Thread");
		//while (true) {
		for (int i = 0; i<5; i++){
			try {
				int num = buffer.read();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
