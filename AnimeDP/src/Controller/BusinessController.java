
package Controller;

import Dao.BusinessDao;
import Model.BusinessModel;
import java.util.List;

public class BusinessController {
    private BusinessDao businessDao;

    public BusinessController() {
        this.businessDao = new BusinessDao();
    }

    public boolean addBusiness(BusinessModel business) {
        return businessDao.addBusiness(business);
    }

    public List<BusinessModel> getAllBusinesses() {
        return businessDao.getAllBusinesses();
    }

    public BusinessModel getBusinessById(int businessID) {
        return businessDao.getBusinessById(businessID);
    }

    public boolean updateBusiness(BusinessModel business) {
        return businessDao.updateBusiness(business);
    }

    public boolean addStaffToBusiness(int staffID, int businessID) {
        return businessDao.addStaffToBusiness(staffID, businessID);
    }

    public List<Integer> getStaffByBusiness(int businessID) {
        return businessDao.getStaffByBusiness(businessID);
    }
}

