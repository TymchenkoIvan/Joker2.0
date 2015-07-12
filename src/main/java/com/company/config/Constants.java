package com.company.config;

public class Constants {

    // App constants
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    // SQL constants
    public static final int SQL_LENGTH_MAIL = 25;
    public static final int SQL_LENGTH_LOGIN = 20;
    public static final int SQL_LENGTH_PASSWORD = 20;
    public static final int SQL_LENGTH_ROLE = 20;
    public static final int SQL_LENGTH_TELEPHONE = 20;
    public static final int SQL_LENGTH_STATUS = 20;

    // Client side vars
    public static final String VAR_JOKES_LIST = "jokes";
    public static final String VAR_ERROR = "error";
    public static final String VAR_COOKIE = "jokerUser";
    public static final String VAR_LOGIN = "login";
    public static final String VAR_MAIL = "mail";
    public static final String VAR_TELEPHONE = "telephone";
    public static final String VAR_PASSWORD = "password";
    public static final String VAR_PASSWORD_REPEAT = "repeat_password";
    public static final String VAR_JOKE_ID = "jokeId";

    // Page constants
    public static final String PAGE_INDEX = "index";
    public static final String PAGE_ADD = "add_page";
    public static final String PAGE_ARCHIVE = "archive";
    public static final String PAGE_AUTO = "authorization";
    public static final String PAGE_SIGN = "sign_in";
    public static final String PAGE_FAQ = "faq";

    // Exception constants
    public static final String ERROR_SIGN_IN = "ERROR: Wrong pair Login/Password, try again";
    public static final String ERROR_LOGIN = "ERROR: Login is not unique, or more than 20 chars, try again";
    public static final String ERROR_MAIL = "ERROR: Mail is not unique, or not real, or more than 25 chars, try again";
    public static final String ERROR_PASSWORD = "ERROR: Password repeat is wrong, or it more than 20 chars, try again";
    public static final String ERROR_TELEPHONE = "ERROR: Your telephone must be no more then 20 chars";
    public static final String ERROR_VOTE = "ERROR: You can't rate one joke twice";
}
