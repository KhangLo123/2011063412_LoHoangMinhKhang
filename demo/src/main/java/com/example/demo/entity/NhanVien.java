package com.example.demo.entity;

import com.example.demo.validator.annotation.ValidCategoryId;
import com.example.demo.validator.annotation.ValidUserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="book")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    @NotEmpty(message = "Title must not be empty")
    @Size(max = 50,min = 1,message = "Title must be less than 50 characters")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "price")
    @NotNull(message = "Price is required")
    private Double price;
    @Column(name = "image")
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @ValidCategoryId
    private PhongBan category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ValidUserId
    private User user;


}
