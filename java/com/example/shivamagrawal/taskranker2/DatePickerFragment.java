package com.example.shivamagrawal.taskranker2;

import android.app.Activity;
import android.app.Dialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    private Activity mActivity;
    private DatePickerDialog.OnDateSetListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        mListener = (DatePickerDialog.OnDateSetListener) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(mActivity, mListener, year, month, day);
    }
}
