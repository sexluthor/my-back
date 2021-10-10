package ru.onemore.vtbhack.back.hasrole;

import ru.onemore.vtbhack.back.enumeration.RolesEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HasRole {

    RolesEnum[] value();

}
