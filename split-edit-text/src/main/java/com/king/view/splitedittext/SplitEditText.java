package com.king.view.splitedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class SplitEditText extends AppCompatEditText {

    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 画笔宽度
     */
    private float mStrokeWidth;

    /**
     * 边框颜色
     */
    private int mBorderColor = 0xFF666666;
    /**
     * 输入的边框颜色
     */
    private int mInputBorderColor = 0xFF1E90FF;
    /**
     * 焦点的边框颜色
     */
    private int mFocusBorderColor;

    /**
     * 框的背景颜色
     */
    private int mBoxBackgroundColor;

    /**
     * 框的圆角大小
     */
    private float mBorderCornerRadius;

    /**
     * 框与框之间的间距大小
     */
    private float mBorderSpacing;

    /**
     * 输入框宽度
     */
    private float mBoxWidth;

    /**
     * 输入框高度
     */
    private float mBoxHeight;

    /**
     * 允许输入的最大长度
     */
    private int mMaxLength = 6;

    /**
     * 文本长度
     */
    private int mTextLength;
    /**
     * 路径
     */
    private Path mPath;

    private RectF mRectF;
    private float[] mRadiusFirstArray;
    private float[] mRadiusLastArray;

    /**
     * 边框风格
     */
    private @BorderStyle int mBorderStyle = BorderStyle.BOX;

    /**
     * 边框风格
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({BorderStyle.BOX, BorderStyle.LINE})
    public @interface BorderStyle {
        /**
         * 框
         */
        int BOX = 0;
        /**
         * 线
         */
        int LINE = 1;
    }

    /**
     * 文本风格
     */
    private @TextStyle int mTextStyle = TextStyle.PLAIN_TEXT;


    /**
     * 文本风格
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TextStyle.PLAIN_TEXT, TextStyle.CIPHER_TEXT})
    public @interface TextStyle {
        /**
         * 明文
         */
        int PLAIN_TEXT = 0;
        /**
         * 密文
         */
        int CIPHER_TEXT = 1;
    }

    /**
     * 密文掩码
     */
    private String mCipherMask;

    /**
     * 是否是粗体
     */
    private boolean isFakeBoldText;

    private static final String DEFAULT_CIPHER_MASK = "*";

    private boolean isDraw;

    private OnTextInputListener mOnTextInputListener;

    public SplitEditText(@NonNull Context context) {
        this(context,null);
    }

    public SplitEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,android.R.attr.editTextStyle);
    }

    public SplitEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }


    private void init(@NonNull Context context, @Nullable AttributeSet attrs){

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        mStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,1f,displayMetrics);
        mBorderSpacing = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8f,displayMetrics);
        setPadding(0,0,0,0);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.SplitEditText);
        final int count = a.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = a.getIndex(i);
            if(attr == R.styleable.SplitEditText_setStrokeWidth){
                mStrokeWidth = a.getDimension(attr,mStrokeWidth);
            }else if (attr == R.styleable.SplitEditText_setBorderColor){
                mBorderColor = a.getColor(attr,mBorderColor);
            }else if (attr == R.styleable.SplitEditText_setInputBorderColor){
                mInputBorderColor = a.getColor(attr,mInputBorderColor);
            }else if (attr == R.styleable.SplitEditText_setFocusBorderColor){
                mFocusBorderColor = a.getColor(attr,mFocusBorderColor);
            }else if (attr == R.styleable.SplitEditText_setBoxBackgroundColor){
                mBoxBackgroundColor = a.getColor(attr,mBoxBackgroundColor);
            }else if (attr == R.styleable.SplitEditText_setBorderCornerRadius){
                mBorderCornerRadius = a.getDimension(attr,mBorderCornerRadius);
            }else if (attr == R.styleable.SplitEditText_setBorderSpacing){
                mBorderSpacing = a.getDimension(attr,mBorderSpacing);
            }else if (attr == R.styleable.SplitEditText_setMaxLength){
                mMaxLength = a.getInt(attr,mMaxLength);
            }else if (attr == R.styleable.SplitEditText_setBorderStyle){
                mBorderStyle = a.getInt(attr,mBorderStyle);
            }else if (attr == R.styleable.SplitEditText_setTextStyle){
                mTextStyle = a.getInt(attr,mTextStyle);
            }else if (attr == R.styleable.SplitEditText_setCipherMask){
                mCipherMask = a.getString(attr);
            }else if (attr == R.styleable.SplitEditText_setFakeBoldText){
                isFakeBoldText = a.getBoolean(attr,false);
            }
        }

        a.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);

        mPath = new Path();
        mRadiusFirstArray = new float[8];
        mRadiusLastArray = new float[8];
        mRectF = new RectF(0,0,0,0);

        if(TextUtils.isEmpty(mCipherMask)){
            mCipherMask = DEFAULT_CIPHER_MASK;
        }else if(mCipherMask.length() > 1){
            mCipherMask = mCipherMask.substring(0,1);
        }

        setBackground(null);
        setCursorVisible(false);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(mMaxLength)});
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int width = w - getPaddingLeft() - getPaddingRight();
        int height = h - getPaddingTop() - getPaddingBottom();
        updateSizeChanged(width,height);
    }

    private void updateSizeChanged(int width,int height){
        //如果框与框之间的间距小于0或者总间距大于控件可用宽度则将间距重置为0
        if(mBorderSpacing < 0 || (mMaxLength - 1) * mBorderSpacing > width){
            mBorderSpacing = 0;
        }
        //计算出每个框的宽度
        mBoxWidth = (width - (mMaxLength - 1) * mBorderSpacing) / mMaxLength - mStrokeWidth;
        mBoxHeight = height - mStrokeWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //移除super.onDraw(canvas);不绘制EditText相关的
        //绘制边框
        drawBorders(canvas);
    }

    private void drawBorders(Canvas canvas){
        isDraw = true;
        //遍历绘制未输入文本的框边界
        for(int i = mTextLength; i < mMaxLength; i++){
            drawBorder(canvas,i,mBorderColor);
        }

        int color = mInputBorderColor != 0 ? mInputBorderColor : mBorderColor;
        //遍历绘制已输入文本的框边界
        for(int i = 0; i < mTextLength; i++){
            drawBorder(canvas,i,color);
        }

        //绘制焦点框边界
        if(mTextLength < mMaxLength && mFocusBorderColor != 0 && isFocused()){
            drawBorder(canvas,mTextLength,mFocusBorderColor);
        }
    }

    private void drawBorder(Canvas canvas,int position,int borderColor){
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setFakeBoldText(false);
        mPaint.setColor(borderColor);

        //计算出对应的矩形
        float left = getPaddingLeft() + mStrokeWidth / 2 + (mBoxWidth + mBorderSpacing) * position;
        float top = getPaddingTop() + mStrokeWidth / 2;
        mRectF.set(left,top,left + mBoxWidth,top + mBoxHeight);

        //边框风格
        switch (mBorderStyle){
            case BorderStyle.BOX:
                drawBorderBox(canvas,position,borderColor);
                break;
            case BorderStyle.LINE:
                drawBorderLine(canvas);
                break;
        }
        if(mTextLength > position && !TextUtils.isEmpty(getText())){
            drawText(canvas,position);
        }
    }

    private void drawText(Canvas canvas,int position){
        mPaint.setStrokeWidth(0);
        mPaint.setColor(getCurrentTextColor());
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setTextSize(getTextSize());
        mPaint.setFakeBoldText(isFakeBoldText);
        float x = mRectF.centerX();
        //y轴坐标 = 中心线 + 文字高度的一半 - 基线到文字底部的距离（也就是bottom）
        float y = mRectF.centerY() + (mPaint.getFontMetrics().bottom - mPaint.getFontMetrics().top) / 2 - mPaint.getFontMetrics().bottom;
        switch (mTextStyle){
            case TextStyle.PLAIN_TEXT:
                canvas.drawText(String.valueOf(getText().charAt(position)),x,y,mPaint);
                break;
            case TextStyle.CIPHER_TEXT:
                canvas.drawText(mCipherMask,x,y,mPaint);
                break;
        }
    }

    /**
     * 绘制框风格
     * @param canvas
     * @param position
     */
    private void drawBorderBox(Canvas canvas,int position,int borderColor){
        if(mBorderCornerRadius > 0){//当边框带有圆角时
            if(mBorderSpacing == 0){//当边框之间的间距为0时，只需要开始一个和最后一个框有圆角
                if(position == 0 || position == mMaxLength - 1){
                    if(mBoxBackgroundColor != 0){
                        mPaint.setStyle(Paint.Style.FILL);
                        mPaint.setColor(mBoxBackgroundColor);
                        canvas.drawPath(getRoundRectPath(mRectF,position == 0),mPaint);
                    }
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setColor(borderColor);
                    canvas.drawPath(getRoundRectPath(mRectF,position == 0),mPaint);
                }else{
                    if(mBoxBackgroundColor != 0){
                        mPaint.setStyle(Paint.Style.FILL);
                        mPaint.setColor(mBoxBackgroundColor);
                        canvas.drawRect(mRectF,mPaint);
                    }
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setColor(borderColor);
                    canvas.drawRect(mRectF,mPaint);
                }
            }else{
                if(mBoxBackgroundColor != 0){
                    mPaint.setStyle(Paint.Style.FILL);
                    mPaint.setColor(mBoxBackgroundColor);
                    canvas.drawRoundRect(mRectF,mBorderCornerRadius,mBorderCornerRadius,mPaint);
                }
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(borderColor);
                canvas.drawRoundRect(mRectF,mBorderCornerRadius,mBorderCornerRadius,mPaint);
            }
        }else{
            if(mBoxBackgroundColor != 0){
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(mBoxBackgroundColor);
                canvas.drawRect(mRectF,mPaint);
            }
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(borderColor);
            canvas.drawRect(mRectF,mPaint);
        }
    }

    /**
     * 绘制线风格
     * @param canvas
     */
    private void drawBorderLine(Canvas canvas){
        float y = getPaddingTop() + mBoxHeight;
        canvas.drawLine(mRectF.left,y,mRectF.right,y,mPaint);
    }

    private Path getRoundRectPath(RectF rectF,boolean isFirst){
        mPath.reset();
        if(isFirst){
            //左上角
            mRadiusFirstArray[0] = mBorderCornerRadius;
            mRadiusFirstArray[1] = mBorderCornerRadius;
            //左下角
            mRadiusFirstArray[6] = mBorderCornerRadius;
            mRadiusFirstArray[7] = mBorderCornerRadius;
            mPath.addRoundRect(rectF,mRadiusFirstArray, Path.Direction.CW);
        }else{
            //右上角
            mRadiusLastArray[2] = mBorderCornerRadius;
            mRadiusLastArray[3] = mBorderCornerRadius;
            //右下角
            mRadiusLastArray[4] = mBorderCornerRadius;
            mRadiusLastArray[5] = mBorderCornerRadius;
            mPath.addRoundRect(rectF,mRadiusLastArray, Path.Direction.CW);
        }
        return mPath;
    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        mTextLength = text.length();
        refreshView();
        //改变监听
        if(mOnTextInputListener != null){
            mOnTextInputListener.onTextInputChanged(text.toString(),mTextLength);
            if(mTextLength == mMaxLength){
                mOnTextInputListener.onTextInputCompleted(text.toString());
            }
        }
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (selStart == selEnd) {
            setSelection(getText() == null ? 0 : getText().length());
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        //焦点改变时刷新状态
        refreshView();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isDraw = false;
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public int getInputBorderColor() {
        return mInputBorderColor;
    }

    public int getFocusBorderColor() {
        return mFocusBorderColor;
    }

    public int getBoxBackgroundColor() {
        return mBoxBackgroundColor;
    }

    public float getBorderCornerRadius() {
        return mBorderCornerRadius;
    }

    public float getBorderSpacing() {
        return mBorderSpacing;
    }

    @BorderStyle
    public int getBorderStyle() {
        return mBorderStyle;
    }


    public void setBorderColor(int borderColor) {
        this.mBorderColor = borderColor;
        refreshView();
    }

    public void setInputBorderColor(int inputBorderColor) {
        this.mInputBorderColor = inputBorderColor;
        refreshView();
    }

    public void setFocusBorderColor(int focusBorderColor) {
        this.mFocusBorderColor = focusBorderColor;
        refreshView();
    }

    public void setBoxBackgroundColor(int boxBackgroundColor) {
        this.mBoxBackgroundColor = boxBackgroundColor;
        refreshView();
    }

    public void setBorderCornerRadius(float borderCornerRadius) {
        this.mBorderCornerRadius = borderCornerRadius;
        refreshView();
    }

    public void setBorderSpacing(float borderSpacing) {
        this.mBorderSpacing = borderSpacing;
        refreshView();
    }

    public void setBorderStyle(@TextStyle int borderStyle) {
        this.mBorderStyle = borderStyle;
        refreshView();
    }

    @TextStyle
    public int getTextStyle() {
        return mTextStyle;
    }

    public void setTextStyle(@TextStyle int textStyle) {
        this.mTextStyle = textStyle;
        refreshView();
    }

    public String getCipherMask() {
        return mCipherMask;
    }

    /**
     * 是否粗体
     * @param fakeBoldText
     */
    public void setFakeBoldText(boolean fakeBoldText) {
        isFakeBoldText = fakeBoldText;
        refreshView();
    }

    /**
     * 设置密文掩码 不设置时，默认为{@link #DEFAULT_CIPHER_MASK}
     * @param cipherMask
     */
    public void setCipherMask(String cipherMask) {
        this.mCipherMask = cipherMask;
        refreshView();
    }

    /**
     * 刷新视图
     */
    private void refreshView(){
        if(isDraw){
            invalidate();
        }
    }

    /**
     * 设置文本输入监听
     * @param onTextInputListener
     */
    public void setOnTextInputListener(OnTextInputListener onTextInputListener) {
        this.mOnTextInputListener = onTextInputListener;
    }

    public static abstract class OnSimpleTextInputListener implements OnTextInputListener{

        @Override
        public void onTextInputChanged(String text, int length) {

        }

    }

    /**
     * 文本输入监听
     */
    public interface OnTextInputListener{
        /**
         * Text改变监听
         * @param text
         * @param length
         */
        void onTextInputChanged(String text,int length);

        /**
         * Text输入完成
         * @param text
         */
        void onTextInputCompleted(String text);
    }
}
