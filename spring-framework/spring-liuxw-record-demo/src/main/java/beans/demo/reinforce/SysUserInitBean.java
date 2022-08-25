package beans.demo.reinforce;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.event.EventListener;


public class SysUserInitBean implements InitializingBean {

	@Override
	@EventListener
	public void afterPropertiesSet() throws Exception {
		System.out.println("SysUserInitBean -> afterPropertiesSet");
	}
}
