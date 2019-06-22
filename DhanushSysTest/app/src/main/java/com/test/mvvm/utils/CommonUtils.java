package com.test.mvvm.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.util.Patterns;


import com.google.gson.Gson;
import com.test.mvvm.R;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

public final class CommonUtils {
    public static String jsonMain ="{\"status_code\":\"success\",\"message\":\"success\",\"data\":[{\"title\":\"A 1\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 2\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 3\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 4\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 5\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 6\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 7\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 8\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 9\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 10\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 11\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 12\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 13\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 14\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 15\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 16\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 17\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 18\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 19\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"},{\"title\":\"A 20\",\"description\":\"\",\"author\":\"\",\"img_url\":\"\",\"blog_url\":\"\",\"published_at\":\"\"}]}";

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getTimestamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }


    public static <T> T getSpecificDataObject(Object object, Class<T> classOfT) {
        T json = null;
        try {
            String jsonString = new Gson().toJson(object);
            json = new Gson().fromJson(jsonString, classOfT);
        } catch (Exception e) {
           // Toast.makeText(MyApplication.getInstance(), "Something went wrong.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return json;

    }

}
