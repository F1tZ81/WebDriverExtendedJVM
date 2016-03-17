package com.ostusa;

/**
 * Created by vanproyaa on 3/3/2016.
 */
public interface Report
{
    void prepReporting();

    void validate(String text, boolean passFail, boolean positiveCheck, byte[] screenShot);

    void validate(String text, boolean passFail, boolean positiveCheck);

    void validate(String text, boolean passFail);

    void writeStep(String text);

    void writeReport();
}
