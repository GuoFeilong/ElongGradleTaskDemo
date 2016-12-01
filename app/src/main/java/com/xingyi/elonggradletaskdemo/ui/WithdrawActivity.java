package com.xingyi.elonggradletaskdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.xingyi.elonggradletaskdemo.R;
import com.xingyi.elonggradletaskdemo.widget.withdraw.WithdrawBankView;

/**
 * Created by feilong.guo on 16/11/30.
 */

public class WithdrawActivity extends AppCompatActivity {
    private LinearLayout container;
    private WithdrawBankView availableBalance;
    private WithdrawBankView withdrawBalance;
    private WithdrawBankView selectBank;
    private WithdrawBankView cardProvincial;
    private WithdrawBankView cardCity;
    private WithdrawBankView bankCard;
    private WithdrawBankView bankName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        container = (LinearLayout) findViewById(R.id.ll_container);

        availableBalance = new WithdrawBankView(this);
        availableBalance.setViewType(WithdrawBankView.ViewType.TEXT_TYPE);
        availableBalance.setLeftTitleDesc("当前用户可用余额: ¥99.0");
        availableBalance.setRightTitleVisible(false);
        container.addView(availableBalance);

        withdrawBalance = new WithdrawBankView(this);
        withdrawBalance.setViewType(WithdrawBankView.ViewType.INPUT_TYPE);
        withdrawBalance.setLeftTitleDesc("本次提取的金额");
        withdrawBalance.setInputClearHintText("请输入提取金额");
        withdrawBalance.setInputClearNumType();
        withdrawBalance.setBottomLineVisible(false);
        container.addView(withdrawBalance);

        selectBank = new WithdrawBankView(this);
        selectBank.setViewType(WithdrawBankView.ViewType.TEXT_TYPE);
        selectBank.setLeftTitleDesc("选择银行");
        selectBank.setRightTitleDesc("招商银行");
        selectBank.setBankViewMarginTop(10);
        container.addView(selectBank);

        cardProvincial = new WithdrawBankView(this);
        cardProvincial.setViewType(WithdrawBankView.ViewType.TEXT_TYPE);
        cardProvincial.setLeftTitleDesc("开卡省");
        cardProvincial.setRightTitleDesc("请选择");
        cardProvincial.setRightTitleTextColor(R.color.color_B2B2B2);
        container.addView(cardProvincial);


        cardCity = new WithdrawBankView(this);
        cardCity.setViewType(WithdrawBankView.ViewType.TEXT_TYPE);
        cardCity.setLeftTitleDesc("开卡市");
        cardCity.setRightTitleDesc("请选择");
        cardCity.setRightTitleTextColor(R.color.color_B2B2B2);
        container.addView(cardCity);

        bankCard = new WithdrawBankView(this);
        bankCard.setViewType(WithdrawBankView.ViewType.BANK_TYPE);
        bankCard.setLeftTitleDesc("储蓄卡卡号");
        bankCard.setInputClearHintText("请输入13-20位卡号");
        bankCard.setInputClearNumType();
        bankCard.showInputClearWithBankText();
        container.addView(bankCard);


        bankName = new WithdrawBankView(this);
        bankName.setViewType(WithdrawBankView.ViewType.INPUT_TYPE);
        bankName.setLeftTitleDesc("持卡人姓名");
        bankName.setInputClearHintText("请输入姓名");
        container.addView(bankName);


    }
}
