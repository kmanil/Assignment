package com.test.mvvm.data.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Form2")
public class Form2 {
    @PrimaryKey
    @SerializedName("formId2")
    @Expose
    public Integer formId2;
    @SerializedName("formId1")
    @Expose
    public Integer formId1;
    @SerializedName("subject1")
    @Expose
    public String subject1;
    @SerializedName("subject2")
    @Expose
    public String subject2;
    @SerializedName("subject3")
    @Expose
    public String subject3;
    @SerializedName("subject4")
    @Expose
    public String subject4;
    @SerializedName("subject5")
    @Expose
    public String subject5;


}
