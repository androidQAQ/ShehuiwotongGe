package com.example.zidingyi;


import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 曹少航 on 2017/10/25.
 */

public class MyCilreView extends View {
    private Paint paint;
    private static  final float RADIUS=50f;
    private Point currentPoint;


    public MyCilreView(Context context) {
        super(context);
    }

    public MyCilreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint =new Paint();
    }

    public MyCilreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public class Point{
        private float x;
        private float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(currentPoint==null){
            currentPoint =new Point(RADIUS,RADIUS);
            canvas.drawCircle(currentPoint.getX(),currentPoint.getY(),RADIUS,paint);
            KaS();
        }else {
            canvas.drawCircle(currentPoint.getX(),currentPoint.getY(),RADIUS,paint);
        }


    }

    class PointEvaluator implements TypeEvaluator{


        @Override
        public Object evaluate(float v, Object o, Object t1) {
          Point startPoint=(Point)o;
          Point endPoint=(Point)t1;

          float x=startPoint.getX()+ v*(endPoint.getX()-startPoint.getX());
          float y=startPoint.getY()+ v*(endPoint.getY()-startPoint.getY());

           return new Point(x,y);
        }
    }



    private void KaS(){
        Point startPoint=new Point(RADIUS,RADIUS);
        Point endPoint=new Point(getWidth()-RADIUS,getHeight()-RADIUS);
        ValueAnimator value=ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
        value.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if(valueAnimator!=null){
                    currentPoint= (Point) valueAnimator.getAnimatedValue();
                    invalidate();
                }
            }
        });
        AnimatorSet set=new AnimatorSet();
        set.play(value);
        set.setDuration(5000);
        set.start();

    }

}
