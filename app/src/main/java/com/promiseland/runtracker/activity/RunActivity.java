package com.promiseland.runtracker.activity;

import android.support.v4.app.Fragment;

import com.promiseland.runtracker.fragment.RunFragment;

public class RunActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RunFragment();
    }
}
