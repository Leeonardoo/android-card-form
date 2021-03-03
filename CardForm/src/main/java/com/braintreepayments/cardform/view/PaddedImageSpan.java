package com.braintreepayments.cardform.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import com.braintreepayments.cardform.utils.ViewUtils;

public class PaddedImageSpan extends ImageSpan {

    private boolean mDisabled;
    private final int mResourceId;
    private final int mPadding;
    private final int mMarginTop;

    public PaddedImageSpan(Context context, int resourceId, int marginTop) {
        super(context, resourceId);
        mResourceId = resourceId;
        mPadding = ViewUtils.dp2px(context, 8);
        mMarginTop = ViewUtils.dp2px(context, marginTop);
    }

    public void setDisabled(boolean disabled) {
        mDisabled = disabled;
    }

    int getResourceId() {
        return mResourceId;
    }

    boolean isDisabled() {
        return mDisabled;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return super.getSize(paint, text, start, end, fm) + (2 * mPadding);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom,
                     Paint paint) {
        super.draw(canvas, text, start, end, x + mPadding, top, y, bottom, paint);
    }

    @Override
    public Drawable getDrawable() {
        Drawable drawable = super.getDrawable();
        Rect newBounds = drawable.getBounds();
        newBounds.offset(0, mMarginTop);
        drawable.mutate().setBounds(newBounds);
        if (mDisabled) {
            drawable.mutate().setAlpha(80);
        }

        return drawable;
    }
}
