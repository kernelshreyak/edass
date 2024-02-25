package com.edass.Record;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class AnalysisRecord extends EdassRecord{
    private final Dataset<Row> dataFrame;
    private final String analysisOutput;
    public AnalysisRecord(String recordName, Dataset<Row> dataFrame, String analysisOutput) {
        super(recordName);
        this.dataFrame = dataFrame;
        this.analysisOutput = analysisOutput;
    }

    public Dataset<Row> getDataFrame() {
        return dataFrame;
    }

    public String getAnalysisOutput() {
        return analysisOutput;
    }
}
