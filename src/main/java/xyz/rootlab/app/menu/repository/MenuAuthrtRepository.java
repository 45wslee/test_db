package xyz.rootlab.app.menu.repository;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.rootlab.app.member.enums.AuthCode;
import xyz.rootlab.app.menu.entity.MenuAuthrt;

import java.util.List;

public interface MenuAuthrtRepository extends JpaRepository<MenuAuthrt, Long> {
    @Query(value = "select auth.authrtCd from MenuAuthrt auth join fetch Menu menu on menu.menuCd = auth.menu where menu.menuUrl = :menuUrl and auth.authrtYn = 'Y'")
    List<AuthCode> findByMenuUrl(@Param("menuUrl") String menuUrl);
}
