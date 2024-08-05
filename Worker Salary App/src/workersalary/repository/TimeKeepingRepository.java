package workersalary.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import workersalary.dao.TimeKeepingDAO;
import workersalary.entity.TimeKeeping;
import workersalary.entity.implement.AdministrativeStaff;
import workersalary.entity.implement.Worker;

public class TimeKeepingRepository {

    private static TimeKeepingRepository instance;

    public static TimeKeepingRepository getInstance() {
        if (instance == null) {
            instance = new TimeKeepingRepository();
        }
        return instance;
    }
    private TimeKeepingDAO dao;
    private List<TimeKeeping> list;

    private TimeKeepingRepository() {
        dao = new TimeKeepingDAO();
        getAll();
    }

    public List<TimeKeeping> getAll() {
        try {
            list = dao.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    public TimeKeeping getById(String id) {
        if (list != null && list.size() > 0) {
            for (TimeKeeping timeKeeping : list) {
                if (timeKeeping.getId().equals(id)) {
                    return timeKeeping;
                }
            }
        }
        return null;
    }

    public void calculateSalary(AdministrativeStaff employee, double dayWork) {
        double basicSalary = 0;
        basicSalary = ((AdministrativeStaff) employee).getBasicSalary();
        double salary = ((basicSalary + employee.getAllowance()) / 26) * dayWork;
        employee.setSalary(salary);
    }

    public void calculateSalary(Worker worker, double dayWork, Date curMonth) {
        double salary = 0;
        salary = ((countSalaryByShift(worker.getId(), curMonth) + worker.getAllowance()) / 26) * dayWork;
        worker.setSalary(salary);
    }

    private double countSalaryByShift(String employeeId, Date date) {
        if (list != null && list.size() > 0) {
            double salary = 0.5f;
            for (TimeKeeping timeKeeping : list) {
                if (timeKeeping.getEmployee().getId().equals(employeeId)
                        && timeKeeping.getCreatedDate().getMonth() == date.getMonth() && timeKeeping.getCreatedDate().getYear() == date.getYear()
                        && timeKeeping.getStatus().equalsIgnoreCase("có mặt")) {
                    salary += timeKeeping.getShiftWork().getSalary();
                }
            }
            return salary;
        }
        return -1;
    }

    public double countDayWork(String employeeId, Date date) {
        if (list != null && list.size() > 0) {
            double totalDay = 0.5f;
            for (TimeKeeping timeKeeping : list) {
                if (timeKeeping.getEmployee().getId().equals(employeeId)
                        && timeKeeping.getCreatedDate().getMonth() == date.getMonth() && 
                        timeKeeping.getCreatedDate().getYear() == date.getYear()
                        && timeKeeping.getStatus().equalsIgnoreCase("có mặt")) {
                    totalDay += 0.5f;
                }
            }
            return totalDay;
        }
        return -1;
    }

    public TimeKeeping checkTimeKeeping(String employeeId, Date date, String shiftWorkId) {
        if (list != null && list.size() > 0) {
            for (TimeKeeping timeKeeping : list) {
                if (timeKeeping.getEmployee().getId().equals(employeeId)
                        && timeKeeping.getCreatedDate().toString().equalsIgnoreCase(date.toString()) && timeKeeping.getShiftWork().getId().equals(shiftWorkId)) {
                    return timeKeeping;
                }
            }
        }
        return null;
    }

    public boolean add(TimeKeeping employee) {
        if (getById(employee.getId()) == null) {
            if (dao.add(employee)) {
                list.add(employee);
                return true;
            }
        }
        return false;
    }

    public boolean update(TimeKeeping timeKeeping) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        TimeKeeping timeKeepingInList = getById(timeKeeping.getId());
        if (timeKeepingInList != null) {
            if (dao.update(timeKeeping)) {
                int index = list.indexOf(timeKeepingInList);
                list.remove(timeKeepingInList);
                list.add(index, timeKeeping);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        TimeKeeping timeKeeping = getById(id);
        if (timeKeeping != null) {
            if (dao.delete(id)) {
                list.remove(timeKeeping);
                return true;
            }
        }
        return false;
    }

    public List<TimeKeeping> getList() {
        return list;
    }

}
