package com.example.guaguaka;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 茅净周 on 2016/11/8.
 */

public class GuaTest extends View {
    private Bitmap mBitmap1,mBitmap2;
    private Paint mPaint;
    private Canvas mCanvas;
    private Path mPath;

    public GuaTest(Context context) {
        super(context);
        initView();
    }

    public GuaTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GuaTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    //初始化
    private void initView() {
        mPaint = new Paint();
        mPaint.setAlpha(0);//透明
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));//圆角
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(50);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPath = new Path();
        mBitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        mBitmap2 = Bitmap.createBitmap(
                mBitmap1.getWidth(),
                mBitmap1.getHeight(),
                Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap2);
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取手指滑动路径
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                break;
        }
        mCanvas.drawPath(mPath, mPaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap1, 0, 0, null);
        canvas.drawBitmap(mBitmap2, 0, 0, null);
    }
}
