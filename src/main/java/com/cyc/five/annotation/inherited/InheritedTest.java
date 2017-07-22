package com.cyc.five.annotation.inherited;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by cyc_e on 2017/6/11.
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface InheritedTest {

    String value();
}