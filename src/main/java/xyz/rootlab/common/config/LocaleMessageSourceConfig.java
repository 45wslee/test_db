package xyz.rootlab.common.config;

import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.rootlab.common.utils.LocaleMessageSource;

import java.time.Duration;

@Configuration
public class LocaleMessageSourceConfig implements WebMvcConfigurer {

    /**
     * @return [Resource 설정] 메세지 Properties 설정
     */
    @Bean
    public MessageSourceProperties properties() {
        return new MessageSourceProperties();
    }

    /**
     * @return [Resource 설정] 메세지 Properties 경로 설정
     */
    @Bean
    public LocaleMessageSource messageSource() {
        LocaleMessageSource messageSource = new LocaleMessageSource();
        if (StringUtils.hasText(properties().getBasename())) {
            messageSource.setBasenames("classpath:/egovframework/message/com/message-common");
        }

        if (properties().getEncoding() != null) {
            messageSource.setDefaultEncoding(properties().getEncoding().name());
        }

        messageSource.setFallbackToSystemLocale(properties().isFallbackToSystemLocale());
        Duration cacheDuration = properties().getCacheDuration();
        if (cacheDuration != null) {
            messageSource.setCacheMillis(cacheDuration.toMillis());
        }
        messageSource.setAlwaysUseMessageFormat(properties().isAlwaysUseMessageFormat());
        messageSource.setUseCodeAsDefaultMessage(properties().isUseCodeAsDefaultMessage());

        return messageSource;
    }

}
