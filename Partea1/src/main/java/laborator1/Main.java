package laborator1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ListaMaterii listaMaterii = new ListaMaterii();
        ListaProfesori listaProfesori = new ListaProfesori();

        Materie mate = new Materie("Matematica");
        Materie info = new Materie("Informatica");
        listaMaterii.adaugaMaterie(mate);
        listaMaterii.adaugaMaterie(info);

        Profesor profesor1 = new Profesor("Ana Popescu");
        ProfesorTitular profesor2 = new ProfesorTitular("Ion Ionescu", "Informatica");
        listaProfesori.adaugaProfesor(profesor1);
        listaProfesori.adaugaProfesor(profesor2);

        ServiciuCatalog serviciuCatalog = new ServiciuCatalog(listaMaterii, listaProfesori);

        serviciuCatalog.atribuieProfesorLaMaterie(profesor1, mate);
        serviciuCatalog.atribuieProfesorLaMaterie(profesor2, info);

        List<Materie> materiiPredatDeProfesor1 = serviciuCatalog.materiiPredateDeProfesor(profesor1);
        System.out.println("Materii predate de Profesor1: " + materiiPredatDeProfesor1);

        List<Profesor> profesoriCarePredauMate = serviciuCatalog.profesoriCarePredauMateria(mate);
        System.out.println("Profesori care predau Matematica: " + profesoriCarePredauMate);

        String domeniuExpertizaProfesor2 = ((ProfesorTitular) profesor2).getDomeniuExpertiza();
        System.out.println("Domeniul de expertiza al Profesorului 2: " + domeniuExpertizaProfesor2);

        serviciuCatalog.eliminaAtribuireProfesorLaMaterie(profesor1, mate);
        materiiPredatDeProfesor1 = serviciuCatalog.materiiPredateDeProfesor(profesor1);
        System.out.println("Materii predate de Profesor1 dupa eliminare: " + materiiPredatDeProfesor1);
    }
}

