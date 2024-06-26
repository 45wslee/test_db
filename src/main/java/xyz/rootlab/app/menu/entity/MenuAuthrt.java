package xyz.rootlab.app.menu.entity;

import lombok.*;
import xyz.rootlab.app.member.enums.AuthCode;
import xyz.rootlab.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "tb_menu_authrt")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MenuAuthrt extends BaseEntity {
    @Id
    @GeneratedValue
    private Long authrtSn;

    @Convert(converter = AuthCode.Converter.class)
    private AuthCode authrtCd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_cd")
    private Menu menu;

    @Column
    private String authrtYn;
}

