package com.keepbit.android.app.recycler.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keepbit.android.app.R;
import com.keepbit.android.app.event.Fragment201905130003MoreEvent;
import com.keepbit.android.app.event.Fragment201905130003ShareEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by CoderMario on 2019-05-13.
 */
public class ViewHolderFragment201905130003 extends RecyclerView.ViewHolder {

    public ViewHolderFragment201905130003(LayoutInflater layoutInflater, ViewGroup parent) {
        super(layoutInflater.inflate(R.layout.item_fragment_201905130003, parent, false));

        itemView.findViewById(R.id.item_tools_btn_01).setOnClickListener(new Btn01ClickListener());
        itemView.findViewById(R.id.item_tools_btn_02).setOnClickListener(new Btn02ClickListener());
        itemView.findViewById(R.id.item_tools_btn_03).setOnClickListener(new Btn03ClickListener());
        itemView.findViewById(R.id.item_tools_btn_04).setOnClickListener(new Btn04ClickListener());
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
            Fragment201905130003ShareEvent event = new Fragment201905130003ShareEvent();
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
