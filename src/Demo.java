import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.ashwin.User;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		cashWithdraw("ashwin", 5000);
		cashWithdraw("sriram",1000);
		cashWithdraw("shanth",7000);
		System.out.println("Program Ended successfully");
		
	}

	public static void cashWithdraw(String accountName, int amountToWithdraw) {

		String accountBalance = "";
		try {
			//go to database and check if the account has enough money. If not allow money within the limit
			FileInputStream fin = new FileInputStream(new File("D://bank/"+accountName+ ".txt"));
			byte[] fileArray = new byte[fin.available()];
			fin.read(fileArray);
			fin.close();
			accountBalance = new String(fileArray);
			debitMoney(accountBalance, amountToWithdraw, accountName,amountToWithdraw);
		}catch(Exception e) {
			e.printStackTrace();
			// caught the exception now handle the error gracefully by default value
			int defaultAmount = 100;
			try {
				debitMoney(accountBalance, defaultAmount, accountName, amountToWithdraw);
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("Insufficient balance");
			}
			
		}
	}
	
	public static void debitMoney(String accountBalance, int amountToWithdraw, String accountName, int originalAsked) throws Exception {
		
		int balance = Integer.parseInt(accountBalance);
		if (amountToWithdraw < balance) {
			balance = balance - amountToWithdraw;
			FileOutputStream fout = new FileOutputStream(new File("D://bank/"+accountName+ ".txt"));
			fout.write(new String(""+balance).getBytes());
			fout.close();
			System.out.println("You asked Rs." + originalAsked + " Rupees " + amountToWithdraw + " Money dispensed !!!!!! to Mr." + accountName + " Your balance now is Rs." + balance);
		}
		else {
			throw new Exception("Insufficient balance Mr."  + accountName);
		}
	}

}
