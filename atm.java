package atmProjact;
import java.util.Scanner;
import java.sql.*;
public class atm {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/atm", "root", "Incapp@12");
			Statement p=c.createStatement();
			Scanner sc= new Scanner(System.in);
			System.out.println("Hey Welcome to ALL in One ATM");
			System.out.println("Enter Your Pin Number");
			int pin=sc.nextInt();
			ResultSet rs=p.executeQuery("select*from list where ac_no="+pin);
			String name=null;
			int count=0;
			int balance =0 ;
			while(rs.next()) {
				name=rs.getString(3);
				balance=rs.getInt(4);
				count++;
			}
			
			int choice;
			int amount = 0;
			int take_amount=0;
			
			if(count>0) {
				System.out.println("Hello"+name);
				while(true) {
					System.out.println("Press 1 to check balance");
					System.out.println("Press 2 to Add Amount");
					System.out.println("Press 3 to Take Amount");
			 		System.out.println("Press 4 to print the Recipt");
					System.out.println("Press 5 to Exit / Quit");
					
					System.out.println();
					System.out.println("Enter Your Choice");
					choice=sc.nextInt();
					
					switch(choice) {
					case 1:
						System.out.println("Your balance is : "+balance);
						break;
					case 2:
						System.out.println("How much amount did you want to add");
					    amount=sc.nextInt();
					    balance=balance+amount;
					    int bal=p.executeUpdate("UPDATE list SET balance = "+balance+" WHERE ac_no ="+pin);
					    System.out.println("successfully added now your current balance is : "+balance);
					    break;
					 case 3:
						 System.out.println("How much amount did you want to take");
					    take_amount=sc.nextInt();
					    if(take_amount>balance) {
					    System.out.println("your balance is insufficient ");
					    }else {
					    	balance= balance + take_amount;
					    	int sub=p.executeUpdate("UPDATE list SET balance ="+balance+" WHERE ac_no ="+pin);
					    	System.out.println("successfully added now your current balance is : "+balance);
					    }
					    break;
					 case 4:
						 System.out.println("Thanks for Coming");
					     System.out.println("Yours current balance is : "+balance);
					     System.out.println("Amount added : "+amount);
					     System.out.println("Amount taken : "+take_amount);
					  break;	
					}
					if(choice==5) {
						break;
					}
				} 
			}else {
				System.out.println("Wrong pin number ");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}

}
