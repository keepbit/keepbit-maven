package com.keepbit.android.app.recycler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.keepbit.android.lib.utils.DimensionUtil;

/**
 * Created by CoderMario on 2019-05-13.
 */
public class ItemDecorationFragment201905130003 extends RecyclerView.ItemDecoration {

    private Drawable mGapDrawable;
    private int mGapHeight;

    public ItemDecorationFragment201905130003(Context context) {
        mGapHeight = DimensionUtil.dp2valueInt(context, 8);
    }

    @Override
    public void getItemOffsets(Rect bound, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int itemCount = 0;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (null != adapter) {
            itemCount = Math.max(0, adapter.getItemCount());
        }
        int itemPosition = recyclerView.getChildAdapterPosition(view);
        if (itemPosition < itemCount - 1) {
            bound.set(0, 0, 0, mGapHeight);
        } else {
            bound.set(0, 0, 0, 0);
        }
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int childCount = recyclerView.getChildCount();
        if (0 >= childCount) {
            return;
        }
        View childView;
        int top, left, right, bottom;
        for (int childIndex = 0; childIndex < childCount; childIndex ++) {
             childView = recyclerView.getChildAt(childIndex);
             if (null == childView) {
                 continue;
             }
             top = childView.getBottom();
             left = childView.getLeft();
             right = childView.getRight();
             bottom = childView.getBottom() + mGapHeight;

             getGapDrawable().setBounds(left, top, right, bottom);
             getGapDrawable().draw(canvas);
        }
    }

    private Drawable getGapDrawable() {
        if (null == this.mGapDrawable) {
            ShapeDrawable drawable = new ShapeDrawable();
            drawable.getPaint().setColor(Color.parseColor("#FFF1F1F1"));
            drawable.getPaint().setAntiAlias(true);
            drawable.getPaint().setDither(true);
            this.mGapDrawable = drawable;
        }
        return this.mGapDrawable;
    }
}
