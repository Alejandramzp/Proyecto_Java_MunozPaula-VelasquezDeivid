
package Model;

public class ReportModel {
    private String businessName;
    private double totalSales;

    public ReportModel(String businessName, double totalSales) {
        this.businessName = businessName;
        this.totalSales = totalSales;
    }

    public String getBusinessName() {
        return businessName;
    }

    public double getTotalSales() {
        return totalSales;
    }
}
