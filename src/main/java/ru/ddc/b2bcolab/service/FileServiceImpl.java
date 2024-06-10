package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ddc.b2bcolab.FileDAO.FileDAO;
import ru.ddc.b2bcolab.manager.FileManager;
import ru.ddc.b2bcolab.model.modFile.FileInfo;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileDAO fileDAO;

private final FileManager fileManager;
    private String keyName;

    @Transactional(rollbackFor = {IOException.class})
    @Override
    public FileInfo upload(MultipartFile resource) throws IOException {
        String key = generateKey(resource.getName());
        FileInfo createdFile = FileInfo.builder()
                .name(resource.getOriginalFilename())
                .key(key)
                .size(resource.getSize())
                .build();
        createdFile = fileDAO.create(createdFile);

        fileManager.upload(resource.getBytes(), key);

        return createdFile;
    }

    private String generateKey(String name) {
        return DigestUtils.md5Hex(name + LocalDateTime.now().toString());
    }
    }
