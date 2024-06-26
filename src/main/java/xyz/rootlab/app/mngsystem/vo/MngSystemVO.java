package xyz.rootlab.app.mngsystem.vo;

import lombok.Getter;
import lombok.Setter;
import xyz.rootlab.common.vo.SearchVO;

@Getter
@Setter
public class MngSystemVO extends SearchVO {

    private int mbrSn;
    private String mbrId;
    private String mbrPswd;
    private String mbrNm;
    private String mbrTelno;
    private String mbrEmail;
    private String mbrAuth;
    private String lockYn;
}
