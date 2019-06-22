package com.test.mvvm.scheduler;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

public interface RetrofitInterface {

    // Regular Retrofit 2 GET request
    @Streaming
    @GET
    Call<ResponseBody> downloadFileByUrl(@Url String fileUrl);


    // Retrofit 2 GET request for rxjava
    @Streaming
    @POST
    Observable<Response<ResponseBody>> dataSyncWithRx(@Url String fileUrl,
                                                      @Body Object object);
}
