package xyz.rootlab.common.ext.log.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.rootlab.common.ext.log.dao.ExtApiLogDAO;
import xyz.rootlab.common.ext.log.entity.ExtApiLog;
import xyz.rootlab.common.ext.log.vo.ReqBody;

@Service("apiLogService")
@RequiredArgsConstructor
public class ExtExtApiLogLogServiceImpl implements ExtApiLogService {

    private final ExtApiLogDAO extApiLogDAO;

    @Override
    public void saveLog(ExtApiLog extApiLog) {
        extApiLogDAO.insertApiLog(extApiLog);
    }

    // 테스트용도
    @Override
    public void ttt(ReqBody reqBody) throws Exception {
        ExtApiLog extApiLog = ExtApiLog.builder().reqBody("이거는 데이터가 들어가면 오류에요").build();

        extApiLogDAO.insertApiLog(extApiLog);

        if(reqBody.getA().equals("service")) {
            throw new IllegalArgumentException("service error");
        }

    }
}
