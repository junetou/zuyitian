package org.adny.work.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Secure {
	
	public enum RequestType{HTTP, AJAX};
	public enum Role{ALL, B2B, B2C};
	
	public RequestType type() default RequestType.HTTP;
	public Role role() default Role.ALL; 
	
}
