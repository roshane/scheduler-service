//package com.aeon.scheduler.service;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.Properties;
//import java.util.stream.Collectors;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class PropertyLoader {
//    private static final Properties properties = new Properties();
//    private static final Logger logger = LoggerFactory.getLogger(PropertyLoader.class);
//
//    public PropertyLoader(String... fileUrl) {
//        List<Properties> propertiesList = Arrays.stream(fileUrl).map(PropertyLoader::loadProperties)
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//        propertiesList.forEach(props -> {
//            props.forEach((key, value) -> properties.merge(key, value, (k, v) -> v));
//        });
//        System.out.println(properties);
//    }
//
//    static Optional<Properties> loadProperties(String filePath) {
//        File propertyFile = new File(filePath);
//        try (InputStream fileInputStream = new FileInputStream(propertyFile)) {
//            Properties props = new Properties();
//            props.load(fileInputStream);
//            return Optional.of(props);
//        } catch (IOException ex) {
//            logger.error("Error loading properties", ex);
//        }
//        return Optional.empty();
//    }
//
//    public Properties getProperties() {
//        return properties;
//    }
//}
