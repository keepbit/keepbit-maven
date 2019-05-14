package com.keepbit.android.app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.keepbit.android.app.R;
import com.keepbit.android.lib.utils.TelephonyUtil;

/**
 * Created by CoderMario on 2019-05-13.
 */
public class Fragment201905130001 extends BaseFragmentV4 {

    private TextView mTextView01, mTextView02;

    public static Fragment201905130001 newInstance(Bundle arguments) {
        Fragment201905130001 fragment = new Fragment201905130001();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected void onInitAllViews(@NonNull View view) {
        mTextView01 = view.findViewById(R.id.text_view_01);
        mTextView02 = view.findViewById(R.id.text_view_02);
    }

    @Override
    protected void onInitAllDatum() {
        mTextView01.setText(TelephonyUtil.getSimOperator(getContext()));
        mTextView02.setText(TelephonyUtil.getSimOperatorName(getContext()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_201905130001;
    }
}
