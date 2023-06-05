package laborator1;

public class ProfesorTitular extends Profesor {

    private int id;

    private String domeniuExpertiza;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProfesorTitular(String nume, String domeniuExpertiza) {
        super(nume);
        this.domeniuExpertiza = domeniuExpertiza;
    }

    public ProfesorTitular(int id, String nume, String domeniuExpertiza) {
        super(id, nume);
        this.id = id;
        this.domeniuExpertiza = domeniuExpertiza;
    }


    public String getDomeniuExpertiza() {
        return domeniuExpertiza;
    }

    public void setDomeniuExpertiza(String domeniuExpertiza) {
        this.domeniuExpertiza = domeniuExpertiza;
    }
}
