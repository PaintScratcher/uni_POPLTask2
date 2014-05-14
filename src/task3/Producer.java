package task3;

public class Producer extends Thread {
	int num;
	private BoundedBuffer buffer;

	public Producer(BoundedBuffer buffer, int num) {
		this.buffer = buffer;
		this.num = num;
	}

	public void run() {
		System.out.println("Starting Producer Thread");
		//while(true){
		for (int i = 0; i<5; i++){
			try {
				buffer.write(num);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
