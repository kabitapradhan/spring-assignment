package com.app.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException  {
		// File name
		// abc.png
		String name = file.getOriginalFilename();
		// generate fandom name for file
		String randomId = UUID.randomUUID().toString();
		String name2 = randomId.concat(name.substring(name.lastIndexOf(".")));
		// Full path
		String filePath = path + File.separator + name2;
		
		// create folder if not created
		File fl = new File(path);
		if(!fl.exists()) {
			fl.mkdir();
		}
		// File copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return name2;
	}

	@Override
	public InputStream getImageResource(String path, String fileName) throws FileNotFoundException {
		String fullpath = path + File.separator + fileName;
		
		InputStream is = new FileInputStream(fullpath);
		
		return is;
	}

}
