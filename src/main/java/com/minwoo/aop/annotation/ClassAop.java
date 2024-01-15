package com.minwoo.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // class 기준
@Retention(RetentionPolicy.RUNTIME) // runtime 까지 적용됨
public @interface ClassAop {
}
