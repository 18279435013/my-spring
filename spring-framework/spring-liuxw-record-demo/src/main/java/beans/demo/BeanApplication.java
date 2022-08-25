package beans.demo;

import beans.demo.doming.SysUser;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanApplication {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans/beans.xml");

		SysUser bean = context.getBean(SysUser.class);
		System.out.println(bean);
	}
}