package xyz.rootlab.common.ext.log.dao;

import xyz.rootlab.common.ext.log.entity.ExtApiLog;

public interface ExtApiLogDAO {
    void insertApiLog(ExtApiLog extApiLog);
}
