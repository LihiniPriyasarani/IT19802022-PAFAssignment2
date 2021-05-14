package com;

import java.sql.*;

public class user {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "localhost", "Broxy1234!@#");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String readUsers() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>User Code</th> <th>User Name</th><th>User Email</th>"
					+ "<th>User Phone</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String userID = Integer.toString(rs.getInt("userID"));
				String userCode = rs.getString("userCode");
				String userName = rs.getString("userName");
				String userEmail = rs.getString("userEmail");
				String userPhone = rs.getString("userPhone");
				// Add into the html table
				output += "<tr><td><input id='hidUserIDUpdate' name='hidUserIDUpdate' type='hidden' value='" + userID
						+ "'>" + userCode + "</td>";
				output += "<td>" + userName + "</td>";
				output += "<td>" + userEmail + "</td>";
				output += "<td>" + userPhone + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-userid='"
						+ userID + "'> </td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='"
						+ userID + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the users.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertUser(String code, String name, String email, String phone) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into user (`userID`,`userCode`,`userName`,`userEmail`,`userPhone`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, phone);

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUser(String ID, String code, String name, String email, String phone) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE user SET userCode=?,userName=?,userEmail=?,userPhone=? WHERE userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, phone);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUser(String userID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from user where userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(userID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":  \"Error while deleting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}