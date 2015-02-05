package com.bignerdranch.android.criminalintent;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RadioGroup;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2014/11/12.
 */
public class DateSelectFragment extends DialogFragment {
    private static final String EXTRA_DATE_SELECT = "com.bignerdranch.android.criminalintent.DATE_SELECT";

    Date mDate;
    RadioGroup mSelectDate;
    Crime mCrime;
    DatePickerFragment localdiaolg;
    DateHappenFragment happendiaplg;

    public DateSelectFragment() {
    }


    public DateSelectFragment newInstance(UUID crimeId, Date localDate, Date happenDate
            , DatePickerFragment localdiaolg, DateHappenFragment happendiaplg) {

        this.localdiaolg = localdiaolg;
        this.happendiaplg = happendiaplg;

        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        DateSelectFragment fragment = new DateSelectFragment();

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_dateselect, null);


        mSelectDate = (RadioGroup) v.findViewById(R.id.radio_select);

        final FragmentManager fm = getActivity().getSupportFragmentManager();

        return new AlertDialog.Builder(getActivity()).setView(v)
                .setTitle(R.string.data_select)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int selectId = mSelectDate.getCheckedRadioButtonId();
                        if (selectId == R.id.radio_date_local) {
                            localdiaolg.show(fm, "DIALOG_DATE");
                        }
                        if (selectId == R.id.radio_date_happen) {
                            happendiaplg.show(fm, "DIALOG_DATE");
                        }
                    }
                }).create();
    }
}
