package com.test.mvvm.data.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Form1")
public class Form1 {
    @PrimaryKey
    @SerializedName("formId1")
    @Expose
    private Integer formId1;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public Boolean isLoading=true;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Form1 form1 = (Form1) o;

        if (status != form1.status) return false;
        if (!formId1.equals(form1.formId1)) return false;
        if (!name.equals(form1.name)) return false;
        if (!mobile.equals(form1.mobile)) return false;
        if (!email.equals(form1.email)) return false;
        return address.equals(form1.address);
    }

    @Override
    public int hashCode() {
        int result = formId1.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + mobile.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + status;
        return result;
    }

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private String address;

    private int status=0;



    public Integer getFormId1() {
        return formId1;
    }

    public void setFormId1(Integer formId1) {
        this.formId1 = formId1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
