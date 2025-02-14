
package xyz.rootlab.common.utils;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Properties;

public class LocaleMessageSource extends ReloadableResourceBundleMessageSource {

    public Properties getProperties(Locale locale) {
        return getMergedProperties(locale).getProperties();
    }

}
