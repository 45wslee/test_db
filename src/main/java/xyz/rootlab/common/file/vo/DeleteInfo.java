package xyz.rootlab.common.file.vo;

import lombok.Data;
import xyz.rootlab.common.file.enums.ReferenceTable;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class DeleteInfo {
    List<Long> deleteList;
    @NotBlank
    ReferenceTable rfrncTbl;
    @NotBlank
    Long rfrncSn;
    Long rgtrSn;
    public boolean isValid() {
        return deleteList.size() != 0 && rfrncTbl != null && rfrncSn != null && rgtrSn != null;
    }
}
