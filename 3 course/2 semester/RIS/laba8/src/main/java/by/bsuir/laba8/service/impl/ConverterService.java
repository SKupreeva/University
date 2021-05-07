package by.bsuir.laba8.service.impl;

import by.bsuir.laba8.service.IConverterService;

import java.util.Arrays;

public class ConverterService implements IConverterService {
    @Override
    public String SortString(String str) {
        char[] sortArray = str.toCharArray();
        Arrays.sort(sortArray);
        return new String(sortArray);
    }
}
