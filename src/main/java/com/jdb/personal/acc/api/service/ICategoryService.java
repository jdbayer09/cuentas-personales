package com.jdb.personal.acc.api.service;

import com.jdb.personal.acc.api.entity.Category;
import com.jdb.personal.acc.api.exception.NotCategoryException;
import com.jdb.personal.acc.api.exception.NotUserException;

import java.util.List;

public interface ICategoryService {

    List<Category> listCategories(Long userId);

    Category findById(Long userId, Long categoryId) throws NotCategoryException;

    Category create(Long userId, Category category) throws NotUserException;

    Category update(Long userId, Long categoryId, Category category) throws NotUserException, NotCategoryException;

    Category disable(Long userId, Long categoryId) throws NotUserException, NotCategoryException;
}
