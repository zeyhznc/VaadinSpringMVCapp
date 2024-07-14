package com.example.application.repository;

import com.example.application.backend.entity.Personel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonelRepository extends JpaRepository<Personel, Long> {
    @Query("SELECT p FROM Personel p WHERE " +
            "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "p.tckn LIKE CONCAT('%', :filter, '%')")
    List<Personel> search(String filter);

}
