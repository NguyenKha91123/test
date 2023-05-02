package Gam2048;

import javax.swing.JFrame;

public class Game2048 extends Thread{
    
    JFrame game;
    QuanLyManHinh qlmh;
    VeManHinh veManHinh;
    QuanLyData qlDT;

//     private void init(){
//         game=new JFrame();
//         qlmh=new QuanLyManHinh(veManHinh, qlDT, game);
//     }

    public void setUp(){
        qlmh.setUp(game);
    }

//    public Game2048(){
//        game=new JFrame();
//        qlmh=new QuanLyManHinh(veManHinh, qlDT, game);
//        setUp();
//    }

    @Override
    public void run(){
    	game=new JFrame();
        qlmh=new QuanLyManHinh(veManHinh, qlDT, game);
        setUp();
    	game.setVisible(true);
    }
    
}
