package com.kuntsevich.task6.validator;

public class NumberValidator {

    public boolean isNumberStringValid(String str) {
        return str.matches("-?\\d+");
    }
}
