package xyz.rootlab.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 자식 클래스에서 아래 필드들을 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) //
public class BaseEntity {
//    @CreatedBy
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "MBR_SN")
//    protected Member rgtrId;

    @CreatedBy
    private Long rgtrSn;

    @CreatedDate
    private LocalDateTime rgtrDt;

//    @LastModifiedBy
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "MBR_SN")
//    private Member mdfrId;

    @LastModifiedBy
    private Long mdfrSn;

    @LastModifiedDate
    private LocalDateTime mdfrDt;
}
