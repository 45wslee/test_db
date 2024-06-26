package xyz.rootlab.app.menu.vo;

import lombok.Getter;
import lombok.Setter;
import xyz.rootlab.common.vo.SearchVO;

@Getter
@Setter
public class MenuVO extends SearchVO {
    private String menuCd;
    private String parntMenuCd;
    private int menuCls;
    private String sort;
    private String menuNm;
    private String menuUrl;
    private String useYn;
    private String menuIconCls;
    private String depthLength;
    private String systemYn;
    private String externalYn;
    private int mCount;
    private String markYn;
    private String mbrAuth;
}
