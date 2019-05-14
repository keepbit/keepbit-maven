package com.keepbit.android.app.fragment;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.keepbit.android.app.R;
import com.keepbit.android.lib.utils.NetworkUtil;

/**
 * Created by CoderMario on 2019-05-13.
 */
public class Fragment201905130002 extends BaseFragmentV4 {

    private TextView mTextView01, mTextView02, mTextView03;

    public static Fragment201905130002 newInstance(Bundle arguments) {
        Fragment201905130002 fragment = new Fragment201905130002();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected void onInitAllViews(@NonNull View view) {
        mTextView01 = view.findViewById(R.id.text_view_01);
        mTextView02 = view.findViewById(R.id.text_view_02);
        mTextView03 = view.findViewById(R.id.text_view_03);
    }

    @Override
    protected void onInitAllDatum() {
        boolean wifiConnected = NetworkUtil.isNetworkConnected(getContext(), ConnectivityManager.TYPE_WIFI);
        String wifiConnectedHint = wifiConnected ? "连接" : "断开";
        boolean mobileConnected = NetworkUtil.isNetworkConnected(getContext(), ConnectivityManager.TYPE_MOBILE);
        String mobileConnectedHint = mobileConnected ? "连接" : "断开";
        boolean bluetoothConnected = NetworkUtil.isNetworkConnected(getContext(), ConnectivityManager.TYPE_BLUETOOTH);
        String bluetoothConnectedHint = bluetoothConnected ? "连接" : "断开";

        mTextView01.setText(wifiConnectedHint);
        mTextView02.setText(mobileConnectedHint);
        mTextView03.setText(bluetoothConnectedHint);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_201905130002;
    }
}
