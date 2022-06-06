package com.isaachome.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author IsaacHome
 */
public interface FileService {
	String uploadImage(String path,MultipartFile file) throws IOException;
}
