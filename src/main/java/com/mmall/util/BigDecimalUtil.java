package com.mmall.util;


import java.math.BigDecimal;

/**
 * @author Created by bameirilyo
 * @date 18-5-14 上午10:26
 */
public class BigDecimalUtil {

    private BigDecimalUtil(){

    }

    //加法
    public static BigDecimal add(double v1, double v2){
        //先转换成String再运算
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    //减法
    public static BigDecimal sub(double v1, double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    //乘法
    public static BigDecimal mul(double v1, double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    //除法
    public static BigDecimal div(double v1, double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        //注意除不尽的情况，四舍五入，保留2位小数，利用
        return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);
    }

}