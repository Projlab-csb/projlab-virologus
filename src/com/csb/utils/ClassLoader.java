package com.csb.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassLoader {

    /**
     * Finds and loades all classes in the given package.
     *
     * @param packageName Name of the searched package
     * @return Set of the loaded classes
     * @author Bálint Kostyál
     */
    public static Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = java.lang.ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader
            .lines()
            .filter(line -> line.endsWith(".class"))
            .filter(line -> !line.contains("$"))
            .map(line -> getClass(line, packageName))
            .collect(Collectors.toSet());
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
