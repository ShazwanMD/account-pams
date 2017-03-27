package my.edu.umk.pams.account.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author canang.technologies
 * @since 7/26/14
 */
@SuppressWarnings("ALL")
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    public static <X> X getSpringBean(Class beanClass) {
        return (X) applicationContext.getBean(beanClass);
    }

    public static Object getSpringBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static void autoWiredBean(Object obj) {
        applicationContext.getAutowireCapableBeanFactory().autowireBean(obj);
    }

    @Override
    @Autowired
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        applicationContext = arg0;
    }
}
