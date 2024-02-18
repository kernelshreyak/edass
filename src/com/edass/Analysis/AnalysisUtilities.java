package com.edass.Analysis;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnalysisUtilities {
    public static String[][] dataframeToRows(Dataset<Row> df,int preview_rows){
        // Get all rows as a list
        List<Row> rows = df.collectAsList();

        // Assuming dataframe is not empty and has a consistent schema in all rows
        int numRows = rows.size();
        int numCols = rows.get(0).size();
        String[][] result = new String[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            if(i > preview_rows){
                break;
            }
            Row row = rows.get(i);
            for (int j = 0; j < numCols; j++) {
                result[i][j] = row.get(j) != null ? row.get(j).toString() : "null";
            }
        }
        return result;
    }
}
