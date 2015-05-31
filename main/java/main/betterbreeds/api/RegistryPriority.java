package main.betterbreeds.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation goes on
 * a handler's class. It allows
 * you to specify when you want
 * your handler to be called.
 * 
 * @author Fir3will
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RegistryPriority
{
	/**
	 * This annotation is going to be used
	 * to sort the lists of handlers. You
	 * add this to the class and it will
	 * be called depending on the number you put.
	 * <p>0 means that it will be called last</p>
	 * <p>100 means that it will be called first</p>
	 * 
	 * @return A value between 0 and 100.
	 */
	int priority() default 50;
}
