package Database;
import java.sql.ResultSet;

public interface DatabaseInterface {
   public void insert(String sql);
   public void delete(String sql);
   public void update(String sql);
   public ResultSet select(String sql);
   public void close ();
}
