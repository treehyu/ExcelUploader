package com.col.util;
import java.sql.DriverManager;
import java.sql.Connection;

public class ConnUtil {
	
	private static ConnUtil util=new ConnUtil();
	private Connection conn=null;
	private String host="192.168.102.11";
	private String user="app2";
	private String pw="1";
	
	private ConnUtil()
	{
		if(conn==null)
			conn=establishConn(host, user, pw);
	}
	
	private Connection establishConn(String host, String user, String pw)
	{
		Connection result=null;
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			result=DriverManager.getConnection("jdbc:oracle:thin:@"+host+":1521:megabox", user, pw);
			
			
		}catch(Exception e){ 
			System.out.println("Connection Exception: "+e.getMessage()); 
		}
		
		return result;
		
	}
	
	public static synchronized ConnUtil getUtil()
	{
		return util;
	}
	
	public Connection getConn() throws Exception
	{
		if(conn==null)
			conn=establishConn(host, user, pw);
		
		if(!conn.isValid(1))
			conn=establishConn(host, user, pw);
		
		return conn;
	}
	
}
