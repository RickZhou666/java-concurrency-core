package com.rick.chap_01.bullet03.content06_param;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Author: Rick
 * @Date: 2022/10/30 16:49
 */
public class T03_TestNG_DDD {
    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod(){
        return new Object[][]{{"data one"}, {"data two"}};
    }

    @Test(dataProvider = "data-provider")
    public void testMethod(String data){
        System.out.println("Data is: " + data);
    }
}
