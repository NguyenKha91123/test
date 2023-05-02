package Gam2048;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.border.Border;

import DataBase.JDBCUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VeManHinh {

    JLabel arrJLabel[][]=new JLabel[4][4];
    QuanLyData qlDT=new QuanLyData();
    JLabel jLabel=new JLabel();
    JLabel jLabel1=new JLabel();
    JLabel jLabel2=new JLabel();
    int timesave;
    public void tinhDiem(JFrame frame){
    	
        jLabel.setText("Point: "+qlDT.getDiem());
        jLabel.setBounds(3, 0, 130, 65);
        jLabel.setBackground(Color.decode("#C8E6C9"));
        jLabel.setOpaque(true);
        jLabel.setVerticalAlignment(SwingConstants.CENTER);
        jLabel.setForeground(Color.blue);
        jLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        Border border = BorderFactory.createLineBorder(Color.BLACK);
//        jLabel.setBorder(border);
        frame.add(jLabel);

        
//        JButton button = new JButton("New Game");
//        button.setBounds(153, 18, 100, 30);
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                
//                System.out.println("Button clicked!");
//            }
//        });
//        frame.add(button);
        
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//           public void run() {
//        	  int count = 0;
//              count++;
//              System.out.println("Time: " + count);
//              jLabel1.setText("Time: "+count);
//           }
//        };
//        long delay = 1000L;
//        long period = 1000L;
//        timer.scheduleAtFixedRate(task, delay, period);
        
//         boolean stopTime = false;
//        
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            int count = 0;
//            public void run() {
//                count++;
//                jLabel1.setText("Time: " + count);
//                timesave=count;
//                if(stopTime==true) {
//                	timer.cancel();
//                }
//            }
//        };
//        long delay = 1000L;
//        long period = 1000L;
//        timer.scheduleAtFixedRate(task, delay, period);
        
//                 timer.cancel();
//                 System.out.println("Timer stopped");
        jLabel1.setText("New Game");
        jLabel1.setBounds(135, 0, 120, 65);
        jLabel1.setBackground(Color.decode("#C8E6C9"));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setOpaque(true);
        jLabel1.setForeground(Color.blue);
        jLabel1.setFont(new Font("MV Boli", Font.PLAIN, 20));
//        jLabel1.setBorder(border);
        jLabel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	qlDT.reset();
            	Update();
            	qlDT.moveEndFirst();
            }
        });
        frame.add(jLabel1);
        
        jLabel2.setText("Best: "+ketNoiSQL());
        jLabel2.setBounds(270, 0, 130, 65);
        jLabel2.setBackground(Color.decode("#C8E6C9"));
        jLabel2.setOpaque(true);
        jLabel2.setForeground(Color.blue);
        jLabel2.setFont(new Font("MV Boli", Font.PLAIN, 20));
//        jLabel2.setBorder(border);
        jLabel2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	frameRank();
            	qlDT.moveEndFirst();
            }
        });
        frame.add(jLabel2);
    }
    
    public int gettime() {
    	return timesave;
    }
    
    public void frameRank() {
    	JDialog dialog = new JDialog();
        dialog.setTitle("Rank");
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);
        
        Connection conn = JDBCUtil.getConnection();

    	try {
    	    Statement statement = conn.createStatement();
    	    
			String getLine="SELECT COUNT(*) FROM game.persons;"+"";
			ResultSet ComgetLine=statement.executeQuery(getLine);
			int line = 0;
    	    if (ComgetLine.next()) {
    	       line = ComgetLine.getInt(1);
    	    }
    	    if(line>=3) {
    	    	String swapDESC="SELECT * FROM game.persons order by point desc limit 3;"+"";
    	    	ResultSet ComswapDESC=statement.executeQuery(swapDESC);
    	    	
    	    	JPanel jPanel=new JPanel();
    	    	jPanel.setLayout(new GridLayout(4, 2, 5, 5));
    	    	
    	    	JLabel rJLabel[]=new JLabel[2*4];
    	    	rJLabel[0]=new JLabel();
    	    	rJLabel[1]=new JLabel();
    	    	rJLabel[0].setText("NAME");
    	    	rJLabel[1].setText("POINT");
    	    	rJLabel[0].setHorizontalAlignment(SwingConstants.CENTER);
    	    	rJLabel[1].setHorizontalAlignment(SwingConstants.CENTER);
    	    	rJLabel[0].setBackground(Color.yellow);
    	    	rJLabel[0].setOpaque(true);
    	    	rJLabel[1].setBackground(Color.yellow);
    	    	rJLabel[1].setOpaque(true);
    	    	rJLabel[0].setForeground(Color.red);
    	    	rJLabel[0].setFont(new Font("Arial", Font.BOLD, 16));
    	    	rJLabel[1].setForeground(Color.red);
    	    	rJLabel[1].setFont(new Font("Arial", Font.BOLD, 16));
    	    	jPanel.add(rJLabel[0]);
    	    	jPanel.add(rJLabel[1]);
    	    	for(int i=2; i<2*4; i=i+2) {
    	    		rJLabel[i]=new JLabel();
    	    		rJLabel[i+1]=new JLabel();
    	    		if(ComswapDESC.next()) {
	    	    		String name=ComswapDESC.getString("name");
	    	    		int point=ComswapDESC.getInt("point");
	    	    		rJLabel[i].setText(name);
	    	    		rJLabel[i+1].setText(""+point);
	    	    	}
	    	    	jPanel.add(rJLabel[i]);
	    	    	jPanel.add(rJLabel[i+1]);
	    	    	rJLabel[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
	    	    	rJLabel[i+1].setHorizontalAlignment(SwingConstants.CENTER);
	    	    	rJLabel[i].setBackground(Color.green);
	    	    	rJLabel[i].setOpaque(true);
	    	    	rJLabel[i+1].setBackground(Color.green);
	    	    	rJLabel[i+1].setOpaque(true);
	    	    	rJLabel[i].setForeground(Color.blue);
	    	    	rJLabel[i].setFont(new Font("Arial", Font.BOLD, 16));
	    	    	rJLabel[i+1].setForeground(Color.blue);
	    	    	rJLabel[i+1].setFont(new Font("Arial", Font.BOLD, 16));
    	    	}
    	    	dialog.add(jPanel);
    	    	
    	    	// Đóng kết nối
    	    	JDBCUtil.closeConnection(conn);
    	    }else if(line==1 || line==2) {
    	    	String swapDESC="SELECT * FROM game.persons order by point desc;"+"";
    	    	ResultSet ComswapDESC=statement.executeQuery(swapDESC);
    	    	
    	    	JPanel jPanel=new JPanel();
    	    	jPanel.setLayout(new GridLayout(4, 2, 5, 5));
    	    	
    	    	JLabel rJLabel[]=new JLabel[2*4];
    	    	rJLabel[0]=new JLabel();
    	    	rJLabel[1]=new JLabel();
    	    	rJLabel[0].setText("NAME");
    	    	rJLabel[1].setText("POINT");
    	    	rJLabel[0].setHorizontalAlignment(SwingConstants.CENTER);
    	    	rJLabel[1].setHorizontalAlignment(SwingConstants.CENTER);
    	    	rJLabel[0].setBackground(Color.yellow);
    	    	rJLabel[0].setOpaque(true);
    	    	rJLabel[1].setBackground(Color.yellow);
    	    	rJLabel[1].setOpaque(true);
    	    	rJLabel[0].setForeground(Color.red);
    	    	rJLabel[0].setFont(new Font("Arial", Font.BOLD, 16));
    	    	rJLabel[1].setForeground(Color.red);
    	    	rJLabel[1].setFont(new Font("Arial", Font.BOLD, 16));
    	    	jPanel.add(rJLabel[0]);
    	    	jPanel.add(rJLabel[1]);
    	    	for(int i=2; i<2*4; i=i+2) {
    	    		rJLabel[i]=new JLabel();
    	    		rJLabel[i+1]=new JLabel();
    	    		if(ComswapDESC.next()) {
	    	    		String name=ComswapDESC.getString("name");
	    	    		int point=ComswapDESC.getInt("point");
	    	    		rJLabel[i].setText(name);
	    	    		rJLabel[i+1].setText(""+point);
	    	    		if(point!=0) {
	    	    			rJLabel[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
	    	    			rJLabel[i+1].setHorizontalAlignment(SwingConstants.CENTER);
	    	    			rJLabel[i].setBackground(Color.green);
	    	    			rJLabel[i].setOpaque(true);
	    	    			rJLabel[i+1].setBackground(Color.green);
	    	    			rJLabel[i+1].setOpaque(true);
	    	    			rJLabel[i].setForeground(Color.blue);
	    	    			rJLabel[i].setFont(new Font("Arial", Font.BOLD, 16));
	    	    			rJLabel[i+1].setForeground(Color.blue);
	    	    			rJLabel[i+1].setFont(new Font("Arial", Font.BOLD, 16));
	    	    		}
	    	    	}
	    	    	jPanel.add(rJLabel[i]);
	    	    	jPanel.add(rJLabel[i+1]);
    	    	}
    	    	dialog.add(jPanel);
    	    	
    	    	// Đóng kết nối
    	    	JDBCUtil.closeConnection(conn);
    	    }else {
    	    	
//    	    	Không có điểm nào
    	    	JLayeredPane layeredPane = new JLayeredPane();
    	        layeredPane.setPreferredSize(new Dimension(400, 300));

    	        JLabel NoR = new JLabel("No Rank");
    	        NoR.setBounds(128, 75, 200, 100);
    	        NoR.setForeground(Color.white);
    	        NoR.setFont(new Font("Arial", Font.BOLD, 36));
    	        layeredPane.add(NoR, Integer.valueOf(1));

    	        ImageIcon backgroundImage = new ImageIcon("Test/src/Assets/BG.jpg");
    	        JLabel backgroundLabel = new JLabel(backgroundImage);
    	        backgroundLabel.setBounds(0, 0, 500, 500);
    	        layeredPane.add(backgroundLabel, Integer.valueOf(0));

    	        dialog.setContentPane(layeredPane);
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}
    	dialog.setVisible(true);
    }

    public void veCacNut(JFrame frame,int w,int h,int s){

        int width_J=(w-3*s)/4;
        int height_J=(w-3*s)/4;
        int x=0, y=0;

        for(int i=0; i<4; i++){
            y=65+i*height_J+(i+1)*s;
            for(int j=0; j<4; j++){
                x=j*width_J+(j+1)*s;
                arrJLabel[i][j]=new JLabel();
                arrJLabel[i][j]=taoO(x, y, width_J, height_J, i, j);    //x, y là vị trí hiển thị ô
                frame.add(arrJLabel[i][j]);                             //i, j để get giá trị xem là bao nhiêu để lấy màu
            }
        }
        frame.add(new JLabel());
    }
   
    public JLabel taoO(int x, int y, int width_J, int height_J, int i, int j){
        JLabel jLable=new JLabel();
        jLable.setBounds(x, y, width_J, height_J);     
        setBG(jLable, i, j);
        return jLable;
    }

    public void setBG(JLabel jLabel, int i, int j){
        int data= Data.getData().arr[i][j];
        String color="";
        switch(data){
            case 2:
                color="#9600ff";
                break;
            case 4:
                color="#f0145a";
                break;
            case 8:
                color="#ffc91b";
                break;
            case 16:
                color="#800080";
                break;
            case 32:
                color="#0095d6";
                break;
            case 64:
                color="#ce007b";
                break;
            case 128:
                color="#ff5518";
                break;
            case 256:
                color="#29d7a5";
                break;
            case 512:
                color="#73c702";
                break;
            case 1024:
                color="#ff0024";
                break;
            case 2048:
                color="#5f069b";
                break;
            default:
                color="#555555";
                break;
        }

        if(data==0){
            jLabel.setText("");
        }else{
            jLabel.setText(""+data);
        }
        Font font=new Font("Arial", Font.BOLD, 26);
        jLabel.setFont(font);
        jLabel.setOpaque(true);
        jLabel.setForeground(Color.white);
        jLabel.setBackground(Color.decode(color));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setVerticalAlignment(SwingConstants.CENTER);
    }

//    QuanLyManHinh qlMH=new QuanLyManHinh();
    public void Update(){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                setBG(arrJLabel[i][j], i, j);
            }
        }
        if(qlDT.isGameTerminated()==false){
            jLabel.setText("Point: "+qlDT.getDiem());
            
        }else {
        	jLabel.setText("Point: "+qlDT.getDiem());
        }
        if(ketNoiSQL()>qlDT.getDiem()) {
        	jLabel2.setText("Best: "+ketNoiSQL());
        }else {
        	jLabel2.setText("Best: "+qlDT.getDiem());
        }
    }
    
    public int ketNoiSQL() {
    	// Tạo kết nối
    	Connection connection = JDBCUtil.getConnection();
    	
    	int maxDiem=0;
    	
    	try {
    	    // Tạo đối tượng statement
    	    Statement statement = connection.createStatement();

    	    String getMax="SELECT MAX(POINT) FROM game.persons;"+"";
    	    ResultSet ComgetMax=statement.executeQuery(getMax);
    	    
    	    if (ComgetMax.next()) {
    	        maxDiem = ComgetMax.getInt(1);
    	    }
    	    // Đóng kết nối
    	    JDBCUtil.closeConnection(connection);
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}
    	return maxDiem;
    	
    }
    
    public void showList() {
    	
    }
}
