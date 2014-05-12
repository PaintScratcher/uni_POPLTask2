package task2;

public class BoundedBuffer {

	BinarySemaphore BS = new BinarySemaphore(0);
	GeneralSemaphore GS1 = new GeneralSemaphore(0);
	GeneralSemaphore GS2 = new GeneralSemaphore(0);
	Producer P1 = new Producer();
	Producer P2 = new Producer();
	Consumer C1 = new Consumer();
	Consumer C2 = new Consumer();
	int[] buffer = new int[2];
	int read = 1;
	int write = 0;


	public void write(int value){

	}
	
	public void read(int value){
		
	}
}
