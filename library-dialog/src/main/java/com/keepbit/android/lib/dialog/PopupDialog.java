package com.keepbit.android.lib.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public abstract class PopupDialog extends PopupWindow implements Callback {

    private final int[] ANIMATION_ATTRS = {
            android.R.attr.windowEnterAnimation, android.R.attr.windowExitAnimation
    };

    private List<PopupCallback> mPopupCallbacks;

    private Interpolator mInterpolatorFadeShow = new LinearInterpolator();
    private Interpolator mInterpolatorFadeHide = new LinearInterpolator();
    private int mMaskColor = Color.parseColor("#20000000");
    private AnimatorSet mShowAnimator;
    private AnimatorSet mHideAnimator;
    private long mDurationFadeShow = 320L;
    private long mDurationFadeHide = 230L;
    private long mUniqueIdentify;

    private Bundle mBundleEnter;
    private Bundle mBundleExit;

    private MaskLayer mMaskLayer;

    private Context mContext;

    private long mState = PopupState.STATE_HIDE_ED;

    private View.OnTouchListener mOutsideTouchListener;

    /****
     * */
    public PopupDialog(Context context) {
        this.mContext = context;
        initBasePopupWindow();
    }

    /****
     * */
    private void initBasePopupWindow() {
        setUniqueIdentify(System.currentTimeMillis()); // 生成唯一标识
    }

    /****
     * */
    public void setAnimationStyle(int animationStyle) {
        super.setAnimationStyle(animationStyle);
        parseAnimationStyle();
    }

    /****
     * */
    private View.OnTouchListener getOutsideTouchListener() {
        return this.mOutsideTouchListener;
    }

    /****
     * */
    public void setOutsideTouchListener(View.OnTouchListener listener) {
        this.mOutsideTouchListener = listener;
    }

    /****
     * */
    private void parseAnimationStyle() {
        int animationStyle = getAnimationStyle();
        TypedArray typedArray = null;
        try {
            typedArray = this.mContext.obtainStyledAttributes(animationStyle, ANIMATION_ATTRS);
            if (null != typedArray && 0 < typedArray.length()) {
                parseTypedArray(typedArray);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } finally {
            releaseTypedArray(typedArray);
        }
    }

    /****
     * */
    private void parseTypedArray(TypedArray typedArray) {
        if (null == typedArray) {
            return;
        }
        int count = typedArray.getIndexCount();
        if (0 >= count) {
            return;
        }
        Animation animation;
        for (int i = 0; i < count; i ++) {
            int resourceId = typedArray.getResourceId(i, 0);
            animation = AnimationUtils.loadAnimation(this.mContext, resourceId);
            if (null == animation) {
                continue;
            }
            if (0 == i) {
                setDurationFadeShow(animation.getDuration());
                setInterpolatorFadeShow(animation.getInterpolator());
            }
            if (1 == i) {
                setDurationFadeHide(animation.getDuration());
                setInterpolatorFadeHide(animation.getInterpolator());
            }
        }
    }

    /****
     * */
    public long getState() {
        return this.mState;
    }

    /****
     * */
    private void setState(long state) {
        this.mState = state;
    }

    /****
     * */
    private void releaseTypedArray(TypedArray typedArray) {
        if (null != typedArray) {
            typedArray.recycle();
        }
    }

    /****
     * */
    public void setBackgroundDrawable(Drawable background) {
        // 抛弃PopupWindow的这个方法
    }

    /****
     * */
    public void setContentView(View contentView) {
        super.setContentView(contentView);
    }

    /****
     * */
    public boolean isShowingActually() {
        return PopupState.STATE_HIDE_ED != this.mState;
    }

    /****
     * */
    protected Context getContext() {
        return this.mContext;
    }

    /****
     * */
    private AnimatorSet getShowAnimatorInit() {
        if (null == this.mShowAnimator) {
            this.mShowAnimator = initShowAnimation();
        }
        return this.mShowAnimator;
    }

    /****
     * */
    private AnimatorSet getShowAnimator() {
        return this.mShowAnimator;
    }

    /****
     * */
    private AnimatorSet getHideAnimatorInit() {
        if (null == this.mHideAnimator) {
            this.mHideAnimator = initHideAnimation();
        }
        return this.mHideAnimator;
    }

    /****
     * */
    private AnimatorSet getHideAnimator() {
        return this.mHideAnimator;
    }

    /****
     * */
    private void setInterpolatorFadeShow(Interpolator interpolator) {
        this.mInterpolatorFadeShow = interpolator;
    }

    /****
     * */
    private Interpolator getInterpolatorFadeShow() {
        return this.mInterpolatorFadeShow;
    }

    /****
     * */
    private void setInterpolatorFadeHide(Interpolator interpolator) {
        this.mInterpolatorFadeHide = interpolator;
    }

    /****
     * */
    private Interpolator getInterpolatorFadeHide() {
        return this.mInterpolatorFadeHide;
    }

    /****
     * */
    private void setDurationFadeShow(long duration) {
        // 动画时长不能小于零
        this.mDurationFadeShow = Math.max(0, duration);
    }

    /****
     * */
    private long getDurationFadeShow() {
        return this.mDurationFadeShow;
    }

    /****
     * */
    private void setDurationFadeHide(long duration) {
        // 动画时长不能小于零
        this.mDurationFadeHide = Math.max(0, duration);
    }

    /****
     * */
    private long getDurationFadeHide() {
        return this.mDurationFadeHide;
    }

    /****
     * */
    private void setMaskLayer(MaskLayer layer) {
        this.mMaskLayer = layer;
    }

    /****
     * */
    private long getUniqueIdentify() {
        return this.mUniqueIdentify;
    }

    /****
     * */
    private void setUniqueIdentify(long identify) {
        this.mUniqueIdentify = identify;
    }

    /****
     * */
    private MaskLayer getMaskLayer() {
        return this.mMaskLayer;
    }

    /****
     * */
    private int getMaskColor() {
        return this.mMaskColor;
    }

    /****
     * */
    public void setMaskColor(int color) {
        this.mMaskColor = color;
        if (!isShowing()) {
            return;
        }
        View maskLayer = obtainMaskLayer();
        if (null != maskLayer) {
            maskLayer.setBackgroundColor(color);
        }
    }

    /****
     * */
    public void addPopupCallback(PopupCallback callback) {
        if (null == callback) {
            return;
        }
        if (null == this.mPopupCallbacks) {
            this.mPopupCallbacks = new ArrayList<>();
        }
        this.mPopupCallbacks.add(callback);
    }

    /****
     * */
    public void removePopupCallback(PopupCallback callback) {
        if (null == callback) {
            return;
        }
        if (null == this.mPopupCallbacks) {
            this.mPopupCallbacks = new ArrayList<>();
        }
        if (this.mPopupCallbacks.contains(callback)) {
            this.mPopupCallbacks.remove(callback);
        }
    }

    /****
     * */
    public void clearPopupCallbacks() {
        if (null == this.mPopupCallbacks) {
            this.mPopupCallbacks = new ArrayList<>();
        }
        this.mPopupCallbacks.clear();
    }

    /****
     * */
    private void prepare2show() {
        setupMaskLayer();
        AnimatorSet showAnimator = getShowAnimatorInit();
        AnimatorSet hideAnimator = getHideAnimator();
        if (null != hideAnimator && hideAnimator.isRunning()) {
            hideAnimator.cancel();
        }
        if (showAnimator.isRunning()) {
            return ;
        }
        showAnimator.start();
    }

    /****
     * */
    private void prepare2Hide() {
        AnimatorSet hideAnimator = getHideAnimatorInit();
        AnimatorSet showAnimator = getShowAnimator();
        if (null != showAnimator && showAnimator.isRunning()) {
            showAnimator.cancel();
        }
        if (hideAnimator.isRunning()) {
            return ;
        }
        hideAnimator.start();
    }

    /****
     * */
    private void setupMaskLayer() {
        MaskLayer layer = getMaskLayer();
        if (null == layer) {
            layer = new MaskLayer(this.mContext);
        }
        int maskColor = getMaskColor();
        layer.setBackgroundColor(maskColor);
        setMaskLayer(layer);
    }

    /****
     * */
    private AnimatorSet initShowAnimation() {
        View maskLayer = getMaskLayer();
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator01 = ObjectAnimator.ofFloat(maskLayer, "alpha", 0.f, 1.f);
        animatorSet.setInterpolator(getInterpolatorFadeShow());
        animatorSet.addListener(new ShowAnimatorListener());
        long durationFadeShow = getDurationFadeShow();
        animatorSet.setDuration(durationFadeShow);
        animatorSet.playTogether(animator01);
        return animatorSet;
    }

    /****
     * */
    private AnimatorSet initHideAnimation() {
        View maskLayer = getMaskLayer();
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator01 = ObjectAnimator.ofFloat(maskLayer, "alpha", 1.f, 0.f);
        animatorSet.setInterpolator(getInterpolatorFadeHide());
        animatorSet.addListener(new HideAnimatorListener());
        long durationFadeHide = getDurationFadeHide();
        animatorSet.setDuration(durationFadeHide);
        animatorSet.playTogether(animator01);
        return animatorSet;
    }

    /****
     * */
    private class ShowAnimatorListener extends AnimatorListenerAdapter {

        private int mCancelTag = 0;

        @Override
        public void onAnimationStart(Animator animation) {
            setState(PopupState.STATE_SHOW_ING);
            dispatchShowAnimatorStarted();
            View maskLayer = obtainMaskLayer();
            if (null == maskLayer) {
                addMaskLayerIfNecessary();
            }
            mCancelTag = 0;
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            mCancelTag = 1;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if (1 != mCancelTag) {
                dispatchShowAnimatorCompleted();
                setState(PopupState.STATE_SHOW_ED);
            }
        }
    }

    /****
     * */
    private void dispatchShowAnimatorStarted() {
        onShowAnimatorStarted(mBundleEnter);
        if (null == this.mPopupCallbacks || 0 >= this.mPopupCallbacks.size()) {
            return;
        }
        for (PopupCallback callback : this.mPopupCallbacks) {
            if (null == callback) {
                continue;
            }
            callback.onShowAnimatorStarted(mBundleEnter);
        }
    }

    /****
     * */
    private void dispatchShowAnimatorCompleted() {
        onShowAnimatorCompleted(mBundleEnter);
        if (null == this.mPopupCallbacks || 0 >= this.mPopupCallbacks.size()) {
            return;
        }
        for (PopupCallback callback : this.mPopupCallbacks) {
            if (null == callback) {
                continue;
            }
            callback.onShowAnimatorCompleted(mBundleEnter);
        }
    }

    /****
     * */
    private void dispatchHideAnimatorStarted() {
        onHideAnimatorStarted(mBundleEnter);
        if (null == this.mPopupCallbacks || 0 >= this.mPopupCallbacks.size()) {
            return;
        }
        for (PopupCallback callback : this.mPopupCallbacks) {
            if (null == callback) {
                continue;
            }
            callback.onHideAnimatorStarted(mBundleEnter);
        }
    }

    /****
     * */
    private void dispatchHideAnimatorCompleted() {
        onHideAnimatorCompleted(mBundleEnter);
        if (null == this.mPopupCallbacks || 0 >= this.mPopupCallbacks.size()) {
            return;
        }
        for (PopupCallback callback : this.mPopupCallbacks) {
            if (null == callback) {
                continue;
            }
            callback.onHideAnimatorCompleted(mBundleEnter);
        }
    }

    /****
     * */
    private class HideAnimatorListener extends AnimatorListenerAdapter {

        private int mCancelTag = 0;

        @Override
        public void onAnimationStart(Animator animation) {
            setState(PopupState.STATE_HIDE_ING);
            dispatchHideAnimatorStarted();
            mCancelTag = 0;
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            mCancelTag = 1;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            View maskLayer = obtainMaskLayer();
            if (null != maskLayer) {
                removeMaskLayerIfNecessary();
            }
            if (1 != mCancelTag) {
                dispatchHideAnimatorCompleted();
                setState(PopupState.STATE_HIDE_ED);
            }
        }
    }

    /****
     * */
    private View obtainMaskLayer() {
        Context context = this.mContext;
        if (null == context) {
            return null;
        }
        View decorView = ((Activity) context).getWindow().getDecorView();
        if (null == decorView) {
            return null;
        }
        View maskLayer = decorView.findViewById(R.id.keep_bit_res_id_mask_layer);
        if (null == maskLayer) {
            return null;
        }
        Object tag = maskLayer.getTag();
        if (!(tag instanceof Long)) {
            return null;
        }
        long identify = (long) tag;
        if (identify != getUniqueIdentify()) { // 对比唯一标识
            return null;
        }
        return maskLayer;
    }

    /****
     * */
    private void addMaskLayerIfNecessary() {
        if(Looper.myLooper() != Looper.getMainLooper()) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new CustomAddMaskLayerHandler());
        } else {
            addMaskLayer();
        }
    }

    /****
     * */
    private class CustomAddMaskLayerHandler implements Runnable {

        @Override
        public void run() {
            addMaskLayer();
        }
    }

    /****
     * */
    private void addMaskLayer() {
        View maskLayer = getMaskLayer();
        if(null == maskLayer) {
            return ;
        }
        Activity activity = (Activity) this.mContext;
        if (null == activity) {
            return;
        }
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        if(null == decorView) {
            return ;
        }
        View view = decorView.findViewById(R.id.keep_bit_res_id_mask_layer);
        if (null != view) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        maskLayer.setId(R.id.keep_bit_res_id_mask_layer);
        maskLayer.setTag(getUniqueIdentify()); // 设置唯一标识
        maskLayer.setOnTouchListener(getOutsideTouchListener());
        decorView.addView(maskLayer, layoutParams);
    }

    /****
     * */
    private void removeMaskLayerIfNecessary() {
        if(Looper.myLooper() != Looper.getMainLooper()) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new InnerRemoveMaskLayerHandler());
        } else {
            removeMaskLayer();
        }
    }

    /****
     * */
    private class InnerRemoveMaskLayerHandler implements Runnable {

        @Override
        public void run() {
            removeMaskLayer();
        }
    }

    /****
     * */
    private void removeMaskLayer() {
        Activity activity = (Activity) this.mContext;
        if (null == activity) {
            return;
        }
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        if(null == decorView) {
            return ;
        }
        View view = decorView.findViewById(R.id.keep_bit_res_id_mask_layer);
        if (null == view) {
            return;
        }
        Object tag = view.getTag();
        if (!(tag instanceof Long)) {
            return;
        }
        long identify = (long) tag;
        if (identify != getUniqueIdentify()) { // 对比唯一标识
            return;
        }
        decorView.removeView(view);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public final void showAsDropDown(View anchor, int offX, int offY, int gravity) {
        if (null == this.mBundleEnter) {
            this.mBundleEnter = new Bundle();
        }
        mBundleEnter.clear();
        super.showAsDropDown(anchor, offX, offY, gravity);
        prepare2show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public final void showAsDropDown(View anchor, int offX, int offY, int gravity, Bundle bundle) {
        if (null == this.mBundleEnter) {
            this.mBundleEnter = new Bundle();
        }
        mBundleEnter.clear();
        if (null != bundle) {
            mBundleEnter.putAll(bundle);
        }
        super.showAsDropDown(anchor, offX, offY, gravity);
        prepare2show();
    }

    public final void showAtLocation(View parent, int gravity, int x, int y) {
        if (null == this.mBundleEnter) {
            this.mBundleEnter = new Bundle();
        }
        mBundleEnter.clear();
        super.showAtLocation(parent, gravity, x, y);
        prepare2show();
    }

    public final void showAtLocation(View parent, int gravity, int x, int y, Bundle bundle) {
        if (null == this.mBundleEnter) {
            this.mBundleEnter = new Bundle();
        }
        mBundleEnter.clear();
        if (null != bundle) {
            mBundleEnter.putAll(bundle);
        }
        super.showAtLocation(parent, gravity, x, y);
        prepare2show();
    }

    public final void showAsDropDown(View anchor, int offX, int offY) {
        if (null == this.mBundleEnter) {
            this.mBundleEnter = new Bundle();
        }
        mBundleEnter.clear();
        super.showAsDropDown(anchor, offX, offY);
        prepare2show();
    }

    public final void showAsDropDown(View anchor, int offX, int offY, Bundle bundle) {
        if (null == this.mBundleEnter) {
            this.mBundleEnter = new Bundle();
        }
        mBundleEnter.clear();
        if (null != bundle) {
            mBundleEnter.putAll(bundle);
        }
        super.showAsDropDown(anchor, offX, offY);
        prepare2show();
    }

    public final void showAsDropDown(View anchor) {
        if (null == this.mBundleEnter) {
            this.mBundleEnter = new Bundle();
        }
        mBundleEnter.clear();
        super.showAsDropDown(anchor);
        prepare2show();
    }

    public final void showAsDropDown(View anchor, Bundle bundle) {
        if (null == this.mBundleEnter) {
            this.mBundleEnter = new Bundle();
        }
        mBundleEnter.clear();
        if (null != bundle) {
            mBundleEnter.putAll(bundle);
        }
        super.showAsDropDown(anchor);
        prepare2show();
    }

    public final void dismiss() {
        if (null == this.mBundleEnter) {
            this.mBundleEnter = new Bundle();
        }
        mBundleEnter.clear();
        super.dismiss();
        prepare2Hide();
    }

    public final void dismiss(Bundle bundle) {
        if (null == this.mBundleEnter) {
            this.mBundleEnter = new Bundle();
        }
        mBundleEnter.clear();
        if (null != bundle) {
            mBundleEnter.putAll(bundle);
        }
        super.dismiss();
        prepare2Hide();
    }

    private class MaskLayer extends View {

        public MaskLayer(Context context) {
            super(context);
        }

        @Override
        public boolean performClick() {
            return super.performClick();
        }
    }
}
