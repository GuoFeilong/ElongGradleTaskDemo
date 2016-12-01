package com.xingyi.elonggradletaskdemo.widget.withdraw;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by feilong.guo on 16/11/30.
 */

public class WithdrawTextWatcher implements TextWatcher {
    private EditText editText;
    private TextChangedListener textChangedListener;

    public WithdrawTextWatcher(EditText editText, TextChangedListener textChangedListener) {
        this.editText = editText;
        this.textChangedListener = textChangedListener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (textChangedListener != null) {
            textChangedListener.currentDesc(s.toString());
        }
    }

    public interface TextChangedListener {
        void currentDesc(String currentText);
    }

}
