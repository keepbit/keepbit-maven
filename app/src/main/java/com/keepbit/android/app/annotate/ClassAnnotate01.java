package com.keepbit.android.app.annotate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by CoderMario on 2019-05-17.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassAnnotate01 {

    int age();

    String name();
}
