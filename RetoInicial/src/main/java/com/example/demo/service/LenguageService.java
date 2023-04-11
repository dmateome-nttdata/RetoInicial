package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Lenguage;

public interface LenguageService {

	public List<Lenguage> findLenguageAll();

	public Lenguage getLenguage(int id);

	public Lenguage createLenguage(Lenguage lenguage);

	public Lenguage updateLenguage(Lenguage lenguage);

	public Lenguage deleteLenguage(Lenguage lenguage);

	public Lenguage getLenguage(String lenguage);
}
