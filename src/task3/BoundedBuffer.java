package task3;

import java.util.Arrays;

public class BoundedBuffer {

	public int[] buffer;
	int size;
	int read = 0;
	int write = 0;
	int elements = 0;

	public BoundedBuffer(int size){
		this.size = size;
		buffer = new int[size];
	}
	
	public synchronized void write(int value) throws InterruptedException{
		while(elements == size){
			wait();
		}
		buffer[write] = value;
		write = (write + 1) % size;
		elements ++;
		notifyAll();
		System.out.println("Wrote "+value + Arrays.toString(buffer) + "write:"+write);
	}
	
	public synchronized int read() throws InterruptedException{
		while(elements == 0){
			wait();
		}
		int value = buffer[read];
		buffer[read] = 0;
		read = (read + 1 ) % size;
		elements --;
		notifyAll();
		System.out.println("Read " + Integer.toString(value) + Arrays.toString(buffer)+"read:"+read);
		
		return value;
	}
}
