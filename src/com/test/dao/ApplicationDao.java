package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.beans.Product;
import com.test.beans.User;

public class ApplicationDao {

	Statement st;
	ResultSet rs;

	public List<Product> searchProducts(String searchString) {
		
		Product prod = null;
		List <Product> products = new ArrayList();
		
		
	try{
		Connection connection = DBConnection.getConnectionToDatabase();
		String query = "select * from products where product_name like '%"+searchString+"%'";
		
st = connection.createStatement();
rs = st.executeQuery(query);
	
	
	while (rs.next()) {
	
		prod = new Product();
		
		prod.setProductId(rs.getInt("product_Id"));
		prod.setProductImgPath("productImgPath");
		prod.setProductName("productName");
		products.add(prod);	
	
	}
	
	
	
	}catch(SQLException ex) {
		ex.printStackTrace();
	}
	
	
return products;}



public int registeruser(User user) {
	
	int rowsAffected = 0;
	
	try {
	PreparedStatement pst;	
	//get connection for the database	
	Connection connection = DBConnection.getConnectionToDatabase();
	
	//write insert query
	String queryForRegister = "insert into users values (?,?,?,?,?,?) ";
		
//set parameter with prepared statement
	pst = connection.prepareStatement(queryForRegister);
	
	
	pst.setString( 1, user.getUsername());
	pst.setString( 2, user.getPassword());
	pst.setString( 3, user.getFirstName());
	pst.setString( 4, user.getLastName());
	pst.setInt( 5, user.getAge());
	pst.setString( 6, user.getActivity());


	rowsAffected = pst.executeUpdate();
	
	}catch(Exception e) {
		
	}

	return rowsAffected;
 }


}