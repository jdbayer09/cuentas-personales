package com.jdb.personal.acc.api.service.impl;

import com.jdb.personal.acc.api.entity.AppUser;
import com.jdb.personal.acc.api.entity.Category;
import com.jdb.personal.acc.api.exception.NotCategoryException;
import com.jdb.personal.acc.api.exception.NotUserException;
import com.jdb.personal.acc.api.repository.ICategoryRepository;
import com.jdb.personal.acc.api.service.IAppUserService;
import com.jdb.personal.acc.api.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IAppUserService appUserService;

    @Override
    @Transactional(readOnly = true)
    public List<Category> listCategories(Long userId) {
        return categoryRepository.findAllByUser_Id(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(Long userId, Long categoryId) throws NotCategoryException {
        Category resp = categoryRepository.findByUser_IdAndId(userId, categoryId);
        if (resp == null || resp.getId() == null || resp.getId().equals(0L)) throw new NotCategoryException("La categoria no existe");
        return resp;
    }

    @Override
    @Transactional
    public Category create(Long userId, Category category) throws NotUserException {
        AppUser user = appUserService.findById(userId);
        Category newCategory = new Category();
        newCategory.setId(null);
        newCategory.setName(category.getName().toUpperCase());
        newCategory.setUser(user);
        newCategory.setDisabled(false);
        newCategory.setDescription(category.getDescription());
        return categoryRepository.save(newCategory);
    }

    @Override
    @Transactional
    public Category update(Long userId, Long categoryId, Category category) throws NotUserException, NotCategoryException {
        Category newCategory = findById(userId, categoryId);

        newCategory.setDescription(category.getDescription());
        newCategory.setName(category.getName());

        return categoryRepository.save(newCategory);
    }

    @Override
    @Transactional
    public Category disable(Long userId, Long categoryId) throws NotUserException, NotCategoryException {
        Category newCategory = findById(userId, categoryId);
        if (true) {
            categoryRepository.delete(newCategory);
            return newCategory;
        } else { /*
            newCategory.setDisabled(true);
            return categoryRepository.save(newCategory); */
        	return null;
        }
    }
}
