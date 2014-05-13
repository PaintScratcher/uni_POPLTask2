package task2;

public class GeneralSemaphore {
	private int value;
	public GeneralSemaphore (int initial) {
		value = initial;
	}
	
	synchronized public void semWait() throws InterruptedException { //The Wait implementation
		while (value == 0) wait();
		--value;
	}
	
	synchronized public void semSignal() { // The signal implementation
		++value;
		notify();
	}
	
}
