package xyz.rootlab.common.ext.log.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ExtApiLog implements Serializable {
    private Long logSn;

    private String clientIp;

    private String clientNm;

    private String reqUri;

    private String method;

    private String queryString;

    private String reqBody;

    private String resBody;

    private LocalDateTime rgtrDt;
}
