package com.example.spring02_jpademo.part02.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "departments")
public class DepartmentsEntity {
	@Id
	@Column(name = "department_id") //오라클에서 테이블의 컬럼명을 가져와야 하기 때문에 @Column 추가 작성해야 함
	private Long departmentId; //컬럼명이랑 변수명이랑 같게 해줌

	@Column(name = "department_name", nullable = false, length = 30)
	private String departmentName;

	@Column(name = "manager_id")
	private Long managerId;

	@Column(name = "location_id")
	private Long locationId;
	
	//일대다 관계: 부서가 인사팀이라면, 그 안에 여러 팀원들이 있겠지 -> Employee 입장에서 Deapartment를 볼 때
	//EmployeesEntity 타입을 여러개 받기 위해서 List로 변수 설정
	@OneToMany(mappedBy = "department")
	private List<EmployeesEntity> employees;
}
