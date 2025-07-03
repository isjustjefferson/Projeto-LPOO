package database;

public interface Repository<T>{
    
    public void insert(T entity);
    
    public boolean confirm(String string);
}
