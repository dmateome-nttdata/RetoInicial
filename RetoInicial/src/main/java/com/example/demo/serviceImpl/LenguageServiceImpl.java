package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Lenguage;
import com.example.demo.repository.LenguageRepository;
import com.example.demo.service.LenguageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LenguageServiceImpl implements LenguageService {

	@Autowired
	LenguageRepository lenguageRepository;

	@Override
	public List<Lenguage> findLenguageAll() {
		return lenguageRepository.findAll();
	}

	@Override
	public Lenguage getLenguage(int id) {
		return lenguageRepository.findById(id).orElse(null);
	}

	@Override
	public Lenguage getLenguage(String lenguage) {
		return lenguageRepository.findByLenguage(lenguage);
	}

	@Override
	public Lenguage createLenguage(Lenguage lenguage) {
		Lenguage lenguageDB = lenguageRepository.findById(lenguage.getId()).orElse(null);
		if (lenguageDB != null) {
			return lenguageDB;
		}

		lenguageDB = lenguageRepository.save(lenguage);
		return lenguageDB;
	}

	@Override
	public Lenguage updateLenguage(Lenguage lenguage) {
		Lenguage lenguageDB = getLenguage(lenguage.getId());
		if (lenguageDB == null) {
			return null;
		}
		lenguageDB.setLenguage(lenguage.getLenguage());
		lenguageDB.setText(lenguage.getText());

		return lenguageRepository.save(lenguageDB);
	}

	@Override
	public Lenguage deleteLenguage(Lenguage lenguage) {
		Lenguage customerDB = getLenguage(lenguage.getId());
		if (customerDB == null) {
			return null;
		}

		lenguageRepository.delete(lenguage);
		return lenguage;
	}

}
