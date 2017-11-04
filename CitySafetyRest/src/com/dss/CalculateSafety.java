package com.dss;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class CalculateSafety extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public CalculateSafety() 
    {
        super();
       
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter writer=response.getWriter();
		String city=request.getParameter("city");
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
		    /*statement.executeUpdate("create table negtraits(City varchar2(20),Mugging number,Assault number,Shooting number,Theft number,Murder number,Kidnap number)");
		    System.out.println("negtraits Table Successfully Created");
		    statement.executeUpdate("insert into negtraits values('Hartford',40,40,10,40,20,10)");
		    statement.executeUpdate("insert into negtraits values('Stamford',45,40,10,45,20,10)");
		    statement.executeUpdate("insert into negtraits values('Bridgeport',50,40,30,40,20,40)");
		    statement.executeUpdate("insert into negtraits values('Greenwich',10,10,10,40,20,10)");
		    statement.executeUpdate("insert into negtraits values('Norwalk',15,15,5,10,15,5)");
		    statement.executeUpdate("insert into negtraits values('Waterbury',20,20,30,30,25,35)");
		    statement.executeUpdate("insert into negtraits values('Danbury',10,10,5,5,25,10)");
		    statement.executeUpdate("insert into negtraits values('Fairfield',10,10,10,10,20,10)");
		    statement.executeUpdate("insert into negtraits values('Milford',35,45,50,60,70,80)");
		    statement.executeUpdate("insert into negtraits values('Westport',65,70,40,60,20,80)");
		    statement.executeUpdate("insert into negtraits values('New Britian',10,15,45,55,75,10)");
		    statement.executeUpdate("insert into negtraits values('Hamden',30,35,35,40,20,60)");
		    statement.executeUpdate("insert into negtraits values('Manchester',10,10,20,40,20,10)");
		    System.out.println("negtraits values successfully inserted");
		    statement.executeUpdate("create table postraits(City varchar2(20),Culture number,Connected number,Effeciency number,SecurityMeasure number,HealthCare number,NaturalDisasterPreparedness number)");
		    System.out.println("postraits Table Successfully Created");
		    statement.executeUpdate("insert into negtraits values('Hartford',60,60,70,60,70,70)");
		    statement.executeUpdate("insert into postraits values('Stamford',70,60,50,60,50,70)");
		    statement.executeUpdate("insert into postraits values('Bridgeport',40,40,40,40,60,50)");
		    statement.executeUpdate("insert into postraits values('Greenwich',80,70,80,70,70,70)");
		    statement.executeUpdate("insert into postraits values('Norwalk',60,60,70,50,70,60)");
		    statement.executeUpdate("insert into postraits values('Waterbury',60,50,50,60,50,70)");
		    statement.executeUpdate("insert into postraits values('Danbury',10,10,10,40,20,10)");
		    statement.executeUpdate("insert into postraits values('Fairfield',50,60,60,70,60,70)");
		    statement.executeUpdate("insert into postraits values('Milford',10,10,10,40,20,10)");
		    statement.executeUpdate("insert into postraits values('Westport',10,10,10,40,20,10)");
		    statement.executeUpdate("insert into postraits values('New Britian',10,10,10,40,20,10)");
		    statement.executeUpdate("insert into postraits values('Hamden',10,10,10,40,20,10)");
		    statement.executeUpdate("insert into postraits values('Manchester',10,10,10,40,20,10)");*/
		    
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
			System.out.println("Exception is: "+e);
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("Start.jsp");
		request.setAttribute("result", result);
		dispatcher.include(request,response);
	}

}
