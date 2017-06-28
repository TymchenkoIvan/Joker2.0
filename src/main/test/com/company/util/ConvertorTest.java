package com.company.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConvertorTest {

    private static final String MD_5 = "MD5";;
    private static final String TEST_PASSWORD = "passwordTest007";

    private Convertor convertor = new Convertor();

    @Test
    public void givenPasswordThanHashStringReturnsNotNull(){
        Assert.assertNotNull(convertor.hashString(TEST_PASSWORD));
    }

    @Test
    public void givenEqualPasswordsThanHashStringReturnsEqualResult(){
        Assert.assertEquals(convertor.hashString(TEST_PASSWORD), convertor.hashString(TEST_PASSWORD));
    }

    @Test
    public void givenPasswordThanHashStringReturnsHashedResultInMD5() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Assert.assertEquals(convertor.hashString(TEST_PASSWORD), convertToMD5(TEST_PASSWORD));
    }

    private String convertToMD5(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance(MD_5 );
        md5.update(StandardCharsets.UTF_8.encode(password));

        return String.format("%032x", new BigInteger(1, md5.digest()));
    }
}