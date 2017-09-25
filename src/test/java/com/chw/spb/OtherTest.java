package com.chw.spb;

import com.google.common.base.Strings;
import com.google.common.base.Ticker;
import org.junit.Test;

/**
 * @author chw
 * 2017/9/22
 */
public class OtherTest {

    @Test
    public void test() {
        Ticker ticker ;
        Ticker ticker1 = Ticker.systemTicker();
        System.out.println(Ticker.systemTicker().read());
        System.out.println(Ticker.systemTicker().read());
        System.out.println(Ticker.systemTicker().read());
    }
    @Test
    public void test1() {
        Ticker ticker  = Ticker.systemTicker();
        Ticker ticker1 = Ticker.systemTicker();
        System.out.println(ticker == ticker1);
    }
    @Test
    public void test3() {
        String hello = Strings.repeat("hello", 3);
        System.out.println(hello);
    }
}
