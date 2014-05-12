package task2;

public class BoundedBuffer {

	BinarySemaphore BS = new BinarySemaphore(0);
	GeneralSemaphore GS1 = new GeneralSemaphore(0);
	GeneralSemaphore GS2 = new GeneralSemaphore(0);
	
	private int[] buffer;
	int size;
	int read = 1;
	int write = 0;

	public BoundedBuffer(int size){
		this.size = size;
		buffer = new int[size];
	}
	public void write(int value) throws InterruptedException{
		GS1.waitSignal();
		BS.waitSignal();
		buffer[write] = value;
		write = (write++ % size);
		BS.notifySignal();
		GS1.notifySignal();
	}
	
	public int read() throws InterruptedException{
		GS1.waitSignal();
		BS.waitSignal();
		int value = buffer[read];
		read = (read++ % size);
		BS.notifySignal();
		GS1.notifySignal();
		buffer[read] = 0;
		return value;
		
	}
}
