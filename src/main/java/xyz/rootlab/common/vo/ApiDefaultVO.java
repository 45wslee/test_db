package xyz.rootlab.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ApiDefaultVO implements Serializable {
    /* 생성자 */
    private Long rgtrSn;
    /* 생성일 */
    private LocalDateTime rgtrDt;
    /* 수정자 */
    private Long mdfrSn;
    /* 수정일 */
    private LocalDateTime mdfrDt;
}
