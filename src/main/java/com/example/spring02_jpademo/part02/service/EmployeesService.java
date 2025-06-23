package com.example.spring02_jpademo.part02.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring02_jpademo.part02.dto.EmployeesDTO;
import com.example.spring02_jpademo.part02.entity.EmployeesEntity;
import com.example.spring02_jpademo.part02.repository.EmployeesRepository;


@Service

public class EmployeesService {

	@Autowired
	private EmployeesRepository employeesRepository;

	public EmployeesService() {
		// TODO Auto-generated constructor stub
	}

	// ✅ JPQL 방식: Entity + 부서명 포함
//	[EmployeesService]
//			 getAllEmployeesWithDepartmentJPQL()
//			   1.  Repository의 findAllWithDepartmentJPQL() 호출하면 Select의 결과을 리턴해준다.
//			   2.  Select의 결과는  List<EmployeesEntity> 리턴한다.
//			   3. EmployeesEntity을 EmployeesDTO로 변경해 준다.
//			   4. EmployeesEntity에는 DepartmentEntity에 대한 정보도 저장되여 있어서 
//			       Map<String, Object> 에 EmployeesDTO와 DepartmentEntity에서 
//			     Department의 DepartmentName을 가져와서 저장한다.
//			    
//			   5  레코드 하나당 Map<String, Object>생성이 되므로 List<Map<String, Object>>에 저장한다.
//			   6 최종적으로 List<Map<String, Object>>을 리턴한다.
//
//
//
//
//			  1. select의 결과을 받아온다
//			  2.  entity -> dto로 변경 (employees, department)
//			  3. map에 employees, department정보를 저장한다.
//			  4. map을 list에 저장
//			   5. list을 리턴함
			  

	
	public List<Map<String, Object>> getAllEmployeesWithDepartmentJPQL() {
		//1. employee의 컬럼이 여러개니까 List에 담음
		List<EmployeesEntity> entities = employeesRepository.findAllWithDepartmentJPQL();
		//Employee 테이블에는 department를 저장할 수 있는 컬럼이 없음
		List<Map<String, Object>> result = new ArrayList<>();

		
		//employee랑 departmentName을 Map에 담아서 4. List로 다시 넘겨서 저장: List<Map<String, Object>> result
		for (EmployeesEntity e : entities) {
			EmployeesDTO dto = EmployeesDTO.toDTO(e); //2. entity를 dto로 변경(employee, department에 대한 정보)
			Map<String, Object> map = new LinkedHashMap<>(); // 3. 변경된 dto를 map에다가 저장
			map.put("employee", dto);
			map.put("departmentName", e.getDepartment().getDepartmentName());
			result.add(map);
		}
		return result;
	}

	// ✅ Native 방식: Object[] → DTO + 부서명
	
	//왜 Object로 가져왔어? 타입이 여러개인데, 모든 클래스의 최상위가 Object에 해당하니까!
	public List<Map<String, Object>> getAllEmployeesWithDepartmentNative() {
	        List<Object[]> rows = employeesRepository.findAllWithDepartmentNative();
	        List<Map<String, Object>> result = new ArrayList<>();
	        
	        
	        //Employee DTO에서 Long, String, Date 등 일부 클래스로 변수? 객체를 선언했는데
	        //여기서 데이터를 받아올 때 object로 받아왔기 때문에 문자는 String으로, Number로 다운캐스팅해서 가지고 옴
	        for (Object[] row : rows) {
	            EmployeesDTO dto = EmployeesDTO.builder()
	                    .employeeId(((Number) row[0]).longValue())
	                    .firstName((String) row[1])
	                    .lastName((String) row[2])
	                    .email((String) row[3])
	                    .phoneNumber((String) row[4])
	                    .hireDate((Date) row[5])
	                    .jobId((String) row[6])
	                    .salary(row[7] != null ? ((Number) row[7]).doubleValue() : null)
	                    .commissionPct(row[8] != null ? ((Number) row[8]).doubleValue() : null)
	                    .managerId(row[9] != null ? ((Number) row[9]).longValue() : null)
	                    .departmentId(row[10] != null ? ((Number) row[10]).longValue() : null)
	                    .build();

	            Map<String, Object> map = new LinkedHashMap<>();
	            map.put("employee", dto);
	            map.put("departmentName", row[11]); //별도로 departmentName 저장
	            result.add(map); //map을 list에 저장
	        }

	        return result;
	    }
}
