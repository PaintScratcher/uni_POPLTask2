package task3;

import java.util.Arrays;

public class BoundedBuffer {

	public int[] buffer;
	int size;
	int read = 0;
	int write = 0;
	int elements = 0;
	int waitingThreads = 0;
	BinarySemaphore lock = new BinarySemaphore(false);
	BinarySemaphore waiting = new BinarySemaphore(true);

	public BoundedBuffer(int size){
		this.size = size;
		buffer = new int[size];
	}
	
	public void write(int value) throws InterruptedException{
		lock.semWait();
		
		while(elements == size){
			//Implementing Wait()
			waitingThreads++;
			lock.semSignal();
			waiting.semWait();
			lock.semWait();
			waitingThreads--;
			waiting.semSignal();
		}
		
		buffer[write] = value;
		write = (write + 1) % size;
		elements ++;
		
		//Implementing notifyAll()
		for(int i = 0; i<waitingThreads;i++){
			waiting.semSignal();
			waitingThreads--;
		}
		System.out.println("Wrote "+value + Arrays.toString(buffer) + "write:"+write);
		lock.semSignal();
		
	}
	
	public int read() throws InterruptedException{
		lock.semWait();
		while(elements == 0){
			//Implementing Wait()
			waitingThreads++;
			lock.semSignal();
			waiting.semWait();
			lock.semWait();
			waitingThreads--;
			waiting.semSignal();
		}
		
		
		int value = buffer[read];
		buffer[read] = 0;
		read = (read + 1 ) % size;
		elements --;
		for(int i = 0; i<waitingThreads;i++){
			waiting.semSignal();
			waitingThreads--;
		}
		System.out.println("Read " + Integer.toString(value) + Arrays.toString(buffer)+"read:"+read);
		lock.semSignal();
		
		
		return value;
	}
}
