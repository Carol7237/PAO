package laborator1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterieDao extends GenericDao<Materie> {

    private static MaterieDao instance = null;

    private MaterieDao(DatabaseConnection databaseConnection) {
        super(databaseConnection);
    }

    public static MaterieDao getInstance(DatabaseConnection databaseConnection) {
        if (instance == null) {
            instance = new MaterieDao(databaseConnection);
        }
        return instance;
    }

    @Override
    public Materie create(Materie materie) {
        String sql = "INSERT INTO Materii (nume) VALUES (?)";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql, new String[] {"ID"})) {
            pstmt.setString(1, materie.getNume());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                materie.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materie;
    }


    @Override
    public Materie read(int id) {
        Materie materie = null;
        String sql = "SELECT nume FROM Materii WHERE id = ?";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nume = rs.getString("nume");
                materie = new Materie(nume);
                materie.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materie;
    }


    @Override
    public void update(Materie materie) {
        String sql = "UPDATE Materii SET nume = ? WHERE id = ?";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, materie.getNume());
            pstmt.setInt(2, materie.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Materie materie) {
        String sql = "DELETE FROM Materii WHERE id = ?";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, materie.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
