package task3;

public class BoundedBuffer {

	public int[] buffer; //Initialise the buffer
	int size;//Initialise the size of the buffer
	int read = 0;//Initialise the position of the read head
	int write = 0;//Initialise the position of the write head
	int elements = 0;//Initialise the number of elements in the buffer
	int waitingThreads = 0;//Initialise the number of threads that are waiting
	BinarySemaphore mutex = new BinarySemaphore(false);//Initialise the semaphore used for mutual exclusion
	BinarySemaphore waiting = new BinarySemaphore(true);//Initialise the semaphore used for waiting threads

	public BoundedBuffer(int size){ //Constructor
		this.size = size;//Set the size of the buffer to the size given in the call
		buffer = new int[size];
	}
	
	public void write(int value) throws InterruptedException{//Method used by producers
		
		while(elements == size){ //While the buffer is full
			
			//Implementing Wait()
			waitingThreads++;
			mutex.semSignal();
			waiting.semWait();
			mutex.semWait();
			waitingThreads--;
			waiting.semSignal();
		}
		
		buffer[write] = value;// Write the value to the buffer
		write = (write + 1) % size;//Increment the position of the write head
		elements ++;//Increase the number of elements in the buffer
		
		//Implements notifyAll()
		for(int i = 0; i<waitingThreads;i++){
			waiting.semSignal();
			waitingThreads--;
		}
		System.out.println("Wrote "+value + "write:"+write);//Debug printing (comment out for increased performance)
		mutex.semSignal();//Release the mutual exclusion
		
	}
	
	public int read() throws InterruptedException{//Method used by consumers
		mutex.semWait(); //Gain the mutual exclusion
		
		while(elements == 0){//While the buffer is empty
			//Implements wait()
			waitingThreads++;
			mutex.semSignal();
			waiting.semWait();
			mutex.semWait();
			waitingThreads--;
			waiting.semSignal();
		}
		
		int value = buffer[read];//Read the value from the buffer
		buffer[read] = 0;//Set the value to 0
		read = (read + 1 ) % size;//Increment the position of the read head
		elements --; //Decrease the number of elements in the buffer
		
		//Implementing notifyAll()
		for(int i = 0; i<waitingThreads;i++){
			waiting.semSignal();
			waitingThreads--;
		}
		System.out.println("Read " + Integer.toString(value) + "read:"+read);//Debug printing (comment out for increased performance)
		mutex.semSignal(); //Release the mutual exclusion
		
		return value;
	}
}
