
package com.test.mvvm.data.local.db.dao;


import com.test.mvvm.data.model.db.Option;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface OptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Option option);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Option> options);

    @Query("SELECT * FROM options")
    List<Option> loadAll();

    @Query("SELECT * FROM options WHERE question_id = :questionId")
    List<Option> loadAllByQuestionId(Long questionId);
}
