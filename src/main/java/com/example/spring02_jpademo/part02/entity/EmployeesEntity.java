package com.example.spring02_jpademo.part02.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "employees")
public class EmployeesEntity {
	    @Id
	    @Column(name = "employee_id")
	    private Long employeeId;

	    @Column(name = "first_name")
	    private String firstName;

	    @Column(name = "last_name", nullable = false)
	    private String lastName;

	    @Column(name = "email", nullable = false)
	    private String email;

	    @Column(name = "phone_number")
	    private String phoneNumber;

	    @Column(name = "hire_date", nullable = false)
	    @Temporal(TemporalType.DATE)
	    private Date hireDate;

	    @Column(name = "job_id", nullable = false)
	    private String jobId;

	    @Column(name = "salary")
	    private Double salary;

	    @Column(name = "commission_pct")
	    private Double commissionPct;

	    @Column(name = "manager_id")
	    private Long managerId;

	 //다대일 관계: department입장에서 팀원을 볼 때
    @ManyToOne
    @JoinColumn(name = "department_id") // employees.department_id FK (1)
    //department_id 컬럼에 있는 걸 emmployee에 저장하고  department에도 저장
    //employee만 조회해도 department 값을 가지고 옴
    private DepartmentsEntity department;
}

/*
 * 
                                  @JoinColumn vs mappedBy
항목	            @JoinColumn	                      mappedBy
위치	            연관관계의 주인 쪽 (@ManyToOne)	      연관관계의 비주인 쪽 (@OneToMany)
외래키 정의	    직접 DB에 외래키를 생성함	              외래키를 만들지 않음
역할	            어떤 컬럼이 외래키인지 지정함	          주인을 지정함 (역방향 지정)
					 
*/
