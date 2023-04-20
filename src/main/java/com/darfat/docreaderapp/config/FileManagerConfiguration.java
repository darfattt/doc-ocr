package com.darfat.docreaderapp.config;

import com.darfat.docreaderapp.service.FileManager;
import com.darfat.docreaderapp.service.impl.LocalFileManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class FileManagerConfiguration {

	@Bean
	@Primary
	public FileManager defaultStorage() {
		return new LocalFileManager();
	}

	@ConditionalOnProperty(name = "file-storage.method", havingValue = "local")
	@Bean
	public FileManager local() {
		return new LocalFileManager();
	}


}
