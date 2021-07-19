package com.google.sps.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.sps.servlets.queryExecuter;

@WebServlet("/addAnswer")
public class addAnswer extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String question_ID = request.getParameter("question_ID");
        String answer = request.getParameter("answer");

        String addAnswerQuery = "INSERT INTO ANSWER (question_ID, answer) VALUES (" + question_ID + ", '" + answer
                + "');";

        try {
            response.setContentType("application/json;");
            response.getWriter().println(queryExecuter.execute(addAnswerQuery));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
