package com.example.spring02_jpademo.part01.repository;

import java.util.List;

import com.example.spring02_jpademo.part01.entity.MemEntity;

//1. 사용자 정의 인터페이스
public interface MemRepositoryCustom {
	List<MemEntity> findByDynamicName(String name);
	
}
