package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {
    private static final String TAG = "CrimeActivity";

	@Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID)getIntent()
            .getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        Log.i(TAG, "creatFragment was called!");
        return CrimeFragment.newInstance(crimeId);
    }
}
