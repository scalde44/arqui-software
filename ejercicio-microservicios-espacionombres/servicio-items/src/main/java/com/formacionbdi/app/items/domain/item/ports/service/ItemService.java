package com.formacionbdi.app.items.domain.item.ports.service;

import com.formacionbdi.app.items.domain.item.models.entity.Item;

import java.util.List;

public interface ItemService {
    public List<Item> findAll();

    public Item findById(Long id, Integer cantidad);
}
