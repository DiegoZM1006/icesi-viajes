package com.edu.icesi.aviazen.controller;

import com.edu.icesi.aviazen.auth.AuthResponse;
import com.edu.icesi.aviazen.auth.AuthService;
import com.edu.icesi.aviazen.auth.RegisterRequest;
import com.edu.icesi.aviazen.domain.Role;
import com.edu.icesi.aviazen.domain.User;
import com.edu.icesi.aviazen.service.EmployeeService;
import com.edu.icesi.aviazen.service.UserCommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Rest controller for managing employee-related operations.
 */

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Retrieves all employees.
     *
     * @param token the authorization token
     * @return a list of all employees
     */
    @GetMapping(value = "allEmployees")
    public ResponseEntity<List<User>> allEmployees(@RequestHeader("Authorization") String token)
    {
        List<User> clients = employeeService.findEmployees();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /**
     * Registers a new employee.
     *
     * @param token the authorization token
     * @param request the registration request containing employee details
     * @return the authentication response
     */
    @PostMapping(value = "addEmployee")
    public ResponseEntity<AuthResponse> register(@RequestHeader("Authorization") String token, @RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.registerEmployee(request));
    }

    /**
     * Searches for an employee by their ID.
     *
     * @param token the authorization token
     * @param id the ID of the employee to search for
     * @return the employee if found, otherwise a not found response
     */
    @GetMapping(value = "searchEmployee/{id}")
    public ResponseEntity<?> searchEmployee(@RequestHeader("Authorization") String token, @PathVariable Integer id)
    {
        Optional<User> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Updates an existing employee.
     *
     * @param token the authorization token
     * @param id the ID of the employee to update
     * @param user the new details of the employee
     * @return the updated employee if found, otherwise a not found response
     * @throws Exception if an error occurs during the update
     */

    @PostMapping(value = "updateEmployee/{id}")
    public ResponseEntity<?> updateEmployee(@RequestHeader("Authorization") String token, @PathVariable Integer id, @RequestBody RegisterRequest user) throws Exception {
        Optional<User> optionalEmployee = employeeService.findById(id);
        if (optionalEmployee.isPresent()) {
            User employee = optionalEmployee.get();
            employee.setName(user.getName());
            employee.setLastname(user.getLastname());
            employee.setEmail(user.getEmail());
            employee.setPhone_number(user.getPhone_number());
            employee.setAddress(user.getAddress());
            employee.setUsername(user.getUsername());
            employee.setPassword(passwordEncoder.encode(user.getPassword()));
            employee.setRole(Role.valueOf(user.getRole()));
            employeeService.save(employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes an employee by marking their status as inactive.
     *
     * @param token the authorization token
     * @param id the ID of the employee to delete
     * @return a success message if the employee is found and deleted, otherwise a not found response
     * @throws Exception if an error occurs during the deletion
     */

    @DeleteMapping(value = "deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployee(@RequestHeader("Authorization") String token, @PathVariable Integer id) throws Exception {
        Optional<User> optionalEmployee = employeeService.findById(id);
        if (optionalEmployee.isPresent()) {
            User employee = optionalEmployee.get();
            employee.setStatus("inactive");
            employeeService.save(employee);
            return new ResponseEntity<>("Empleado eliminado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Counts the total number of employees.
     *
     * @param token the authorization token
     * @return the total number of employees
     */

    @GetMapping(value = "countEmployees")
    public ResponseEntity<Long> countEmployees(@RequestHeader("Authorization") String token) {
        Long employees = employeeService.countEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}
