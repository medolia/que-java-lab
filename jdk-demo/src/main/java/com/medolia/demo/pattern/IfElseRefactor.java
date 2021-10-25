package com.medolia.demo.pattern;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 用工厂、单例、策略模式重构多 if else
 *
 * @author lb@li@trip.com
 * @date 2021/7/19
 */
public class IfElseRefactor {
    public static void payNotify(String priceCode) {
        VipRechargeStrategy strategy = VipRechargeFactory.getInstance().getSpecificStrategy(priceCode);
        if (strategy == null) {
            return;
        }
        strategy.recharge(priceCode);
    }

    public static void main(String[] args) {
        IfElseRefactor.payNotify("一个月会员");
    }
}

class VipRechargeFactory {
    static final Object lock = new Object();
    static Map<String, VipRechargeStrategy> map;
    static volatile VipRechargeFactory instanceHolder;

    static {
        map = ImmutableMap.of(
                "一个月会员", new OneMonthVipStrategy(),
                "三个月会员", new ThreeMonthVipStrategy(),
                "一年会员", new OneYearVipStrategy()
        );
    }

    public static VipRechargeFactory getInstance() {
        if (instanceHolder == null) {
            synchronized (lock) {
                if (instanceHolder == null) {
                    instanceHolder = new VipRechargeFactory();
                }
            }
        }
        return instanceHolder;
    }

    public VipRechargeStrategy getSpecificStrategy(String priceCode) {
        return map.get(priceCode);
    }
}

interface VipRechargeStrategy {
    /**
     * 收费
     *
     * @param priceCode priceCode
     */
    void recharge(String priceCode);
}

class OneMonthVipStrategy implements VipRechargeStrategy {
    @Override
    public void recharge(String priceCode) {
        System.out.println("一个月会员");
    }
}

class ThreeMonthVipStrategy implements VipRechargeStrategy {
    @Override
    public void recharge(String priceCode) {
        System.out.println("三个月会员");
    }
}

class OneYearVipStrategy implements VipRechargeStrategy {
    @Override
    public void recharge(String priceCode) {
        System.out.println("一年会员");
    }
}