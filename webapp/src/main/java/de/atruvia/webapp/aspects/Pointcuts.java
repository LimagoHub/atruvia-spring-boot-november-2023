package de.atruvia.webapp.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut(value ="execution(public * de.atruvia.webapp.presentation.controller.personen.version1.PersonenQueryController.*(..))")
    public void personenControllerMethods(){}

    @Pointcut(value ="within(de.atruvia.webapp.presentation.controller.personen.version1.PersonenCommandController)")
    public void personenCommandControllerMethods(){}

    @Pointcut("@within(de.atruvia.webapp.aspects.Dozent)")
    public void dozentMethods(){};

//    @Pointcut("@within(org.springframework.stereotype.Service)")
//    public void serviceMethods(){};

    @Pointcut("within(de.atruvia.webapp.domain.service.internal.*)")
    public void serviceMethods(){};


}
