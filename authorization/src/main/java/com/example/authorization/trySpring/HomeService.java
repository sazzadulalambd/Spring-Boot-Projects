package com.example.authorization.trySpring;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Point : @Service
@Service
public class HomeService {
    private final HomeRepository homeRepository;

    @Autowired
    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public Employee findOne(int id) {
        Map<String, Object> map = homeRepository.findOne(id);
        if (map == null || map.isEmpty()) {
            return null;
        }
// Get value from Map
        int employeeId = (Integer) map.get("id");
        String employeeName = (String) map.get("name");
        int employeeAge = (Integer) map.get("age");
        String employeeDepartment = (String) map.get("department");
// Set values in the Employee class
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setEmployeeName(employeeName);
        employee.setEmployeeAge(employeeAge);
        employee.setEmployeeDepartment(employeeDepartment);
        return employee;
    }

}


//@Autowired
//private HomeRepository homeRepository;
//
//public Employee findOne(int id) {
//// Search execution
//    Map<String, Object> map = homeRepository.findOne(id);
//
//Employee employee = new Employee();
//        employee.setEmployeeId((int) map.get("id"));
//        employee.setEmployeeName((String) map.get("name"));
//        employee.setEmployeeAge((int) map.get("age"));
//        employee.setEmployeeDepartment((String) map.get("department"));
//        return employee;