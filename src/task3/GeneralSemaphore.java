package task3;

public class GeneralSemaphore {
	private int value;
	public GeneralSemaphore (int initial) {
		value = initial;
	}
	synchronized public void semSignal() {
		++value;
		notify();
	}
	synchronized public void semWait() throws InterruptedException {
		while (value == 0) wait();
		--value;
	}
}
