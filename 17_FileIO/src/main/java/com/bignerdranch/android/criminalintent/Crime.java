package com.bignerdranch.android.criminalintent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_LOCAL_DATE = "local_date";
    private static final String JSON_HAPPEM_DATE = "happen_date";


    private UUID mId;
    private String mTitle;
    private Date mLocalDate;
    private Date mHappenDate;
    private boolean mSolved;

    public Crime() {
        mId = UUID.randomUUID();
        mLocalDate = new Date();
        mHappenDate = new Date();
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Date getmLocalDate() {
        return mLocalDate;
    }

    public void setmLocalDate(Date mLocalDate) {
        this.mLocalDate = mLocalDate;
    }

    public Date getmHappenDate() {
        return mHappenDate;
    }

    public void setmHappenDate(Date mHappenDate) {
        this.mHappenDate = mHappenDate;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_SOLVED, mSolved);
        json.put(JSON_LOCAL_DATE, mLocalDate.getTime());
        json.put(JSON_HAPPEM_DATE, mHappenDate.getTime());
        return json;
    }

    public Crime(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        if (json.has(JSON_TITLE)) {
            mTitle = json.getString(JSON_TITLE);
        }

        mSolved = json.getBoolean(JSON_SOLVED);
        mHappenDate = new Date(json.getLong(JSON_HAPPEM_DATE));
        mLocalDate = new Date(json.getLong(JSON_LOCAL_DATE));
    }

}
