package tw.com.ian.pwci.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import tw.com.ian.pwci.R;

public class RecogintionView extends View {

    public RecogintionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.GREEN);
    }

    public RecogintionView(Context context,AttributeSet attrs,int color)
    {
        super(context, attrs);
        setBackgroundColor(getResources().getColor(color,null));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
