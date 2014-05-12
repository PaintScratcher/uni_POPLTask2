package task2;

public class BinarySemaphore {
	  private boolean locked = false;
	 
	  BinarySemaphore(int initial) {
	    locked = (initial == 0);
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
