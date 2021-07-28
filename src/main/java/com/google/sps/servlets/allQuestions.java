package com.google.sps.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.sps.servlets.queryExecuter;

@WebServlet("/allQuestions")
public class allQuestions extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String end = request.getParameter("end");

        String replaceEnd = end.replace("(", "%"); 
        replaceEnd = replaceEnd.replace(")", "'"); 

        String iphoneQuestionQuery = "SELECT question, question_ID, device_type FROM QUESTIONS WHERE ("+ replaceEnd +");";

        try {
            response.setContentType("application/json;");
            response.getWriter().println(queryExecuter.execute(iphoneQuestionQuery));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
