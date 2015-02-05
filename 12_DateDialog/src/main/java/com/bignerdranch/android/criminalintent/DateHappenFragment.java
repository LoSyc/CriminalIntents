package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2014/11/11.
 */
public class DateHappenFragment extends DialogFragment {
    public static final String EXTRA_HAPPEN = "com.bignerdranch.android.criminalintent.happendate";
    private static final int REQUEST_HAPPEN_DATE = 1;
    Date mDate;

    public static DateHappenFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_HAPPEN, date);

        DateHappenFragment datehappenfragment = new DateHappenFragment();
        datehappenfragment.setArguments(args);

        return datehappenfragment;
    }

    private void sendResult(int ResultId) {
        if (getTargetFragment() != null) {
            Intent i = new Intent();
            i.putExtra(EXTRA_HAPPEN, mDate);

            getTargetFragment().onActivityResult(getTargetRequestCode(), ResultId, i);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date) getArguments().getSerializable(EXTRA_HAPPEN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_date, null);

        DatePicker datePicker = (DatePicker) v.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                mDate = new GregorianCalendar(year, month, day).getTime();

                // update argument to preserve selected value on rotation
                getArguments().putSerializable(EXTRA_HAPPEN, mDate);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_happen_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }
}
