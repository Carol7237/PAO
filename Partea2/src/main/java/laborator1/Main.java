package laborator1;

import java.util.List;
import java.sql.SQLException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Selectati optiunea: ");
                System.out.println("1. Creare Materie");
                System.out.println("2. Citire Materie");
                System.out.println("3. Modificare Materie");
                System.out.println("4. Stergere Materie");
                System.out.println("5. Creare Profesor");
                System.out.println("6. Citire Profesor");
                System.out.println("7. Modificare Profesor");
                System.out.println("8. Stergere Profesor");
                System.out.println("9. Creare ProfesorTitular");
                System.out.println("10. Citire ProfesorTitular");
                System.out.println("11. Modificare ProfesorTitular");
                System.out.println("12. Stergere ProfesorTitular");
                System.out.println("13. Creare AsociereMaterieProfesor");
                System.out.println("14. Citire AsociereMaterieProfesor");
                System.out.println("15. Modificare AsociereMaterieProfesor");
                System.out.println("16. Stergere AsociereMaterieProfesor");
                System.out.println("17. Iesire");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.println("Introduceti numele materiei: ");
                        String numeMaterie = scanner.nextLine();
                        createMaterie(dbConnection, numeMaterie);
                        break;
                    case 2:
                        System.out.println("Introduceti id-ul materiei: ");
                        int idMaterie = scanner.nextInt();
                        readMaterie(dbConnection, idMaterie);
                        break;
                    case 3:
                        System.out.println("Introduceti id-ul materiei: ");
                        int idMaterieModif = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Introduceti numele nou al materiei: ");
                        String numeMaterieModif = scanner.nextLine();
                        updateMaterie(dbConnection, idMaterieModif, numeMaterieModif);
                        break;
                    case 4:
                        System.out.println("Introduceti id-ul materiei: ");
                        int idMaterieDel = scanner.nextInt();
                        deleteMaterie(dbConnection, idMaterieDel);
                        break;
                    case 5:
                        System.out.println("Introduceti numele profesorului: ");
                        String numeProfesor = scanner.nextLine();
                        createProfesor(dbConnection, numeProfesor);
                        break;
                    case 6:
                        System.out.println("Introduceti id-ul profesorului: ");
                        int idProfesor = scanner.nextInt();
                        readProfesor(dbConnection, idProfesor);
                        break;
                    case 7:
                        System.out.println("Introduceti id-ul profesorului: ");
                        int idProfesorModif = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Introduceti numele nou al profesorului: ");
                        String numeProfesorModif = scanner.nextLine();
                        updateProfesor(dbConnection, idProfesorModif, numeProfesorModif);
                        break;
                    case 8:
                        System.out.println("Introduceti id-ul profesorului: ");
                        int idProfesorDel = scanner.nextInt();
                        deleteProfesor(dbConnection, idProfesorDel);
                        break;
                    case 9:
                        System.out.println("Introduceti id-ul profesorului si domeniul de expertiza: ");
                        int idProfesorTitular = scanner.nextInt();
                        scanner.nextLine();
                        String domeniuExpertiza = scanner.nextLine();
                        createProfesorTitular(dbConnection, idProfesorTitular, domeniuExpertiza);
                        break;
                    case 10:
                        System.out.println("Introduceti id-ul Profesorului Titular: ");
                        int idProfesorTitular2 = scanner.nextInt();
                        readProfesorTitular(dbConnection, idProfesorTitular2);
                        break;
                    case 11:
                        System.out.println("Introduceti id-ul Profesorului Titular: ");
                        int idProfesorTitularModif = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Introduceti noul domeniu de expertiza: ");
                        String domeniuExpertizaModif = scanner.nextLine();
                        updateProfesorTitular(dbConnection, idProfesorTitularModif, domeniuExpertizaModif);
                        break;
                    case 12:
                        System.out.println("Introduceti id-ul Profesorului Titular: ");
                        int idProfesorTitularDel = scanner.nextInt();
                        deleteProfesorTitular(dbConnection, idProfesorTitularDel);
                        break;
                    case 13:
                        System.out.println("Introduceti id-ul materiei si id-ul profesorului: ");
                        int idMaterie2 = scanner.nextInt();
                        int idProfesor2 = scanner.nextInt();
                        createAsociere(dbConnection, idMaterie2, idProfesor2);
                        break;
                    case 14:
                        System.out.println("Introduceti id-ul Asocierei: ");
                        int idAsociere = scanner.nextInt();
                        readAsociere(dbConnection, idAsociere);
                        break;
                    case 15:
                        System.out.println("Introduceti id-ul Asocierei: ");
                        int idAsociereModif = scanner.nextInt();
                        System.out.println("Introduceti id-urile noi ale materiei si profesorului: ");
                        int idMaterieModif2 = scanner.nextInt();
                        int idProfesorModif2 = scanner.nextInt();
                        updateAsociere(dbConnection, idAsociereModif, idMaterieModif2, idProfesorModif2);
                        break;
                    case 16:
                        System.out.println("Introduceti id-ul Asocierei: ");
                        int idAsociereDel = scanner.nextInt();
                        deleteAsociere(dbConnection, idAsociereDel);
                        break;
                    case 17:
                        return;
                    default:
                        System.out.println("Optiune invalida, va rugam sa selectati din nou");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createMaterie(DatabaseConnection dbConnection, String numeMaterie) throws SQLException {
        MaterieDao materieDao = MaterieDao.getInstance(dbConnection);
        Materie materie = new Materie(numeMaterie);
        materie = materieDao.create(materie);
        AuditService.getInstance().logEvent("Materie creata: " + materie.getNume());
    }

    private static void readMaterie(DatabaseConnection dbConnection, int idMaterie) throws SQLException {
        MaterieDao materieDao = MaterieDao.getInstance(dbConnection);
        Materie materieCitita = materieDao.read(idMaterie);
        AuditService.getInstance().logEvent("Materie citita: " + materieCitita.getNume());
    }

    private static void updateMaterie(DatabaseConnection dbConnection, int idMaterie, String numeNou) throws SQLException {
        MaterieDao materieDao = MaterieDao.getInstance(dbConnection);
        Materie materieCitita = materieDao.read(idMaterie);
        materieCitita.setNume(numeNou);
        materieDao.update(materieCitita);
        AuditService.getInstance().logEvent("Materie modificata: " + materieCitita.getNume());
    }

    private static void deleteMaterie(DatabaseConnection dbConnection, int idMaterie) throws SQLException {
        MaterieDao materieDao = MaterieDao.getInstance(dbConnection);
        Materie materieCitita = materieDao.read(idMaterie);
        materieDao.delete(materieCitita);
        AuditService.getInstance().logEvent("Materie stearsa: " + materieCitita.getNume());
    }
    private static void createProfesor(DatabaseConnection dbConnection, String numeProfesor) throws SQLException {
        ProfesorDao profesorDao = ProfesorDao.getInstance(dbConnection);
        Profesor profesor = new Profesor(numeProfesor);
        profesor = profesorDao.create(profesor);
        AuditService.getInstance().logEvent("Profesor creat: " + profesor.getNume());
    }

    private static void readProfesor(DatabaseConnection dbConnection, int idProfesor) throws SQLException {
        ProfesorDao profesorDao = ProfesorDao.getInstance(dbConnection);
        Profesor profesorCitit = profesorDao.read(idProfesor);
        AuditService.getInstance().logEvent("Profesor citit: " + profesorCitit.getNume());
    }

    private static void updateProfesor(DatabaseConnection dbConnection, int idProfesor, String numeNou) throws SQLException {
        ProfesorDao profesorDao = ProfesorDao.getInstance(dbConnection);
        Profesor profesorCitit = profesorDao.read(idProfesor);
        profesorCitit.setNume(numeNou);
        profesorDao.update(profesorCitit);
        AuditService.getInstance().logEvent("Profesor modificat: " + profesorCitit.getNume());
    }

    private static void deleteProfesor(DatabaseConnection dbConnection, int idProfesor) throws SQLException {
        ProfesorDao profesorDao = ProfesorDao.getInstance(dbConnection);
        Profesor profesorCitit = profesorDao.read(idProfesor);
        profesorDao.delete(profesorCitit);
        AuditService.getInstance().logEvent("Profesor sters: " + profesorCitit.getNume());
    }
    private static void createProfesorTitular(DatabaseConnection dbConnection, int idProfesor, String domeniuExpertiza) throws SQLException {
        ProfesorTitularDao profesorTitularDao = ProfesorTitularDao.getInstance(dbConnection);
        ProfesorDao profesorDao = ProfesorDao.getInstance(dbConnection);
        Profesor profesorCitit = profesorDao.read(idProfesor);

        if (profesorCitit != null) {
            ProfesorTitular profesorTitular = new ProfesorTitular(profesorCitit.getId(), profesorCitit.getNume(), domeniuExpertiza);
            profesorTitular = profesorTitularDao.create(profesorTitular);
            AuditService.getInstance().logEvent("ProfesorTitular creat: " + profesorTitular.getNume());
        } else {
            System.out.println("Profesorul cu id-ul " + idProfesor + " nu exista in baza de date.");
        }
    }

    private static void readProfesorTitular(DatabaseConnection dbConnection, int idProfesorTitular) throws SQLException {
        ProfesorTitularDao profesorTitularDao = ProfesorTitularDao.getInstance(dbConnection);
        ProfesorTitular profesorTitularCitit = profesorTitularDao.read(idProfesorTitular);

        if (profesorTitularCitit != null) {
            System.out.println("Profesorul titular citit din baza de date: " + profesorTitularCitit.getNume() + ", " + profesorTitularCitit.getDomeniuExpertiza());
            AuditService.getInstance().logEvent("ProfesorTitular citit: " + profesorTitularCitit.getNume() + ", " + profesorTitularCitit.getDomeniuExpertiza());
        } else {
            System.out.println("Profesorul titular cu id-ul " + idProfesorTitular + " nu exista in baza de date.");
        }
    }

    private static void updateProfesorTitular(DatabaseConnection dbConnection, int idProfesorTitular, String domeniuExpertiza) throws SQLException {
        ProfesorTitularDao profesorTitularDao = ProfesorTitularDao.getInstance(dbConnection);
        ProfesorTitular profesorTitularCitit = profesorTitularDao.read(idProfesorTitular);

        if (profesorTitularCitit != null) {
            profesorTitularCitit.setDomeniuExpertiza(domeniuExpertiza);
            profesorTitularDao.update(profesorTitularCitit);
            AuditService.getInstance().logEvent("ProfesorTitular modificat: " + profesorTitularCitit.getNume() + ", " + profesorTitularCitit.getDomeniuExpertiza());
        } else {
            System.out.println("Profesorul titular cu id-ul " + idProfesorTitular + " nu exista in baza de date.");
        }
    }

    private static void deleteProfesorTitular(DatabaseConnection dbConnection, int idProfesorTitular) throws SQLException {
        ProfesorTitularDao profesorTitularDao = ProfesorTitularDao.getInstance(dbConnection);
        ProfesorTitular profesorTitularCitit = profesorTitularDao.read(idProfesorTitular);

        if (profesorTitularCitit != null) {
            profesorTitularDao.delete(profesorTitularCitit);
            AuditService.getInstance().logEvent("ProfesorTitular sters: " + profesorTitularCitit.getNume() + ", " + profesorTitularCitit.getDomeniuExpertiza());
        } else {
            System.out.println("Profesorul titular cu id-ul " + idProfesorTitular + " nu exista in baza de date.");
        }
    }
    private static void createAsociere(DatabaseConnection dbConnection, int idMaterie, int idProfesor) throws SQLException {
        MaterieDao materieDao = MaterieDao.getInstance(dbConnection);
        ProfesorDao profesorDao = ProfesorDao.getInstance(dbConnection);
        Materie materie = materieDao.read(idMaterie);
        Profesor profesor = profesorDao.read(idProfesor);

        if (materie != null && profesor != null) {
            AsociereMaterieProfesorDao asociereDao = AsociereMaterieProfesorDao.getInstance(dbConnection, materieDao, profesorDao);
            AsociereMaterieProfesor asociere = new AsociereMaterieProfesor(materie, profesor);
            asociere = asociereDao.create(asociere);
            AuditService.getInstance().logEvent("AsociereMaterieProfesor creata: " + asociere.getId());
        } else {
            System.out.println("Materia sau profesorul cu id-ul specificat nu exista in baza de date.");
        }
    }

    private static void readAsociere(DatabaseConnection dbConnection, int idAsociere) throws SQLException {
        MaterieDao materieDao = MaterieDao.getInstance(dbConnection);
        ProfesorDao profesorDao = ProfesorDao.getInstance(dbConnection);
        AsociereMaterieProfesorDao asociereDao = AsociereMaterieProfesorDao.getInstance(dbConnection, materieDao, profesorDao);
        AsociereMaterieProfesor asociereCitita = asociereDao.read(idAsociere);

        if (asociereCitita != null) {
            System.out.println("Asocierea citită din baza de date: " + asociereCitita.getProfesor().getNume() + ", " + asociereCitita.getMaterie().getNume());
            AuditService.getInstance().logEvent("AsociereMaterieProfesor citita: " + asociereCitita.getId());
        } else {
            System.out.println("Asocierea cu id-ul specificat nu exista in baza de date.");
        }
    }

    private static void updateAsociere(DatabaseConnection dbConnection, int idAsociere, int idMaterie, int idProfesor) throws SQLException {
        MaterieDao materieDao = MaterieDao.getInstance(dbConnection);
        ProfesorDao profesorDao = ProfesorDao.getInstance(dbConnection);
        Materie materie = materieDao.read(idMaterie);
        Profesor profesor = profesorDao.read(idProfesor);

        if (materie != null && profesor != null) {
            AsociereMaterieProfesorDao asociereDao = AsociereMaterieProfesorDao.getInstance(dbConnection, materieDao, profesorDao);
            AsociereMaterieProfesor asociereCitita = asociereDao.read(idAsociere);

            if (asociereCitita != null) {
                asociereCitita.setMaterie(materie);
                asociereCitita.setProfesor(profesor);
                asociereDao.update(asociereCitita);
                AuditService.getInstance().logEvent("AsociereMaterieProfesor modificata: " + asociereCitita.getId());
            } else {
                System.out.println("Asocierea cu id-ul specificat nu exista in baza de date.");
            }
        } else {
            System.out.println("Materia sau profesorul cu id-ul specificat nu exista in baza de date.");
        }
    }

    private static void deleteAsociere(DatabaseConnection dbConnection, int idAsociere) throws SQLException {
        MaterieDao materieDao = MaterieDao.getInstance(dbConnection);
        ProfesorDao profesorDao = ProfesorDao.getInstance(dbConnection);
        AsociereMaterieProfesorDao asociereDao = AsociereMaterieProfesorDao.getInstance(dbConnection, materieDao, profesorDao);
        AsociereMaterieProfesor asociereCitita = asociereDao.read(idAsociere);

        if (asociereCitita != null) {
            asociereDao.delete(asociereCitita);
            AuditService.getInstance().logEvent("AsociereMaterieProfesor ștearsa: " + asociereCitita.getId());
        } else {
            System.out.println("Asocierea cu id-ul specificat nu exista in baza de date.");
        }
    }
}
