package laborator1;

public class AsociereMaterieProfesor {
    private Materie materie;
    private Profesor profesor;

    public AsociereMaterieProfesor(Materie materie, Profesor profesor) {
        this.materie = materie;
        this.profesor = profesor;
    }

    public Materie getMaterie() {
        return materie;
    }

    public Profesor getProfesor() {
        return profesor;
    }
}