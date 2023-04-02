package com.georgesleblanc.glb.repositories;

import com.georgesleblanc.glb.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
}
