package task2;

public class GeneralSemaphore {
	private int value;
	public GeneralSemaphore (int initial) {
		value = initial;
	}
	synchronized public void nofity() {
		++value;
		notify();
	}
	synchronized public void w8() throws InterruptedException {
		while (value == 0) wait();
		--value;
	}
}
