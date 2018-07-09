package com.ahe.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;

public class CustomLayoutYEDEK extends ConstraintLayout {
    private Paint mPaint;
    private Path mPath;

    private int width;
    private int height;
    private int radius;
    private int vaweHeight;
    private int lineHeight;
    private int period=13;//tepe sayısı
    private int periodSize;


    public CustomLayoutYEDEK(Context context) {
        super(context);
        init();
    }

    public CustomLayoutYEDEK(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=getMeasuredWidth();
        height=getMeasuredHeight();
        radius=(int)(Math.min(width, height)*0.5);
        vaweHeight=(int)(height*0.1);
        lineHeight=height-vaweHeight;
        periodSize=width/period;
    }



    @Override
    public void dispatchDraw(Canvas canvas) {


        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mPaint.setShadowLayer(10, 0, 0, Color.BLACK);

        // Important for certain APIs
        setLayerType(LAYER_TYPE_SOFTWARE, mPaint);

        mPath.moveTo(width,vaweHeight);
        mPath.lineTo(width,height);
        mPath.lineTo(0,height);
        mPath.lineTo(0,vaweHeight);

        int counter=0;
        for(int i=periodSize;i<=width;i=i+periodSize)
        {
            int lastX=(width-i) < periodSize ?  width : i;
            int temp;
            if(counter % 2 != 0 )//yukaru
            {
                mPath.quadTo(i-(periodSize/2), 0, lastX, vaweHeight);
            }
            else//aşağı
            {
                mPath.quadTo(i-(periodSize/2), vaweHeight*2,lastX, vaweHeight);
            }
            temp=i-(periodSize/2);
            Log.d("counterlay",i+" "+temp);
            counter++;

        }

        canvas.drawPath(mPath, mPaint);

        Log.d("CustomVİEWasd","width = "+width+" height = "+height+" radius"+radius);

        super.dispatchDraw(canvas);
    }
}
