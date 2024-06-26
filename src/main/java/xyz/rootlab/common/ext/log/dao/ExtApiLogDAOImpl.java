package xyz.rootlab.common.ext.log.dao;

import org.springframework.stereotype.Repository;
import xyz.rootlab.common.dao.SqlComAbstractDAO;
import xyz.rootlab.common.ext.log.entity.ExtApiLog;

@Repository
public class ExtApiLogDAOImpl extends SqlComAbstractDAO implements ExtApiLogDAO {
    @Override
    public void insertApiLog(ExtApiLog extApiLog) {
        insert("ExtApiLogDAO.insertApiLog", extApiLog);
    }
}
