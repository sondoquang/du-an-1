
package daos;

import java.util.List;

abstract public class DAOs<EntityType, KeyType> {
    abstract public int insert(EntityType e);
    abstract public int update(EntityType e);
    abstract public int delete(KeyType key);
    abstract public List<EntityType> selectAll();
    abstract public EntityType selectByID(KeyType key);
    abstract protected List<EntityType> selectBySql(String sql, Object... args);
}
