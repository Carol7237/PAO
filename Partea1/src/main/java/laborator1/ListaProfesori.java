package laborator1;

import java.util.ArrayList;
import java.util.List;

public class ListaProfesori {
    private List<Profesor> profesori = new ArrayList<>();

    public void adaugaProfesor(Profesor profesor) {
        profesori.add(profesor);
    }

    public void stergeProfesor(Profesor profesor) {
        profesori.remove(profesor);
    }

    public List<Profesor> getProfesori() {
        return profesori;
    }
}