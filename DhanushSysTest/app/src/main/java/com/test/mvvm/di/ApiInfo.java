package com.test.mvvm.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiInfo {

}
