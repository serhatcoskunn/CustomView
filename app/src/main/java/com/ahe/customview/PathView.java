package com.ahe.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PathView extends View {

    private Paint mPaint;
    private Path mPath;

    private int width;
    private int height;
    private int radius;
    private int vaweHeight;
    private int lineHeight;
    private int period=13;//tepe sayısı
    private int periodSize;

    public PathView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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

        //setMeasuredDimension((int)radius*2,(int)radius*2);

        Log.d("CustomVİEW","width = "+width+" height = "+height+" radius"+radius);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);


        mPath.moveTo(width,vaweHeight);
        mPath.lineTo(width,height);
        mPath.lineTo(0,height);
        mPath.lineTo(0,vaweHeight);


        //mPath.quadTo(width/2, vaweHeight*2, width, vaweHeight);//aşağı doğru
        //mPath.quadTo(width/2, 0, width, vaweHeight);//yukarı doğru

        //template
        //mPath.quadTo(width/4, vaweHeight*2, width/2, vaweHeight);//aşağı doğru
        //mPath.quadTo((width/4)*3, 0, width, vaweHeight);//yukarı doğru



        int counter=0;
        for(int i=periodSize;i<=width;i=i+periodSize)
        {
            int temp;
            if(counter % 2 != 0 )//yukaru
            {
                mPath.quadTo(i-(periodSize/2), 0, i, vaweHeight);
                //mPath.quadTo(i-(periodSize/2), -vaweHeight, i, vaweHeight);

            }
            else//aşağı
            {
                mPath.quadTo(i-(periodSize/2), vaweHeight*2,i, vaweHeight);
                //mPath.quadTo(i-(periodSize/2), vaweHeight*2,i, vaweHeight);
            }
            temp=(i-(periodSize*counter))/2;
            Log.d("counter",""+temp);
            counter++;

        }










        //YEDEK
        /*mPath.moveTo(width,0);
        mPath.lineTo(width,height/2);
        mPath.lineTo(0,height/2);
        mPath.lineTo(0,0);

        mPath.quadTo(50, -50, 100, 0);
        mPath.quadTo(150, 100, 200, 0);
        mPath.quadTo(250, 50, 300, 0);
        mPath.quadTo(350, 50, 400, 0);
        mPath.quadTo(450, -50, 500, 100);*/


        //mPath.moveTo(0,vaweHeight);
        //mPath.lineTo(width,vaweHeight);




        canvas.drawPath(mPath, mPaint);

        Log.d("CustomVİEWasd","width = "+width+" height = "+height+" radius"+radius);
        //mPath.reset();
        //mPaint.setColor(Color.GREEN);
        //mPaint.setStrokeWidth(1);
        //mPath.moveTo(50, 50);
        //mPath.lineTo(300, 50);
        //mPath.lineTo(100, 400);
        //mPath.lineTo(400, 400);

        //canvas.drawPath(mPath, mPaint);
    }
}