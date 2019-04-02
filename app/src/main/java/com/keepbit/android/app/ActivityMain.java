package com.keepbit.android.app;

import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.keepbit.android.lib.utils.NetworkUtil;
import com.keepbit.android.lib.utils.TelephonyUtil;

public class ActivityMain extends AppCompatActivity {

    private TextView mTextView01;
    private TextView mTextView02;
    private TextView mTextView03;
    private TextView mTextView04;
    private TextView mTextView05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView01 = findViewById(R.id.text_view_01);
        mTextView02 = findViewById(R.id.text_view_02);
        mTextView03 = findViewById(R.id.text_view_03);
        mTextView04 = findViewById(R.id.text_view_04);
        mTextView05 = findViewById(R.id.text_view_05);

        mTextView01.setText(TelephonyUtil.getSimOperator(this));
        mTextView02.setText(TelephonyUtil.getSimOperatorName(this));

        boolean wifiConnected = NetworkUtil.isNetworkConnected(this, ConnectivityManager.TYPE_WIFI);
        String wifiConnectedHint = wifiConnected ? "连接" : "断开";
        boolean mobileConnected = NetworkUtil.isNetworkConnected(this, ConnectivityManager.TYPE_MOBILE);
        String mobileConnectedHint = mobileConnected ? "连接" : "断开";
        boolean bluetoothConnected = NetworkUtil.isNetworkConnected(this, ConnectivityManager.TYPE_BLUETOOTH);
        String bluetoothConnectedHint = bluetoothConnected ? "连接" : "断开";

        mTextView03.setText(wifiConnectedHint);
        mTextView04.setText(mobileConnectedHint);
        mTextView05.setText(bluetoothConnectedHint);
    }
}
