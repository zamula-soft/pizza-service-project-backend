package com.example.pizzaserviceproject.models.cafe;

import com.example.pizzaserviceproject.models.cafe.Cafe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeRepository extends CrudRepository<Cafe, String> {
//    List<Cafe> getAllCafes();
    List<Cafe> findByNameContaining(String name);
}
