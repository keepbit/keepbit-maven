package com.keepbit.android.app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keepbit.android.lib.dialog.PopupCallback;

/**
 * Created by CoderMario on 2019-05-13.
 */
public abstract class BaseFragmentV4 extends Fragment implements PopupCallback {

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        onInitAllViews(view);
        onInitAllDatum();
    }

    protected void onInitAllViews(@NonNull View view) {
        //
    }

    protected void onInitAllDatum() {
        //
    }

    @Override
    public void onShowAnimatorStarted(Bundle bundle) {
        //
    }

    @Override
    public void onShowAnimatorCompleted(Bundle bundle) {
        //
    }

    @Override
    public void onHideAnimatorStarted(Bundle bundle) {
        //
    }

    @Override
    public void onHideAnimatorCompleted(Bundle bundle) {
        //
    }

    public boolean onBackPressed() {
        return false;
    }

    protected abstract int getLayoutId();
}
