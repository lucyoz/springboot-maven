package com.springboot.hello2.parser;

public interface Parser<T> {
    T parse(String str);
}

