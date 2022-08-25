package beans.demo.reinforce;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class SysUserPostProcess implements BeanPostProcessor {


	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("SysUserPostProcess -> postProcessBeforeInitialization {"+beanName+"}");
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}


	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("SysUserPostProcess -> postProcessAfterInitialization {"+beanName+"}");
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
