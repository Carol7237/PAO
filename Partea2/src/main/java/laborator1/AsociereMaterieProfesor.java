package laborator1;

public class AsociereMaterieProfesor {
    private Materie materie;
    private Profesor profesor;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AsociereMaterieProfesor(Materie materie, Profesor profesor) {
        this.materie = materie;
        this.profesor = profesor;
    }

    public Materie getMaterie() {
        return materie;
    }

    public void setMaterie(Materie materie) {
        this.materie = materie;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
