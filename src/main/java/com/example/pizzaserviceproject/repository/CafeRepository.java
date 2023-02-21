package com.example.pizzaserviceproject.repository;

import com.example.pizzaserviceproject.entity.Cafe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
//    List<Cafe> getAllCafes();
    List<Cafe> findByNameContaining(String name);
    List<Cafe> findByAddressContaining(String name);

    @Query("select c from Cafe c")
    public List<Cafe> getAllSorted(Sort sort);

    @Query("select c from Cafe c ORDER BY c.id")
    Page<Cafe> getPage(Pageable pageable);


}
