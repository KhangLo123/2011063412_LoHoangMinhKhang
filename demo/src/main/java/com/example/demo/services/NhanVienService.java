package com.example.demo.services;

import com.example.demo.entity.NhanVien;
import com.example.demo.repository.INhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {
    @Autowired
    private INhanVienRepository bookRepository;
    public List<NhanVien> getAllBooks(){
        return bookRepository.findAll();
    }
    public NhanVien getBookId(Long id){
        return bookRepository.findById(id).orElse(null);
    }
    public void addBook(NhanVien book){
        bookRepository.save(book);
    }
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
    public void updateBook(NhanVien book){
        bookRepository.save(book);
    }

}
