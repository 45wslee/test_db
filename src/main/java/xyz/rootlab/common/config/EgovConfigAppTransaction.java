package xyz.rootlab.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;

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
 * @ClassName : EgovConfigAppTransaction.java
 * @Description : Transaction 설정
 * @since : 2021. 7. 20
 */
@Configuration
@RequiredArgsConstructor
public class EgovConfigAppTransaction {
    private final DataSource dataSource;

//    @Bean
//    public DataSourceTransactionManager transactionManager() {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(dataSource);
//        return dataSourceTransactionManager;
//    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    // -------------------------------------------------------------
    // TransactionAdvice 설정
    // -------------------------------------------------------------

    @Bean
    public TransactionInterceptor transactionAdvice(JpaTransactionManager txManager) {
        TransactionInterceptor txAdvice = new TransactionInterceptor();
        txAdvice.setTransactionManager(txManager);
        txAdvice.setTransactionAttributeSource(getNameMatchTransactionAttributeSource());
        return txAdvice;
    }

    private NameMatchTransactionAttributeSource getNameMatchTransactionAttributeSource() {
        NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();
        txAttributeSource.setNameMap(getRuleBasedTxAttributeMap());
        return txAttributeSource;
    }

    private HashMap<String, TransactionAttribute> getRuleBasedTxAttributeMap() {
        HashMap<String, TransactionAttribute> txMethods = new HashMap<String, TransactionAttribute>();

        RuleBasedTransactionAttribute txAttribute = new RuleBasedTransactionAttribute();
        txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        txMethods.put("*", txAttribute);

        return txMethods;
    }

    // -------------------------------------------------------------
    // TransactionAdvisor 설정
    // -------------------------------------------------------------

    @Bean
    public Advisor transactionAdvisor(JpaTransactionManager txManager) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(
                "execution(* xyz.rootlab..*Impl.*(..))");
        return new DefaultPointcutAdvisor(pointcut, transactionAdvice(txManager));
    }
}
