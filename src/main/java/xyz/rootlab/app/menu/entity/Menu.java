package xyz.rootlab.app.menu.entity;

import lombok.*;
import xyz.rootlab.app.menu.dto.MenuDto;
import xyz.rootlab.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "tb_menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Menu extends BaseEntity {
    @Id
    private String menuCd;

    @Column
    private String parntMenuCd;

    @Column
    private int menuCls;

    @Column
    private int sort;

    @Column
    private String menuNm;

    @Column
    private String menuUrl;

    @Column
    private String useYn;

    public MenuDto toDto() {
        return MenuDto.builder()
                .menuCd(menuCd)
                .parntMenuCd(parntMenuCd)
                .menuNm(menuNm)
                .menuUrl(menuUrl)
                .sort(sort)
                .build();
    }
}
