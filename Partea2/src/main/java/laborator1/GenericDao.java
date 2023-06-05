package laborator1;

public abstract class GenericDao<T> {
    protected final DatabaseConnection databaseConnection;

    protected GenericDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public abstract T create(T obj);
    public abstract T read(int id);
    public abstract void update(T obj);
    public abstract void delete(T obj);

}
