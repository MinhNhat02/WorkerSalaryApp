package workersalary.entity.implement;

import java.sql.Date;

import workersalary.entity.Department;
import workersalary.entity.Employee;

public class Worker extends Employee {

    public Worker(String id, String fullName, String indetifyCard, Date dateOfBirth, String gender, double salary, double allowance,
            String phoneNumber, String address, Department department) {
        super(id, fullName, indetifyCard, dateOfBirth, gender, salary, allowance, phoneNumber, address, department);
    }

    public Worker(String id, String fullName, String identifyCard, Date dateOfBirth, String gender, double allowance, String phoneNumber, String address, Department department) {
        super(id, fullName, identifyCard, dateOfBirth, gender, allowance, phoneNumber, address, department);
    }

    
    
    public Worker() {
    }

}
