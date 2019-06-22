package com.test.mvvm.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

public interface SchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
