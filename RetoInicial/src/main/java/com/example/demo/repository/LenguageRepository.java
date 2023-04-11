package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Lenguage;

public interface LenguageRepository extends JpaRepository<Lenguage,Integer>{

	public Lenguage findByLenguage(String lenguage);
}
