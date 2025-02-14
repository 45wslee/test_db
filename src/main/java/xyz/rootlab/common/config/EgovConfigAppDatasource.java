package xyz.rootlab.common.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

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
 * @ClassName : EgovConfigAppDatasource.java
 * @Description : DataSource 설정
 * @since : 2021. 7. 20
 */
@Configuration
public class EgovConfigAppDatasource {

    /**
     *  @Value 을 어노테이션을 이용하는 방법
     */
    //	@Value("${Globals.DbType}")
    //	private String dbType;
    //
    //	@Value("${Globals.DriverClassName}")
    //	private String className;
    //
    //	@Value("${Globals.Url}")
    //	private String url;
    //
    //	@Value("${Globals.UserName}")
    //	private String userName;
    //
    //	@Value("${Globals.Password}")
    //	private String password;

    /**
     * Environment 의존성 주입하여 사용하는 방법
     */

    @Autowired
    Environment env;

    private String className;

    private String url;

    private String userName;

    private String password;

    @PostConstruct
    void init() {
        //Exception 처리 필요
        className = env.getProperty("spring.datasource.driver-class-name");
        url = env.getProperty("spring.datasource.url");
        userName = env.getProperty("spring.datasource.username");
        password = env.getProperty("spring.datasource.password");
    }

    /**
     * @return [dataSource 설정] basicDataSource 설정
     */
    private DataSource basicDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(className);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(userName);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }

    /**
     * @return [DataSource 설정]
     */
    @Bean(name = {"dataSource", "egov.dataSource", "egovDataSource"})
    public DataSource dataSource() {
        return basicDataSource();
    }
}
