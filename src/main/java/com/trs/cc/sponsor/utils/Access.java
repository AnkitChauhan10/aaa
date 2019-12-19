package com.trs.cc.sponsor.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Custom Annotation for access information
 * Example Usage: @Access(levels = { Authorization.ADMIN,Authorization.USER} )
 * @author TRS
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) // on Method level
public @interface Access {


	Roles[] levels() default Roles.USER;

	String createdBy() default "TRS";

}
