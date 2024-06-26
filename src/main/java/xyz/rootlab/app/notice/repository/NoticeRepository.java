package xyz.rootlab.app.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.rootlab.app.notice.entity.Notice;

/**
 * 공지사항 관리를 위한 Repository 인터페이스
 *
 * @author 김태형
 * @version 1.0
 * @since 2023.01.25
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 수정일         수정자      수정내용
 * -----------  -------   ----------------------
 * 2023.01.25   김태형      최초생성
 *
 *
 * </pre>
 */
@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}