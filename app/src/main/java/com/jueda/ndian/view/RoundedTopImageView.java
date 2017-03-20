package com.jueda.ndian.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/6/28.
 * 顶部圆角
 */
public class RoundedTopImageView extends ImageView {
    /*圆角的半径，依次为左上角xy半径，右上角，右下角，左下角*/
    private float[] rids = {15.0f,15.0f,15.0f,15.0f,0.0f,0.0f,0.0f,0.0f,};
    public RoundedTopImageView( Context context) {
        super(context);
    }
    public RoundedTopImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public RoundedTopImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /**	 * 画图	 * by Hankkin at:2015-08-30 21:15:53	 * @param canvas	 */
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        		/*向路径中添加圆角矩形。radii数组定义圆角矩形的四个圆角的x,y半径。radii长度必须为8*/
        path.addRoundRect(new RectF(0,0,w,h),rids,Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
