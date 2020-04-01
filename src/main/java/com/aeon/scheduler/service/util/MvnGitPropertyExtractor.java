package com.aeon.scheduler.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class MvnGitPropertyExtractor {
    private static final Logger logger = LoggerFactory.getLogger(MvnGitPropertyExtractor.class);
    private static final String gitPropertyFileName = "git.properties";

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.error("File path not give for [{}] hence skipping", gitPropertyFileName);
        }
        final Path filePath = Paths.get(args[0], gitPropertyFileName);
        logger.debug("File path given [{}]", filePath.toString());
        final Map<String, String> properties = new HashMap<>();
        properties.put("last.commit", CommandUtil.getLatestCommitHash().orElse("na"));
        properties.put("last.timestamp", CommandUtil.getLatestCommitTimeAsString().orElse("na"));
        writeFile(filePath, properties);
    }

    private static void writeFile(Path path, Map<String, String> properties) {
        deleteFileIfExists(path);
        try {
            Path newPath = Files.createFile(path);
            String propertyLines = properties.entrySet().stream().map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue()))
                    .reduce("", (prev, current) -> prev + current + "\n");
            Files.write(newPath, propertyLines.getBytes(), StandardOpenOption.WRITE);
            logger.info("Created [{}]", gitPropertyFileName);
        } catch (IOException ex) {
            logger.error(String.format("Error creating/writing to file [%s]", gitPropertyFileName), ex);
        }
    }

    private static void deleteFileIfExists(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException ex) {
            logger.error("Error deleting " + path, ex);
        }
    }
}
