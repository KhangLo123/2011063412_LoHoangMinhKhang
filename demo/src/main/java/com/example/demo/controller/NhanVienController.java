package com.example.demo.controller;

import com.example.demo.entity.NhanVien;
import com.example.demo.services.NhanVienService;
import com.example.demo.services.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/nhanviens")
public class NhanVienController {
    @Autowired
    private NhanVienService bookService;
    @Autowired
    private PhongBanService categoryService;
    @GetMapping
    public String showAllBooks(Model model){
        List<NhanVien> books = bookService.getAllBooks();
        model.addAttribute("books",books);
        return "nhanvien/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new NhanVien());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "nhanvien/add";
    }
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") NhanVien book, @RequestParam("imageProduct") MultipartFile imageProduct, BindingResult result, Model model){
        // check lỗi
        if(result.hasErrors()){
            model.addAttribute("categories",categoryService.getAllCategories());
            return "nhanvien/add";
        }
        else {
            if (imageProduct != null && imageProduct.getSize() > 0) {
                try {
                    File saveFile = new ClassPathResource("static/images").getFile();
                    String newImageFile = UUID.randomUUID() + ".png";
                    Path path =  Paths.get(saveFile.getAbsolutePath() + File.separator + newImageFile);
                    Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    book.setImage(newImageFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            bookService.addBook(book);
            return "redirect:/nhanviens";
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return "redirect:/nhanviens";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id,Model model){
        NhanVien editBook = bookService.getBookId(id);
        if(editBook!=null){
            model.addAttribute("book",editBook);
            model.addAttribute("categories",categoryService.getAllCategories());
            return "nhanvien/edit";
        }
        else{
            return "redirect:/nhanviens";
        }
    }
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id")Long id, @ModelAttribute("book") NhanVien editBook, @RequestParam MultipartFile imageProduct, BindingResult result, Model model){
        // check lỗi
        if(result.hasErrors()){
            model.addAttribute("categories",categoryService.getAllCategories());
            return "nhanvien/edit";
        }
        if(imageProduct!=null&&imageProduct.getSize()>0){
            try {
                File savefile = new ClassPathResource("static/images").getFile();
                Path path = Paths.get(savefile.getAbsolutePath()+File.separator+editBook.getImage());
                Files.copy(imageProduct.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            NhanVien existingBook = bookService.getBookId(id);
            if (existingBook != null){
                existingBook.setImage(editBook.getImage());
                existingBook.setAuthor(editBook.getAuthor());
                existingBook.setTitle(editBook.getTitle());
                existingBook.setPrice(editBook.getPrice());
                existingBook.setCategory((editBook.getCategory()));
                bookService.updateBook(existingBook);
            }
            return "redirect:/nhanviens";
        }
        return "redirect:/nhanviens";
    }

}
