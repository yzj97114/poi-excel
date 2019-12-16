package com.zzzsj.common.annotation;

import com.zzzsj.common.enums.DecimalType;
import com.zzzsj.common.enums.TimeType;

import java.lang.annotation.*;

/**
 * 自定义Excel注解
 * @author 79282
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Excel {
    /**
     * 表头中文
     *
     * @return
     */
    String titleName();

    /**
     * 列宽 默认30
     *
     * @return
     */
    int titleSize() default 30;

    /**
     * 字段顺序 正序
     *
     * @return
     */
    int orderNum();

    /**
     * 是否允许空值,默认不允许
     *
     * @return
     */
    boolean empty() default false;

    /**
     * 内部类 设置格式
     *
     * @return
     */
    CellType type() default @CellType;

    /**
     * 设置格式
     *
     * @return
     */
    @interface CellType {

        TimeType timeType() default TimeType.TIMEF_FORMAT;

        DecimalType decimalType() default DecimalType.TWO;

        boolean IsMoney() default false;
    }

}
