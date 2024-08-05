package workersalary.entity;

import java.sql.Date;

public abstract class Employee {

    protected String id;
    protected String fullName;
    protected String identifyCard;
    protected Date dateOfBirth;
    protected String gender;
    protected double salary;
    protected double allowance;
    protected String phoneNumber;
    protected String address;
    protected Department department;

    public Employee(String id, String fullName, String identifyCard, Date dateOfBirth, String gender, double salary, double allowance, String phoneNumber, String address, Department department) {
        this.id = id;
        this.fullName = fullName;
        this.identifyCard = identifyCard;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.salary = salary;
        this.allowance = allowance;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.department = department;
    }

    public Employee(String id, String fullName, String identifyCard, Date dateOfBirth, String gender, double allowance, String phoneNumber, String address, Department department) {
        this.id = id;
        this.fullName = fullName;
        this.identifyCard = identifyCard;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.allowance = allowance;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.department = department;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", fullName=" + fullName + ", identifyCard=" + identifyCard + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", salary=" + salary + ", allowance=" + allowance + ", phoneNumber=" + phoneNumber + ", address=" + address + ", department=" + department + '}';
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdentifyCard() {
        return identifyCard;
    }

    public void setIdentifyCard(String indetifyCard) {
        this.identifyCard = indetifyCard;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
