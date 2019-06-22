package com.test.mvvm.data.local.db;


import com.test.mvvm.data.local.db.dao.Form1Dao;
import com.test.mvvm.data.local.db.dao.Form2Dao;
import com.test.mvvm.data.local.db.dao.OptionDao;
import com.test.mvvm.data.local.db.dao.QuestionDao;
import com.test.mvvm.data.local.db.dao.UserDao;
import com.test.mvvm.data.model.db.Form1;
import com.test.mvvm.data.model.db.Form2;
import com.test.mvvm.data.model.db.Option;
import com.test.mvvm.data.model.db.Question;
import com.test.mvvm.data.model.db.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Question.class, Option.class, Form1.class, Form2.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract OptionDao optionDao();

    public abstract QuestionDao questionDao();

    public abstract UserDao userDao();

    public abstract Form1Dao form1Dao();

    public abstract Form2Dao form2Dao();
}
