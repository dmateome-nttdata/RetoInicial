package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="tbl_lenguages")
public class Lenguage {

	@Id
	@GeneratedValue
	private int id;
	private String text;
	
	@Column(unique=true)
	private String lenguage;
	
}
