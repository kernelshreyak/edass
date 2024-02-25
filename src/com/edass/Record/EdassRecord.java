package com.edass.Record;



/**
 * Represents an operation (Analysis, Transformation etc.) on EDASS which can be stored and executed at a later time
 */
public class EdassRecord {
    private final String recordName;
    private final EdassRecord parentRecord;

    public EdassRecord(String recordName, EdassRecord parentRecord) {
        this.recordName = recordName;
        this.parentRecord = parentRecord;
    }

    public String getRecordName() {
        return recordName;
    }

    public EdassRecord getParentRecord() {
        return parentRecord;
    }
}
