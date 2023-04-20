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
        if(ext.isPresent()){
            return key+"."+ext.get();
        } else {
            return fileName;
        }
    }
}
