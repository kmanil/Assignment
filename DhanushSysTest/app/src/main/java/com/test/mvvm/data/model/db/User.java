package com.test.mvvm.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Kumar Anil on 19/06/2019.
 */
@Entity(tableName = "users")
public class User {

    @ColumnInfo(name = "created_at")
    public String createdAt;

    @PrimaryKey
    public Long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "mobile")
    public String mobile;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "updated_at")
    public String updatedAt;
}
