package com.example.shivamagrawal.taskranker2;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.app.TimePickerDialog;
import android.app.Dialog;
import java.util.Calendar;
import android.app.Activity;
import android.app.TimePickerDialog.OnTimeSetListener;

public class TimePickerFragment extends DialogFragment {

    private Activity mActivity;
    private OnTimeSetListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        mListener = (OnTimeSetListener) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(mActivity, mListener, hour, minute, false);
    }
}