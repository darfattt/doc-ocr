package com.darfat.docreaderapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

@Getter
@Setter
public class OutputContentFile extends ContentFile implements Serializable {

	private static final long serialVersionUID = 1L;

	private ByteArrayOutputStream file;

	private String mimeType;

	private String path;

}
