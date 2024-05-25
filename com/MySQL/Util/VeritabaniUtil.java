package com.MySQL.Util;
import java.sql.*;


public class VeritabaniUtil {

	static Connection conn= null;
	
	public static Connection Baglan() {
		try {
			//"jdbc:mysql://ServerIPAdresi/db_ismi", "kullanici" , "sifre";
			conn=DriverManager.getConnection("jdbc:mysql://localhost/ogrencikayitsistei","root","");
			System.out.println("conection");
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
			return null;
		}

	}
	
	
     public static void BaglantiKapat() {
    	 conn= Baglan();
    	 try {
    		 conn.close(); 
    		 System.out.println("kapandi");
    	 }
    	 catch (Exception e) {
 			// TODO: handle exception
 			System.out.println(e.getMessage().toString());
 			
 		}	 
     }
	
	
	
}
