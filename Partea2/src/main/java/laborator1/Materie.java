package laborator1;

public class Materie {
    private int id;
    private String nume;

    public Materie(String nume) {
        this.nume = nume;
    }

    public Materie(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Materie{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                '}';
    }
}
