package laborator1;

public class Materie {
    private String nume;

    public Materie(String nume) {
        this.nume = nume;
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
                "nume='" + nume + '\'' +
                '}';
    }

}