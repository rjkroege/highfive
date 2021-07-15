package com.google.sps.servlets;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

@WebServlet("/test")
public class test extends HttpServlet
{
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
       Connection conn; // Cloud SQL connection 
      try
      {
          conn = DriverManager.getConnection("jdbc:mysql:///team5project?cloudSqlInstance=wramos-sps-summer21:us-central1:team5project&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=root&password=spsTeam5&useSSL=false"); 
          ResultSet rs = conn.prepareStatement("SELECT * FROM QUESTIONS").executeQuery(); 
          List<JSONObject> resList = new ArrayList<JSONObject>();
        try {
            // get column names
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnCnt = rsMeta.getColumnCount();
            List<String> columnNames = new ArrayList<String>();
            for(int i=1;i<=columnCnt;i++) {
                columnNames.add(rsMeta.getColumnName(i).toUpperCase());
            }

            while(rs.next()) { // convert each object to an human readable JSON object
                JSONObject obj = new JSONObject();
                for(int i=1;i<=columnCnt;i++) {
                    String key = columnNames.get(i - 1);
                    String value = rs.getString(i);
                    obj.put(key, value);
                }
                resList.add(obj);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
          response.setContentType("application/json;"); 
          response.getWriter().println(resList);

      }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      finally{
          //Nothing here
      }
  }
}
