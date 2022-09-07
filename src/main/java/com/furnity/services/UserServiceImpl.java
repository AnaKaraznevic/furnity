package com.furnity.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.furnity.entities.User;
import com.furnity.repositories.UserRepositories;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepositories userrepositories;

	final String UPLOAD_DIR = "F:\\\\accenture\\\\furnity\\\\src\\\\main\\\\resources\\\\static\\\\image";
	// final String UPLOAD_DIR = new
	// ClassPathResource("static/image/").getFile().getAbsolutePath();

	public UserServiceImpl() throws IOException {
		super();

	}

	@Override
	public User findByEmailPass(User user) {
		System.out.println(user.getEmail());
		System.out.println(user.getPass());
		User user1 = userrepositories.findByEmailAndPass(user.getEmail(), user.getPass());
		return user1;
	}

	/*
	 * @Override public User saveUser(User user) { // TODO Auto-generated method
	 * stub return userrepositories.save(user); }
	 */

	@Override
	public User saveUser(User user, MultipartFile multipartFile) {

		boolean isPhotoInserted = false;
		try {
			
			Files.copy(multipartFile.getInputStream(),
					Paths.get(UPLOAD_DIR + File.separator + user.getEmail().replace(".", "") + "."
							+ FilenameUtils.getExtension(multipartFile.getOriginalFilename())),
					StandardCopyOption.REPLACE_EXISTING);
			isPhotoInserted = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (isPhotoInserted) {
			// If you do not set to null it throws error (MultipartFile cannot be cast to
			// class java.sql.Blob)
			user.setFilename(null);
			user.setFile(user.getEmail().replace(".", "") + "."
					+ FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
			// byte[] contents = multipartFile.getBytes();
			// Blob blob = new SerialBlob(contents);
			// user.setFilename(multipartFile);
			return userrepositories.save(user);
		}
		else return null;

		
		
	}
}
