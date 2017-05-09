package com.honyum.owner.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.honyum.owner.R;

/**
 * Created by 李有鬼 on 2017/3/7 0007
 */

public class MyLetterView extends View {

    private static final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
            "Z"};

    private Rect mRect;

    private Paint mPaint;

    private TextView tv;

    private int mLetterTextColor;

    private int mLetterTextPressedColor;

    private int idx = -1;

    public MyLetterView(Context context) {
        this(context, null);
    }

    public MyLetterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLetterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyLetterView);

        int mLetterTextSize = a.getDimensionPixelSize(R.styleable.MyLetterView_letterTextSize,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15,
                        getResources().getDisplayMetrics()));

        mLetterTextColor = a.getColor(R.styleable.MyLetterView_letterTextColor, Color.parseColor("#A1A1A1"));

        mLetterTextPressedColor = a.getColor(R.styleable.MyLetterView_letterTextPressedColor, Color.BLACK);

        a.recycle();

        mRect = new Rect();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(mLetterTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.getTextBounds(letters[0], 0, letters[0].length(), mRect);
            width = getPaddingLeft() + mRect.width() + getPaddingRight();
        }


        setMeasuredDimension(width, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight() / letters.length;

        for (int i = 0; i < letters.length; i++) {
            mPaint.setColor(mLetterTextColor);
            float x = width / 2 - mRect.width() / 2;
            float y = height / 2 + mRect.height() / 2 + height * i;
            canvas.drawText(letters[i], x, y, mPaint);
        }

        if (idx >= 0 && idx < letters.length) {
            mPaint.setColor(mLetterTextPressedColor);
            float x = width / 2 - mRect.width() / 2;
            float y = height / 2 + mRect.height() / 2 + height * idx;
            canvas.drawText(letters[idx], x, y, mPaint);
        }
    }

    public void setTextView(TextView tv) {
        this.tv = tv;
        tv.setVisibility(GONE);
    }


    public interface onTouchLetterListener {
        void onTouchLetter(String letter);
    }

    private onTouchLetterListener onTouchLetterListener;

    public void setOnTouchLetterListener(onTouchLetterListener onTouchLetterListener) {
        this.onTouchLetterListener = onTouchLetterListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                idx = (int) (event.getY() * letters.length / getHeight());

                if (tv != null) {
                    tv.setVisibility(VISIBLE);
                }

                if (idx >= 0 && idx < letters.length) {
                    if (tv != null) {
                        tv.setText(letters[idx]);
                    }

                    if (onTouchLetterListener != null) {
                        onTouchLetterListener.onTouchLetter(letters[idx]);
                    }

                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (tv != null) {
                    tv.setVisibility(GONE);
                }
                idx = -1;
                postInvalidate();
                break;
        }

        return true;
    }
}
