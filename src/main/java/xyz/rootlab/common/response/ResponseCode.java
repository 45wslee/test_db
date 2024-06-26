package xyz.rootlab.common.response;

import lombok.Data;
import lombok.Getter;

/**
 * @author thkim
 * @see <pre>
 * << 개정이력(Modification Information) >>
 * 수정일         수정자      수정내용
 * -----------  -------   -------------------
 * 2023/02/23      thkim   최초 생성
 *
 *
 * </pre>
 * @since 2023/02/23
 */
@Getter
public enum ResponseCode {
    SUCCESS(1000, "Success", "성공"),
    NO_DATA(1010, "No Data", "결과없음(상세조회, 리스트 검색)"),
    EXIST_INFO(1011, "Exist Info", "중복된 데이터가 존재할 시"),
    TIMEOUT(1013, "Response Timeout", "응답 시간 초과"),
    TARGET_DISABLED(1013, "Target server is disabled", "요청 대상 서버가 비활성 상태일 경우"),
    INVALID_PARAM(1400, "Invalid parameter ", "파라미터 값이 잘못되거나 누락됨"),
    INVALID_PARAM_LEN(1400, "Invalid parameter [Scale Chk] ", "파라미터의 길이값이 잘못됨"),
    INVALID_PARAM_PATTERN(1400, "Invalid parameter [Pattern] ", "파라미터값의 패턴이 잘못됨"),
    INVALID_PARAM_TYPE(1400, "Invalid format parameter ", "파라미터값의 Data Type 이 잘못됨"),
    INVALID_PARAM_CASTING(1400, "Cast Error [data confirm]", "파라미터 cast 에러"),
    NOT_AUTHENTICATED(1401, "Not Authenticated", "인증 실패"),
    NOT_AUTHORIZED(1401, "Not Authorized", "권한 없음"),
    NO_SESSION(1420, "No Session Data ", "세션에 값이 없을 경우"),
    FAIL_UPDATE(1500, "Failed To Update", "수정 실패"),
    FAIL_DELETE(1500, "Failed To Delete", "삭제 실패"),
    FAIL_INSERT(1500, "Failed To Insert", "등록 실패"),
    FAIL_UPSERT(1500, "Failed To Upsert", "등록 / 수정 실패"),
    FAIL_PROCESS(1500, "Failed To Process", "내부에서 요청 처리에 실패(DB 처리 연관)"),
    UNKNOWN(1500, "Unknown Error", "알 수 없는 오류(정립되지 않은)"),
    NOT_IMPLEMENTED(1500, "Not Implemented", "미구현 요청 호출"),
    ;

    private final int code;
    private final String msg;
    private final String desc;

    ResponseCode(int code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }
}
