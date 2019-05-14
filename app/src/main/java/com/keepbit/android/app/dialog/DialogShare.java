package com.keepbit.android.app.dialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.keepbit.android.app.R;
import com.keepbit.android.lib.dialog.PopupDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CoderMario on 2019-05-13.
 */
public class DialogShare extends PopupDialog {

    private View mPlatformTabIcon01, mPlatformTabIcon02, mPlatformTabIcon03, mPlatformTabIcon04, mPlatformTabIcon05;
    private LinearLayout mPlatformTab01, mPlatformTab02, mPlatformTab03, mPlatformTab04, mPlatformTab05;

    public DialogShare(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fragment_201905130003, null);
        onInitAllViews(view);
        setContentView(view);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setAnimationStyle(R.style.anim_dialog_input_method);

        setMaskColor(Color.parseColor("#42000000"));

        setOutsideTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                dismiss();
                return true;
            }
        });
    }

    private void onInitAllViews(View view) {
        mPlatformTabIcon01 = view.findViewById(R.id.share_dialog_platform_tab_01_icon);
        mPlatformTabIcon02 = view.findViewById(R.id.share_dialog_platform_tab_02_icon);
        mPlatformTabIcon03 = view.findViewById(R.id.share_dialog_platform_tab_03_icon);
        mPlatformTabIcon04 = view.findViewById(R.id.share_dialog_platform_tab_04_icon);
        mPlatformTabIcon05 = view.findViewById(R.id.share_dialog_platform_tab_05_icon);

        mPlatformTab01 = view.findViewById(R.id.share_dialog_platform_tab_01_layout);
        mPlatformTab02 = view.findViewById(R.id.share_dialog_platform_tab_02_layout);
        mPlatformTab03 = view.findViewById(R.id.share_dialog_platform_tab_03_layout);
        mPlatformTab04 = view.findViewById(R.id.share_dialog_platform_tab_04_layout);
        mPlatformTab05 = view.findViewById(R.id.share_dialog_platform_tab_05_layout);
    }

    @Override
    public void onShowAnimatorStarted(Bundle bundle) {
        if (null != bundle) {
            String id = bundle.getString("id");
            if (null != id && 0 < id.trim().length()) {
                Log.e(" ******* CoderMario ", String.format("id = %s", id));
            }
        }


    }

    @Override
    public void onHideAnimatorStarted(Bundle bundle) {
        AnimatorSet animatorSet = new AnimatorSet();

        List<Animator> animators = new ArrayList<>();

        ObjectAnimator animator = null;
        View target = null;
        for (int index = 0; index < 5; index ++) {
            if (0 == index) {
                target = mPlatformTabIcon05;
            }
            if (1 == index) {
                target = mPlatformTabIcon04;
            }
            if (2 == index) {
                target = mPlatformTabIcon03;
            }
            if (3 == index) {
                target = mPlatformTabIcon02;
            }
            if (4 == index) {
                target = mPlatformTabIcon01;
            }

            animator = initExitAnimator(target, index);
            animator.setStartDelay(index * 100L);
            animators.add(animator);
        }

        animatorSet.playTogether(animators);

        animatorSet.start();
    }

    private ObjectAnimator initEnterAnimator(View target, int index) {
        ObjectAnimator animator = new ObjectAnimator();
        animator.setProperty(View.TRANSLATION_Y);
        animator.setTarget(target);
        animator.setFloatValues(480, 0 - 280 + 36 * index, 0);
        animator.setInterpolator(new DecelerateInterpolator(0.6f));
        animator.setDuration(640 - 35 * index);
        return animator;
    }

    private ObjectAnimator initExitAnimator(final View target, final int index) {
        ObjectAnimator animator = new ObjectAnimator();
        animator.setProperty(View.TRANSLATION_Y);
        animator.setTarget(target);
        animator.setFloatValues(0 - 240 + 24 * (4 - index), 480);
        animator.setDuration(560 - 30 * (4 - index));

        Log.e(" ******* CoderMario ", String.format("%s-%s", target, 560 - 30 * (4 - index)));
        return animator;
    }
}
