
package Controller;

import Dao.PropsDao;
import Model.PropsModel;
import java.util.List;
import java.util.Scanner;


public class PropsController {
    
    private PropsDao propsDao;
    private Scanner scanner;


    public PropsController() {
        this.propsDao = new PropsDao();
        this.scanner = new Scanner(System.in);
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
   
   public PropsModel getPropsById(int propsId){
       return propsDao.getPropsById(propsId);
   }
   
   public void updatePropsStatus(){
       
       try {
            System.out.print("Ingrese el ID de la utilería a actualizar: ");
            int propsId = scanner.nextInt();
            scanner.nextLine(); 

            PropsModel props = propsDao.getPropsById(propsId);
            if (props == null) {
                System.out.println("Utilería con ID " + propsId + " no encontrado.");
                return;
            }

            System.out.println("\n-----------------------------------------");
            System.out.println(" Estado actual de la utilería: " + props.getStatus());
            System.out.println("-----------------------------------------");

            System.out.println("\n-----------------------------------------");
            System.out.println("  Ingrese el nuevo estado de la utilería:  ");
            System.out.println("                                           ");
            System.out.println("    1. En el sitio                         ");
            System.out.println("    2. En el almacen                       ");
            System.out.println("      Seleccione una opción:               ");
            System.out.println("-------------------------------------------");

            int optionS;
            String newStatus = "";

            while (true) {
                try {
                    optionS = scanner.nextInt();
                    scanner.nextLine();
                    if (optionS == 1){
                        newStatus = "En sitio"; 
                        break;
                    }else if(optionS == 2){
                        newStatus = "En almacen";
                        break;
                    }else{
                        System.out.println("Opción no valida. Por favor, seleccione 1 o 2.");
                    }
                }catch (Exception e) {
                    System.out.println("Entrada no valida. Por favor, ingrese un número entero.");
                    scanner.next();
                }
            }

            props.setStatus(newStatus);
            if(propsDao.updatePropsStatus(props)){
                System.out.println("Estado de la utilería actualizado exitosamente a " + newStatus + ".");
            }else {
                System.out.println("Error al actualizar el estado de la utilería . Por favor, intente de nuevo.");
            }
        }catch (Exception e) {
        System.out.println("Se produjo un error al intentar actualizar el estado de la utilería: " + e.getMessage());
        }
   }
   
   public void updatePropsQuantity(){
       try{
        System.out.print("Ingrese el ID de la utilería a actualizar: ");
         int propsId = scanner.nextInt();
         scanner.nextLine(); 

         PropsModel props = propsDao.getPropsById(propsId);
         if (props == null) {
             System.out.println("Utilería con ID " + propsId + " no encontrado.");
             return;
         }

         System.out.println("\n-----------------------------------------");
         System.out.println(" Cantidad actual de la utilería: " + props.getQuantity());
         System.out.println("-----------------------------------------");

         System.out.println("\n-----------------------------------------");
         System.out.println("   Ingrese la nueva cantidad de utilería:  ");
         System.out.println("-------------------------------------------");

         int newQuantity;
         newQuantity = scanner.nextInt();

         props.setQuantity(newQuantity);
         if(propsDao.updatePropsQuantity(props)){
             System.out.println("\nLa cantidad de la utilería actualizada exitosamente a " + newQuantity + ".");
         }else {
             System.out.println("Error al actualizar la cantidad de la utilería. Por favor, intente de nuevo.");
         } 
       }catch (Exception e) {
        System.out.println("Se produjo un error al intentar actualizar la cantidad de la utilería: " + e.getMessage());
        } 
   }
   
   public void deletePropsById() {
        System.out.print("Ingrese el ID de la utilería a eliminar: ");
        int propsId = scanner.nextInt();
        scanner.nextLine(); 

        PropsModel props = propsDao.getPropsById(propsId);
        if (props == null) {
            System.out.println("Utilería con ID " + propsId + " no encontrada.");
            return;
        }

        System.out.println("¿Está seguro que desea eliminar la utilería con ID " + propsId + " (Si/No)?");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("Si")) {
            boolean deleted = propsDao.deletePropsById(propsId);
            if (deleted) {
                System.out.println("Utilería eliminada exitosamente.");
            } else {
                System.out.println("Error al eliminar la utilería. Por favor, intente de nuevo.");
            }
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }
}
