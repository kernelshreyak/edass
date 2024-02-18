package com.edass.Analysis;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
/**
 * The main Analysis Engine powered by Apache Spark
 * For efficiency it creates and persists the session
 */
public class AnalysisEngine {
    private SparkSession spark;
    private Dataset<Row> df;
    public void initSession(){
        SparkSession session = SparkSession
                .builder()
                .appName("Java Spark SQL CSV Reader")
                .master("local")
                .getOrCreate();
        this.spark = session;
        System.out.println("------EDASS Analysis Engine started-------");
    }
    public void loadDataFrame(String filePath,String format) {
        try {
            Dataset<Row> df = this.spark.read()
                    .format(format)
                    .option("header", "true") // Use first line of all files as header
                    .option("treatEmptyValuesAsNulls", "true")
                    .option("inferSchema", "true") // Automatically infer data types
                    .load(filePath);
            this.df = df;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Dataset<Row> getDataFrame() {
        return df;
    }

    public void stopSession(){
        this.spark.stop();
        System.out.println("------EDASS Analysis Engine stopped-------");
    }

    public void descriptiveStats(){
        this.df.describe().show();
    }
}
