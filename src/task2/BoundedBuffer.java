package task2;

public class BoundedBuffer {

	public int[] buffer; //Initialise the buffer
	int size; //Initialise the size of the buffer
	int read = 0; //Initialise the position of the read head
	int write = 0; //Initialise the position of the write head
	BinarySemaphore Mutex; //Initialise the semaphore used for mutual exclusion
	GeneralSemaphore EmptySpaces; //Initialise the semaphore used for storing the number of empty spaces
	GeneralSemaphore FullSpaces; //Initialise the semaphore used for storing the number of empty spaces

	public BoundedBuffer(int size){ //Constructor
		this.size = size;
		buffer = new int[size]; //Set the size of the buffer to the size given in the call
		Mutex = new BinarySemaphore(false); //Set the semaphores
		EmptySpaces = new GeneralSemaphore(size); 
		FullSpaces = new GeneralSemaphore(0);
	}
	
	public void write(int value) throws InterruptedException{ //Method used by producers
		
		EmptySpaces.semWait(); //Decrement the number of empty spaces
		Mutex.semWait(); //Gain the mutual exclusion over the buffer
		buffer[write] = value; // Write the value to the buffer
		write = (write + 1) % size; //Increment the position of the write head
		System.out.println("Wrote "+value + "write:"+write); //Debug printing (comment out for increased performance)
		Mutex.semSignal(); //Release the mutual exclusion
		FullSpaces.semSignal(); //Increase the number of used spaces
	}
	
	public int read() throws InterruptedException{ //Method used by consumers
		
		FullSpaces.semWait(); //Decrement the number of used spaces
		Mutex.semWait(); //Gain the mutual exclusion over the buffer
		int value = buffer[read]; //Read the value from the buffer
		buffer[read] = 0; //Set the value to 0
		read = (read + 1 ) % size; //Increment the position of the read head
		System.out.println("Read " + Integer.toString(value) +"read:"+read); //Debug printing (comment out for increased performance)
		Mutex.semSignal(); //Release the mutual exclusion 
		EmptySpaces.semSignal(); //Increase the number of free spaces
		
		return value;
	}
}
