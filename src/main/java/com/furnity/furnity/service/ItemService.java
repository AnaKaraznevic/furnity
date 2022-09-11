package com.furnity.furnity.service;

import com.furnity.furnity.exception.ItemNotFoundException;
import com.furnity.furnity.model.Item;
import com.furnity.furnity.repository.ItemRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {

	private final ItemRepository itemRepository;
	@Value(("${furniture.filepath}"))
	String UPLOAD_DIR;

	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public Item addItem(Item item, MultipartFile multipartFile) {
		boolean isPhotoInserted = false;
		try {

			Files.copy(multipartFile.getInputStream(),
					Paths.get(UPLOAD_DIR + File.separator + item.getName().replace(".", "") + "_"
							+ timeStamp.replace(".", "") + "."
							+ FilenameUtils.getExtension(multipartFile.getOriginalFilename())),
					StandardCopyOption.REPLACE_EXISTING);
			isPhotoInserted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (isPhotoInserted) {

			item.setFilename(null);
			item.setFile(item.getName().replace(".", "") + "_" + timeStamp.replace(".", "")
							+ "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
			return itemRepository.save(item);
		} else
			return null;

	}

	public List<Item> findAllItems() {
		return itemRepository.findAll();
	}

	public List<Item> findItemsByUserId(Long id) {
		return itemRepository.findItemsByUserId(id);
	}
	public void deleteItem(Long id) {
		this.itemRepository.deleteById(id);
	}

	public Item findItemById(Long id) {
		return itemRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException("Item by id " + id + " was not found"));
	}

	public Item updateItem(Long id, Item request) {
		Optional<Item> fromDB = itemRepository.findById(id);
		if (fromDB.isPresent()) {
			Item item = fromDB.get();
			item.setCategory(request.getCategory());
			item.setName(request.getName());
			item.setPrice(request.getPrice());
			item.setDescription(request.getDescription());
			item.setItemCondition(request.getItemCondition());
			return itemRepository.save(item);
		} else {
			throw new ItemNotFoundException("Item with ID :: " + id + " not found in DB");
		}
	}

	public List<Item> findItemsByKeyword(String keyword) {
		return itemRepository.findByKeyword(keyword);
	}

	public List<Item> findItemsByKeywordAndUserId(String keyword, Long userId) {
		return itemRepository.findByKeywordAndUserId(keyword, userId);
	}
}
