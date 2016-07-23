package com.company.util;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class Convertor {

    public synchronized String hashString(String string){
        HashFunction hf = Hashing.md5();
        return hf.hashString(string, Charsets.UTF_8).toString();
    }
}
