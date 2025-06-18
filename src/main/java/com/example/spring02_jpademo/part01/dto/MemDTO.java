package com.example.spring02_jpademo.part01.dto;

import com.example.spring02_jpademo.part01.entity.MemEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemDTO {
	private int num;
	private String name;
	private int age;
	private String loc;


	//static인 변수를 사용하지 않음..?
	//변수 4개 중 일부만 사용해서 생성자를 활용하기 위해서! 아니면 일일이 생성자를 만들어야 해서 귀찮고 많아짐
	//MemDTO => MemEntity
		public MemEntity toEntity() {
			return MemEntity.builder()
					.num(num)
					.name(name)
					.age(age)
					.loc(loc)
					.build();
		}


		//static? 매개변수인 memEntity를 받아오기 때문에
		//MemEntity => MemDTO
		public static MemDTO toDTO(MemEntity memEntity) {
			return MemDTO.builder()
					.num(memEntity.getNum())
					.name(memEntity.getName())
					.age(memEntity.getAge())
					.loc(memEntity.getLoc())
					.build();
		}
}






