package com.isaachome.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.isaachome.service.FileService;

/**
 *
 * @author IsaacHome
 */

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// File name
		String name=file.getOriginalFilename();

		String ramdomID = UUID.randomUUID().toString();
		String fileName =ramdomID.concat(name.substring(name.lastIndexOf(".")));
		
		// Fullpath
		String filePath = path + File.separator+fileName;
		
		// create folder if not created
		File f=new File(path);
		
		if(!f.exists()) {
			f.mkdir();
		}
		
		// file copy
		Files.copy(file.getInputStream(), Path.of(filePath));
		return fileName;
	}

}
