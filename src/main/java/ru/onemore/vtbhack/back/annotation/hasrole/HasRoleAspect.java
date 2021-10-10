package ru.onemore.vtbhack.back.annotation.hasrole;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.onemore.vtbhack.back.enumeration.RolesEnum;
import ru.onemore.vtbhack.back.util.SecurityUtil;

import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class HasRoleAspect {

    @Pointcut("@annotation(ru.onemore.vtbhack.back.annotations.security.hasrole.HasRole)")
    private void hasRoleAnnotation() {
    }

    @Around("@annotation(hr)")
    public Object doSomething(ProceedingJoinPoint pjp, HasRole hr) throws Throwable {

        RolesEnum[] roles = hr.value();
        if (roles == null || roles.length == 0) {
            return null;
        }

        boolean userHasRole = SecurityUtil.containsRole(roles);
        if (!userHasRole) {
            return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).build();
        }

        return pjp.proceed();
    }

}
