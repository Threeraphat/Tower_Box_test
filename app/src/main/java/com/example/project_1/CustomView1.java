package com.example.project_1;

import androidx.annotation.Nullable;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.project_1.Model.ShapeModel;
import java.util.List;

public class CustomView1 extends View {

    private Paint mPaintFill, mPaintStroke;

    public CustomView1(Context context) {
        super(context);
        init(null);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        mPaintFill = new Paint();
        mPaintStroke = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawblock(canvas);
        invalidate();
    }

    private void drawblock(Canvas canvas) {
        List<ShapeModel> colorList = MainActivity.shapeModelList;
        for (int i = 0; i < colorList.size(); i++) {
            mPaintFill.setColor(getResources().getColor(colorList.get(i).getColor()));
            mPaintFill.setStyle(Paint.Style.FILL);
            mPaintFill.setAntiAlias(true);
            mPaintStroke.setStyle(Paint.Style.STROKE);
            mPaintStroke.setColor(getResources().getColor(R.color.gray2));
            mPaintStroke.setStrokeWidth(5);
            mPaintStroke.setAntiAlias(true);
            int cornorRadius = 50;
            int screenWidth = getWidth();
            int screenHeight = getHeight() - 210;

            RectF rect = new RectF();
            rect.left = 30;
            rect.top = screenHeight - (220 * i);
            rect.right = screenWidth - 30;
            rect.bottom = rect.top + 200;
            if (i < colorList.size() - 1) {
                canvas.drawRoundRect(rect, cornorRadius, cornorRadius, mPaintFill);
                canvas.drawRoundRect(rect, cornorRadius, cornorRadius, mPaintStroke);
            } else {
                drawRhombus(canvas,mPaintFill,screenWidth/2,screenHeight - (220 * i),410);
                drawRhombus(canvas,mPaintStroke,screenWidth/2,screenHeight - (220 * i),410);
            }
        }
    }

    public void drawRhombus(Canvas canvas, Paint paint, int x, int y, int width) {
        int halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(x, y + halfWidth); // Top
        path.lineTo(x - halfWidth, y); // Left
        path.lineTo(x, y - halfWidth); // Bottom
        path.lineTo(x + halfWidth, y); // Right
        path.lineTo(x, y + halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, paint);
    }
}