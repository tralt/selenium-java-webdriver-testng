package javaTester;

import java.util.ArrayList;

public class Topic_01_Data_Type {
	
	public static void main(String[] args) {
		// Khai bao bien = kieu du lieu + ten bien
		int studenNumber;
		
		// Khoi tao du lieu
		studenNumber = 100;
		
		// Khai bao + khoi tao
		int teacherNumber = 20;
		
		
		// I - Nguyen Thuy (primitive)
		
		// boolean
		boolean studenSex = true;
		
		// byte
		byte bEmployee = 100;
		
		// short
		short sEmployee = 1000;
		
		// int
		int iEmployee = 10;
		
		// long
		long lEmployee = 1000000000;
		
		// float 
		float fEmployee = 7.5f;
		
		// double
		double dEmployee = 8.9d;
		
		// char: chi duoc phep khai bao 1 ky tu;
		char a = 'B';
		
		
		// II - Tham Chieu (Reference)
		
		// Array
		int[] studentNumbers = {30, 15, -7, 10};
		String[] studentNames = {"Nguyen Van Nam", "Ngo Thi Ngoc"};
		
		
		// Class/ Interface
		//WebDriver driver = new ChromeDriver(); // interface
		
		//Actions actions = new Actions(driver); // class
		
		
		// Collection: List/ Set/ Queue
		ArrayList<String> studentCountries = new ArrayList<String>();
		
		
		// Object
		Object phone;
		
		
		// String: so, chu, ky tu dac biet
		String studentName = "Le Thanh Tra @ltd 1995";
		
		String oob = "tralt 1995";
		
		System.out.println(oob);
		
		String x = oob;
		String y = "Hihi";
		
		x = y;
		
		System.out.print(x);
		
		
	}

}
