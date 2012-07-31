package com.carlosfu.connotative.controllers;

import java.lang.annotation.Annotation;



import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

public class LoginRequiredInterceptor extends ControllerInterceptorAdapter {

    public LoginRequiredInterceptor() {
        // 这个优先级要小于PassportInterceptor，必须的
        this.setPriority(900);
    }

    // 覆盖这个方法返回一个注解类，使得只有注解了该annotation的方法才会被起作用(注解在控制器类或方法上均有效)
    // 还有一个相反功能的方法：getDenyAnnotationClass，表示注解了某个annotatioin后，拦截器不要拦截他
    @Override
    protected Class<? extends Annotation> getRequiredAnnotationClass() {
        return LoginRequired.class;
    }

    @Override
    protected Object before(final Invocation inv) throws Exception {
    	System.out.println("--------before---------");
    	int logined = 0;
        // 如果当前没有登录就返回"r:/lib/login"表示重定向到http://host:port/lib/login页面
        if (logined == 0) {
            // 没有返回true或null，表示要中断整个处理流程，即不再继续调用其他拦截器以及最终的控制器
            return "r:/ConnotativePic/comic/test";
        }
        return true;
    }
}
