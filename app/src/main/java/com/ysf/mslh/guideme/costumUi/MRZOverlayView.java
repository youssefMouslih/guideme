package com.ysf.mslh.guideme.costumUi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class MRZOverlayView extends View {
    private Rect bounds;

    // Constructor that takes Context and AttributeSet
    public MRZOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Optional: If you want to initialize custom attributes or styles, you can do it here.
    }

    // Method to set the bounds for drawing
    public void setBounds(Rect bounds) {
        this.bounds = bounds;
        invalidate(); // Redraw the view
    }

    // Method to clear the bounds and stop drawing the rectangle
    public void clearBounds() {
        this.bounds = null;
        invalidate();
    }

    // Method to draw the bounds (rectangle) on the canvas
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bounds != null) {
            Paint paint = new Paint();
            paint.setColor(Color.RED); // Choose your color
            paint.setStyle(Paint.Style.STROKE); // Make it a stroke (outline)
            paint.setStrokeWidth(5); // Set the stroke width
            canvas.drawRect(bounds, paint); // Draw the rectangle
        }
    }
}
