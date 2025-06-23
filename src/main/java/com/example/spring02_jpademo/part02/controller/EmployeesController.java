package com.example.spring02_jpademo.part02.controller;

import com.example.spring02_jpademo.part02.service.EmployeesService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees") //url에서 반복되는 부분 설정이고 항상 앞에 와야 함
@RequiredArgsConstructor //생성자가 안에서 자동적으로 생성되서 굳이 아래 클래스에 생성자를 작성하지 않아도 괜찮다.
public class EmployeesController {

    private final EmployeesService employeesService; //이건 스프링컨테이너에 빈이 생성되어있는 객체를 넣어준다는 의미
    //private final String string; //얘는 데이터가 저장되어 있지 않음!
    
    
    
//    //이전에는 아래처럼 작성했지만, 위처럼 적어도 된다
//    @Autowired
//    private EmployeesService employeesService;
//    public EmployeesController() {
//    	this.employeesService = employeesService;
//    } //-> 생성자

    // http://localhost:8090/api/employees/with-department-jpql
    // JPQL 방식
    @GetMapping("/with-department-jpql")
    public List<Map<String, Object>> getEmployeesWithDepartmentJPQL() {
        return employeesService.getAllEmployeesWithDepartmentJPQL();
    }

    
 // http://localhost:8090/api/employees/with-department-native
    //  Native SQL 방식
    @GetMapping("/with-department-native")
    public List<Map<String, Object>> getEmployeesWithDepartmentNative() {
        return employeesService.getAllEmployeesWithDepartmentNative();
    }
}
