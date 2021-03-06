package com.ztemt.test.basic.item;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ztemt.test.basic.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by younix on 17-11-15.
 *
 */
public class EarphoneTest2 extends LoopbackTest {
    private static final String TAG = "EarphoneTest2";
    private static final int MSG_DELAY_TIME = 3000;
    protected static final int MSG_START = 1;
    protected static final int MSG_END = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.earphone2, container, false);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onHandleMessage(final int index) {
        switch (index) {
            case MSG_START:
                setTimerTask(MSG_END, MSG_DELAY_TIME);
                setButtonVisibility(false);
                try {
                    setEarphoneOn();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case MSG_END:
                setButtonVisibility(true);
                break;
        }
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        setTimerTask(MSG_START, 0);
        return true;
    }

    @Override
    public String getTestName() {
        return getContext().getString(R.string.earphone2_title);
    }

    @Override
    public boolean isNeedTest() {
        return getSystemProperties("earphone", true);
    }

    private void setEarphoneOn() throws IOException {
        SystemProperties.set("ctl.start","alctest");
        Log.v(TAG, "Call System Service 'alctest' in init.rc to test audio Earphone.");
    }
}
