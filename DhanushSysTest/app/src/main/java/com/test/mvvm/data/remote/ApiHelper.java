package com.test.mvvm.data.remote;


import com.test.mvvm.data.model.api.BlogResponse;
import com.test.mvvm.data.model.api.LoginRequest;
import com.test.mvvm.data.model.api.LoginResponse;
import com.test.mvvm.data.model.api.LogoutResponse;
import com.test.mvvm.data.model.api.OpenSourceResponse;

import io.reactivex.Single;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

public interface ApiHelper {

    Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request);

    Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request);

    Single<LogoutResponse> doLogoutApiCall();

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    ApiHeader getApiHeader();

    Single<BlogResponse> getBlogApiCall();

    Single<OpenSourceResponse> getOpenSourceApiCall();
}
