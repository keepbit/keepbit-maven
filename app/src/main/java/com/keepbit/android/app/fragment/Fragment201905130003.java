package com.keepbit.android.app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.keepbit.android.app.R;
import com.keepbit.android.app.dialog.Fragment201905130003DialogMore;
import com.keepbit.android.app.event.Fragment201905130003MoreEvent;
import com.keepbit.android.app.event.ShareEvent;
import com.keepbit.android.app.recycler.ItemDecorationFragment201905130003;
import com.keepbit.android.app.recycler.adapter.AdapterFragment201905130003;
import com.keepbit.android.app.widget.ShareFramePanel;
import com.keepbit.android.lib.dialog.PopupState;
import com.keepbit.android.lib.utils.DimensionUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by CoderMario on 2019-05-13.
 */
public class Fragment201905130003 extends BaseFragmentV4 {

    private RecyclerView mRecyclerView;

    private Fragment201905130003DialogMore mMoreDialog;
    private ShareFramePanel mShareFramePanel;

    public static Fragment201905130003 newInstance(Bundle arguments) {
        Fragment201905130003 fragment = new Fragment201905130003();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onInitAllViews(@NonNull View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new AdapterFragment201905130003());

        ItemDecorationFragment201905130003 itemDecoration = new ItemDecorationFragment201905130003(getContext());
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(ShareEvent event) {
        if (null == mShareFramePanel) {
            mShareFramePanel = new ShareFramePanel(getContext());
            mShareFramePanel.attach(getActivity().getWindow().getDecorView());
        }
        mShareFramePanel.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMoreDialogEvent(Fragment201905130003MoreEvent event) {
        if (null == mMoreDialog) {
            mMoreDialog = new Fragment201905130003DialogMore(getContext());
            mMoreDialog.addPopupCallback(this);
        }
        if (PopupState.STATE_HIDE_ED != mMoreDialog.getState()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("action", "keep.bit.action.more");

        float f = event.getTop();
        float t = DimensionUtil.getHeightPixel(getContext()) - event.getBottom();
        if (f >= t) {
            mMoreDialog.setMode("f");
        } else {
            mMoreDialog.setMode("t");
        }

        int xOffset = 0 - DimensionUtil.dp2valueInt(getContext(), 220);
        int yOffset = 0;
        if (f >= t) {
            yOffset = 0 - ((int) Math.abs(event.getBottom() - event.getTop()) + DimensionUtil.dp2valueInt(getContext(), 72));
            mMoreDialog.setAnimationStyle(R.style.anim_dialog_fade_f);
        } else {
            mMoreDialog.setAnimationStyle(R.style.anim_dialog_fade_t);
        }
        mMoreDialog.showAsDropDown(event.getAnchor(), xOffset, yOffset, bundle);
    }

    @Override
    public boolean onBackPressed() {
        if (null != mShareFramePanel && View.VISIBLE == mShareFramePanel.getVisibility()) {
            mShareFramePanel.hide();
            return true;
        }
        if (null != mMoreDialog && PopupState.STATE_HIDE_ED != mMoreDialog.getState()) {
            Bundle bundle = new Bundle();
            bundle.putString("action", "keep.bit.action.more");
            mMoreDialog.dismiss(bundle);
            return true;
        }
        return super.onBackPressed();
    }

    @Override
    public void onHideAnimatorCompleted(Bundle bundle) {
        if (null == bundle) {
            return;
        }
        String action = bundle.getString("action");
        if ("keep.bit.action.more".equals(action)) {
            String toast = bundle.getString("toast");
            if (null == toast || 0 >= toast.trim().length()) {
                toast = "取消";
            }
            Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_201905130003;
    }
}
