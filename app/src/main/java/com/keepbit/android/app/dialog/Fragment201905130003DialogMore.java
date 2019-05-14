package com.keepbit.android.app.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.keepbit.android.app.R;
import com.keepbit.android.lib.dialog.PopupDialog;

/**
 * Created by CoderMario on 2019-05-14.
 */
public class Fragment201905130003DialogMore extends PopupDialog {

    private View mAnchort, mAnchorf;
    private LinearLayout mLinearLayoutMenu01, mLinearLayoutMenu02, mLinearLayoutMenu03, mLinearLayoutMenu04;

    public Fragment201905130003DialogMore(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fragment_201905130003_more, null);
        onInitAllViews(view);
        setContentView(view);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        setMaskColor(Color.parseColor("#99000000"));

        setOutsideTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                Bundle bundle = new Bundle();
                bundle.putString("action", "keep.bit.action.more");
                dismiss(bundle);
                return true;
            }
        });
    }

    public void setMode(String mode) {
        if ("t".equals(mode)) {
            mAnchorf.setVisibility(View.VISIBLE);
            mAnchort.setVisibility(View.GONE);
            return;
        }
        if ("f".equals(mode)) {
            mAnchorf.setVisibility(View.GONE);
            mAnchort.setVisibility(View.VISIBLE);
            return;
        }

        mAnchorf.setVisibility(View.GONE);
        mAnchort.setVisibility(View.GONE);
    }

    private void onInitAllViews(View view) {
        mAnchorf = view.findViewById(R.id.id_anchor_f);
        mAnchort = view.findViewById(R.id.id_anchor_t);

        mLinearLayoutMenu01 = view.findViewById(R.id.menu_item_01);
        mLinearLayoutMenu02 = view.findViewById(R.id.menu_item_02);
        mLinearLayoutMenu03 = view.findViewById(R.id.menu_item_03);
        mLinearLayoutMenu04 = view.findViewById(R.id.menu_item_04);

        mLinearLayoutMenu01.setOnClickListener(new OnMenu01ClickListener());
        mLinearLayoutMenu02.setOnClickListener(new OnMenu02ClickListener());
        mLinearLayoutMenu03.setOnClickListener(new OnMenu03ClickListener());
        mLinearLayoutMenu04.setOnClickListener(new OnMenu04ClickListener());
    }

    private class OnMenu01ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putString("action", "keep.bit.action.more");
            bundle.putString("toast", "不感兴趣");
            dismiss(bundle);
        }
    }

    private class OnMenu02ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putString("action", "keep.bit.action.more");
            bundle.putString("toast", "内容重复");
            dismiss(bundle);
        }
    }

    private class OnMenu03ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putString("action", "keep.bit.action.more");
            bundle.putString("toast", "拉黑作者");
            dismiss(bundle);
        }
    }

    private class OnMenu04ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putString("action", "keep.bit.action.more");
            bundle.putString("toast", "不看相关推荐");
            dismiss(bundle);
        }
    }
}
