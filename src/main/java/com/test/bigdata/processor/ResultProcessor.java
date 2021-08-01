package com.test.bigdata.processor;

import com.test.bigdata.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ResultProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ResultProcessor.class);


    /**
     * This method will find special character count in file content
     * @param fileContent
     * @return
     */
    private static long getSpecialCharacterCount(String fileContent) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(fileContent);
        long count = 0;
        while (matcher.find())
            count++;
        return count;
    }

    /**
     * This method will find word count from file content
     * @param fileContent
     * @return
     */
    private static long getWordCount(String fileContent) {

        return fileContent.split(" ").length;
    }

    /**
     * This method will find dot count from file content
     * @param fileContent
     * @return
     */
    private static long getDotCount(String fileContent) {

        return StringUtils.countOccurrencesOf(fileContent, ".");
    }

    /**
     * This method will find most used word from file content
     * @param fileContent
     * @return
     */
    private static String getMostUsedWord(String fileContent) {
        String words[] = fileContent.split(" ");
        Map<String, Long> countMap = Arrays.stream(words)
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        countMap.remove("");
        return countMap.entrySet().
                stream()
                .max(Comparator.comparingInt(entry -> entry.getValue().intValue()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * This method will process results
     * @param fileContent
     * @return
     */
    public Result processResult(String fileContent) {
        Result result = Result.build();
        if (StringUtils.isEmpty(fileContent)) {
            logger.info("No Content found in file");
            return result;
        }
        result.setSpecialCharacterCount(this.getSpecialCharacterCount(fileContent));
        result.setWordCount(this.getWordCount(fileContent));
        result.setDotCount(this.getDotCount(fileContent));
        result.setMostUsedWord(this.getMostUsedWord(fileContent));
        return result;
    }
}
