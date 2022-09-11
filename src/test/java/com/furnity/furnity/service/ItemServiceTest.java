package com.furnity.furnity.service;

import com.furnity.furnity.enums.*;
import com.furnity.furnity.exception.ItemNotFoundException;
import com.furnity.furnity.model.Category;
import com.furnity.furnity.model.Item;
import com.furnity.furnity.model.User;
import com.furnity.furnity.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;
    private ItemService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ItemService(itemRepository);
    }

    @Test
    void shouldAddItem() {
        //given
        Item item = createItem(1l);
        MultipartFile multipartFile = new MockMultipartFile("fileName", "", "image/jpeg", new byte[0]);

        //when
        underTest.addItem(item, multipartFile);

        //then
        ArgumentCaptor<Item> itemArgumentCaptor = ArgumentCaptor.forClass(Item.class);

        verify(itemRepository).save(itemArgumentCaptor.capture());

        Item capturedItem = itemArgumentCaptor.getValue();
        assertThat(capturedItem).isEqualTo(item);
    }

    @Test
    void shouldThrowExceptionIfNullItemIsAdded() {
        //given
        Item item = null;
        MultipartFile multipartFile = new MockMultipartFile("fileName", "", "image/jpeg", new byte[0]);
        when(itemRepository.save(item)).thenThrow(IllegalArgumentException.class);

        //when
        //then
        assertThatThrownBy(() -> underTest.addItem(item, multipartFile))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void shouldFindAllItems() {
        //when
        underTest.findAllItems();
        //then
        verify(itemRepository).findAll();
    }

    @Test
    void shouldDeleteItem() {
        //given
        Item item = createItem(1l);

        //when
        underTest.deleteItem(item.getId());

        //then
        verify(itemRepository).deleteById(item.getId());
    }

    @Test
    void shouldFindItemById() {
        //given
        Item item = createItem(1l);
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        //when
        Item itemFounded = underTest.findItemById(item.getId());

        //then
        assertThat(itemFounded).isEqualTo(item);
        verify(itemRepository).findById(item.getId());
    }

    @Test
    void shouldThrowExceptionIfItemNotFoundById() {
        //given
        Item item = createItem(1l);
        //when(itemRepository.findById(item.getId())).thenReturn(Optional.empty());
        given(itemRepository.findById(item.getId())).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.findItemById(item.getId()))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessageContaining("Item by id " + item.getId() + " was not found");
    }

    @Test
    void shouldUpdateItemIfItemFoundById() {
        //given
        Item item = createItem(1l);

        Item itemWithUpdatedInfo = createItem(2l);
        itemWithUpdatedInfo.setPrice(20.0);
        itemWithUpdatedInfo.setDescription("so nice");

        Item itemAfterUpdate = new Item(item.getId(), itemWithUpdatedInfo.getCategory(),
                itemWithUpdatedInfo.getUser(),
                itemWithUpdatedInfo.getName(), itemWithUpdatedInfo.getDescription(),
                itemWithUpdatedInfo.getPrice(), itemWithUpdatedInfo.getItemCondition(),
                itemWithUpdatedInfo.getItemColor(), itemWithUpdatedInfo.getItemMaterial(),
                itemWithUpdatedInfo.getItemStyle(), itemWithUpdatedInfo.getFile());

        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        //when
        underTest.updateItem(item.getId(), itemWithUpdatedInfo);

        //then
        ArgumentCaptor<Item> itemArgumentCaptor = ArgumentCaptor.forClass(Item.class);

        verify(itemRepository).save(itemArgumentCaptor.capture());

        Item capturedItem = itemArgumentCaptor.getValue();
        assertThat(capturedItem).isEqualTo(itemAfterUpdate);
    }

    @Test
    void shouldThrowExceptionWhenItemIsUpdatedIfItemNotFoundById() {
        //given
        Item item = createItem(1l);

        Item itemWithUpdatedInfo = createItem(2l);
        itemWithUpdatedInfo.setPrice(20.0);
        itemWithUpdatedInfo.setDescription("so nice");

        when(itemRepository.findById(item.getId())).thenReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> underTest.updateItem(item.getId(), itemWithUpdatedInfo))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessageContaining("Item with ID :: " + item.getId() + " not found in DB");
    }


    private Item createItem(Long id) {
        Category category = new Category(1l, "table");
        User user = new User(1l, "Ana", "Ana", null, "ana@gmail.com", "password", Collections.emptySet());
        Item item = new Item(id, category, user, "good table", "very good table", 10.0, ItemCondition.POOR, ItemColor.BLUE, ItemMaterial.METAL, ItemStyle.RUSTIC, null);
        return item;
    }



}