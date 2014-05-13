package task3;

public class Main {

	public static void main(String[] args) {
		BoundedBuffer buffer = new BoundedBuffer(2); //Creates a bounded buffer of size 2
		int numberOfProducers = 2; // Indicates the amount of produces to be created
		int numberOfConsumers = 2; // Indicates the amount of consumers to be created
		
		for(int i = 0; i < numberOfProducers; i++) { //Create a producer until the cap is reached
			new Producer(buffer, i+1).start();
		};
		
		for(int i = 0; i < numberOfConsumers; i++) { //Create a consumer until the cap is reached
			new Consumer(buffer).start();
		};
	}

}
