package com.kuntsevich.task6.validator;

public class NumberValidator {

    public boolean validateNumberString(String str) {
        return str.matches("-?\\d+");
    }
}
