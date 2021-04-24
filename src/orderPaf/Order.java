package orderPaf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Order 
	{ 		//A common method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
			
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
			}
			catch (Exception e)
			{e.printStackTrace();}
			return con;
		}
		
		public String insertOrder(Integer price, Integer quantity, String phone, String delivery_Name, String delivery_Address, Integer total_Amount)
		{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
				// create a prepared statement
				 String query = " INSERT INTO test.user(`order_ID`,`price`,`quantity`,`phone`,`delivery_Name`,`delivery_Address`,`total_Amount`)"
						 + " values (?,?, ?, ?, ?, ?,?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setInt(2, price);
				preparedStmt.setInt(3, quantity);
				preparedStmt.setString(4, phone);
				preparedStmt.setString(5, delivery_Name );
				preparedStmt.setString(6, delivery_Address);
				preparedStmt.setInt(7, total_Amount);
//				
				
				// execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Inserted successfully"; 
				 } 
			catch (Exception e) 
			{ 
				 output = "Error while inserting the order."; 
				 System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
				
		public String readOrder() 
		{ 
			String output = ""; 
			
			try
			{ 
				 Connection con = connect(); 
				 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for reading."; 
				 } 
				 
				 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>price</th><th>quantity</th>" +
				 "<th>phone</th>" + 
				 "<th>delivery_Name</th>" + "<th>delivery_Address</th>" + "<th>total_Amount</>" +
				 "<th>Update</th><th>Remove</th></tr>"; 
				 
				 String query = "select * from order"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
					 String order_ID = Integer.toString(rs.getInt("order_ID"));
					 String price = Integer.toString(rs.getInt("price")); 
					 String quantity = Integer.toString(rs.getInt("quantity"));  
					 String phone = rs.getString("phone"); 
					 String delivery_Name = rs.getString("delivery_Name"); 
					 String delivery_Address = rs.getString("delivery_Address"); 
					 String total_Amount = rs.getString("total_Amount");
				 
					 // Add into the html table
					 output += "<tr><td>" + price + "</td>"; 
					 output += "<td>" + quantity + "</td>"; 
					 output += "<td>" + phone + "</td>"; 
					 output += "<td>" + delivery_Name + "</td>"; 
					 output += "<td>" + delivery_Address + "</td>"; 
					 output += "<td>" + total_Amount + "</td>"; 
					 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
							 + "<td><form method='post' action='Users.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
							 + "<input name='order_ID' type='hidden' value='" + order_ID + "'>" + "</form></td></tr>"; 
				 } 
				 con.close(); 
				 // Complete the html table
				 output += "</table>"; 
			} 
			catch (Exception e) 
			{ 
				 output = "Error while reading the user."; 
				 System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
				
		public String updateOrder(String order_ID,Integer price, Integer quantity, String phone, String delivery_Name, String delivery_Address, Integer total_Amount)
		{ 
			String output = ""; 
			
			try
			{ 
				Connection con = connect(); 
			
				if (con == null) 
				{
					return "Error while connecting to the database for updating."; 
				} 
				
				 // create a prepared statement
				 String query = "UPDATE order SET price=?,quantity=?,phone=?,delivery_Name=?,delivery_Address=?,total_Amount=? WHERE order_ID=?"; 
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, price);
				 preparedStmt.setInt(2, quantity); 
				 preparedStmt.setString(3, phone);
				 preparedStmt.setString(4, delivery_Name); 
				 preparedStmt.setString(5, delivery_Address);
				 preparedStmt.setInt(5, Integer.parseInt(order_ID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 output = "Updated successfully"; 
			} 
			catch (Exception e) 
			{ 
				 output = "Error while updating the item."; 
				 System.err.println(e.getMessage()); 
			}
			
			return output; 
		} 
				
		public String deleteOrder(String order_ID) 
		{ 
			String output = ""; 
			
			try
			{ 
				 Connection con = connect(); 
				 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for deleting."; 
				 } 
				 
				 // create a prepared statement
				 String query = "delete from User where order_ID=?"; 
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(order_ID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Deleted successfully"; 
			} 
			catch (Exception e) 
			{ 
				 output = "Error while deleting the user."; 
				 System.err.println(e.getMessage()); 
			} 
			
			return output; 
		} 
	} 

