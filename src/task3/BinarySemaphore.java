package task3;

public class BinarySemaphore {
	  private boolean locked = false;
	 
	  BinarySemaphore(boolean initial) {
	    locked = initial;
	  }
	 
	  public synchronized void semWait() throws InterruptedException {
	    while (locked) {
	      wait();
	    }
	    locked = true;
	  }
	 
	  public synchronized void semSignal() {
	    if (locked) {
	      notify();
	    }
	    locked = false;
	  }
	}
