
package Controller;

import Dao.PropsDao;
import Model.PropsModel;


public class PropsController {
    
    private PropsDao propsDao;

    public PropsController() {
        this.propsDao = new PropsDao();
    }

    public boolean addProps(PropsModel props){
        return propsDao.addProps(props);
    }
    
    
  
    
}
