package com.furnity.furnity.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.furnity.furnity.exception.ItemNotFoundException;
import com.furnity.furnity.model.Item;
import com.furnity.furnity.repository.ItemRepository;


@Service
@Transactional
public class ItemService {

	private final ItemRepository itemRepository;
	@Value(("${upload.filepath}"))
	String UPLOAD_DIR;

	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public Item addItem(Item item, MultipartFile multipartFile) {

		String originalFilename = multipartFile.getOriginalFilename();
		if (originalFilename != null && !originalFilename.isEmpty()) {
			try {
				Files.copy(multipartFile.getInputStream(),
						Paths.get(UPLOAD_DIR + File.separator + item.getName().replace(".", "") + "_"
								+ timeStamp.replace(".", "") + "."
								+ FilenameUtils.getExtension(multipartFile.getOriginalFilename())),
						StandardCopyOption.REPLACE_EXISTING);

				String file = item.getName().replace(".", "") + "_" + timeStamp.replace(".", "")
						+ "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());

				item.setFile(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return itemRepository.save(item);

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
