package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Administrator on 2014/12/28.
 */
public class CriminalIntentJSONSerializer {
    private static final String TAG = "criminalintent.CriminalIntentJSONSerializer";
    private Context mContext;
    private String mFilename;
    private File mFile;

    public CriminalIntentJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public void saveCrime(ArrayList<Crime> crimes) throws IOException, JSONException {
        JSONArray array = new JSONArray();
        for (Crime c : crimes) {
            array.put(c.toJSON());
        }

        Writer writer = null;
        try {
/*
OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
writer = new OutputStreamWriter(out);
*/
            if (Environment.getExternalStorageState()
                    == Environment.MEDIA_MOUNTED) {
                mFile = Environment.getExternalStorageDirectory();
                Log.i(TAG, mFile.getAbsolutePath());
                mFile = new File(mFile, "/testSdCard");
                mFile.mkdirs();
                if (!mFile.mkdirs()) {
                    Log.e(TAG, "Directory not created");
                }
                mFile = new File(mFile, mFilename);
                FileOutputStream out = new FileOutputStream(mFile);
                writer = new OutputStreamWriter(out);
                writer.write(array.toString());
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<Crime> loadCrimes() throws IOException, JSONException {
        ArrayList<Crime> crimes = new ArrayList<Crime>();
        BufferedReader reader = null;
        try {
/*
InputStream in = mContext.openFileInput(mFilename);
reader = new BufferedReader(new InputStreamReader(in));
*/

            mFile = Environment.getExternalStorageDirectory();
            mFile = new File(mFile, "/testSdCard/" + mFilename);
            FileInputStream in = new FileInputStream(mFile);
            reader = new BufferedReader(new InputStreamReader(in));


            StringBuffer jsonString = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < array.length(); i++) {
                crimes.add(new Crime((array.getJSONObject(i))));
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return crimes;
    }



}
