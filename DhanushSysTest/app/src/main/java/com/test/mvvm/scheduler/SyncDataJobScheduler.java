package com.test.mvvm.scheduler;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import com.test.mvvm.BuildConfig;
import com.test.mvvm.R;
import com.test.mvvm.data.model.db.Form1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by suprasoft on 6/26/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SyncDataJobScheduler extends JobService {

    private static final String CHANNEL_ID = "CH_10005";
    private static final int NOTIFICATION_ID = 123456789;
    String TAG = "SyncDataJobScheduler";
    private int taskCount = 0;
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("SyncDataJobScheduler", "SyncDataJobScheduler....");
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            // Create the channel for the notification
            NotificationChannel mChannel =
                    new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription("no sound");
            mChannel.setSound(null, null);
            mChannel.enableLights(false);
            mChannel.setLightColor(Color.BLUE);
            mChannel.enableVibration(false);

            // Set the Notification Channel for the Notification Manager.
            mNotificationManager.createNotificationChannel(mChannel);
        }
        startForeground(NOTIFICATION_ID, getNotification());


    }


    private Notification getNotification() {

        RemoteViews remoteViews = new RemoteViews(getPackageName(),
                R.layout.custom_push);

        Intent activityIntent = new Intent();
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        // The PendingIntent to launch activity.
        PendingIntent activityPendingIntent = PendingIntent.getActivity(this, 0,
                activityIntent, 0);

        Notification notification = new Notification();
        notification.defaults = 0;
        notification.contentView = remoteViews;


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setDefaults(notification.defaults)
                .setContentIntent(activityPendingIntent)
                //.setCustomBigContentView(remoteViews)
                .setContentTitle("Syncing data..")
                .setProgress(100, 50, true)
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setSound(null)
                .setShowWhen(false)
                .setSmallIcon(R.drawable.dhanush);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID); // Channel ID
        }

        return builder.build();

    }

    private void downloadZipFileRx(String path, Object object) {
        RetrofitInterface downloadService = createService(RetrofitInterface.class, BuildConfig.BASE_URL+"/");
        downloadService.dataSyncWithRx(path, object)
                .flatMap(processResponse())
               // .flatMap(unpackZip())
                //   .flatMap(dbOprations())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(handleResult());

    }


    private Func1<Response<ResponseBody>, Observable<File>> processResponse() {
        return new Func1<Response<ResponseBody>, Observable<File>>() {
            @Override
            public Observable<File> call(Response<ResponseBody> responseBodyResponse) {

                        Intent intent1 = new Intent();
                        intent1.putExtra("message", responseBodyResponse.code()+" "+responseBodyResponse.raw().message());
                        intent1.setAction("download.file.com");
                        //intent1.putExtras(bundle);
                        sendBroadcast(intent1);

                return saveToDiskRx(responseBodyResponse);
            }
        };
    }

    private Observable<File> saveToDiskRx(final Response<ResponseBody> response) {
        return Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                Log.d("anil", "Anil.......2");
               /* try {
                    String header = response.headers().get("Content-Disposition");
                    HttpUrl url = response.raw().request().url();
                    String filename = url.toString().substring(url.toString().lastIndexOf("/") + 1);
                    //String filename = header.replace("attachment; filename=", "");

                    new File("/data/data/" + getPackageName() + "/drug_master").mkdir();
                    File destinationFile = new File("/data/data/" + getPackageName() + "/drug_master/" + filename);

                    BufferedSink bufferedSink = Okio.buffer(Okio.sink(destinationFile));
                    Log.d("anil", "Anil.......3");
                    bufferedSink.writeAll(response.body().source());
                    bufferedSink.close();
                    Log.d("anil", "Anil.......1");
                    subscriber.onNext(destinationFile);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    stopSelf();
                    e.printStackTrace();
                    subscriber.onError(e);
                }*/
            }
        });
    }


    public void parseDataFromFile() {


    }

    private Func1<File, Observable<File>> unpackZip() {
        return new Func1<File, Observable<File>>() {
            @Override
            public Observable<File> call(File file) {
                InputStream is;
                ZipInputStream zis;
                String parentFolder;
                String filename;
                try {
                    parentFolder = file.getParentFile().getPath();
                    is = new FileInputStream(file.getAbsolutePath());
                    zis = new ZipInputStream(new BufferedInputStream(is));
                    ZipEntry ze;
                    byte[] buffer = new byte[1024];
                    int count;
                    while ((ze = zis.getNextEntry()) != null) {
                        filename = ze.getName();
                        if (ze.isDirectory()) {
                            File fmd = new File(parentFolder + "/" + filename);
                            fmd.mkdirs();
                            continue;
                        }
                        FileOutputStream fout = new FileOutputStream(parentFolder + "/" + filename);
                        while ((count = zis.read(buffer)) != -1) {
                            fout.write(buffer, 0, count);
                        }
                        fout.close();
                        zis.closeEntry();
                    }
                    zis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                File extractedFile = new File(file.getAbsolutePath().replace(".zip", ""));
                if (!file.delete()) Log.d("unpackZip", "Failed to deleted the zip file.");
                return Observable.just(extractedFile);
            }
        };
    }

    private Observer<File> handleResult() {
        return new Observer<File>() {
            @Override
            public void onCompleted() {
                taskCount++;
                Log.d(TAG, "onCompleted" + taskCount);
                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            stopSelf();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 1000 * 60);*/
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.d(TAG, "Error " + e.getMessage());
            }

            @Override
            public void onNext(File file) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        parseDataFromFile();
                        stopForeground(true);
                        Log.d(TAG, "File downloaded and extracted to " + file.getAbsolutePath() + "\n " + file.isDirectory());
                        stopSelf();
                        return null;
                    }
                }.execute();
            }
        };
    }

    public <T> T createService(Class<T> serviceClass, String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        return retrofit.create(serviceClass);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service destroyed onDestroy");
    }


    @Override
    public boolean onStartJob(JobParameters params) {
        downloadZipFileRx("xyz", new Form1());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


}
