package com.darfat.docreaderapp.service;


import com.darfat.docreaderapp.dto.InputContentFile;
import com.darfat.docreaderapp.dto.OutputContentFile;

import java.util.List;

public interface FileManager {

	void addImage(InputContentFile inputContentFile) throws Exception;

	void addFile(InputContentFile inputContentFile) throws Exception;

	void addFiles(List<InputContentFile> inputContentFiles) throws Exception;

	OutputContentFile getFile(String path, String key, String fileName)throws Exception;

	OutputContentFile getFile(String path) throws Exception;

	void deleteFile(String path, String name) throws Exception;

}
