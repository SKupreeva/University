package by.bsuir.laba8;

import by.bsuir.laba8.service.IConverterService;
import by.bsuir.laba8.service.IInputService;
import by.bsuir.laba8.service.impl.ConverterService;
import by.bsuir.laba8.service.impl.InputService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		IConverterService converterBean = (ConverterService) context.getBean("converterBean");
		IInputService inputService = (InputService) context.getBean("inputBean");

		System.out.print("Enter string you want to sort: ");
		String input = inputService.ReadString();
		String result = converterBean.SortString(input);
		System.out.printf("Result: %s", result);

		context.close();
	}
}
