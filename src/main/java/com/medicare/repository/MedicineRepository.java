package com.medicare.repository;

import com.medicare.entity.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicineRepository extends PagingAndSortingRepository<Medicine, Long> {
  Medicine findById(Long id);

  Medicine save(Medicine medicine);

  void deleteById(Long id);

  boolean existsByName(String name);
  @Query("select m from Medicine m where m.name like %:search% or m.seller.name like %:search% or m.description like %:search%")
  Page<Medicine> search(Pageable pageable, String search);
  @Query("select m from Medicine m where m.price <=:price")
  Page<Medicine> search(Pageable pageable, Float price);
  @Query("select m from Medicine m where (m.name like %:search% or m.seller.name like %:search% or m.description like %:search%) and  m.price <=:price")
  Page<Medicine> search(Pageable pageable, String search, Float price);
  @Query("select m from Medicine m where m.seller.id = :seller")
  Page<Medicine> search(Pageable pageable, Long seller);
  @Query("select m from Medicine m where (m.name like %:search% or m.description like %:search%) and m.seller.id = :seller")
  Page<Medicine> search(Pageable pageable, Long seller, String search);
  @Query("select m from Medicine m where m.price <=:price and  m.seller.id = :seller")
  Page<Medicine> search(Pageable pageable, Long seller, Float price);
  @Query("select m from Medicine m where (m.name like %:search% or m.description like %:search%) and m.price <=:price and  m.seller.id = :seller")
  Page<Medicine> search(Pageable pageable, Long seller, String search, Float price);
  @Query("select max(m.price) from Medicine m")
  Float max();
  boolean existsById(Long id);
}
