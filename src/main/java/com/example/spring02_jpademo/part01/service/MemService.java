package com.example.spring02_jpademo.part01.service;

import java.util.List;

import com.example.spring02_jpademo.part01.dto.MemDTO;

public interface MemService {
	public List<MemDTO> getByJPQL(String name);
	public List<MemDTO> getByCriteria(String name);
	public List<MemDTO> getByNativeQuery(String name);
	public List<MemDTO> getByNamedQuery(String name);
	public List<MemDTO> getMemByNum(int num);
	public List<MemDTO> getMemByNameAndAge(String name, int age);
	public List<MemDTO> getMemByAgeIsNull();
	//public int insertMemByNative(String name, int age, String loc);
	public int insertMemByNative(MemDTO memDTO);
	
}
