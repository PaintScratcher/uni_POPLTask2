package task2;

public class BoundedBuffer<value> {

	BinarySemaphore BS = new BinarySemaphore(0);
	GeneralSemaphore GS1 = new GeneralSemaphore(0);
	GeneralSemaphore GS2 = new GeneralSemaphore(0);
	Producer P1 = new Producer();
	Producer P2 = new Producer();
	Consumer C1 = new Consumer();
	Consumer C2 = new Consumer();
	int[] buffer = new int[2];

	public static void main(String[] args) {
		new BoundedBuffer();
	}
	
	public BoundedBuffer(){
		
		Thread T1 = new Thread(){
			
		};
		
		Thread T2 = new Thread(){
			
		};
		
		Thread T3 = new Thread(){
			
		};
		
		Thread T4 = new Thread(){
			
		};
	}
	
	public void write(int value){
		buffer.add(value);
	}
}
