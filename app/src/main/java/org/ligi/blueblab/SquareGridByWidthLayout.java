package org.ligi.blueblab;

import android.content.Context;
import android.support.v7.widget.GridLayout;
import android.util.AttributeSet;


public class SquareGridByWidthLayout extends GridLayout {

    public SquareGridByWidthLayout(final Context context) {
        super(context);
    }

    public SquareGridByWidthLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

}
