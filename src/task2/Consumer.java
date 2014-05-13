package task2;

public class Consumer extends Thread {
	private BoundedBuffer buffer; //Initialise the buffer the consumer will use

	public Consumer(BoundedBuffer buffer) {
		this.buffer = buffer; //Set the buffer the consumer will use to the one given in the call
	}

	public void run() { //What will run when the thread is started
		System.out.println("Starting Consumer Thread");
		for (int i = 0; i<5; i++){ // Run for 5 iterations then stop
			try {
				int num = buffer.read(); // Read from the buffer
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
