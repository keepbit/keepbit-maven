package com.keepbit.android.app.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.keepbit.android.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CoderMario on 2019-05-14.
 */
public class ShareFramePanel extends FrameLayout {

    public final static int STATE_HIDE_ING = 1; //
    public final static int STATE_HIDE_ED = 2; //
    public final static int STATE_SHOW_ING = 4; //
    public final static int STATE_SHOW_ED = 8; //

    private int mState = STATE_HIDE_ED;

    private LinearLayout mLinearLayoutContainer;
    private FrameLayout mFrameLayoutBackground;

    private View mPlatformTabIcon01;
    private View mPlatformTabIcon02;
    private View mPlatformTabIcon03;
    private View mPlatformTabIcon04;
    private View mPlatformTabIcon05;

    private View[] mTargets = null;

    private ViewGroup mViewGroup;

    public ShareFramePanel(@NonNull Context context) {
        super(context);
        initShareFramePanel();
    }

    public ShareFramePanel(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initShareFramePanel();
    }

    private void initShareFramePanel() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_share_panel, this, true);

        mFrameLayoutBackground = findViewById(R.id.id_weight_share_panel_background);
        mLinearLayoutContainer = findViewById(R.id.id_weight_share_panel_container);

        mFrameLayoutBackground.setOnClickListener(new OutsideClickListener());
        mLinearLayoutContainer.setOnClickListener(new ContainerClickListener());

        mPlatformTabIcon01 = findViewById(R.id.share_dialog_platform_tab_01_icon);
        mPlatformTabIcon02 = findViewById(R.id.share_dialog_platform_tab_02_icon);
        mPlatformTabIcon03 = findViewById(R.id.share_dialog_platform_tab_03_icon);
        mPlatformTabIcon04 = findViewById(R.id.share_dialog_platform_tab_04_icon);
        mPlatformTabIcon05 = findViewById(R.id.share_dialog_platform_tab_05_icon);

        mTargets = new View[] {mPlatformTabIcon01, mPlatformTabIcon02, mPlatformTabIcon03
                , mPlatformTabIcon04, mPlatformTabIcon05};
    }

    public void attach(View view) {
        if (view instanceof ViewGroup) {
            this.mViewGroup = (ViewGroup) view;
        }
    }

    public void show() {
        prepareShow();
        initShowAnimator();
    }

    public void hide() {
        initHideAnimator();
    }

    public int getState() {
        return this.mState;
    }

    private void prepareShow() {
        if (null == this.mViewGroup) {
            return; // 报错;
        }
        ViewParent parent = getParent();
        if (parent instanceof ViewGroup && parent != this.mViewGroup) {
            ((ViewGroup) parent).removeView(this);
            this.mViewGroup.addView(this, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        } else if (!(parent instanceof ViewGroup)) {
            this.mViewGroup.addView(this, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    private void initShowAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator animator = null;
        View target = null;
        for (int index = 0, size = this.mTargets.length; index < size; index ++) {
            target = this.mTargets[index];
            animator = initEnterAnimator(target, index);
            animators.add(animator);
        }

        animators.add(initPanelBackgroundShowAnimator());
        animators.add(initPanelContainerShowAnimator());

        animatorSet.playTogether(animators);
        animatorSet.addListener(new ShowAnimatorSetListener());

        animatorSet.start();
    }

    private void initHideAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator animator = null;
        View target = null;
        for (int index = 0, size = this.mTargets.length; index < size; index ++) {
            target = this.mTargets[size - 1 - index];
            animator = initExitAnimator(target, index);
            animators.add(animator);
        }

        animators.add(initPanelBackgroundHideAnimator());
        animators.add(initPanelContainerHideAnimator());

        animatorSet.playTogether(animators);
        animatorSet.addListener(new HideAnimatorSetListener());

        animatorSet.start();
    }

    private class ShowAnimatorSetListener extends AnimatorListenerAdapter {

        @Override
        public void onAnimationStart(Animator animation) {
            dispatchOnShowAnimationStart();
            mState = STATE_SHOW_ING;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            dispatchOnShowAnimationEnd();
            mState = STATE_SHOW_ED;
        }
    }

    private void dispatchOnShowAnimationStart() {
        this.mPlatformTabIcon01.setTranslationY(480);
        this.mPlatformTabIcon02.setTranslationY(480);
        this.mPlatformTabIcon03.setTranslationY(480);
        this.mPlatformTabIcon04.setTranslationY(480);
        this.mPlatformTabIcon05.setTranslationY(480);

        this.mFrameLayoutBackground.setAlpha(0.f);
        this.mLinearLayoutContainer.setTranslationY(this.mLinearLayoutContainer.getHeight());
    }

    private void dispatchOnShowAnimationEnd() {
        //
    }

    private class HideAnimatorSetListener extends AnimatorListenerAdapter {

        @Override
        public void onAnimationStart(Animator animation) {
            dispatchOnHideAnimationStart();
            mState = STATE_HIDE_ING;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            dispatchOnHideAnimationEnd();
            mState = STATE_HIDE_ED;
        }
    }

    private void dispatchOnHideAnimationStart() {
        //
    }

    private void dispatchOnHideAnimationEnd() {
        ViewParent parent = getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(ShareFramePanel.this);
        }
    }

    private ObjectAnimator initPanelContainerShowAnimator() {
        ObjectAnimator animator = new ObjectAnimator();
        animator.setProperty(View.TRANSLATION_Y);
        animator.setTarget(this.mLinearLayoutContainer);
        animator.setFloatValues(this.mLinearLayoutContainer.getHeight(), 0);
        animator.setInterpolator(new DecelerateInterpolator(0.6f));
        animator.setDuration(360);
        animator.setStartDelay(100);
        return animator;
    }

    private ObjectAnimator initPanelBackgroundShowAnimator() {
        ObjectAnimator animator = new ObjectAnimator();
        animator.setProperty(View.ALPHA);
        animator.setTarget(this.mFrameLayoutBackground);
        animator.setFloatValues(0.f, 1.f);
        animator.setInterpolator(new DecelerateInterpolator(0.6f));
        animator.setDuration(360);
        animator.setStartDelay(100);
        return animator;
    }

    private ObjectAnimator initPanelContainerHideAnimator() {
        ObjectAnimator animator = new ObjectAnimator();
        animator.setProperty(View.TRANSLATION_Y);
        animator.setTarget(this.mLinearLayoutContainer);
        animator.setFloatValues(0, this.mLinearLayoutContainer.getHeight());
        animator.setInterpolator(new DecelerateInterpolator(0.6f));
        animator.setDuration(360);
        animator.setStartDelay(320);
        return animator;
    }

    private ObjectAnimator initPanelBackgroundHideAnimator() {
        ObjectAnimator animator = new ObjectAnimator();
        animator.setProperty(View.ALPHA);
        animator.setTarget(this.mFrameLayoutBackground);
        animator.setFloatValues(1.f, 0.f);
        animator.setInterpolator(new DecelerateInterpolator(0.6f));
        animator.setDuration(360);
        animator.setStartDelay(320);
        return animator;
    }

    private ObjectAnimator initEnterAnimator(View target, int index) {
        ObjectAnimator animator = new ObjectAnimator();
        animator.setProperty(View.TRANSLATION_Y);
        animator.setTarget(target);
        animator.setFloatValues(480, 0 - 240 + 24 * index, 0);
        animator.setInterpolator(new DecelerateInterpolator(0.6f));
        animator.setDuration(640 - 42 * index);
        animator.setStartDelay(81 * index);
        return animator;
    }

    private ObjectAnimator initExitAnimator(final View target, final int index) {
        ObjectAnimator animator = new ObjectAnimator();
        animator.setProperty(View.TRANSLATION_Y);
        animator.setTarget(target);
        animator.setFloatValues(0, 0 - 240 + 24 * (4 - index), 480);
        animator.setInterpolator(new AccelerateInterpolator(0.6f));
        animator.setDuration(560 - 35 * index);
        animator.setStartDelay(index * 60L);
        return animator;
    }

    private class OutsideClickListener implements OnClickListener {

        @Override
        public void onClick(View view) {
            hide();
        }
    }

    private class ContainerClickListener implements OnClickListener {

        @Override
        public void onClick(View view) {
            //
        }
    }
}
