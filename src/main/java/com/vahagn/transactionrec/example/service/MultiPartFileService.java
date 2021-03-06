package com.vahagn.transactionrec.example.service;

import com.vahagn.transactionrec.example.exception.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * The type Multi part file service.
 */
public class MultiPartFileService implements  FileService {

    private static final Logger logger = LoggerFactory.getLogger(MultiPartFileService.class);

    private final Path baseLocation;

    /**
     * Instantiates a new Multi part file service.
     *
     * @param fileDir the file dir
     */
    @Autowired
    public MultiPartFileService(String fileDir) {
        this.baseLocation = Paths.get(fileDir);
        this.init();
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(baseLocation);
            logger.info("{} will be storing files under {}.", this.getClass().getSimpleName(), this.baseLocation);
        }
        catch (IOException e) {
            throw new FileException("Could not initialize storage", e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (filename.contains("..")) {
                String msg = "Cannot store file with relative path outside current directory " + filename;
                logger.error(msg);
                throw new FileException(msg);
            }
            Path filePath = this.baseLocation.resolve(filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            logger.info("Storing file: " + filePath);
        }
        catch (IOException e) {
            String msg = "Failed to store file " + filename;
            logger.error(msg);
            throw new FileException(msg, e);
        }
    }

    @Override
    public Path load(String filename) {
        if (filename.contains("..")) {
            String msg = "Cannot load file with relative path outside current directory " + filename;
            logger.error(msg);
            throw new FileException(msg);
        }
        Path filePath = baseLocation.resolve(filename);
        logger.info("Retrieving file path: " + filePath);
        return filePath;
    }

    @Override
    public void delete(String filename) {
        try {
            boolean deleted = Files.deleteIfExists(this.load(filename));
            if (deleted) {
                logger.debug("{} has been properly deleted from {}.",  filename, this.baseLocation);
            }
        } catch (IOException e) {
            String msg = "Failed to delete file " + filename;
            logger.error(msg);
            throw new FileException(msg, e);
        }
    }
}
