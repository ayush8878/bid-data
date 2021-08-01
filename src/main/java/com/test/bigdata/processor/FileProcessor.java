package com.test.bigdata.processor;

import com.test.bigdata.domain.Result;
import com.test.bigdata.exception.BigDataCustomException;
import org.apache.camel.Exchange;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
public class FileProcessor {

    private static final Logger logger = LoggerFactory.getLogger(FileProcessor.class);
    public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";

    @Autowired
    ResultProcessor resultProcessor;


    /**
     * This method will process file
     * @param exchange
     * @return
     * @throws Exception
     */
    public Result processFile(Exchange exchange) throws Exception {
        File file = exchange.getIn().getBody(File.class);
        Tika tika = new Tika();
        String fileContentType = tika.detect(file);
        if (!CONTENT_TYPE_TEXT_PLAIN.equals(fileContentType)) {
            logger.warn("Only .txt files are supported");
            throw new BigDataCustomException("Only .txt files are supported");
        }
        String fileContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        Result result = resultProcessor.processResult(fileContent);
        return result;
    }
}
