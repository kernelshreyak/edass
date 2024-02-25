package com.edass.Record;



/**
 * Represents an operation (Analysis, Transformation etc.) on EDASS which can be stored and executed at a later time
 */
public class EdassRecord {
    private String recordName;

    public EdassRecord(String recordName) {
        this.recordName = recordName;
    }

    public String getRecordName() {
        return recordName;
    }
}
