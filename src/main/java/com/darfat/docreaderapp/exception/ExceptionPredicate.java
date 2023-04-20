package com.darfat.docreaderapp.exception;


import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

public final class ExceptionPredicate {

	private ExceptionPredicate() {
		throw new IllegalStateException("Utility class");
	}

	public static Supplier<EntityNotFoundException> attachmentGroupNotFound(String id) {
		return () -> new EntityNotFoundException(String.format("AttachmentGroup with id=%s Not Found", id));
	}

	public static Supplier<EntityNotFoundException> attachmentNotFound(String id) {
		return () -> new EntityNotFoundException(String.format("Attachment with id=%s Not Found", id));
	}

}
