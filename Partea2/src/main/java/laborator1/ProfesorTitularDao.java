package laborator1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesorTitularDao extends GenericDao<ProfesorTitular> {
    private static ProfesorTitularDao instance = null;

    private ProfesorTitularDao(DatabaseConnection databaseConnection) {
        super(databaseConnection);
    }

    public static ProfesorTitularDao getInstance(DatabaseConnection databaseConnection) {
        if (instance == null) {
            instance = new ProfesorTitularDao(databaseConnection);
        }
        return instance;
    }

    @Override
    public ProfesorTitular create(ProfesorTitular profesor) {
        String sql = "INSERT INTO ProfesorTitular (id_profesor, nume, domeniu_expertiza) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, profesor.getId());
            pstmt.setString(2, profesor.getNume());
            pstmt.setString(3, profesor.getDomeniuExpertiza());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profesor;
    }


    @Override
    public ProfesorTitular read(int id) {
        ProfesorTitular profesor = null;
        String sql = "SELECT * FROM ProfesorTitular WHERE id_profesor = ?";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nume = rs.getString("nume");
                String domeniuExpertiza = rs.getString("domeniu_expertiza");
                profesor = new ProfesorTitular(id, nume, domeniuExpertiza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profesor;
    }


    @Override
    public void update(ProfesorTitular profesor) {
        String sql = "UPDATE ProfesorTitular SET domeniu_expertiza = ? WHERE id_profesor = ?";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, profesor.getDomeniuExpertiza());
            pstmt.setInt(2, profesor.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ProfesorTitular profesor) {
        String sql = "DELETE FROM ProfesorTitular WHERE id_profesor = ?";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, profesor.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
