package com.example.demo.repository;

import com.example.demo.entity.NhanVien;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface INhanVienRepository extends JpaRepository<NhanVien,Long> {

}
