package com.vahagn.transactionrec.example.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

/**
 * The interface File service.
 */
public interface FileService {
    /**
     * Init.
     */
    void init();

    /**
     * Store.
     *
     * @param file the file
     */
    void store(MultipartFile file);

    /**
     * Load path.
     *
     * @param filename the filename
     * @return the path
     */
    Path load(String filename);

    /**
     * Delete.
     *
     * @param filename the filename
     */
    void delete(String filename);
}
