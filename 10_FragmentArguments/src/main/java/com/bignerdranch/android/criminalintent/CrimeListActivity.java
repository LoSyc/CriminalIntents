package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;
import android.util.Log;

public class CrimeListActivity extends SingleFragmentActivity {
    private static final String TAG = "CrimeListActivity";

    @Override
    protected Fragment createFragment() {
        Log.i(TAG, "creatFragment was called!");
        return new CrimeListFragment();
    }
}
