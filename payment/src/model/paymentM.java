package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class paymentM {

	//A common method to connect to the DB
	private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

	//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paymentdb", "root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

	
	public String insertpaymentM(String Username, String Amount, String Type, String CrdType)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for inserting."; }
	// create a prepared statement
	String query = " insert into payment(`PaymentID`,`Username`,`Amount`,`Type`,`CrdType`)"
	+ " values (?, ?, ?, ?, ?)";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2, Username);
	preparedStmt.setString(3, Amount);
	preparedStmt.setString(4, Type);
	preparedStmt.setString(5, CrdType);
	//execute the statement

	preparedStmt.execute();
	con.close();
	output = "Inserted successfully";
	}
	catch (Exception e)
	{
	output = "Error while inserting the Payment Details.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	public String readpaymentM()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>PaymentID</th> +<th>Username</th><th>Amount</th>" +"<th>Type</th>" +"<th>CrdType</th>" +"<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from payment";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String PaymentID = Integer.toString(rs.getInt("PaymentID"));
	 String Username = rs.getString("Username");
	 String Amount = rs.getString("Amount");
	 String Type = rs.getString("Type");
	 String CrdType = rs.getString("CrdType");
	 // Add into the html table
	 output += "<tr><td>" + PaymentID + "</td>";
	 output += "<td>" + Username + "</td>";
	 output += "<td>" + Amount + "</td>";
	 output += "<td>" + Type + "</td>";
	 output += "<td>" + CrdType + "</td>";
	// buttons
		output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				+ "<td><form method='post' action='items.jsp'>"
				+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				+ "<input name='PaymentID' type='hidden' value='" + PaymentID 
				+ "'>" + "</form></td></tr>"; 
	} 
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 

	
	
	public String updatepaymentM( String Username, String Amount, String Type, String CrdType, int PaymentID)

	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for updating."; }
	// create a prepared statement
	String query = "UPDATE payment SET Username=?,Amount=?,Type=?,CrdType=? WHERE PaymentID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setString(1, Username);
	preparedStmt.setString(2, Amount);
	preparedStmt.setString(3, Type);
	preparedStmt.setString(4, CrdType);
	preparedStmt.setInt(5, PaymentID);
	
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Updated successfully";
	}
	catch (Exception e)
	{
	output = "Error while updating the Payment Details.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	public String deletepaymentM(String PaymentID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
	// create a prepared statement
				String query = "delete from payment where PaymentID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
				preparedStmt.setInt(1, Integer.parseInt(PaymentID));
	// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the Payment Details.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	}

