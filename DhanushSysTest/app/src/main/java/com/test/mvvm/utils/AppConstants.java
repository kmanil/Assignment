package com.test.mvvm.utils;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

public final class AppConstants {

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "mindorks_mvvm.db";

    public static final long NULL_INDEX = -1L;

    public static final String PREF_NAME = "mindorks_pref";

    public static final String SEED_DATABASE_OPTIONS = "seed/options.json";

    public static final String SEED_DATABASE_QUESTIONS = "seed/questions.json";
    public static final String MAIN_GRID = "seed/mainpage.json";

    public static final String STATUS_CODE_FAILED = "failed";

    public static final String STATUS_CODE_SUCCESS = "success";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
