package com.ahe.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;

public class CustomLayout extends ConstraintLayout {
    private Paint mPaint;
    private Paint mPaintLine;
    private Path mPath;

    private int width;
    private int height;
    private int radius;

    private int vaweHeight;
    private float vawePercent;

    private int lineHeight;
    private float linePercent;
    private int lineLocation;
    private int lineColor;
    private int linewidth;



    private float period;
    private int periodSize;




    public CustomLayout(Context context) {
        super(context);
        init();
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyCustomElement, 0, 0);
        try {
            lineLocation = ta.getInteger(R.styleable.MyCustomElement_line_location, 0);
            linePercent = ta.getFloat(R.styleable.MyCustomElement_line_percent, 0.1f);
            lineColor = ta.getColor(R.styleable.MyCustomElement_line_color, Color.BLUE);
            vawePercent = ta.getFloat(R.styleable.MyCustomElement_vawe_percent, 0.1f);
            period = ta.getFloat(R.styleable.MyCustomElement_vawe_period, 6.5f);
        } finally {
            ta.recycle();
        }
        init();
    }

    private void init() {
        Log.d("CustomView","init");
        mPaint = new Paint();
        mPaintLine=new Paint();


        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setShadowLayer(10, 0, 0, Color.BLACK);
        setLayerType(LAYER_TYPE_SOFTWARE, mPaint);

        mPaintLine.setStyle(Paint.Style.STROKE);
        //mPaintLine.setStrokeWidth(width/10);
        mPaintLine.setColor(Color.BLUE);
        mPath = new Path();


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("CustomView","onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=getMeasuredWidth();
        height=getMeasuredHeight();
        radius=(int)(Math.min(width, height)*0.5);
        vaweHeight=(int)(height*vawePercent);
        lineHeight=height-vaweHeight;
        linewidth=(int)(width*linePercent);
        //periodSize=(int)(width/(period*2));
        mPaintLine.setStrokeWidth(width*linePercent);

        if(lineLocation==1 && lineLocation ==2)
        {
            periodSize=(int)((width-linewidth)/(period*2));
        }
        else
        {
            periodSize=(int)(width/(period*2));
        }
    }



    @Override
    public void dispatchDraw(Canvas canvas) {
        Log.d("CustomView","dispatchDraw");

        mPath.moveTo(width,vaweHeight);
        mPath.lineTo(width,height);
        mPath.lineTo(0,height);
        mPath.lineTo(0,vaweHeight);





        int counter=0;
        int temI=periodSize;
        if(lineLocation==0){ mPath.moveTo(linewidth,vaweHeight);temI+=linewidth;}
        for(int i=temI;i<=width;i=i+periodSize)
        {

            int lastX=(width-i) < periodSize ?  width : i;

            if(counter % 2 != 0 )//up vawe
            {
                mPath.quadTo(i-(periodSize/2), 0, lastX, vaweHeight);
            }
            else//down vawe
            {
                mPath.quadTo(i-(periodSize/2), vaweHeight*2,lastX, vaweHeight);
            }
            counter++;

        }

        canvas.drawPath(mPath, mPaint);

        switch (lineLocation)
        {
            case 0://left
                canvas.drawLine(linewidth/2,vaweHeight,linewidth/2,height,mPaintLine);//leftBottom
                break;
            case 1://right
                canvas.drawLine(linewidth/2,vaweHeight,linewidth/2,height,mPaintLine);//rightLine
                break;
            case 2://bottom
                canvas.drawLine(0,height-(linewidth/2),width,height-(linewidth/2),mPaintLine);
                break;
            default://left
                canvas.drawLine(linewidth/2,vaweHeight,linewidth/2,height,mPaintLine);//leftBottom
                break;
        }


        super.dispatchDraw(canvas);
    }
}
