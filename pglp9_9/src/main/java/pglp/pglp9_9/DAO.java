package pglp.pglp9_9;

import java.sql.SQLException;

public interface DAO <T>{
public  T create(T obj) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException;
public T find(String id);
public T update(T obj);
public T delete(T obj);


}
