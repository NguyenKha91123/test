package Gam2048;

//public class Data {
//    public int[][] arr = new int[4][4];
//
//    public void setData(int i, int j, int value) {
//        arr[i][j] = value;
//    }
//
//    public int getData(int i, int j) {
//        return arr[i][j];
//    }
//}

public class Data {

    // Singleton: Data
    // Instance: data

    private static Data data=new Data();   //Bắt buộc private
//    static{
//        data=new Data();
//    }
    public static Data getData(){   //Bắt buộc static
        return data;
    }

    public int arr[][]=new int[4][4];
    
    public int a[][]=new int [8][8];
}

//public class Data {
//    public int[][] arr = new int[4][4];
//
//    public void set(int i, int j, int value) {
//        arr[i][j] = value;
//    }
//
//    public int get(int i, int j) {
//        return arr[i][j];
//    }
//    
////    public static void main(String[] args) {
////		for(int i=0; i<4; i++) {
////			for(int j=0; j<4; j++) {
////				System.out.print(Data.get(i, j)+" ");
////			}
////			System.out.println("\n");
////		}
////	}
//}

