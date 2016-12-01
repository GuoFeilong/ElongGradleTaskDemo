package com.xingyi.elonggradletaskdemo.widget.withdraw;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.elonggradletaskdemo.R;

/**
 * 银行卡提现通用的itemview
 * <p>
 * Created by feilong.guo on 16/11/30.
 */

public class WithdrawBankView extends LinearLayout {
    private static final String TAG = "WithdrawBankView";
    private int currentType;
    private Context context;
    private TextView leftTitle;
    private TextView confirmValue;
    private TextView rightTitle;
    private ImageView bottomLine;
    private WithdrawClearEditText inputClear;
    private TextView bankCardDesc;

    // 银行卡类型内部view
    private View include_et_type;
    private ImageView iv_bank_crad_line;


    public WithdrawBankView(Context context) {
        this(context, null);
    }

    public WithdrawBankView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WithdrawBankView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    /**
     * 设置当前用户使用的viewtype类型
     *
     * @param viewType view 类型
     */
    public void setViewType(ViewType viewType) {
        this.currentType = viewType.getValue();
        this.removeAllViews();
        View conentView = null;
        switch (viewType) {
            case TEXT_TYPE:
                conentView = LayoutInflater.from(context).inflate(R.layout.item_withdraw_text_type, null, false);
                break;
            case INPUT_TYPE:
                conentView = LayoutInflater.from(context).inflate(R.layout.item_withdraw_edit_type, null, false);
                break;
            case CONFIRM_TYPE:
                conentView = LayoutInflater.from(context).inflate(R.layout.item_withdraw_confirm_type, null, false);
                break;
            case BANK_TYPE:
                conentView = LayoutInflater.from(context).inflate(R.layout.item_withdraw_bank_type, null, false);
                break;
            default:
                Toast.makeText(context, "无效的类型属性", Toast.LENGTH_SHORT).show();
                break;
        }
        initView(conentView);
        this.addView(conentView);

    }

    /**
     * 根据填充的view类型实例化当前view
     */
    private void initView(View parentView) {
        // 使用任何一个子控件的时候都要对其进行空判断
        try {
            leftTitle = (TextView) parentView.findViewById(R.id.tv_left_title);
            confirmValue = (TextView) parentView.findViewById(R.id.tv_confirm_value);
            rightTitle = (TextView) parentView.findViewById(R.id.tv_right_title);
            bottomLine = (ImageView) parentView.findViewById(R.id.iv_bottom_line);
            inputClear = (WithdrawClearEditText) parentView.findViewById(R.id.cet_right_clear);
            bankCardDesc = (TextView) parentView.findViewById(R.id.tv_bank_card_num);
            include_et_type = parentView.findViewById(R.id.include_et_type);
            iv_bank_crad_line = (ImageView) parentView.findViewById(R.id.iv_bank_crad_line);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * 设置底部的横线是否可见
     *
     * @param visible 是否可见
     */
    public void setBottomLineVisible(boolean visible) {
        setViewVisible(visible, bottomLine);
    }

    /**
     * 设置左边的标题文案
     *
     * @param leftTitleDesc 左边标题文案
     */
    public void setLeftTitleDesc(String leftTitleDesc) {
        if (leftTitle != null) {
            leftTitle.setText(leftTitleDesc);
        }
    }

    /**
     * 设置左边标题的的颜色
     *
     * @param colorRes color文件颜色
     */
    public void setLeftTitleColor(int colorRes) {
        if (leftTitle != null) {
            leftTitle.setTextColor(ContextCompat.getColor(context, colorRes));
        }
    }

    /**
     * 设置左边标题文字大小
     *
     * @param textSizeSP 文字大小
     */
    public void setLeftTitleTextSize(int textSizeSP) {
        if (leftTitle != null) {
            leftTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP);
        }
    }

    /**
     * 设置左边的标题是否可见
     *
     * @param visible 可见状态
     */
    public void setLeftTitleVisible(boolean visible) {
        setViewVisible(visible, leftTitle);
    }

    /**
     * 设置确认值的文案
     *
     * @param confirmValueDesc 文案
     */
    public void setConfirmValueDesc(String confirmValueDesc) {
        if (confirmValue != null) {
            confirmValue.setText(confirmValueDesc);
        }
    }

    /**
     * 设置确认文案大小
     *
     * @param textSizeSP 文字大小
     */
    public void setConfirmValueTextSize(int textSizeSP) {
        if (confirmValue != null) {
            confirmValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP);
        }
    }

    /**
     * 设置文案颜色
     *
     * @param colorRes 文案颜色
     */
    public void setConfirmValueTextColor(int colorRes) {
        if (confirmValue != null) {
            confirmValue.setTextColor(ContextCompat.getColor(context, colorRes));
        }
    }

    /**
     * 设置confirm控件是否可见
     *
     * @param visible 可见状态
     */
    public void setConfirmValueVisible(boolean visible) {
        setViewVisible(visible, confirmValue);
    }

    /**
     * 设置右边view是否可见
     *
     * @param visible 是否可见
     */
    public void setRightTitleVisible(boolean visible) {
        setViewVisible(visible, rightTitle);
    }

    /**
     * 设置右边文案
     *
     * @param rightTitleDesc 文案
     */
    public void setRightTitleDesc(String rightTitleDesc) {
        if (rightTitle != null) {
            rightTitle.setText(rightTitleDesc);
        }
    }

    /**
     * 设置右边文字大小
     *
     * @param textSizeSP 文字大小
     */
    public void setRightTitleTextSize(int textSizeSP) {
        if (rightTitle != null) {
            rightTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP);
        }
    }

    /**
     * 设置右边文字颜色
     *
     * @param colorRes 颜色
     */
    public void setRightTitleTextColor(int colorRes) {
        if (rightTitle != null) {
            rightTitle.setTextColor(ContextCompat.getColor(context, colorRes));
        }
    }

    /**
     * 设置放大的银行卡信息是否已可见
     *
     * @param visible 可见状态
     */
    public void setBankCardDescVisible(boolean visible) {
        setViewVisible(visible, bankCardDesc);
    }

    /**
     * 设置放大银行卡文案
     *
     * @param textDesc 文案
     */
    public void setBankCardDesc(String textDesc) {
        if (bankCardDesc != null) {
            bankCardDesc.setText(textDesc);
        }
    }

    /**
     * 设置放大银行卡文字大小
     *
     * @param textSizeSP 文字大小
     */
    public void setBankCardDescTextSize(int textSizeSP) {
        if (bankCardDesc != null) {
            bankCardDesc.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSP);
        }
    }

    /**
     * 设置放大的银行卡文字颜色
     *
     * @param colorRes 颜色
     */
    public void setBankCardDescTextColor(int colorRes) {
        if (bankCardDesc != null) {
            bankCardDesc.setTextColor(ContextCompat.getColor(context, colorRes));
        }
    }

    /**
     * 设置右边的文字空间没有drawable图片
     */
    public void setRightTitleNoDrawable() {
        if (rightTitle != null) {
            rightTitle.setCompoundDrawablePadding(0);
            rightTitle.setCompoundDrawables(null, null, null, null);
        }
    }

    /**
     * 设置右边文字控件显示图片
     *
     * @param drawableRes 资源文件
     */
    public void setRightTitleRightDrawable(int drawableRes) {
        if (rightTitle != null) {
            Drawable drawable = ContextCompat.getDrawable(context, drawableRes);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            rightTitle.setCompoundDrawablePadding(dp2px(context, 8));
            rightTitle.setCompoundDrawables(null, null, drawable, null);
        }
    }


    /**
     * 设置输入框hint文案
     *
     * @param hintText 文案
     */
    public void setInputClearHintText(String hintText) {
        if (inputClear != null) {
            inputClear.setHint(hintText);
        }
    }

    /**
     * 设置输入框的文字颜色
     *
     * @param colorRes 文字颜色用于出错展示
     */
    public void setInputClearTextColor(int colorRes) {
        if (inputClear != null) {
            inputClear.setTextColor(ContextCompat.getColor(context, colorRes));
        }
    }

    /**
     * 设置数字模式
     */
    public void setInputClearNumType() {
        if (inputClear != null) {
            inputClear.setInputType(8194);
        }
    }


    /**
     * 设置文本模式
     */
    public void setInputClearTextType() {
        if (inputClear != null) {
            inputClear.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    /**
     * 设置银行卡输入和银行卡放大文件的关联
     */
    public void showInputClearWithBankText() {
        // 只有是银行卡模式的时候才设置文案的关联
        if (currentType == ViewType.BANK_TYPE.getValue()) {
            if (inputClear != null) {
                inputClear.setOnFocusChangeListener(new OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            setBankCardDescVisible(false);
                        }
                    }
                });


                inputClear.addTextChangedListener(new WithdrawTextWatcher(inputClear, new WithdrawTextWatcher.TextChangedListener() {
                    @Override
                    public void currentDesc(String currentText) {
                        if (TextUtils.isEmpty(currentText)) {
                            setBankCardDescVisible(false);
                            setBankCardDescLineVisible(false);
                        } else {
                            setBankCardDescVisible(true);
                            setBankCardDescLineVisible(true);
                            setBankCardDesc(currentText);

                            if (include_et_type != null) {
                                include_et_type.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                            }
                            if (bankCardDesc != null) {
                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                lp.gravity = Gravity.RIGHT;
                                lp.leftMargin = dp2px(context, 124);
                                lp.topMargin = dp2px(context, 4);
                                lp.height = dp2px(context, 40);
                                bankCardDesc.setPadding(dp2px(context, 12), 0, 0, 0);
                                bankCardDesc.setLayoutParams(lp);
                            }

                            if (iv_bank_crad_line != null) {
                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 1);
                                lp.leftMargin = dp2px(context, 12);
                                iv_bank_crad_line.setLayoutParams(lp);
                            }

                        }
                    }
                }));
            }
        }
    }

    /**
     * 设置银行卡类型的线条
     *
     * @param b
     */
    private void setBankCardDescLineVisible(boolean b) {
        setViewVisible(b, iv_bank_crad_line);
    }


    /**
     * 设置view是否可见
     *
     * @param viewVisible 是否可见
     * @param view        操作的view
     */
    private void setViewVisible(boolean viewVisible, View view) {
        int value = viewVisible ? View.VISIBLE : View.GONE;
        if (view != null) {
            view.setVisibility(value);
        }
    }

    /**
     * 组合view的类型
     */
    public enum ViewType {
        // 文本模式,包括是否显示箭头
        TEXT_TYPE(1),
        // 输入模式,可以改变输入类型的限制为数字模式或者其他
        INPUT_TYPE(2),
        // 文本确定预览模式,可以隐藏view和下划线
        CONFIRM_TYPE(3),
        // 银行卡模式 : 包含放大的银行卡和输入银行卡的输入模式,放大银行卡和输入关联
        BANK_TYPE(4);

        private int value;

        ViewType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    private static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * 设置当前view的距离顶部的距离
     *
     * @param dip 距离
     */
    public void setBankViewMarginTop(int dip) {
        LinearLayout.LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = dp2px(context, dip);
        this.setLayoutParams(layoutParams);
    }

}
