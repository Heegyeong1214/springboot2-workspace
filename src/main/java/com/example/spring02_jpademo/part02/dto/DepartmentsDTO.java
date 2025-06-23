package com.example.spring02_jpademo.part02.dto;

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
public class DepartmentsDTO {
	  private Long departmentId;
	    private String departmentName;
	    private Long managerId;
	    private Long locationId;
	    
	    
	    
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

	    // DTO → Entity
	    public DepartmentsEntity toEntity() {
	        return DepartmentsEntity.builder()
	                .departmentId(departmentId)
	                .departmentName(departmentName)
	                .managerId(managerId)
	                .locationId(locationId)
	                .build();
	    }
}
