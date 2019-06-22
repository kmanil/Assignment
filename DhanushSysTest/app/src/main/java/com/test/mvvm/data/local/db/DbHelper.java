
package com.test.mvvm.data.local.db;

import com.test.mvvm.data.model.db.Form1;
import com.test.mvvm.data.model.db.Form2;
import com.test.mvvm.data.model.db.Option;
import com.test.mvvm.data.model.db.Question;
import com.test.mvvm.data.model.db.User;

import java.util.List;

import io.reactivex.Observable;


public interface DbHelper {

    Observable<List<Question>> getAllQuestions();

    Observable<List<User>> getAllUsers();

    Observable<User> getUsersByCredentials(String userId,String password);

    Observable<User> getUsersByEmail(String userId,String mobile);

    Observable<List<Option>> getOptionsForQuestionId(Long questionId);

    Observable<Boolean> insertUser(final User user);

    Observable<Long> insertForm1(final Form1 form1);

    Observable<List<Form1>> getForm1();

    Observable<Long> insertForm2(final Form2 form2);

    Observable<Boolean> isOptionEmpty();

    Observable<Boolean> isQuestionEmpty();

    Observable<Boolean> saveOption(Option option);

    Observable<Boolean> saveOptionList(List<Option> optionList);

    Observable<Boolean> saveQuestion(Question question);

    Observable<Boolean> saveQuestionList(List<Question> questionList);
}
