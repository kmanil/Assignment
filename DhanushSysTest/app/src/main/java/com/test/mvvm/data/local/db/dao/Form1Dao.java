
package com.test.mvvm.data.local.db.dao;


import com.test.mvvm.data.model.db.Form1;
import com.test.mvvm.data.model.db.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
public interface Form1Dao {

    @Delete
    void delete(Form1 form1);

    /*@Query("SELECT * FROM Form1 WHERE (email =:name or mobile =:name) and password =:password LIMIT 1")
    User findByName(String name, String password);

    @Query("SELECT * FROM users WHERE email =:name or mobile =:mobile LIMIT 1")
    User findByNameEmail(String name, String mobile);
*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Form1 form1);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Query("SELECT * FROM Form1")
    List<Form1> getList();
/*
    @Query("SELECT * FROM users")
    List<User> loadAll();

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    List<User> loadAllByIds(List<Integer> userIds);*/
}
