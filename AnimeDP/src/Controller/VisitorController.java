
package Controller;

import Dao.VisitorDao;
import Model.VisitorModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VisitorController {
    private VisitorDao visitorDao;
    private static final String[] GENDERS = {"Hombre", "Mujer", "Otros"};
    private static final String[] STATUSES = {"Participa", "No Participa", "Ganador"};

    public VisitorController() {
        this.visitorDao = new VisitorDao();
    }

    public void createVisitor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del visitante:");
        String name = scanner.nextLine();

        System.out.println("Ingrese el documento de identificación del visitante:");
        String identificationDocument = scanner.nextLine();

        System.out.println("Seleccione el género del visitante:");
        for (int i = 0; i < GENDERS.length; i++) {
            System.out.println((i + 1) + ". " + GENDERS[i]);
        }
        int genderChoice = scanner.nextInt();
        String gender = (genderChoice > 0 && genderChoice <= GENDERS.length) ? GENDERS[genderChoice - 1] : "Otros";

        System.out.println("Ingrese la fecha de nacimiento (YYYY-MM-DD):");
        scanner.nextLine();  // Consume the newline character
        String dateOfBirthStr = scanner.nextLine();
        Date dateOfBirth = null;
        try {
            dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirthStr);
        } catch (ParseException e) {
            System.out.println("Formato de fecha incorrecto. Se usará la fecha actual.");
            dateOfBirth = new Date();
        }

        System.out.println("Ingrese el correo electrónico del visitante (opcional):");
        String email = scanner.nextLine();

        System.out.println("Ingrese el número de teléfono del visitante (opcional):");
        String phoneNumber = scanner.nextLine();

        System.out.println("Seleccione el estado del visitante:");
        for (int i = 0; i < STATUSES.length; i++) {
            System.out.println((i + 1) + ". " + STATUSES[i]);
        }
        int statusChoice = scanner.nextInt();
        String status = (statusChoice > 0 && statusChoice <= STATUSES.length) ? STATUSES[statusChoice - 1] : "No Participa";

        VisitorModel visitor = new VisitorModel(0, name, identificationDocument, gender, dateOfBirth, email, phoneNumber, status);
        if (visitorDao.addVisitor(visitor)) {
            System.out.println("Visitante añadido exitosamente.");
        } else {
            System.out.println("Error al añadir el visitante.");
        }
    }

    public void listVisitors() {
        List<VisitorModel> visitors = visitorDao.getAllVisitors();
        visitors.forEach(visitor -> {
            System.out.println("\n-----------------------------------------");
            System.out.println(" ID: " + visitor.getVisitorID());
            System.out.println(" Nombre: " + visitor.getName());
            System.out.println(" Documento de Identificación: " + visitor.getIdentificationDocument());
            System.out.println(" Género: " + visitor.getGender());
            System.out.println(" Fecha de Nacimiento: " + visitor.getDateOfBirth());
            System.out.println(" Correo Electrónico: " + visitor.getEmail());
            System.out.println(" Número de Teléfono: " + visitor.getPhoneNumber());
            System.out.println(" Estado: " + visitor.getStatus());
            System.out.println("-----------------------------------------");
        });
    }
}

