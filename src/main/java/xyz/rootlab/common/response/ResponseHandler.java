package xyz.rootlab.common.response;

public class ResponseHandler {

    /**
     * 성공
     *
     * @return response
     */
    public static Response success() {
        Response response = new Response();
        response.setResponse(ResponseCode.SUCCESS);
        return response;
    }

    /**
     * 성공 - 응답 데이터 있음
     *
     * @param data 응답 데이터
     * @return response
     */
    public static Response success(Object data) {
        Response response = new Response();
        response.setResponse(ResponseCode.SUCCESS);
        response.setData(data);
        return response;
    }

    /**
     * 예외 발생 시 오류메시지 응답
     * @return response
     */
    public static Response error(ResponseCode responseCode) {
        Response response = new Response();
        response.setResponse(responseCode);
        return response;
    }

    /**
     * 예외 발생 시 오류메시지 응답 (데이터 포함)
     *
     * @param data
     * @return response
     */
    public static Response errorWithData(String data, ResponseCode code) {
        Response response = new Response();
        response.setResponse(code, data);
        return response;
    }

    /**
     * 응답 데이터 없음
     *
     * @return response
     */
    public static Response noData() {
        Response response = new Response();
        response.setResponse(ResponseCode.NO_DATA);
        return response;
    }

    /**
     * 중복된 데이터가 존재할 시
     *
     * @return response
     */
    public static Response existInfo() {
        Response response = new Response();
        response.setResponse(ResponseCode.EXIST_INFO);
        return response;
    }

    /**
     * 외부 서버 타임아웃
     *
     * @return response
     */
    public static Response timeOut() {
        Response response = new Response();
        response.setResponse(ResponseCode.TIMEOUT);
        return response;
    }

    /**
     * 요청 대상 서버가 비활성 상태일 경우
     *
     * @return response
     */
    public static Response serverDisabled() {
        Response response = new Response();
        response.setResponse(ResponseCode.TARGET_DISABLED);
        return response;
    }

    /**
     * *** 파라미터 값이 잘못되거나 누락됨 *** 파라미터 확인
     *
     * @param param 파라미터
     * @return response
     */
    public static Response invalidParam(String param) {
        Response response = new Response();
        response.setResponse(ResponseCode.INVALID_PARAM, param);
        return response;
    }

    /**
     * *** 파라미터의 길이값이 잘못됨
     *
     * @param param 파라미터
     * @return response
     */
    public static Response invalidParamLen(String param) {
        Response response = new Response();
        response.setResponse(ResponseCode.INVALID_PARAM_LEN, param);
        return response;
    }

    /**
     * *** 파라미터값의 패턴이 잘못됨
     *
     * @param param 파라미터
     * @return response
     */
    public static Response invalidParamPattern(String param) {
        Response response = new Response();
        response.setResponse(ResponseCode.INVALID_PARAM_PATTERN, param);
        return response;
    }

    /**
     * *** 파라미터값의 Data Type이 잘못됨
     *
     * @param param 파라미터
     * @return response
     */
    public static Response invalidParamType(String param) {
        Response response = new Response();
        response.setResponse(ResponseCode.INVALID_PARAM_TYPE, param);
        return response;
    }

    /**
     * 파라미터 cast 에러
     *
     * @param param 파라미터
     * @return response
     */
    public static Response invalidParamCasting(String param) {
        Response response = new Response();
        response.setResponse(ResponseCode.INVALID_PARAM_CASTING, param);
        return response;
    }

    /**
     * 인증 실패
     *
     * @return response
     */
    public static Response notAuthenticated() {
        Response response = new Response();
        response.setResponse(ResponseCode.NOT_AUTHENTICATED);
        return response;
    }

    /**
     * 권한 없음
     *
     * @return response
     */
    public static Response notAuthorized() {
        Response response = new Response();
        response.setResponse(ResponseCode.NOT_AUTHORIZED);
        return response;
    }

    /**
     * 세션에서 조회한 결과가 없을 때
     *
     * @return response
     */
    public static Response noSession(String key) {
        Response response = new Response();
        response.setResponse(ResponseCode.NO_SESSION, key);
        return response;
    }

    /**
     * 수정 실패
     *
     * @return response
     */
    public static Response failToUpdate() {
        Response response = new Response();
        response.setResponse(ResponseCode.FAIL_UPDATE);
        return response;
    }

    /**
     * 삭제 실패
     *
     * @return response
     */
    public static Response failToDelete() {
        Response response = new Response();
        response.setResponse(ResponseCode.FAIL_DELETE);
        return response;
    }

    /**
     * 등록 실패
     *
     * @return response
     */
    public static Response failToInsert() {
        Response response = new Response();
        response.setResponse(ResponseCode.FAIL_INSERT);
        return response;
    }

    /**
     * 등록 / 수정 실패
     *
     * @return response
     */
    public static Response failToUpsert() {
        Response response = new Response();
        response.setResponse(ResponseCode.FAIL_UPSERT);
        return response;
    }

    /**
     * 내부에서 요청처리에 실패 (DB처리 연관)
     *
     * @return response
     */
    public static Response failToProcess() {
        Response response = new Response();
        response.setResponse(ResponseCode.FAIL_PROCESS);
        return response;
    }

    /**
     * 알 수 없는 오류(정립되지 않은)
     *
     * @return response
     */
    public static Response unknownError() {
        Response response = new Response();
        response.setResponse(ResponseCode.UNKNOWN);
        return response;
    }

    /**
     * 미구현 요청 호출
     *
     * @return response
     */
    public static Response notImplemented() {
        Response response = new Response();
        response.setResponse(ResponseCode.NOT_IMPLEMENTED);
        return response;
    }
}
