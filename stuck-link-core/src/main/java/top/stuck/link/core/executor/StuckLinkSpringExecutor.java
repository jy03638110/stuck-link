package top.stuck.link.core.executor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created on 2020-04-26
 *
 * @author Octopus
 */
public class StuckLinkSpringExecutor extends StuckLinkExecutor implements ApplicationContextAware, InitializingBean, DisposableBean {

    private static ApplicationContext applicationContext;

    public StuckLinkSpringExecutor(){
        super();
    }

    public StuckLinkSpringExecutor(Integer poolSize) {
        super(poolSize);
    }

    @Override
    public void destroy() throws Exception {
        super.toStop();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        StuckLinkSpringExecutor.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
