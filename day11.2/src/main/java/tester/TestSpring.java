package tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dependent.ATMImpl;

public class TestSpring {

	public static void main(String[] args) {

		//create instance of classpathXmlApplicationContext <String Configfile) :this is autocloaseble
		//tp start SC  in the standalone environment ,
		//using XML based metadata 
		//instrictions, placed in runtime class path(i.e <resources>)
		
		try(ClassPathXmlApplicationContext ctx =new ClassPathXmlApplicationContext("my_config.xml"))
		
		{
		System.out.println("sc up n running ");	
		//withdraw 1000 from the atm
		//API of O>S>B>F BeanFactory
		//<T> T getBean(String beanId/name,class<T>class)
		ATMImpl ref1=ctx.getBean("my_atm",ATMImpl.class);
		ref1.withdraw(1000);
		//another demand for bean
		ATMImpl ref2=ctx.getBean("my_atm",ATMImpl.class);
	System.out.println(ref1==ref2);  //singleton :true  
		}  //JVM :ctx.close -->SC shutting down --tries to call destroy method on singleton beans --Garbage collection all spring beans.
		
		
		
		
		
		catch(Exception e) {
			e.printStackTrace();
		}
	
		
		
	}

}
