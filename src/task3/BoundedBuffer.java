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
	BinarySemaphore hold = new BinarySemaphore(false);
	BinarySemaphore alive = new BinarySemaphore(false);

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
			hold.semWait();
			alive.semSignal();
			lock.semWait();
		}
		buffer[write] = value;
		write = (write + 1) % size;
		elements ++;
		
		//Implementing notifyAll()
		for(int i = 0; i<waitingThreads;i++){
			hold.semSignal();
			alive.semWait();
			waitingThreads--;
		}
		System.out.println("Wrote "+value + Arrays.toString(buffer) + "write:"+write);
	}
	
	public int read() throws InterruptedException{
		while(elements == 0){
			//Implementing Wait()
			waitingThreads++;
			lock.semSignal();
			hold.semWait();
			alive.semSignal();
			lock.semWait();
		}
		int value = buffer[read];
		buffer[read] = 0;
		read = (read + 1 ) % size;
		elements --;
		for(int i = 0; i<waitingThreads;i++){
			hold.semSignal();
			alive.semWait();
			waitingThreads--;
		}
		System.out.println("Read " + Integer.toString(value) + Arrays.toString(buffer)+"read:"+read);
		
		return value;
	}
}
