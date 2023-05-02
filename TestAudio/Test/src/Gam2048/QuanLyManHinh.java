package Gam2048;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class QuanLyManHinh extends Thread{
    public int space=2;
    public int width=400;
    public int height=400;
    VeManHinh veManHinh;
    QuanLyData qlDT;
    Music music;
    
    public QuanLyManHinh(VeManHinh veManHinh,QuanLyData qlDT, JFrame frame){
        this.veManHinh=veManHinh;
        this.veManHinh=new VeManHinh();

        this.qlDT=qlDT;
        this.qlDT=new QuanLyData();

        this.veManHinh.tinhDiem(frame);
        this.veManHinh.veCacNut(frame, width, height, space);
    }

    public QuanLyManHinh() {
		
	}

	public void setUp(JFrame frame){
        frame.setTitle("Game 2048");
        frame.setSize(width+16, height+38+65);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.decode("#C8E6C9"));
        frame.setResizable(false);
        frame.setLayout(null);

        // layeredPane = new JLayeredPane();
        // // layeredPane.setPreferredSize(new Dimension(500, 500));

        // ImageIcon backgroundImage = new ImageIcon("Test/src/Gam2048/BG.jpg");
        // JLabel backgroundLabel = new JLabel(backgroundImage);
        // backgroundLabel.setBounds(0, 0, width+16, height+38+65);
        // layeredPane.add(backgroundLabel, Integer.valueOf(0));
        // frame.setContentPane(layeredPane);

        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

                throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
            }

            @Override
            public void keyPressed(KeyEvent e) {

                throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    qlDT.moveLeft();
                }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                	qlDT.moveRight();
                }else if(e.getKeyCode()==KeyEvent.VK_UP){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                	qlDT.moveUp();
                }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                	qlDT.moveDown();
                }else if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    qlDT.reset();
//                    music.isRunning=false;
//                    music.run();
                }

                veManHinh.Update();
                qlDT.timMax();
                throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
            }
            
        });
        
    }
}
