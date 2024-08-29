
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
    
    
}
