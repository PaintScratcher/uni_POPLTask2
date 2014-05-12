package task2;

public class GeneralSemaphore {
	private int value;
	public GeneralSemaphore (int initial) {
		value = initial;
	}
	synchronized public void notifySignal() {
		++value;
		notify();
	}
	synchronized public void waitSignal() throws InterruptedException {
		while (value == 0) wait();
		--value;
	}
}
