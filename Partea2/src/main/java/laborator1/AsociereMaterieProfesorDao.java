package laborator1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AsociereMaterieProfesorDao extends GenericDao<AsociereMaterieProfesor> {
    private MaterieDao materieDao;
    private ProfesorDao profesorDao;


    private static AsociereMaterieProfesorDao instance = null;

    private AsociereMaterieProfesorDao(DatabaseConnection databaseConnection, MaterieDao materieDao, ProfesorDao profesorDao) {
        super(databaseConnection);
        this.materieDao = materieDao;
        this.profesorDao = profesorDao;
    }

    public static AsociereMaterieProfesorDao getInstance(DatabaseConnection databaseConnection, MaterieDao materieDao, ProfesorDao profesorDao) {
        if (instance == null) {
            instance = new AsociereMaterieProfesorDao(databaseConnection, materieDao, profesorDao);
        }
        return instance;
    }


    @Override
    public AsociereMaterieProfesor create(AsociereMaterieProfesor asociere) {
        String sql = "INSERT INTO AsociereMateriiProfesori (id_materie, id_profesor) VALUES (?, ?)";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql, new String[] {"ID"})) {
            pstmt.setInt(1, asociere.getMaterie().getId());
            pstmt.setInt(2, asociere.getProfesor().getId());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                asociere.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return asociere;
    }

    @Override
    public AsociereMaterieProfesor read(int id) {
        AsociereMaterieProfesor asociere = null;
        String sql = "SELECT * FROM AsociereMateriiProfesori WHERE id = ?";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int idProfesor = rs.getInt("id_profesor");
                int idMaterie = rs.getInt("id_materie");
                Profesor profesor = profesorDao.read(idProfesor);
                Materie materie = materieDao.read(idMaterie);
                asociere = new AsociereMaterieProfesor(materie, profesor);
                asociere.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return asociere;
    }


    @Override
    public void update(AsociereMaterieProfesor asociere) {
        String sql = "UPDATE AsociereMateriiProfesori SET id_profesor = ?, id_materie = ? WHERE id_profesor = ? AND id_materie = ?";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, asociere.getProfesor().getId());
            pstmt.setInt(2, asociere.getMaterie().getId());
            pstmt.setInt(3, asociere.getProfesor().getId());
            pstmt.setInt(4, asociere.getMaterie().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(AsociereMaterieProfesor asociere) {
        String sql = "DELETE FROM AsociereMateriiProfesori WHERE id_profesor = ? AND id_materie = ?";

        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, asociere.getProfesor().getId());
            pstmt.setInt(2, asociere.getMaterie().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
