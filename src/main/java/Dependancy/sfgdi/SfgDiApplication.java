package Dependancy.sfgdi;

import Dependancy.sfgdi.controllers.PetController;
import Dependancy.sfgdi.controllers.*;
import Dependancy.sfgdi.dataSource.FakeDataSource;
import Dependancy.sfgdi.services.PrototypeBean;
import Dependancy.sfgdi.services.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"Dependancy.sfgdi","Dependancy.pets"})
@SpringBootApplication
public class SfgDiApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);
		System.out.println("--------------------------Primary Bean------------------------------");
		MyController myController = (MyController) ctx.getBean("myController");
		System.out.println(myController.sayHello());

		System.out.println("--------------------------Property------------------------------");
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("--------------------------setter------------------------------");
		SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("--------------------------Constructor------------------------------");
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());

		System.out.println("--------------------------I18nController------------------------------");
		I18nController i18nController = (I18nController) ctx.getBean("i18nController");
		System.out.println(i18nController.sayHello());

		PetController petController = (PetController)ctx.getBean("petController");
		System.out.println("--- The Best Pet is ---");
		System.out.println(petController.whichPetIsTheBest());

		System.out.println("--------------------------Scope Bean------------------------------");

		SingletonBean singletonBean1= ctx.getBean(SingletonBean.class);
		System.out.println("++++++++++++"+singletonBean1.getMyScope());
		SingletonBean singletonBean2= ctx.getBean(SingletonBean.class);
		System.out.println("++++++++++++"+singletonBean2.getMyScope());
		PrototypeBean prototypeBean1= ctx.getBean(PrototypeBean.class);
		System.out.println("------------"+prototypeBean1.getMyScope());
		PrototypeBean PrototypeBean2= ctx.getBean(PrototypeBean.class);
		System.out.println("------------"+PrototypeBean2.getMyScope());


		System.out.println("--------------------------Prpperties data sources------------------------------");
		FakeDataSource fakeDataSource =ctx.getBean(FakeDataSource.class);
		System.out.println(" password="+fakeDataSource.getPassword()+"username="+fakeDataSource.getUserName()+"url="+fakeDataSource.getJdbcurl());


	}

}
