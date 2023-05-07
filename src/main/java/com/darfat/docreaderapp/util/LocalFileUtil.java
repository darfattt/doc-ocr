package com.darfat.docreaderapp.util;

import java.util.Optional;

public class LocalFileUtil {
    private static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static String formatActualFile(String key,String fileName){
        Optional<String> ext = getExtensionByStringHandling(fileName);
        if(key!=null && ext.isPresent()){
            String dotExt = "."+ext.get();
            return new StringBuilder().append(fileName.replaceAll(dotExt,"")).append("_").append(key).append(".").append(ext.get()).toString();
        }
        return fileName;
    }

    public static String formatActualFileToPdf(String key,String fileName){
        Optional<String> ext = getExtensionByStringHandling(fileName);
        if(ext.isPresent()){
            String dotExt = "."+ext.get();
            if(key!=null) {
                return new StringBuilder().append(fileName.replaceAll(dotExt, "")).append("_").append(key).append(".").append("pdf").toString();
            } else {
                return new StringBuilder().append(fileName.replaceAll(dotExt, "")).append(".").append("pdf").toString();
            }
        }
        return fileName;
    }
}
