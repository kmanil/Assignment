
package com.test.mvvm.data.local.db.dao;


import com.test.mvvm.data.model.db.Form2;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;


@Dao
public interface Form2Dao {


    /*@Query("SELECT * FROM Form1 WHERE (email =:name or mobile =:name) and password =:password LIMIT 1")
    User findByName(String name, String password);

    @Query("SELECT * FROM users WHERE email =:name or mobile =:mobile LIMIT 1")
    User findByNameEmail(String name, String mobile);
*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Form2 form2);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Form2> form2s);
/*
    @Query("SELECT * FROM users")
    List<User> loadAll();

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    List<User> loadAllByIds(List<Integer> userIds);*/
}
