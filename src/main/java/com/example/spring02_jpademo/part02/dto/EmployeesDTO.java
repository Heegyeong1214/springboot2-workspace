package com.example.spring02_jpademo.part02.dto;

import java.util.Date;

import com.example.spring02_jpademo.part02.entity.DepartmentsEntity;
import com.example.spring02_jpademo.part02.entity.EmployeesEntity;

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
public class EmployeesDTO {
	//기본 데이터 탑이 아니라 클래스 (Long, String, Date, Double)로 되어있어서 기본 값이 null
	private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private String jobId;
    private Double salary;
    private Double commissionPct;
    private Long managerId;
    private Long departmentId;
    
 // DTO → Entity
    public EmployeesEntity toEntity(DepartmentsEntity department) {
        return EmployeesEntity.builder()
                .employeeId(employeeId)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .hireDate(hireDate)
                .jobId(jobId)
                .salary(salary)
                .commissionPct(commissionPct)
                .managerId(managerId)
                .department(department) // 연관관계 주입
                .build();
    }

 // Entity → DTO
    public static EmployeesDTO toDTO(EmployeesEntity entity) {
        return EmployeesDTO.builder()
                .employeeId(entity.getEmployeeId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .hireDate(entity.getHireDate())
                .jobId(entity.getJobId())
                .salary(entity.getSalary())
                .commissionPct(entity.getCommissionPct())
                .managerId(entity.getManagerId())
                .departmentId(entity.getDepartment() != null ? entity.getDepartment().getDepartmentId() : null)
                .build();
    }

}
