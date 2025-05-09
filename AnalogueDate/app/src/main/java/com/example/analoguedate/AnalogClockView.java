package com.example.analoguedate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class AnalogClockView extends View {

    private Paint circlePaint;
    private Paint linePaint;
    private Paint hourPaint;
    private Paint minutePaint;
    private Paint secondPaint;

    private int circleColor = Color.BLACK;
    private int lineColor = Color.GRAY;
    private int hourColor = Color.BLUE;
    private int minuteColor = Color.RED;
    private int secondColor = Color.GREEN;

    private int circleRadius;
    private int lineWidth;
    private int hourLength;
    private int minuteLength;
    private int secondLength;

    private int hours;
    private int minutes;
    private int seconds;

    public AnalogClockView(Context context) {
        super(context);

        initPaints();
        initDimensions();
        initTime();
    }

    private void initPaints() {
        circlePaint = new Paint();
        circlePaint.setColor(circleColor);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(2);

        linePaint = new Paint();
        linePaint.setColor(lineColor);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(1);

        hourPaint = new Paint();
        hourPaint.setColor(hourColor);
        hourPaint.setStyle(Paint.Style.STROKE);
        hourPaint.setStrokeWidth(5);

        minutePaint = new Paint();
        minutePaint.setColor(minuteColor);
        minutePaint.setStyle(Paint.Style.STROKE);
        minutePaint.setStrokeWidth(3);

        secondPaint = new Paint();
        secondPaint.setColor(secondColor);
        secondPaint.setStyle(Paint.Style.STROKE);
        secondPaint.setStrokeWidth(1);
    }

    private void initDimensions() {
        circleRadius = 200;
        lineWidth = 10;
        hourLength = 100;
        minuteLength = 150;
        secondLength = 200;
    }

    private void initTime() {
        hours = 0;
        minutes = 0;
        seconds = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawCircle(canvas);
        drawLines(canvas);
        drawHour(canvas);
        drawMinute(canvas);
        drawSecond(canvas);

        invalidate();
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, circleRadius, circlePaint);
    }

    private void drawLines(Canvas canvas) {
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(getWidth() / 2, getHeight() / 2 - circleRadius,
                    getWidth() / 2, getHeight() / 2 - circleRadius + lineWidth,
                    linePaint);
            canvas.rotate(30, getWidth() / 2, getHeight() / 2);
        }
    }

    private void drawHour(Canvas canvas) {
        canvas.save();
        canvas.rotate(hours * 30 + minutes * 0.5f, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                getWidth() / 2, getHeight() / 2 - hourLength,
                hourPaint);
        canvas.restore();
    }

    private void drawMinute(Canvas canvas) {
        canvas.save();
        canvas.rotate(minutes * 6 + seconds * 0.1f, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                getWidth() / 2, getHeight() / 2 - minuteLength,
                minutePaint);
        canvas.restore();
    }

    private void drawSecond(Canvas canvas) {
        canvas.save();
        canvas.rotate(seconds * 6, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                getWidth() / 2, getHeight() / 2 - secondLength,
                secondPaint);
        canvas.restore();
    }

    public void updateClock() {
        hours = android.text.format.DateFormat.is24HourFormat(getContext()) ? android.icu.util.Calendar.getInstance().get(android.icu.util.Calendar.HOUR_OF_DAY) : android.icu.util.Calendar.getInstance().get(android.icu.util.Calendar.HOUR);
        minutes = android.icu.util.Calendar.getInstance().get(android.icu.util.Calendar.MINUTE);
        seconds = android.icu.util.Calendar.getInstance().get(android.icu.util.Calendar.SECOND);
    }
}