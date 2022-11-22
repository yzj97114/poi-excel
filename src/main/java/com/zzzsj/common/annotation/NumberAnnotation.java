package com.zzzsj.common.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NumberAnnotation {
    /**
     * 金额默认保留两位小数，采用四舍五入
     * @return
     */
    int numberPattern() default 2;
}
