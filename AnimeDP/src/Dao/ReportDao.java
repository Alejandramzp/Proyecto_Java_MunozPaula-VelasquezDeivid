
package Dao;

import Connection.Conexion;
import Model.ReportModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReportDao {

    public List<ReportModel> getGeneralBalanceReport() {
        List<ReportModel> reportList = new ArrayList<>();
        String sql = "SELECT b.Name AS BusinessName, SUM(o.TotalValue) AS TotalSales " +
                     "FROM Business b " +
                     "LEFT JOIN Orderr o ON b.BusinessID = o.BusinessID " +
                     "GROUP BY b.BusinessID, b.Name";
        Conexion conexion = new Conexion();

        try (Connection conn = conexion.establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String businessName = rs.getString("BusinessName");
                double totalSales = rs.getDouble("TotalSales");
                reportList.add(new ReportModel(businessName, totalSales));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reportList;
    }

    public ReportModel getIndividualBalanceReport(int businessID) {
        String sql = "SELECT b.Name AS BusinessName, SUM(o.TotalValue) AS TotalSales " +
                     "FROM Business b " +
                     "LEFT JOIN Orderr o ON b.BusinessID = o.BusinessID " +
                     "WHERE b.BusinessID = ? " +
                     "GROUP BY b.BusinessID, b.Name";
        Conexion conexion = new Conexion();

        try (Connection conn = conexion.establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, businessID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String businessName = rs.getString("BusinessName");
                    double totalSales = rs.getDouble("TotalSales");
                    return new ReportModel(businessName, totalSales);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
