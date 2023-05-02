package Gam2048;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DataBase.JDBCUtil;

public class QuanLyData extends Thread{

    Main m;
    Random rand=new Random();
    public static int diem=0;
    VeManHinh veManHinh;
    JFrame frame;
//    Music music =new Music();
    Data data=new Data();

    public QuanLyData(){
        taoSo();
    }
    
    public void taoSo(){
        int oConTrong=0;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(Data.getData().arr[i][j]==0){
                    oConTrong++;
                }
            }
        }
        if(oConTrong>=2){
            if(rand.nextInt(5)>3){       
                oConTrong=2;                    //Tỷ lệ ra 1 là 20%
            }else{
                oConTrong=1;                    //Tỷ lệ ra 2 là 80%
            }
        }
        while(oConTrong!=0){
            int x=rand.nextInt(4);
            int y=rand.nextInt(4);
            while(Data.getData().arr[x][y]!=0){
                x=rand.nextInt(4);
                y=rand.nextInt(4);
            }
            if(rand.nextInt(5)>3){
                Data.getData().arr[x][y]=4;     //Tỷ lệ ra 4 là 1/3
            }else{
                Data.getData().arr[x][y]=2;     //Tỷ lệ ra 2 là 2/3
            }
            oConTrong--;
        }
        System.out.println("taoSo");
    }

    public void moveLeft(){
    	int cl=0;
    	
    	int[][] a=new int[4][4];
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			a[i][j]=Data.getData().arr[i][j];
    		}
    	}
    	
        moveAudio();
        for(int i=0; i<4; i++){					//Hàm gộp chỉ có thể gộp 2 ô gần nhau lại lên cần đưa nó lại gần nhau rồi gộp rồi dồn
            for(int j=0; j<4; j++){
                if(Data.getData().arr[i][j]!=0){
                    donO(i, j, 0, -1);          //Mỗi lần lùi 1 bước sang trái
                }
            }
        }
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(Data.getData().arr[i][j]!=0){
                    gopO(i, j, 0, 1);       //jt tăng sang phải vì ktra từ trái sang
                }
            }
        }
        
        //Sau khi gộp ô thì sẽ có ô còn trống bên trái nên phải dồn 1 lần nữa
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(Data.getData().arr[i][j]!=0){
                    donO(i, j, 0, -1);
                }
            }
        }
        
        for(int i=0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			if(Data.getData().arr[i][j]!=a[i][j]) {
    				cl=1;
    			}
    		}
    	}
        
        if(cl==1) {
        	taoSo();
        }else {
        	return;
        }
    }

	public void moveRight(){
		int cr=0;
		
		int[][] a=new int[4][4];
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			a[i][j]=Data.getData().arr[i][j];
    		}
    	}
    	
        moveAudio();
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(Data.getData().arr[i][j]!=0){
                    donO(i, j, 0, 1);          //Mỗi lần lùi 1 bước sang phải
                }
            }
        }
        for(int i=3; i>=0; i--){
            for(int j=3; j>=0; j--){
                if(Data.getData().arr[i][j]!=0){
                    gopO(i, j, 0, 1);       //jt tăng sang phải vì ktra từ trái sang
                }
            }
        }
        //Sau khi gộp ô thì sẽ có ô còn trống bên trái nên phải dồn 1 lần nữa
        for(int i=3; i>=0; i--){
            for(int j=3; j>=0; j--){
                if(Data.getData().arr[i][j]!=0){
                    donO(i, j, 0, 1);
                }
            }
        }
        
        for(int i=0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			if(Data.getData().arr[i][j]!=a[i][j]) {
    				cr=1;
    			}
    		}
    	}
        
        if(cr==1) {
        	taoSo();
        }else {
        	return;
        }
    }

	public void moveUp(){
		int cu=0;
		
		int[][] a=new int[4][4];
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			a[i][j]=Data.getData().arr[i][j];
    		}
    	}
    	
        moveAudio();
	    for(int i=3; i>=0; i--){
            for(int j=3; j>=0; j--){
                if(Data.getData().arr[i][j]!=0){
                    donO(i, j, -1, 0);
                }
            }
        }
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(Data.getData().arr[i][j]!=0){
                    gopO(i, j, -1, 0);
                }
            }
        }
        //Sau khi gộp ô thì sẽ có ô còn trống bên trái nên phải dồn 1 lần nữa
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(Data.getData().arr[i][j]!=0){
                    donO(i, j, -1, 0);
                }
            }
        }

        for(int i=0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			if(Data.getData().arr[i][j]!=a[i][j]) {
    				cu=1;
    			}
    		}
    	}
        
        if(cu==1) {
        	taoSo();
        }else {
        	return;
        }
	}

	public void moveDown(){
		int cd=0;
		
		int[][] a=new int[4][4];
    	for(int i=0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			a[i][j]=Data.getData().arr[i][j];
    		}
    	}
    	
        moveAudio();
	    for(int i=3; i>=0; i--){
            for(int j=3; j>=0; j--){
                if(Data.getData().arr[i][j]!=0){
                    donO(i, j, 1, 0);
                }
            }
        }
        for(int i=3; i>=0; i--){
            for(int j=3; j>=0; j--){
                if(Data.getData().arr[i][j]!=0){
                    gopO(i, j, 1, 0);
                }
            }
        }
        //Sau khi gộp ô thì sẽ có ô còn trống bên trái nên phải dồn 1 lần nữa
        for(int i=3; i>=0; i--){
            for(int j=3; j>=0; j--){
                if(Data.getData().arr[i][j]!=0){
                    donO(i, j, 1, 0);
                }
            }
        }
        
        for(int i=0; i<4; i++) {
    		for(int j=0; j<4; j++) {
    			if(Data.getData().arr[i][j]!=a[i][j]) {
    				cd=1;
    			}
    		}
    	}
        
        if(cd==1) {
        	taoSo();
        }else {
        	return;
        }
	}

    public void donO(int i, int j, int it, int jt){    //it, jt là số bước lùi -1-
        if(i==0 && it==-1){
            return;
        }
        if(i==3 && it==1){
            return;
        }
        if(j==0 && jt==-1){
            return;
        }
        if(j==3 && jt==1){
            return;
        }

        int x=i+it;
        int y=j+jt;
        if(Data.getData().arr[x][y]!=0){    //Nếu đã có sẵn 1 ô ở vị trí lùi tới thì return
            return;
        }
        int k=Data.getData().arr[i][j];
        Data.getData().arr[x][y]=k;         //Đã lùi đc 1 ô
        Data.getData().arr[i][j]=0;
        donO(x, y, it, jt);                 //Gọi lại hàm để lùi tiếp, vị trí ban đầu là x, y vừa lùi
    }

    public void gopO(int i, int j, int it, int jt){
        if(i==0 && it==-1){
            return;
        }
        if(i==3 && it==1){
            return;
        }
        if(j==0 && jt==-1){
            return;
        }
        if(j==3 && jt==1){
            return;
        }

        int x=i+it;
        int y=j+jt;
        if(Data.getData().arr[x][y]!=Data.getData().arr[i][j]){ //2 ô gtr khác nhau thì return
            return;
        }
        int k=Data.getData().arr[i][j];
        Data.getData().arr[x][y]=2*k;
        Data.getData().arr[i][j]=0;
        // gopO(x, y, it, jt);

        diem+=2*k;
    }

    public int getDiem(){
        return diem;
    }

    public boolean isGameTerminated() {
        boolean terminated = true;
        // Kiểm tra xem còn ô trống hay không
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Data.getData().arr[i][j]==0) {
                    terminated = false;
                    break;
                }
            }
        }
        // Nếu còn ô trống thì trò chơi chưa kết thúc
        if (!terminated) {
            return false;
        }
        // Kiểm tra xem còn cách nào di chuyển ô hay không
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int tile = Data.getData().arr[i][j];
                if ((i < 4 - 1 && tile == Data.getData().arr[i + 1][j]) ||
                    (j < 4 - 1 && tile == Data.getData().arr[i][j + 1])) {
                    // Còn cách di chuyển ô
                    return false;
                }
            }
        }
        // Nếu không còn cách di chuyển ô thì trò chơi đã kết thúc
        return true;
    }

    public void timMax(){
        int max=Data.getData().arr[0][0];
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                max=Math.max(max, Data.getData().arr[i][j]);
            }
        }
        if(isGameTerminated()==true){
        	
//    		Kết nối SQL
        	Connection connection=JDBCUtil.getConnection();
        	try {
				Statement stmt=connection.createStatement();
				
//				Sắp xếp điểm giảm dần
//				String queryAll = "SELECT * FROM GAME.PERSONS ORDER BY POINT DESC";
//	    	    ResultSet resultSetAll = stmt.executeQuery(queryAll);
	    	    
//	    	    Đếm có bao nhiêu hàng
				String getLine="SELECT COUNT(*) FROM game.persons;"+"";
//												as point
	    	    ResultSet ComgetLine=stmt.executeQuery(getLine);
	    	    int count = 0;
	    	    if (ComgetLine.next()) {
	    	       count = ComgetLine.getInt(1);
//	    	       							"point"
	    	    }
	    	    System.out.println(count);
	    	    if(count>=3) {
//	    	    	Lấy điểm của hàng thứ 3
	    	    	String point3="SELECT * FROM game.persons order by point desc limit 1 offset 2";
	    			ResultSet Compoint3=stmt.executeQuery(point3);
	    			int pointLine3=0;
	    			if (Compoint3.next()) {
	    				pointLine3 = Compoint3.getInt("point");
	    			}
	    			System.out.println(getDiem());
	    			System.out.println(pointLine3);
	    	    	if (getDiem() > pointLine3) {
	    	    		// Nhập tên người chơi
	    	    		String newNameFirst = JOptionPane.showInputDialog("Nhập tên của bạn: ");
	    	    		
	    	    		// Thêm dữ liệu vào bảng
	    	    		String insertQuery = "INSERT INTO GAME.PERSONS (NAME, POINT) VALUES (?, ?)";
	    	    		PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	    	    		preparedStatement.setString(1, newNameFirst);
	    	    		preparedStatement.setInt(2, getDiem());
	    	    		preparedStatement.executeUpdate();
	    	    		
	    	    		if(newNameFirst==null) {
	    	    			return;
	    	    		}
	    	    		
	    	    		String swapDESFirst = "SELECT * FROM GAME.PERSONS ORDER BY POINT DESC;"+"";
	    	    	    ResultSet ComswapDESFirst = stmt.executeQuery(swapDESFirst);
	    	    	    moveEndLast();
	    	    	}else {
	    	    		moveEndFirst();
	        	    	JOptionPane.showMessageDialog(null, "Điểm của bạn: "+getDiem());
	        	    	JOptionPane.showMessageDialog(null, "Click SPACE to replay");
	        	    	moveEndLast();
	    	    	}
	    	    }else {
	    	    	// Nhập tên người chơi
    	    		String newNameLast = JOptionPane.showInputDialog("Nhập tên của bạn: ");
    	    		
    	    		// Thêm dữ liệu vào bảng
    	    		String insertQuery = "INSERT INTO GAME.PERSONS (NAME, POINT) VALUES (?, ?)";
    	    		PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
    	    		preparedStatement.setString(1, newNameLast);
    	    		preparedStatement.setInt(2, getDiem());
    	    		preparedStatement.executeUpdate();
    	    		
    	    		if(newNameLast==null) {
    	    			return;
    	    		}
    	    		
    	    		String swapDESLast = "SELECT * FROM GAME.PERSONS ORDER BY POINT DESC;"+"";
    	    	    ResultSet ComswapDESLast = stmt.executeQuery(swapDESLast);
    	    	    moveEndLast();
	    	    }
	    	    
	    	    JDBCUtil.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	
			try {
				String timeEG = "INSERT INTO GAME.PERSONS3 (time) VALUES (?)";
				PreparedStatement pre = connection.prepareStatement(timeEG);
				pre.setInt(1, veManHinh.gettime());
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
//        	In điểm vào file txt
        	LocalDateTime layNgayGio = LocalDateTime.now();
            DateTimeFormatter dinhDangChuoi = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String inNgayGio = layNgayGio.format(dinhDangChuoi);
            
    		try {
    		    FileWriter writer = new FileWriter("Test/src/File/savePoint.txt", true); //true để không ghi đè dữ liệu cũ
    		    
    		    writer.write("Point: "+getDiem() + System.getProperty("line.separator")); // Ghi dữ liệu vào file
    		    writer.write("Date: " + inNgayGio+System.getProperty("line.separator")+System.getProperty("line.separator"));
    		    writer.close();
    		} catch (IOException e) {
    		    e.printStackTrace();
    		}
    		
//    		Xuất điểm ra màn hình
    		try {
    		    File file = new File("Test/src/File/savePoint.txt"); // Đường dẫn đến file
    		    BufferedReader br = new BufferedReader(new FileReader(file)); // Đối tượng BufferedReader
    		    String line;
    		    while ((line = br.readLine()) != null) { // Đọc từng dòng dữ liệu
    		        System.out.println(line); // In ra màn hình
    		    }
    		    br.close();
    		} catch (IOException e) {
    		    e.printStackTrace();
    		}
        	return;
        }
    }

    public void reset(){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                Data.getData().arr[i][j]=0;
            }
        }
        System.out.println("Reset");
        diem=0;
        taoSo();
    }

    public void moveAudio(){
        try {
            File file = new File("Test/src/Assets/2.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}
    }

    public void moveEndFirst(){
        try {
            File file = new File("Test/src/Assets/jump.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}
    }

    public void moveEndLast(){
        try {
            File file = new File("Test/src/Assets/3.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}
    }

}
