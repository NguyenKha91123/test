package Gam2048;

public class Main {
        
	public static Game2048 t1=new Game2048();
	public static Music t2 = new Music();
	
    public void startG() {
        t1.start();
        t2.start();
//        try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
        
//        t2.stopMusic();
    }
    
//    public void pauseM() {
//        t2.pauseMusic();
//    }

    public static void main(String[] args) {
        Main m=new Main();
        m.startG();
    }
}