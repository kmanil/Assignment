package com.test.mvvm.data.local.db;

import com.test.mvvm.data.model.db.Form1;
import com.test.mvvm.data.model.db.Form2;
import com.test.mvvm.data.model.db.Option;
import com.test.mvvm.data.model.db.Question;
import com.test.mvvm.data.model.db.User;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;



@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<List<Question>> getAllQuestions() {
        return Observable.fromCallable(new Callable<List<Question>>() {
            @Override
            public List<Question> call() throws Exception {
                return mAppDatabase.questionDao().loadAll();
            }
        });
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return Observable.fromCallable(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return mAppDatabase.userDao().loadAll();
            }
        });
    }

    @Override
    public Observable<User> getUsersByCredentials(String userId, String password) {
        return Observable.fromCallable(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return mAppDatabase.userDao().findByName(userId,password);
            }
        });
    }

    @Override
    public Observable<User> getUsersByEmail(String userId,String mobile) {
        return Observable.fromCallable(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return mAppDatabase.userDao().findByNameEmail(userId,mobile);
            }
        });
    }

    @Override
    public Observable<List<Option>> getOptionsForQuestionId(final Long questionId) {
        return Observable.fromCallable(new Callable<List<Option>>() {
            @Override
            public List<Option> call() throws Exception {
                return mAppDatabase.optionDao().loadAllByQuestionId(questionId);
            }
        });
    }

    @Override
    public Observable<Boolean> insertUser(final User user) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.userDao().insert(user);
                return true;
            }
        });
    }

    @Override
    public Observable<Long> insertForm1(Form1 form1) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mAppDatabase.form1Dao().insert(form1);
            }
        });
    }

    @Override
    public Observable<List<Form1>> getForm1() {
        return Observable.fromCallable(new Callable<List<Form1>>() {
            @Override
            public List<Form1> call() throws Exception {
                return mAppDatabase.form1Dao().getList();
            }
        });
    }

    @Override
    public Observable<Long> insertForm2(Form2 form2) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mAppDatabase.form2Dao().insert(form2);
            }
        });
    }

    @Override
    public Observable<Boolean> isOptionEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return mAppDatabase.optionDao().loadAll().isEmpty();
            }
        });
    }

    @Override
    public Observable<Boolean> isQuestionEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return mAppDatabase.questionDao().loadAll().isEmpty();
            }
        });
    }

    @Override
    public Observable<Boolean> saveOption(final Option option) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.optionDao().insert(option);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveOptionList(final List<Option> optionList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.optionDao().insertAll(optionList);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveQuestion(final Question question) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.questionDao().insert(question);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveQuestionList(final List<Question> questionList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.questionDao().insertAll(questionList);
                return true;
            }
        });
    }
}
