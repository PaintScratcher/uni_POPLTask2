package task2;

public class BoundedBuffer {

	public int[] buffer;
	int size;
	int read = 0;
	int write = 0;
	BinarySemaphore Mutex;
	GeneralSemaphore EmptySpaces;
	GeneralSemaphore FullSpaces;

	public BoundedBuffer(int size){
		this.size = size;
		buffer = new int[size];
		Mutex = new BinarySemaphore(false);
		EmptySpaces = new GeneralSemaphore(size);
		FullSpaces = new GeneralSemaphore(0);
	}
	
	public void write(int value) throws InterruptedException{
		
		EmptySpaces.semWait();
		Mutex.semWait();
		buffer[write] = value;
		write = (write + 1) % size;
		System.out.println("Wrote "+value + "write:"+write);
		Mutex.semSignal();
		FullSpaces.semSignal();
	}
	
	public int read() throws InterruptedException{
		
		FullSpaces.semWait();
		Mutex.semWait();
		int value = buffer[read];
		buffer[read] = 0;
		read = (read + 1 ) % size;
		System.out.println("Read " + Integer.toString(value) +"read:"+read);
		Mutex.semSignal();
		EmptySpaces.semSignal();
		
		return value;
	}
}
