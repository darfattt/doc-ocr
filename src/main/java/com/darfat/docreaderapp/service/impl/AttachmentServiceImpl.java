package com.darfat.docreaderapp.service.impl;

import com.darfat.docreaderapp.constants.AttachmentTypeEnum;
import com.darfat.docreaderapp.domain.Attachment;
import com.darfat.docreaderapp.domain.AttachmentGroup;
import com.darfat.docreaderapp.dto.InputContentFile;
import com.darfat.docreaderapp.dto.OutputContentFile;
import com.darfat.docreaderapp.dto.request.AttachmentRequest;
import com.darfat.docreaderapp.exception.ExceptionPredicate;
import com.darfat.docreaderapp.repository.AttachmentGroupRepository;
import com.darfat.docreaderapp.repository.AttachmentRepository;
import com.darfat.docreaderapp.service.AttachmentService;
import com.darfat.docreaderapp.service.FileManager;
import com.darfat.docreaderapp.util.DateConvertUtil;
import com.darfat.docreaderapp.util.LocalFileUtil;
import com.darfat.docreaderapp.util.ObjectMapperUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentGroupRepository attachmentGroupRepository;

    private final AttachmentRepository attachmentRepository;

    private final FileManager fileManager;

    private final static boolean WITHOUTKEY= true;

    AttachmentServiceImpl(
        AttachmentGroupRepository attachmentGroupRepository,
        AttachmentRepository attachmentRepository,
        FileManager fileManager
    ) {
        this.attachmentGroupRepository = attachmentGroupRepository;
        this.attachmentRepository = attachmentRepository;
        this.fileManager = fileManager;
    }

    @Transactional
    @Override
    public <T> AttachmentGroup createAttachmentGroup(Class<T> object) {
        synchronized (this) {
            AttachmentGroup attachmentGroup = new AttachmentGroup();
            attachmentGroup.setEntity(object.getSimpleName());
            AttachmentGroup attachmentGroupResult = attachmentGroupRepository.save(attachmentGroup);
            String newPath = null;//attachmentGroupResult.getId();
            if (attachmentGroup.getBasePath() != null) {
                //attachmentGroup.getBasePath();
            } else {
                attachmentGroupResult.setBasePath(buildPathWithYear(newPath));
            }
            return attachmentGroupRepository.save(attachmentGroupResult);
        }
    }

    @Transactional
    @Override
    public <T> AttachmentGroup createAttachmentGroup(
        Class<T> object,
        String attachmentGroupParentId,
        String description,
        String name,
        String basePath
    ) {
        synchronized (this) {
            AttachmentGroup attachmentGroup = new AttachmentGroup();
            attachmentGroup.setEntity(object.getSimpleName());
            attachmentGroup.setBasePath(basePath);
            if (attachmentGroupParentId != null) {
                attachmentGroup.setAttachmentGroupParent(attachmentGroupRepository.findById(attachmentGroupParentId).orElse(null));
            }
            attachmentGroup.setDescription(description);
            attachmentGroup.setName(name);
            AttachmentGroup attachmentGroupResult = attachmentGroupRepository.save(attachmentGroup);
            String newPath = null;//attachmentGroupResult.getId();
            if (basePath != null) {
                attachmentGroupResult.setBasePath(basePath);
            } else {
                attachmentGroupResult.setBasePath(buildPathWithYear(newPath));
            }
            return attachmentGroupRepository.save(attachmentGroupResult);
        }
    }

    @Override
    public <T> AttachmentGroup createAttachmentGroup(
        String entityName,
        String attachmentGroupParentId,
        String description,
        String name,
        String basePath
    ) {
        AttachmentGroup attachmentGroup = new AttachmentGroup();
        attachmentGroup.setEntity(entityName);
        attachmentGroup.setBasePath(basePath);
        if (attachmentGroupParentId != null) {
            attachmentGroup.setAttachmentGroupParent(attachmentGroupRepository.findById(attachmentGroupParentId).orElse(null));
        }
        attachmentGroup.setDescription(description);
        attachmentGroup.setName(name);
        AttachmentGroup attachmentGroupResult = attachmentGroupRepository.save(attachmentGroup);
        String newPath = null;
        if (basePath != null) {
            attachmentGroupResult.setBasePath(basePath);
        } else {
            attachmentGroupResult.setBasePath(buildPathWithYear(newPath));
        }
        return attachmentGroupRepository.save(attachmentGroupResult);
    }

    @Transactional
    @Override
    public List<Attachment> saveAttachment(AttachmentGroup attachmentGroup, Set<Attachment> attachments, String reference) {
        List<Attachment> attachmentResult = new ArrayList<>();
        for (Attachment attachment : attachments) {
            if (attachment.getId() != null && attachment.getRemoveFlag()) {
                attachmentRepository.deleteById(attachment.getId());
                removeFile(attachment);
            } else {
                if (attachmentGroup != null) {
                    attachment.setAttachmentGroup(attachmentGroup);
                    Attachment storedAttachment = attachmentRepository.save(attachment);
                    attachment.setId(storedAttachment.getId());
                    if (storedAttachment.getBlobFile() == null) {
                        log.info("stored attachment blob is null");
                    } else {
                        log.info("stored not null");
                    }
                    if (attachment.getBlobFile() == null) {
                        log.info("attachment attachment blob is null");
                    } else {
                        log.info("attachment not null");
                    }
                    if (attachment.getBlobFile()!=null && attachment.getType().equals(AttachmentTypeEnum.Image.name())) {
                        writeImageFiles(attachment);
                    } else if (attachment.getBlobFile()!=null && attachment.getType().equals(AttachmentTypeEnum.Video.name())) {
                        writeNonImageFiles(attachment);
                    } else {
                        if(attachment.getBlobFile()!=null) {
                            writeNonImageFiles(attachment);
                        }
                    }
                    attachmentGroupRepository.save(attachment.getAttachmentGroup());
                    attachmentResult.add(storedAttachment);
                }
            }
        }
        return attachmentResult;
    }

    @Transactional
    @Override
    public Boolean removeGroup(AttachmentGroup attachmentGroupRequest) {
        AttachmentGroup attachmentGroup = attachmentGroupRepository
            .findById(attachmentGroupRequest.getId())
            .orElseThrow(ExceptionPredicate.attachmentGroupNotFound(attachmentGroupRequest.getId()));
        List<Attachment> attachments = attachmentRepository.findAllByAttachmentGroupIdOrderByCreatedDateDesc(attachmentGroup.getId());
        for (Attachment attachment : attachments) {
            attachment.setRemoveFlag(true);
        }

        saveAttachment(attachmentGroup, new LinkedHashSet<>(attachments), null);
        if (attachments == null || attachments.isEmpty()) {
            attachmentGroupRepository.delete(attachmentGroup);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Transactional
    @Override
    public void removeAttachment(String attachmentId, String reference) throws Exception {
        Optional<Attachment> attachment = attachmentRepository.findById(attachmentId);
        if (attachment.isPresent()) {
            removeFile(attachment.get());
            attachmentRepository.deleteById(attachmentId);
        }
    }

    @Override
    public OutputContentFile downloadAttachment(String id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(ExceptionPredicate.attachmentNotFound(id));
        try {
            if(!WITHOUTKEY) {
                return fileManager.getFile(attachment.getAttachmentGroup().getBasePath(), attachment.getId(), attachment.getName());
            }else {
                return fileManager.getFile(attachment.getAttachmentGroup().getBasePath(), null, attachment.getName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Attachment> findAllAttachmentByGroupId(String attachmentGroupId) {
        return attachmentRepository.findAllByAttachmentGroupId(attachmentGroupId);
    }

    // TODO : only applicable for local
    // need to move to the fileManager to make it general base on local storage method
    @Override
    public String generateUrl(Attachment attachment) {
        String staticResourceHost = "localhost";
        StringBuilder url = new StringBuilder();
        url
            .append(staticResourceHost)
            .append("/")
            .append(attachment.getAttachmentGroup().getBasePath().replace("\\", "/"))
            .append(LocalFileUtil.formatActualFile(attachment.getId(), attachment.getName()));
        return url.toString();
    }

    @Transactional
    @Override
    public List<Attachment> saveAttachment(AttachmentRequest attachmentRequest) {
        AttachmentGroup attachmentGroupResult = null;
        if (attachmentRequest.getAttachmentGroupId() == null) {
            attachmentGroupResult =
                this.createAttachmentGroup(
                        attachmentRequest.getClassName(),
                        attachmentRequest.getAttachmentGroupParentId(),
                        attachmentRequest.getDescription(),
                        attachmentRequest.getName(),
                        attachmentRequest.getBasePath()
                    );
        } else {
            attachmentGroupResult =
                attachmentGroupRepository
                    .findById(attachmentRequest.getAttachmentGroupId())
                    .orElseThrow(ExceptionPredicate.attachmentGroupNotFound(attachmentRequest.getAttachmentGroupId()));
        }
        List<Attachment> inputAttachments = attachmentRequest
            .getAttachments()
            .stream()
            .map(attachment -> ObjectMapperUtil.MAPPER.convertValue(attachment, Attachment.class))
            .collect(Collectors.toList());
        List<Attachment> attachmentResult = this.saveAttachment(attachmentGroupResult, new HashSet<>(inputAttachments), null);
        return attachmentResult;
    }

    private void removeFile(Attachment attachment) {
        try {
            fileManager.deleteFile(
                attachment.getAttachmentGroup().getBasePath(),
                LocalFileUtil.formatActualFile(attachment.getId(), attachment.getName())
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Attachment writeNonImageFiles(Attachment attachment) {
        InputContentFile input = constructFromAttachment(attachment);
        try {
            fileManager.addFile(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        attachment.getAttachmentGroup().setBasePath(input.getPath());
        return attachment;
    }

    private Attachment writeImageFiles(Attachment attachment) {
        InputContentFile input = constructFromAttachment(attachment);
        try {
            fileManager.addImage(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        attachment.getAttachmentGroup().setBasePath(input.getPath());
        return attachment;
    }

    private InputContentFile constructFromAttachment(Attachment attachment) {
        InputContentFile inputContentFile = new InputContentFile();
        inputContentFile.setFileName(attachment.getName());
        inputContentFile.setPath(attachment.getAttachmentGroup().getBasePath());
        if (attachment.getBlobFile() == null) {
            log.info("Blob is null, why?");
        } else {
            log.info("Blob not null [{}]", attachment.getBlobFile().length());
        }
        byte[] byteArray = Base64.getMimeDecoder().decode(attachment.getBlobFile());
        inputContentFile.setFile(new ByteArrayInputStream(byteArray));
        inputContentFile.setKey(attachment.getId());
        return inputContentFile;
    }

    private String buildPathWithYear(String path) {
        String year = DateConvertUtil.toString(Instant.now(), DateConvertUtil.DATE_FORMAT_4);
        String month = DateConvertUtil.toString(Instant.now(), DateConvertUtil.DATE_FORMAT_10);
        String yyyymm = new StringBuilder()
            .append(year)
            .append(File.separator)
            .append(month).toString();
        if(path!=null){
        return new StringBuilder()
            .append(yyyymm)
            .append(path)
            .append(File.separator)
            .toString();
        }
        return yyyymm;
    }
}
