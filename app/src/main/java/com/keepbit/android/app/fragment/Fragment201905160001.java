package com.keepbit.android.app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;

import com.keepbit.android.app.R;

/**
 * Created by CoderMario on 2019-05-15.
 */
public class Fragment201905160001 extends BaseFragmentV4 {

    private EditText mEditText;

    public static Fragment201905160001 newInstance(Bundle arguments) {
        Fragment201905160001 fragment = new Fragment201905160001();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected void onInitAllViews(@NonNull View view) {
        mEditText = view.findViewById(R.id.edit_text);

        mEditText.setFilters(new InputFilter[]{new CustomFilter()});
    }

    private class CustomFilter implements InputFilter {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if (null == source || 0 >= source.length()) { // 说明是删除操作
                return dispatchCharSequenceDelete(source, dest, dstart, dend);
            } else {
                return dispatchCharSequenceInsert(source, dest, dstart, dend);
            }
        }

        private CharSequence dispatchCharSequenceDelete(CharSequence source, Spanned dest, int start, int end) {
            String result = dest.subSequence(0, start) + "" + dest.subSequence(end, dest.length());

            return null;
        }

        private CharSequence dispatchCharSequenceInsert(CharSequence source, Spanned dest, int start, int end) {
            return null;
        }
    }

    @Override
    protected void onInitAllDatum() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_201905160001;
    }
}
