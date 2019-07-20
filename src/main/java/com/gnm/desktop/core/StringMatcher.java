package com.gnm.desktop.core;

import java.util.ArrayList;
import java.util.List;

public class StringMatcher<T extends StringMatcher.Matchable> {

    private static final int NOT_MATCH = -1;
    private static final int CONTAINS = 1;
    private static final int START_WITH = 2;

    private String input;
    private List<String> sameInput;

    private List<T> containsList = new ArrayList<>();
    private List<T> startWithList = new ArrayList<>();

    public StringMatcher(String input) {
        this.input = input;
        setupSameStrings();
    }

    public void add(T word) {

        switch (getMatchLevel(word.getMatchable())) {
            case NOT_MATCH:
                // nothing :)
                break;
            case CONTAINS:
                containsList.add(word);
                break;
            case START_WITH:
                startWithList.add(word);
                break;
            default:
                throw new RuntimeException("invalid code");
        }
    }

    private int getMatchLevel(String word) {

        for (String s : sameInput) {
            if (word.startsWith(s)) return START_WITH;
            if (word.contains(s)) return CONTAINS;
        }

        return NOT_MATCH;
    }

    private void setupSameStrings() {

        var lst = new ArrayList<String>();

        lst.add(input);
        if (input.contains("آ")) lst.add(input.replace("آ", "ا"));
        if (input.contains("ا")) lst.add(input.replace("ا", "آ"));

        sameInput = lst;
    }

    public List<T> getList() {
        var result = new ArrayList<T>();

        result.addAll(startWithList);
        result.addAll(containsList);
        return result;
    }

    public interface Matchable {
        String getMatchable();
    }
}
