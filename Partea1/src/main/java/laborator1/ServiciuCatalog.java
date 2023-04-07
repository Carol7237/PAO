package laborator1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiciuCatalog {
    private ListaMaterii listaMaterii;
    private ListaProfesori listaProfesori;
    private Map<Profesor, List<Materie>> profesorMaterii;

    public ServiciuCatalog(ListaMaterii listaMaterii, ListaProfesori listaProfesori) {
        this.listaMaterii = listaMaterii;
        this.listaProfesori = listaProfesori;
        this.profesorMaterii = new HashMap<>();
    }

    public void atribuieProfesorLaMaterie(Profesor profesor, Materie materie) {
        if (!profesorMaterii.containsKey(profesor)) {
            profesorMaterii.put(profesor, new ArrayList<>());
        }
        profesorMaterii.get(profesor).add(materie);
    }

    public void eliminaAtribuireProfesorLaMaterie(Profesor profesor, Materie materie) {
        if (profesorMaterii.containsKey(profesor)) {
            profesorMaterii.get(profesor).remove(materie);
        }
    }

    public List<Materie> materiiPredateDeProfesor(Profesor profesor) {
        return profesorMaterii.getOrDefault(profesor, new ArrayList<>());
    }

    public List<Profesor> profesoriCarePredauMateria(Materie materie) {
        List<Profesor> profesori = new ArrayList<>();
        for (Map.Entry<Profesor, List<Materie>> entry : profesorMaterii.entrySet()) {
            if (entry.getValue().contains(materie)) {
                profesori.add(entry.getKey());
            }
        }
        return profesori;
    }
}
