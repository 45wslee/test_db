package xyz.rootlab.common.config;

import com.p6spy.engine.spy.P6SpyOptions;
import org.springframework.context.annotation.Configuration;
import xyz.rootlab.common.p6spy.P6spyPrettySqlFormatter;

import javax.annotation.PostConstruct;

@Configuration
public class P6spyConfig {
    @PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6spyPrettySqlFormatter.class.getName());
    }
}
