package com.company.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(3600);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
    }
}
