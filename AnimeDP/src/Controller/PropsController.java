
package Controller;

import Dao.PropsDao;
import Model.PropsModel;
import java.util.List;


public class PropsController {
    
    private PropsDao propsDao;

    public PropsController() {
        this.propsDao = new PropsDao();
    }

    public boolean addProps(PropsModel props){
        return propsDao.addProps(props);
    }
    
    public boolean isEventExists(int id) {
        return propsDao.isEventExists(id);
    }
  
   public List<PropsModel> getAllProps(){
       return propsDao.getAllProps();
   }
}
