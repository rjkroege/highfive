package com.google.sps.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.sps.servlets.queryExecuter;

@WebServlet("/androidQuestions")
public class androidQuestions extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String end = request.getParameter("end");

        String replaceEnd = end.replace("(", "%"); 
        replaceEnd = replaceEnd.replace(")", "'"); 

        String androidQuestionQuery = "SELECT question, question_ID, device_type FROM QUESTIONS WHERE ("+ replaceEnd +") AND device_type = 'android';";

        try {
            response.setContentType("application/json;");
            response.getWriter().println(queryExecuter.execute(androidQuestionQuery));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
