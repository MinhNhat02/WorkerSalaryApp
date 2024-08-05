package workersalary.entity.implement;

import java.sql.Date;

import workersalary.entity.Employee;
import workersalary.entity.ShiftWork;
import workersalary.entity.TimeKeeping;

public class TimeKeepingStaff extends TimeKeeping {

    public TimeKeepingStaff(String id, Employee employee, Date createdDate, ShiftWork shiftWork, String status,
            boolean leavePermission) {
        super(id, employee, createdDate, shiftWork, status, leavePermission);
    }

    public TimeKeepingStaff() {
    }
    

}
