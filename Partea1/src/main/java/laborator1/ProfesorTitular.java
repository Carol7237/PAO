package laborator1;

public class ProfesorTitular extends Profesor {
    private String domeniuExpertiza;

    public ProfesorTitular(String nume, String domeniuExpertiza) {
        super(nume);
        this.domeniuExpertiza = domeniuExpertiza;
    }

    public String getDomeniuExpertiza() {
        return domeniuExpertiza;
    }

    public void setDomeniuExpertiza(String domeniuExpertiza) {
        this.domeniuExpertiza = domeniuExpertiza;
    }
}

