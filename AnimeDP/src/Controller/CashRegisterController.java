
package Controller;

import Dao.CashRegisterDao;
import Model.CashRegisterModel;
import java.util.List;


public class CashRegisterController {
    private CashRegisterDao cashRegisterDao;

    public CashRegisterController() {
        this.cashRegisterDao = new CashRegisterDao();
    }
    
    public boolean addCashRegister(CashRegisterModel CashRegister){
        return cashRegisterDao.addCashRegister(CashRegister);
    }
    
    public boolean isStaffExists(int id) {
        return cashRegisterDao.isStaffExists(id);
    }
    
    public List<CashRegisterModel> getAllCashRegisterModel(){
       return cashRegisterDao.getAllCashRegister();
    }
   
    public CashRegisterModel getCashRegisterById(int cashRegisterID) {
       return cashRegisterDao.getCashRegisterById(cashRegisterID);
    }
    
    public boolean updateCashRegister(CashRegisterModel cashRegister) {
        return cashRegisterDao.updateCashRegister(cashRegister);
    }

    public boolean activateCashRegister(int cashRegisterID, double openingAmount, int businessStaffID) {
        CashRegisterModel cashRegister = getCashRegisterById(cashRegisterID);

        if (cashRegister != null && "Inactivo".equals(cashRegister.getStatus())) {
            cashRegister.setOpeningAmount(openingAmount);
            cashRegister.setBusinessStaffID(businessStaffID);
            cashRegister.setStatus("Activo");
            return updateCashRegister(cashRegister);
        }
        return false;
    }

    public boolean desactivateCashRegister(int cashRegisterID, double closingAmount) {
        CashRegisterModel cashRegister = getCashRegisterById(cashRegisterID);

        if (cashRegister != null && "Activo".equals(cashRegister.getStatus())) {
            cashRegister.setClosingAmount(closingAmount);
            cashRegister.setBusinessStaffID(0);  // Desasigna el personal
            cashRegister.setStatus("Inactivo");
            return updateCashRegister(cashRegister);
        }
        return false;
    }
}
