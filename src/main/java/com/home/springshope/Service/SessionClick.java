package com.home.springshope.Service;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionClick {

    private long amountClick = 0;


    public SessionClick() {
        System.out.println("Start counting");

    }


    public long getAmountClick() {
        return amountClick;
    }

    public void addClick() {
        amountClick++;
    }
}
