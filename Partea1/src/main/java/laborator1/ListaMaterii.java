package laborator1;

import java.util.ArrayList;
import java.util.List;

public class ListaMaterii {
    private List<Materie> materii = new ArrayList<>();

    public void adaugaMaterie(Materie materie) {
        materii.add(materie);
    }

    public void stergeMaterie(Materie materie) {
        materii.remove(materie);
    }

    public List<Materie> getMaterii() {
        return materii;
    }
}
