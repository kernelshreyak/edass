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
    public void initSession(){
        SparkSession session = SparkSession
                .builder()
                .appName("Java Spark SQL CSV Reader")
                .master("local")
                .getOrCreate();
        this.spark = session;
        System.out.println("------Analysis Engine started-------");
    }
    public void loadDataFrame(String filePath,String format) {
        try {
            // Create a Spark session


            // Read the CSV file into a DataFrame
            Dataset<Row> df = this.spark.read()
                    .format(format)
                    .option("header", "true") // Use first line of all files as header
                    .option("inferSchema", "true") // Automatically infer data types
                    .load(filePath);

            // Show the DataFrame content
            df.show();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void stopSession(){
        this.spark.stop();
        System.out.println("------Analysis Engine stopped-------");
    }
}
