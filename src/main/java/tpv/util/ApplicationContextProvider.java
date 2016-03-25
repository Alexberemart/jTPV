package tpv.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class ApplicationContextProvider implements ApplicationContextAware {

	static protected ApplicationContextProvider instance;
	
	private static ApplicationContext ctx = null;
	
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}
	
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		ApplicationContextProvider.ctx = ctx;
	}

	public static synchronized ApplicationContextProvider getInstance() {
		if(ApplicationContextProvider.instance == null) {
			ApplicationContextProvider.instance = new ApplicationContextProvider();
		}
		return ApplicationContextProvider.instance;
	}
	
	public Object getBean(String ref) {
		return ApplicationContextProvider.ctx.getBean(ref);
	}

	public Object getBean(Class<?> theClass) {
		return ApplicationContextProvider.ctx.getBean(theClass);
	}	

    public Map<String, ?> getBeansOfType(Class<?> theClass) {
        return ApplicationContextProvider.ctx.getBeansOfType(theClass);
    }

}

