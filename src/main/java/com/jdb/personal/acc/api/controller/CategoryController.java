package com.jdb.personal.acc.api.controller;

import com.jdb.personal.acc.api.entity.Category;
import com.jdb.personal.acc.api.exception.NotCategoryException;
import com.jdb.personal.acc.api.exception.NotUserException;
import com.jdb.personal.acc.api.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories/{userId}")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/list")
    @Secured({"ROLE_USER"})
    public ResponseEntity<?> listAll(@PathVariable(name = "userId") Long userId) {
        try {
            List<Category> resp = categoryService.listCategories(userId);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (DataAccessException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_USER"})
    public ResponseEntity<?> findById(@PathVariable(name = "userId") Long userId, @PathVariable(name = "id") Long id) {
        try {
            Category resp = categoryService.findById(userId, id);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NotCategoryException noEx) {
            return new ResponseEntity<>(noEx, HttpStatus.NOT_FOUND);
        } catch (DataAccessException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @Secured({"ROLE_USER"})
    public ResponseEntity<?> create(@PathVariable(name = "userId") Long userId, @RequestBody Category category) {
        try {
            Category resp = categoryService.create(userId, category);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NotUserException noEx) {
            return new ResponseEntity<>(noEx, HttpStatus.NOT_FOUND);
        } catch (DataAccessException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{categoryId}")
    @Secured({"ROLE_USER"})
    public ResponseEntity<?> update(@PathVariable(name = "userId") Long userId, @PathVariable(name = "categoryId") Long id, @RequestBody Category category) {
        try {
            Category resp = categoryService.update(userId, id, category);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NotUserException | NotCategoryException noEx) {
            return new ResponseEntity<>(noEx, HttpStatus.NOT_FOUND);
        } catch (DataAccessException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{categoryId}")
    @Secured({"ROLE_USER"})
    public ResponseEntity<?> disable(@PathVariable(name = "userId") Long userId, @PathVariable(name = "categoryId") Long id) {
        try {
            Category resp = categoryService.disable(userId, id);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NotUserException | NotCategoryException noEx) {
            return new ResponseEntity<>(noEx, HttpStatus.NOT_FOUND);
        } catch (DataAccessException e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
