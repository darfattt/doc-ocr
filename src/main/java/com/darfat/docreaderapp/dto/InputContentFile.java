package com.darfat.docreaderapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.io.Serializable;

@Getter
@Setter
public class InputContentFile extends ContentFile implements Serializable {

	private static final long serialVersionUID = 1L;

	private InputStream file;

	private String path;

	private String key;

}
