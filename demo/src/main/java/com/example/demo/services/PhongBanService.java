package com.example.demo.services;

import com.example.demo.entity.PhongBan;
import com.example.demo.repository.IPhongBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongBanService {
    @Autowired
    private IPhongBanRepository categoryRepository;
    public List<PhongBan> getAllCategories(){
        return categoryRepository.findAll();
    }
    public PhongBan getCategoryById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }
    public PhongBan saveCategory(PhongBan category){
        return categoryRepository.save(category);
    }
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
