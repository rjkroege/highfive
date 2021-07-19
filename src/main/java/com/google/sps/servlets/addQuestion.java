package com.google.sps.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.sps.servlets.queryExecuter;

@WebServlet("/addQuestion")
public class addQuestion extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String user_ID = request.getParameter("user_ID");
        String question = request.getParameter("question");
        String device_type = request.getParameter("device_type");

        String addQuestionQuery = "INSERT INTO QUESTIONS (user_ID, question, device_type) VALUES (" + user_ID + ", '"
                + question + "', '" + device_type + "');";

        try {
            response.setContentType("application/json;");
            response.getWriter().println(queryExecuter.execute(addQuestionQuery));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
