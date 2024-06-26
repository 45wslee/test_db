package xyz.rootlab.app.code.vo;

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
public class CodeVO extends SearchVO {

    private String totalCd;

    private String mainCd;

    private String subCd;

    private String codeNm;

    private int sort;

    private String useYn;

    private String systemYn;

    private String spare1;

    private String spare2;

    private String spare3;

    private String spare4;

    private String sysregidx;

    private String sysregdate;

    private String sysmodidx;

    private String sysmoddate;

    private List<CodeVO> codeList;

    /* 선택된 코드ID */
    private String selMainCd;

    private String isEdit;
}