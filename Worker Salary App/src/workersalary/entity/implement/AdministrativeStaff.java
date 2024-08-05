package workersalary.entity.implement;

import java.sql.Date;

import workersalary.entity.Department;
import workersalary.entity.Employee;

public class AdministrativeStaff extends Employee {

    private double basicSalary;

    public AdministrativeStaff(String id, String fullName, String indetifyCard, Date dateOfBirth, String gender, double salary, double basicSalary, double allowance,
            String phoneNumber, String address, Department department) {
        super(id, fullName, indetifyCard, dateOfBirth, gender, salary, allowance, phoneNumber, address, department);
        this.basicSalary = basicSalary;
    }

    public AdministrativeStaff(double basicSalary, String id, String fullName, String identifyCard, 
            Date dateOfBirth, String gender, double allowance, String phoneNumber, String address, Department department) {
        super(id, fullName, identifyCard, dateOfBirth, gender, allowance, phoneNumber, address, department);
        this.basicSalary = basicSalary;
    }
    

    public AdministrativeStaff() {
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

}
