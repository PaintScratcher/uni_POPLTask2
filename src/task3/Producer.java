package task3;

public class Producer extends Thread {
	int num; // Initialise the number the producer will produce
	private BoundedBuffer buffer;	// Initialise the butter the producer will use

	public Producer(BoundedBuffer buffer, int num) { //Constructor
		this.buffer = buffer; //Set the buffer to the buffer passed in the call
		this.num = num; //Set the number to the number given in the call
	}

	public void run() { // What will run when a thread is created
		System.out.println("Starting Producer Thread");
		for (int i = 0; i<5; i++){ // 5 iterations (producer will produce 5 times then stop)
			try {
				buffer.write(num); //write to the buffer
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
