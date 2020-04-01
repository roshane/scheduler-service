package com.aeon.scheduler.service.util;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Function;

public class CommandUtil {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private abstract class GitCommands {
        private static final String LATEST_COMMIT_HASH = "git log -n1 --pretty=format:\"%H\"";
        private static final String LATEST_COMMIT_DATE = " git log -n1 --pretty=format:\"%ad\" --date=unix";
    }

    public static Optional<String> getLatestCommitTimeAsString() {
        return getLatestCommitTime().map(localDateTime -> localDateTime.format(dateTimeFormatter));
    }

    public static Optional<LocalDateTime> getLatestCommitTime() {
        return executeSysCommand(GitCommands.LATEST_COMMIT_DATE, s -> LocalDateTime
                .ofEpochSecond(Long.parseLong(s), 0, ZoneOffset.systemDefault().getRules().getStandardOffset(Instant.now()))
        );
    }

    public static Optional<String> getLatestCommitHash() {
        return executeSysCommand(GitCommands.LATEST_COMMIT_HASH, s -> s);
    }

    private static <T> Optional<T> executeSysCommand(String command, Function<String, T> converter) {
        final Optional<T> empty = Optional.empty();
        try {
            final Process process = process(command);
            process.waitFor();
            if (process.exitValue() != 0) {
                return empty;
            }
            return Optional.of(converter.apply(asString(process.getInputStream())));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return empty;
    }

    private static Process process(String commandString) {
        try {
            return Runtime.getRuntime().exec(commandString);
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Error executing command [%s]", commandString), ex);
        }
    }

    private static String asString(InputStream inputStream) throws IOException {
        try {
            byte[] byteArray = new byte[inputStream.available()];
            int count = 0;
            while (inputStream.available() > 0) {
                byteArray[count] = (byte) inputStream.read();
                count++;
            }
            return new String(byteArray);
        } catch (IOException ex) {
            throw new RuntimeException("Error reading from inputStream", ex);
        } finally {
            inputStream.close();
        }
    }
}
