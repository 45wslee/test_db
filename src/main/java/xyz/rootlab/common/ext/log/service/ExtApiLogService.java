package xyz.rootlab.common.ext.log.service;

import xyz.rootlab.common.ext.log.entity.ExtApiLog;
import xyz.rootlab.common.ext.log.vo.ReqBody;

public interface ExtApiLogService {
    void saveLog(ExtApiLog extApiLog) throws Exception;

    void ttt(ReqBody reqBody) throws Exception;
}
