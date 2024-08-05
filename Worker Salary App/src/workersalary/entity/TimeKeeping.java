package workersalary.entity;

import java.sql.Date;

public abstract class TimeKeeping {

    protected String id;
    protected Employee employee;
    protected Date createdDate;
    protected ShiftWork shiftWork;
    protected String status;
    protected boolean leavePermission;

    public TimeKeeping(String id, Employee employee, Date createdDate, ShiftWork shiftWork, String status,
            boolean leavePermission) {
        this.id = id;
        this.employee = employee;
        this.createdDate = createdDate;
        this.shiftWork = shiftWork;
        this.status = status;
        this.leavePermission = leavePermission;
    }

    public TimeKeeping() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public ShiftWork getShiftWork() {
        return shiftWork;
    }

    public void setShiftWork(ShiftWork shiftWork) {
        this.shiftWork = shiftWork;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isLeavePermission() {
        return leavePermission;
    }

    public void setLeavePermission(boolean leavePermission) {
        this.leavePermission = leavePermission;
    }

}
