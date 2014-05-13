package task3;

public class BinarySemaphore {
	  private boolean locked = false; 
	 
	  BinarySemaphore(boolean initial) { //Take the value given and use it to initially lock the semaphore
	    locked = initial;
	  }
	 
	  public synchronized void semWait() throws InterruptedException { //The Wait implementation
	    while (locked) {
	      wait();
	    }
	    locked = true;
	  }
	 
	  public synchronized void semSignal() { // The signal implementation
	    if (locked) {
	      notify();
	    }
	    locked = false;
	  }
	}
