package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class project 

	{ // A common method to connect to the DB
		private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("localhost:3307/projectdb", "root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

	public String insertproject(String name, String category, String budget, String description)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into project(`projectID`,`projectName`,`category`,`budget`,`description`)"
	 + " values (?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, name);
	 preparedStmt.setString(3, category);
	 preparedStmt.setString(4, budget);
	 preparedStmt.setString(5, description);
	// execute the statement

	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the project.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	public String readproject()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr> <th>projectID</th> + <th>projectName</th>" + "<th>category</th>" + "<th>budget</th>" + "<th>description</th>" + "<th>Update</th> <th>Remove</th> </tr>";
	 

	 String query = "select * from project";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String projectID = Integer.toString(rs.getInt("projectID"));
	 String projectName = rs.getString("projectName");
	 String category = rs.getString("category");
	 String budget = rs.getString("budget");
	 String description = rs.getString("description");
	 // Add into the html table
	 output += "<tr><td>" + projectName + "</td>";
	 output += "<td>" + category + "</td>";
	 output += "<td>" + budget + "</td>";
	 output += "<td>" + description + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='projectID' type='hidden' value='" + projectID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the projects.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	public String updateproject(String name, String category, String budget, String description, int projectID)

	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE project SET projectName=?,category=?,budget=?,description=? WHERE projectID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1,name );
	 preparedStmt.setString(2, category);
	 preparedStmt.setString(3, budget);
	 preparedStmt.setString(4, description);
	 preparedStmt.setInt(5, projectID);

	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the Details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

		public String deleteproject(String projectID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from projects where projectID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(projectID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the projects.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	}

