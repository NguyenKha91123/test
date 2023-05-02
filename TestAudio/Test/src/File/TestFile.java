package File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestFile {
	public static void main(String[] args) {
		
		LocalDateTime layNgayGio = LocalDateTime.now();
        DateTimeFormatter dinhDangChuoi = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String inNgayGio = layNgayGio.format(dinhDangChuoi);
        
		try {
		    FileWriter writer = new FileWriter("Test/src/File/file1.txt", true); // Thêm tham số true để không ghi đè dữ liệu cũ
		    
		    writer.write("Hello World!" + System.getProperty("line.separator")); // Ghi dữ liệu vào file
		    writer.write("Bay gio la: " + inNgayGio+System.getProperty("line.separator")+System.getProperty("line.separator"));
		    writer.close(); // Đóng file
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		try {
		    File file = new File("Test/src/File/file1.txt"); // Đường dẫn đến file
		    BufferedReader br = new BufferedReader(new FileReader(file)); // Đối tượng BufferedReader
		    String line;
		    while ((line = br.readLine()) != null) { // Đọc từng dòng dữ liệu
		        System.out.println(line); // In ra màn hình
		    }
		    br.close(); // Đóng file
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
}

