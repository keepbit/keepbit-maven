package com.keepbit.android.app;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.keepbit.android.app.fragment.BaseFragmentV4;
import com.keepbit.android.app.fragment.Fragment201905150001;
import com.keepbit.android.app.fragment.Fragment201905160001;

public class ActivityMain extends AppCompatActivity {

    private BaseFragmentV4 mBaseFragmentV4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBaseFragmentV4 = Fragment201905150001.newInstance(null);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, mBaseFragmentV4);
        transaction.commitNowAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        if (null != mBaseFragmentV4 && mBaseFragmentV4.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
