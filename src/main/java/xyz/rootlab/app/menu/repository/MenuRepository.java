package xyz.rootlab.app.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.rootlab.app.member.enums.AuthCode;
import xyz.rootlab.app.menu.entity.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, String> {
    @Query(value = "select menu from Menu menu join fetch MenuAuthrt auth on menu.menuCd = auth.menu where auth.authrtCd = :authCode and menu.useYn = 'Y'")
    List<Menu> findAllByAuthrtCd(@Param("authCode") AuthCode authCode);
}
