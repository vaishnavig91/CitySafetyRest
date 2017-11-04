package com.dss.rest;
import java.sql.*;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

@Path("/safety")
public class CalculateSafety 
{

	@Path("/post")
    @POST
    @Produces(MediaType.APPLICATION_JSON)  
	public String findSafetyRatingPost(String jsonobject) throws JsonParseException, JsonMappingException, IOException, SQLException {
    	
		String city = "";
		Map<String,String> resultMap = new HashMap<String,String>();
    	ObjectMapper mapper = new ObjectMapper();
    	Map<String,Object> map = mapper.readValue(jsonobject, Map.class);
    	Connection connection = getConnection();
    	for(String key:map.keySet())
    	{
    		Object value = map.get(key);
    		city = value.toString();
    		String result = findSafety(city,connection);
    		System.out.println(key+" - "+value);
    		resultMap.put(city, result);
    	}
		//return "<?xml version=\"1.0\"?> <result>" + result + "</result>";
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String jsonresp = "";
		try{
			jsonresp = ow.writeValueAsString(resultMap);
		}catch(Exception e){}
		
		return jsonresp;
	}
	
	@Path("/{city}")
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public String findSafetyRating(@PathParam("city") String tempcity) {
    	
    	String city=  tempcity;
		String result="Safety Rating is:";
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
	        Statement statement=connection.createStatement();
	        System.out.println("Connection Established "+connection);
	        int p1,p2,p3,p4,p5,p6;
		    int n1,n2,n3,n4,n5,n6;
		    int pavg=0,navg = 0;
		 
		    
		    String q3="select * from postraits where city='"+city+"'";
		    String q4="select * from negtraits where city='"+city+"'"; 
		    ResultSet rsPosTra=statement.executeQuery(q3);
		    while (rsPosTra.next()) {
				//type type = (type) en.nextElement();
//				String CITY=rsPosTra.getString("City");
				p1=rsPosTra.getInt(2);
				p2=rsPosTra.getInt(3);
				p3=rsPosTra.getInt(4);
				p4=rsPosTra.getInt(5);
				p5=rsPosTra.getInt(6);
				p6=rsPosTra.getInt(7);
				pavg=(p1+p2+p3+p4+p5+p6)/6;
			}
		    
		    ResultSet rsNegTra=statement.executeQuery(q4);
		    while (rsNegTra.next()) {
				//type type = (type) en.nextElement();
//				String CITY=rsPosTra.getString("City");
				n1=rsNegTra.getInt(2);
				n2=rsNegTra.getInt(3);
				n3=rsNegTra.getInt(4);
				n4=rsNegTra.getInt(5);
				n5=rsNegTra.getInt(6);
				n6=rsNegTra.getInt(7);
				navg=(n1+n2+n3+n4+n5+n6)/6; 
			}
			
			if(navg<pavg)
			{
				int z1=pavg-navg;
				if(z1<20)
				{
					result="Safety Rating for city of "+city+"  is 6";
				}
				else if(z1==80)
				{
					result="Best City To Live";
				}
				else if(z1>=60 && z1<80)
				{
					result="Safety Rating for city of "+city+" is 9";
				}
				else if(z1>=40 && z1<=60)
				{
					result="Safety Rating for city of "+city+" is 8";
				}
				else if(z1>=20 && z1<=40)
				{
					result="Safety Rating for city of "+city+" is 7";
				}

			}
			else if(navg>pavg)
			{
				int z=navg-pavg;
				if(z<20)
				{
					result="Safety Rating for city of "+city+" is 4";
				}
				else if(z>=20 && z<=35)
				{
					result="Safety Rating for city of "+city+" is 3";
				}
				else if(z>=35 && z<=50)
				{
					result="Safety Rating for city of "+city+" is 2";
				}
				else if(z>=50 && z<=80)
				{
					result="Safety Rating for city of "+city+" is 1";
				}
			}
			else if(navg==pavg)
			{
				result="Safety Rating for city of "+city+" is 5";
			}
		}
		catch(Exception e)
		{
			result = "Exception is: "+e;
			System.out.println("Exception is: "+e);
		}
    	
		//return "<?xml version=\"1.0\"?> <result>" + result + "</result>";
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String jsonresp = "";
		try{
			jsonresp = ow.writeValueAsString(result);
		}catch(Exception e){}
		
		return jsonresp;
	}
	Connection getConnection()
	{
		Connection connection = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return connection;
	}
	String findSafety(String city,Connection connection) throws SQLException
	{
		String safetyValue = "";
		Statement statement=connection.createStatement();
        System.out.println("Connection Established "+connection);
        int p1,p2,p3,p4,p5,p6;
	    int n1,n2,n3,n4,n5,n6;
	    int pavg=0,navg = 0;
	 
	    
	    String q3="select * from postraits where city='"+city+"'";
	    String q4="select * from negtraits where city='"+city+"'"; 
	    ResultSet rsPosTra=statement.executeQuery(q3);
	    while (rsPosTra.next()) {
			//type type = (type) en.nextElement();
//			String CITY=rsPosTra.getString("City");
			p1=rsPosTra.getInt(2);
			p2=rsPosTra.getInt(3);
			p3=rsPosTra.getInt(4);
			p4=rsPosTra.getInt(5);
			p5=rsPosTra.getInt(6);
			p6=rsPosTra.getInt(7);
			pavg=(p1+p2+p3+p4+p5+p6)/6;
		}
	    
	    ResultSet rsNegTra=statement.executeQuery(q4);
	    while (rsNegTra.next()) {
			//type type = (type) en.nextElement();
//			String CITY=rsPosTra.getString("City");
			n1=rsNegTra.getInt(2);
			n2=rsNegTra.getInt(3);
			n3=rsNegTra.getInt(4);
			n4=rsNegTra.getInt(5);
			n5=rsNegTra.getInt(6);
			n6=rsNegTra.getInt(7);
			navg=(n1+n2+n3+n4+n5+n6)/6; 
		}
		
		if(navg<pavg)
		{
			int z1=pavg-navg;
			if(z1<20)
			{
				safetyValue="6";
			}
			else if(z1==80)
			{
				safetyValue="Best City To Live";
			}
			else if(z1>=60 && z1<80)
			{
				safetyValue="9";
			}
			else if(z1>=40 && z1<=60)
			{
				safetyValue="8";
			}
			else if(z1>=20 && z1<=40)
			{
				safetyValue="7";
			}

		}
		else if(navg>pavg)
		{
			int z=navg-pavg;
			if(z<20)
			{
				safetyValue="4";
			}
			else if(z>=20 && z<=35)
			{
				safetyValue="3";
			}
			else if(z>=35 && z<=50)
			{
				safetyValue="2";
			}
			else if(z>=50 && z<=80)
			{
				safetyValue="1";
			}
		}
		else if(navg==pavg)
		{
			safetyValue="5";
		}
	
		return safetyValue;
	}
}
