package com.google.sps.servlets;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;

import com.google.sps.servlets.connection;

public class queryExecuter {

    public static List<JSONObject> execute(String query) throws SQLException {
        ResultSet rs = connection.conn().prepareStatement(query).executeQuery();
        List<JSONObject> resList = new ArrayList<JSONObject>();

        try {
            // get column names
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnCnt = rsMeta.getColumnCount();
            List<String> columnNames = new ArrayList<String>();
            for (int i = 1; i <= columnCnt; i++) {
                columnNames.add(rsMeta.getColumnName(i).toUpperCase());
            }

            while (rs.next()) { // convert each object to an human readable JSON object
                JSONObject obj = new JSONObject();
                for (int i = 1; i <= columnCnt; i++) {
                    String key = columnNames.get(i - 1);
                    String value = rs.getString(i);
                    obj.put(key, value);
                }
                resList.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resList;
    }
}
