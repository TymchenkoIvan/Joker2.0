package com.company.config;

/**
 * Created by tymchenkoivan on 12.07.2015.
 */
public class Constants {

    // Client side vars
    public static final String VAR_JOKES_LIST = "jokes";
    public static final String VAR_ERROR = "error";
    public static final String VAR_COOKIE = "jokerUser";
    public static final String VAR_LOGIN = "login";

    // Page constants
    public static final String PAGE_INDEX = "index";
    public static final String PAGE_ADD = "add_page";
    public static final String PAGE_ARCHIVE = "archive";
    public static final String PAGE_AUTO = "authorization";
    public static final String PAGE_SIGN = "sign_in";
    public static final String PAGE_FAQ = "faq";

    // Exception constants
    public static final String ERROR_SIGN_IN = "ERROR: Wrong pair Login/Password, try again";
    public static final String ERROR_LOGIN = "ERROR: Login is not unique, try again";
    public static final String ERROR_MAIL_NOT_UNIQUE = "ERROR: Mail is not unique, try again";
    public static final String ERROR_MAIL_NOT_REAL = "ERROR: Mail is not real, try again";
    public static final String ERROR_PASSWORD = "ERROR: Password repeat is wrong, try again";
    public static final String ERROR_VOTE = "ERROR: You can't rate one joke twice";
}
