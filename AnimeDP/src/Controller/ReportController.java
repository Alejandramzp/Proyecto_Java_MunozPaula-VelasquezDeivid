
package Controller;


import Dao.ReportDao;
import Model.ReportModel;

import java.util.List;

public class ReportController {
    private ReportDao reportDao;

    public ReportController() {
        this.reportDao = new ReportDao();
    }

    public List<ReportModel> getGeneralBalanceReport() {
        return reportDao.getGeneralBalanceReport();
    }

    public ReportModel getIndividualBalanceReport(int businessID) {
        return reportDao.getIndividualBalanceReport(businessID);
    }
}
