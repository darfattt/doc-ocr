package com.darfat.docreaderapp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;

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
        private String source;
        private String original;
        private String verified;

        public String getSourceFullPath(){
            return new StringBuilder()
                .append(root)
                .append(File.separator)
                .append(source).toString();
        }

        public String getOriginalFullPath(){
            return new StringBuilder()
                .append(root)
                .append(File.separator)
                .append(original).toString();
        }

        public String getVerifiedFullPath(){
            return new StringBuilder()
                .append(root)
                .append(File.separator)
                .append(verified).toString();
        }

	}


}
