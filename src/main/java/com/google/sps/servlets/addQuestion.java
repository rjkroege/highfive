package com.google.sps.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.sps.servlets.queryExecuter;
import com.google.sps.servlets.connection;

import org.json.simple.JSONObject;

@WebServlet("/addQuestion")
public class addQuestion extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter("email");
        String question = request.getParameter("question");
        String device_type = request.getParameter("device_type");

        try {
            List<JSONObject> checkEmail = queryExecuter
                    .execute("SELECT COUNT(email) as C FROM USERS WHERE email = '" + email + "';");
            int result = Integer.parseInt((String) checkEmail.get(0).get("C"));

            if (result == 0) {
                System.out.print("No existe \n");
                connection.conn().createStatement()
                        .executeUpdate("INSERT INTO USERS (email) VALUES ('" + email + "');");
                List<JSONObject> user_IDcount = queryExecuter.execute("SELECT COUNT(email) as C FROM USERS");

                int user_ID = Integer.parseInt((String) user_IDcount.get(0).get("C"));

                String questionQuery = "INSERT INTO QUESTIONS (user_ID, question, device_type) VALUES (" + user_ID
                        + ",'" + question + "','" + device_type + "');";

                connection.conn().createStatement().executeUpdate(questionQuery);
                System.out.print("Ya termino \n");
            } else {
                System.out.print("Existe \n");
                List<JSONObject> user_IDcheck = queryExecuter
                        .execute("SELECT user_ID FROM USERS WHERE email = '" + email + "';");

                int user_ID = Integer.parseInt((String) user_IDcheck.get(0).get("USER_ID"));

                String questionQuery = "INSERT INTO QUESTIONS (user_ID, question, device_type) VALUES (" + user_ID
                        + ",'" + question + "','" + device_type + "');";

                connection.conn().createStatement().executeUpdate(questionQuery);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
