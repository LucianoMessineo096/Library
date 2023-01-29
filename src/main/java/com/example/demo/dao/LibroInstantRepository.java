package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Libro;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LibroInstantRepository extends JpaRepository<Libro,Integer>{

}
