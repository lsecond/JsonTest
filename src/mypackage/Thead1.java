package mypackage;

public class Thead1 implements Runnable {
	public void run() {
		synchronized (this) {
			for(int i = 0; i <5; i++) {
				System.out.println(Thread.currentThread().getName() + " Synchronized loop " + i);
			}
		}
	}
    public static void main(String[] args) {  
    	Thead1 t1 = new Thead1();  
        Thread ta = new Thread(t1, "A");  
        Thread tb = new Thread(t1, "B");  
        ta.start();  
        tb.start();  
   } 

}
