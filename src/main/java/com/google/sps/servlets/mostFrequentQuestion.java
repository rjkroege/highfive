package com.google.sps.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.sps.servlets.queryExecuter;

@WebServlet("/mostFrequentQuestion")
public class mostFrequentQuestion extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String mostFrequentQuestionQuery = "SELECT question, question_ID, device_type FROM QUESTIONS ORDER BY question_ID DESC LIMIT 5;";

        try {
            response.setContentType("application/json;");
            response.getWriter().println(queryExecuter.execute(mostFrequentQuestionQuery));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
