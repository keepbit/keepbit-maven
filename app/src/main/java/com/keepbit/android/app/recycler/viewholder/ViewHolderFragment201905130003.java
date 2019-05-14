package com.keepbit.android.app.recycler.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keepbit.android.app.R;
import com.keepbit.android.app.event.Fragment201905130003MoreEvent;
import com.keepbit.android.app.event.ShareEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by CoderMario on 2019-05-13.
 */
public class ViewHolderFragment201905130003 extends RecyclerView.ViewHolder {

    private View mLinearLayoutBtn01, mLinearLayoutBtn02, mLinearLayoutBtn03, mLinearLayoutBtn04;

    public ViewHolderFragment201905130003(LayoutInflater layoutInflater, ViewGroup parent) {
        super(layoutInflater.inflate(R.layout.item_fragment_201905130003, parent, false));

        mLinearLayoutBtn01 = itemView.findViewById(R.id.item_tools_btn_01);
        mLinearLayoutBtn02 = itemView.findViewById(R.id.item_tools_btn_02);
        mLinearLayoutBtn03 = itemView.findViewById(R.id.item_tools_btn_03);
        mLinearLayoutBtn04 = itemView.findViewById(R.id.item_tools_btn_04);


        mLinearLayoutBtn01.setOnClickListener(new Btn01ClickListener());
        mLinearLayoutBtn02.setOnClickListener(new Btn02ClickListener());
        mLinearLayoutBtn03.setOnClickListener(new Btn03ClickListener());
        mLinearLayoutBtn04.setOnClickListener(new Btn04ClickListener());
    }



    private class Btn01ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //
        }
    }

    private class Btn02ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //
        }
    }

    private class Btn03ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            ShareEvent event = new ShareEvent();
            EventBus.getDefault().post(event);
        }
    }

    private class Btn04ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Fragment201905130003MoreEvent event = new Fragment201905130003MoreEvent();
            int[] location = new int[2];
            view.getLocationInWindow(location);
            event.setAnchor(view);
            event.setTop(location[1]);
            event.setBottom(location[1] + view.getHeight());
            EventBus.getDefault().post(event);
        }
    }
}
