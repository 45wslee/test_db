package xyz.rootlab.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import xyz.rootlab.common.enums.CommonTypeHandler;
import xyz.rootlab.common.enums.EntityEnumerable;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author : 윤주호
 * @version : 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자               수정내용
 *  -------------  ------------   ---------------------
 *   2021. 7. 20    윤주호               최초 생성
 * </pre>
 * @ClassName : EgovConfigAppMapper.java
 * @Description : Mapper 설정
 * @since : 2021. 7. 20
 */
@Configuration
@PropertySources({
        @PropertySource("classpath:/egovframework/egovProps/globals.properties")
})
public class EgovConfigAppMapper {
    @Autowired
    DataSource dataSource;

    @Autowired
    Environment env;

    @PostConstruct
    void init() {
    }

    @Bean
    @Lazy
    public DefaultLobHandler lobHandler() {
        return new DefaultLobHandler();
    }

    @Bean(name = {"sqlSession", "egov.sqlSession"})
    public SqlSessionFactoryBean sqlSession() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setConfigLocation(
                pathMatchingResourcePatternResolver
                        .getResource("classpath:/egovframework/mapper/config/mapper-config.xml"));

        try {
            sqlSessionFactoryBean.setMapperLocations(
                    pathMatchingResourcePatternResolver
                            .getResources("classpath:/egovframework/mapper/rootlab/**/*.xml"));

            sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[] {
                    new CommonTypeHandler(EntityEnumerable.class)
            });
        } catch (IOException e) {
            // TODO Exception 처리 필요
        }

        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate egovSqlSessionTemplate(@Qualifier("sqlSession") SqlSessionFactory sqlSession) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSession);
        return sqlSessionTemplate;
    }
}
