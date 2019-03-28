package com.keepbit.android.app;

import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.keepbit.android.lib.utils.NetworkUtil;
import com.keepbit.android.lib.utils.TelephonyUtil;

public class ActivityMain extends AppCompatActivity {

    private TextView mTextView01;
    private TextView mTextView02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView01 = findViewById(R.id.text_view_01);
        mTextView02 = findViewById(R.id.text_view_02);

        mTextView01.setText(TelephonyUtil.getSimOperator(this));
        mTextView02.setText(TelephonyUtil.getSimOperatorName(this));

        int phoneType = TelephonyUtil.getPhoneType(this);
        Log.e(" ******* CoderMario ", String.format("phoneType = %s", phoneType));

        String networkOperatorName = TelephonyUtil.getNetworkOperatorName(this);
        Log.e(" ******* CoderMario ", String.format("networkOperatorName = %s", networkOperatorName));

        NetworkInfo networkInfo = NetworkUtil.getActiveNetworkInfo(this);
        Log.e(" ******* CoderMario ", String.format("%s", 123));
    }
}
