package com.example.spring02_jpademo.part01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.spring02_jpademo.part01.dto.MemDTO;
import com.example.spring02_jpademo.part01.entity.MemEntity;

import jakarta.transaction.Transactional;

@Repository
public interface MemRepository extends JpaRepository<MemEntity, Integer>, MemRepositoryCustom {
//todolist에서는 JPA 적용
	
	//JPQL (Java Persistence Query Language)
		@Query(value="SELECT m FROM MemEntity m WHERE m.name = :name")
	List<MemEntity> findByNameJPQL(@Param("name") String name);
		
	//Native Query
		@Query(value="SELECT * FROM mem WHERE name = :name", nativeQuery = true)
		List<MemEntity> findByNameNative(@Param("name") String name);
	
		//Named Query
		 @Query(name = "MemEntity.findExpensive")
		    List<MemEntity> callNamedQuery(@Param("name") String name);
		 
		 //쿼리 메소드 : find+엔티티(생략가능)+By+변수명//////////////////////////
		 //WHERE num >= ?1 (매개변수의 인덱스)
		 List<MemEntity> findMemEntityByNumGreaterThanEqual(int num);
		 
		 //WHERE name='홍길동' AND age=10;
		 List<MemEntity> findMemEntityByNameAndAge(String name, int age);
		 
		 //WHERE age IS NOT NULL;
		 List<MemEntity> findMemEntityByAgeIsNotNull();
		 
		 //Native Query:Insert, Update, Delete 제공함
		 //Insert, Update, Delete 작성한 쿼리에서는 @Modifying 넣어줘야 한다
		 
		 //INSERT
//		 @Modifying
//		 @Query(value="INSERT INTO mem (num, name, age, loc) VALUES (mem_num_seq.nextval, :name, :age, :loc)", nativeQuery = true)
//		 int insertMemByNative(@Param("name") String name, @Param("age") Integer age, @Param("loc") String loc);
		 
		 
//		 mem이라는 Table명을 줌
		 @Modifying
		 @Query(value="INSERT INTO mem (num, name, age, loc) VALUES (mem_num_seq.nextval, :#{#memDTO.name}, :#{#memDTO.age}, :#{#memDTO.loc})", nativeQuery = true)
		 int insertMemByNative(@Param("memDTO") MemDTO memDTO );
		 
		 
		 //UPDATE
//		 -> 오라클에서 쿼리문 작성하기 -> JPA
		 @Modifying
		   @Query(value = "UPDATE mem SET name = :#{#memEntity.name}, age = :#{#memEntity.age},loc = :#{#memEntity.loc} WHERE num = :#{#memEntity.num}", nativeQuery = true)
		   int updateMemByNative(@Param("memEntity") MemEntity memEntity);
		
		 
		 //DELETE -> NUM만 넘겨주면된다
		 @Modifying
		    @Query(value = "DELETE FROM mem WHERE num = :num", nativeQuery = true)
		    int deleteMemByNative(@Param("num") int num);


		 //JPQL : Update, Delete은 제공/////////////////////////////////
		 //JPQL에서는 Insert는 제공안함
		 //테이블 명이 아니라 Entity 줌
		 
		 @Modifying
		 @Query(value = "UPDATE MemEntity m  SET m.name = :#{#memEntity.name}, m.age = :#{#memEntity.age}, m.loc = :#{#memEntity.loc} WHERE m.num = :#{#memEntity.num}")
		   int updateMemByJpql(@Param("memEntity") MemEntity memEntity);
		 
		 @Modifying
		   @Query(value = "DELETE FROM MemEntity m WHERE m.num = :num")
		   int deleteMemByJpql(@Param("num") int num);



//memEntity.name: 넘겨준 값
		 		 
	
}