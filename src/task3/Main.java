package task3;

public class Main {

	public static void main(String[] args) {
		BoundedBuffer buffer = new BoundedBuffer(2);
		int numberOfProducers = 2;
		int numberOfConsumers = 2;
		
		for(int i = 0; i < numberOfProducers; i++) {
			new Producer(buffer, i+1).start();
		};
		
		for(int i = 0; i < numberOfConsumers; i++) {
			new Consumer(buffer).start();
		};
	}

}
