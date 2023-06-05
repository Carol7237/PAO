package laborator1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesorDao extends GenericDao<Profesor> {
    private static ProfesorDao instance = null;

    private ProfesorDao(DatabaseConnection databaseConnection) {
        super(databaseConnection);
    }

    public static ProfesorDao getInstance(DatabaseConnection databaseConnection) {
        if (instance == null) {
            instance = new ProfesorDao(databaseConnection);
        }
        return instance;
    }

    @Override
    public Profesor create(Profesor profesor) {
        String sql = "INSERT INTO Profesori (nume) VALUES (?)";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql, new String[] {"ID"})) {
            pstmt.setString(1, profesor.getNume());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                profesor.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profesor;
    }

    @Override
    public Profesor read(int id) {
        Profesor profesor = null;
        String sql = "SELECT nume FROM Profesori WHERE id = ?";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nume = rs.getString("nume");
                profesor = new Profesor(id, nume);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profesor;
    }

    @Override
    public void update(Profesor profesor) {
        String sql = "UPDATE Profesori SET nume = ? WHERE id = ?";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, profesor.getNume());
            pstmt.setInt(2, profesor.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Profesor profesor) {
        String sql = "DELETE FROM Profesori WHERE id = ?";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, profesor.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
