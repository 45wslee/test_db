package xyz.rootlab.app.mngsystem.dao;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import xyz.rootlab.app.mngsystem.vo.MngSystemVO;

public interface MngSystemDAO {


    int memberListCnt(MngSystemVO mngSystemVO) throws Exception;

    List<EgovMap> selectMemberList(MngSystemVO mngSystemVO) throws Exception;
}
