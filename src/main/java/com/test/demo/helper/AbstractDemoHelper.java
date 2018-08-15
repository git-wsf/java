package com.test.demo.helper;

import com.test.demo.constrant.b;

public abstract class AbstractDemoHelper implements b {

    @Override
    public String produce(){
        System.out.println("produce");
        return "produce";
    }


    @Override
    public String build(){
        System.out.println("build");
        return "build";
    }

}
