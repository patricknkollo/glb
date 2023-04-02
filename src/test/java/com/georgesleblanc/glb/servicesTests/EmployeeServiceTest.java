package com.georgesleblanc.glb.servicesTests;

import com.georgesleblanc.glb.entities.Employee;
import com.georgesleblanc.glb.repositories.EmployeeRepository;
import com.georgesleblanc.glb.services.EmployeeService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {EmployeeServiceTest.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceTest {

    private Logger logger  = LoggerFactory.getLogger(EmployeeService.class);

    @InjectMocks
    private EmployeeService service;
    @Mock
    private EmployeeRepository repository;

    private Employee employee1 = new Employee();
    private Employee employee2 = new Employee();

    @BeforeEach
    void setUp(){
        employee1.setVilleid(1L);
        employee1.setAge(32);
        employee1.setLastname("nkollo");
        employee1.setFirstname("patrick");

        employee2.setVilleid(1L);
        employee2.setAge(32);
        employee2.setLastname("nkollo");
        employee2.setFirstname("patrick");
    }

    @Test
    void  test_saveEmployee(){
        Mockito.when(repository.save(employee1)).thenReturn(employee2);
        Employee result = service.saveEmployee(employee1);
        Assertions.assertEquals(employee2, result);
    }

    @Test
    void test_findAllEmployees(){
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(employee1, employee2));
        ResponseEntity<List<Employee>> result = service.findAllEmployees();
        Assertions.assertEquals(Arrays.asList(employee1, employee2), result.getBody());
    }

    @Test
    void test_updateEmployee(){
        ResponseEntity<Employee> expected = new ResponseEntity<>(employee2, HttpStatus.OK);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(employee1));
        Mockito.when(repository.save(employee1)).thenReturn(employee2);
        ResponseEntity<Employee> result = service.updateEmployee(employee1, 1L);
        Assertions.assertEquals(expected, result);

    }

    @Test
    void test_removeEmployeeWithID(){

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(employee1));
        service.removeEmployeeWithID(1L);
        Mockito.verify(repository).deleteById(1L);

    }
}
