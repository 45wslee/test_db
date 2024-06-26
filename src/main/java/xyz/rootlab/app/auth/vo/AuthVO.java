package xyz.rootlab.app.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.rootlab.common.vo.SearchVO;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthVO extends SearchVO {
    private String roleCd;
    private String roleNm;
    private String roleDesc;
    private String useYn;
    private String totalCd;
    private String authrtCd;
    private String menuCd;
    private String authrtYn;
    private String authrtSn;
    private String parntMenuCd;
}