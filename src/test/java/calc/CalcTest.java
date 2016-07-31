/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.Test;

/**
 *
 * @author murayamakenichirou
 */
public class CalcTest {
    private final double d = 21.123456789;
    
    @Test
    public void 切り捨て() {
        // intへの変換
        int i = (int) d;
        System.out.println(i);
        // Math.floor
        double d1 = Math.floor(d);
        System.out.println(d1);
        // 小数点以下の切り捨て
        BigDecimal b = BigDecimal.valueOf(d);
        b = b.setScale(0, RoundingMode.FLOOR);
        System.out.println(b);
    }
    
    @Test
    public void 切り上げ() {
        // Math.ceil
        double d1 = Math.ceil(d);
        System.out.println(d1);
        // 小数点3位以下の切り上げ
        BigDecimal b = BigDecimal.valueOf(d);
        b = b.setScale(2, RoundingMode.CEILING);
        System.out.println(b);
    }

    @Test
    public void 四捨五入() {
        // Math.round
        double d1 = Math.round(d);
        System.out.println(d1);
        // 小数点2位以下を四捨五入
        BigDecimal b = BigDecimal.valueOf(d);
        b = b.setScale(1, RoundingMode.HALF_UP);
        System.out.println(b);
    }
    
    @Test
    public void 乗算() {
        double d1 = 3;
        double d2 = 4;
        double d3 = d1 * d2;
        System.out.println(d3);
        BigDecimal b1 = BigDecimal.valueOf(3);
        BigDecimal b2 = BigDecimal.valueOf(4);
        BigDecimal b3 = b1.multiply(b2);
        System.out.println(b3);
    }
    
    @Test
    public void 除算() {
        double d1 = 3;
        double d2 = 4;
        double d3 = d1 / d2;
        System.out.println(d3);
        BigDecimal b1 = BigDecimal.valueOf(3);
        BigDecimal b2 = BigDecimal.valueOf(4);
        BigDecimal b3 = b1.divide(b2);
        System.out.println(b3);
        BigDecimal b4 = b1.divide(b2, 1, RoundingMode.HALF_UP);
        System.out.println(b4);
    }
    
    @Test
    public void 階乗() {
        double d1 = 2.0;
        // 2乗
        double d2 = Math.pow(d1, 2);
        System.out.println(d2);
        // 3乗
        d2 = Math.pow(d1, 3);
        System.out.println(d2);
        // BigDecimal.pow
        BigDecimal b1 = BigDecimal.valueOf(d1);
        b1 = b1.pow(2);
        System.out.println(b1);
    }
    
    @Test
    public void 誤差の発生する計算() {
        System.out.println(0.7+0.2+0.1);//0.9999999999999999
        System.out.println(1.00-9*0.1);//0.09999999999999998
        System.out.println(new BigDecimal(0.1));//0.10000000000000000555111...
        // is ok
        System.out.println(new BigDecimal("0.1"));//0.1
        System.out.println(BigDecimal.valueOf(0.1));//0.1
    }
}
