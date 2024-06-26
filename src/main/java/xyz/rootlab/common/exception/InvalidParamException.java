package xyz.rootlab.common.exception;

import lombok.Getter;
import xyz.rootlab.common.response.ResponseCode;

/**
 * @author thkim
 * @see <pre>
 * << 개정이력(Modification Information) >>
 * 수정일         수정자      수정내용
 * -----------  -------   -------------------
 * 2023/02/24      thkim   최초 생성
 *
 *
 * </pre>
 * @since 2023/02/24
 */
@Getter
public class InvalidParamException extends RuntimeException {
    private String param;
    private ResponseCode responseCode;

    public InvalidParamException() {
    }

    public InvalidParamException(String param) {
        this.param = param;
        this.responseCode = ResponseCode.INVALID_PARAM;
    }

    public InvalidParamException(String param, ResponseCode responseCode) {
        this.param = param;
        this.responseCode = responseCode;
    }
}
