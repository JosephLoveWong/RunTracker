package com.promiseland.runtracker.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.promiseland.runtracker.R;

/**
 * Created by joseph on 2016/8/13.
 */
public class RunFragment extends Fragment {
    private static final String TAG = "RunFragment";

    private Button mStartButton, mStopButton;
    private TextView mStartedTextView, mLatitudeTextView,
            mLongitudeTextView, mAltitudeTextView, mDurationTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_run, container, false);
        mStartButton = (Button) rootView.findViewById(R.id.start);
        mStopButton = (Button) rootView.findViewById(R.id.stop);
        mStartedTextView = (TextView) rootView.findViewById(R.id.started);
        mLatitudeTextView = (TextView) rootView.findViewById(R.id.latitude);
        mLongitudeTextView = (TextView) rootView.findViewById(R.id.longitude);
        mAltitudeTextView = (TextView) rootView.findViewById(R.id.altitude);
        mDurationTextView = (TextView) rootView.findViewById(R.id.elapsedTime);


        return rootView;
    }
}
