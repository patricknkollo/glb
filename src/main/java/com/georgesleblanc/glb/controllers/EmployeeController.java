package com.georgesleblanc.glb.controllers;

import com.georgesleblanc.glb.entities.Employee;
import com.georgesleblanc.glb.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Component
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public @ResponseBody Employee saveEmployee(@RequestBody Employee employee){
       return service.saveEmployee(employee);
    }

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Employee>> findAllEmployees(){
       return  service.findAllEmployees();
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable Long id){
        return service.updateEmployee(employee, id);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public void removeEmployeeWithID(@RequestParam("employeeid") Long id){
       service.removeEmployeeWithID(id);
    }
}
