package com.edass.Analysis;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
/**
 * The main Analysis Engine powered by Apache Spark
 */
public class AnalysisEngine {
    private SparkSession spark;  //For efficiency, it creates and persists the session
    private Dataset<Row> df;
    public void initSession(){
        this.spark = SparkSession
                .builder()
                .appName("Java Spark SQL CSV Reader")
                .master("local")
                .getOrCreate();
        System.out.println("------EDASS Analysis Engine started-------");
    }
    public void loadDataFrame(String filePath,String format) {
        try {
            this.df = this.spark.read()
                    .format(format)
                    .option("header", "true") // Use first line of all files as header
                    .option("treatEmptyValuesAsNulls", "true")
                    .option("inferSchema", "true") // Automatically infer data types
                    .load(filePath);
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

    public Dataset<Row> descriptiveStats(){
        return this.df.describe();
    }
}
