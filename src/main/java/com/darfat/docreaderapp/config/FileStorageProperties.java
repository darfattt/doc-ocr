package com.darfat.docreaderapp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "file-storage", ignoreUnknownFields = true)
public class FileStorageProperties {

	private Local local;


	@Getter
	@Setter
	public static class Local {

		private String root;

	}


}
