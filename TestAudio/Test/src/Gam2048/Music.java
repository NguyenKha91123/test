package Gam2048;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music extends Thread{
	
    @Override
    public void run(){
    	try {
    		File file = new File("Test/src/Assets/micraft_audio.wav");
    		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
    		Clip clip = AudioSystem.getClip();
    		clip.open(audioStream);
//            	clip.start();
    		clip.loop(Clip.LOOP_CONTINUOUSLY);
    		while (clip.isActive()) {
    			try {
    				Thread.sleep(100);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
    	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
    		e1.printStackTrace();
    	}
    }
}

//public class Music extends Thread {
//    private volatile boolean stop;
//
//    public void stopMusic() {
//        stop = true;
//    }
//
//    @Override
//    public void run() {
//        try {
//            File file = new File("Test/src/Gam2048/micraft_audio.wav");
//            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioStream);
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
//            while (!stop) {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            clip.stop();
//            clip.close();
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
//            e1.printStackTrace();
//        }
//    }
//}
