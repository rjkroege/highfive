package com.google.sps.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.sps.servlets.queryExecuter;

@WebServlet("/questionAndAnswer")
public class questionAndAnswer extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String question_ID = request.getParameter("question_ID");

        String questionAndAnswerQuery = "(SELECT question as data FROM team5project.QUESTIONS WHERE question_id = "
                + question_ID + ") UNION (SELECT answer FROM team5project.ANSWERS WHERE question_id = " + question_ID
                + ");";

        try {
            response.setContentType("application/json;");
            response.getWriter().println(queryExecuter.execute(questionAndAnswerQuery));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
