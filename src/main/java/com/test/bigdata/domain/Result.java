package com.test.bigdata.domain;

import org.springframework.util.StringUtils;

import java.util.StringJoiner;

public class Result {

    private Long wordCount;
    private Long specialCharacterCount;
    private Long dotCount;
    private String mostUsedWord;

    public Long getDotCount() {
        return dotCount;
    }

    public Result setDotCount(Long dotCount) {
        this.dotCount = dotCount;
        return this;
    }

    public String  getMostUsedWord() {
        return mostUsedWord;
    }

    public Result setMostUsedWord(String mostUsedWord) {
        this.mostUsedWord = mostUsedWord;
        return this;
    }

    public Long getWordCount() {
        return wordCount;
    }

    public Result setWordCount(Long wordCount) {
        this.wordCount = wordCount;
        return this;
    }

    public Long getSpecialCharacterCount() {
        return specialCharacterCount;
    }

    public Result setSpecialCharacterCount(Long specialCharacterCount) {
        this.specialCharacterCount = specialCharacterCount;
        return this;
    }

    public static Result build()
    {
        return new Result();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Result.class.getSimpleName() + "[", "]")
                .add("wordCount=" + wordCount)
                .add("specialCharacterCount=" + specialCharacterCount)
                .add("dotCount=" + dotCount)
                .add("mostUsedWord=" + mostUsedWord)
                .toString();
    }
}
