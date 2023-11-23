package de.atruvia.simplespring.demo;

import de.atruvia.simplespring.translator.Translator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//import jakarta.inject.Named;


@Component
//@Scope(BeanDefinition.SCOPE_PROTOTYPE) // Spring ist Fabrik
@Scope(BeanDefinition.SCOPE_SINGLETON) // Nur eine Instanz wird erzeugt (Default)
//@Lazy

//@Named

public class Demo {


    private String name = "Fritz";

    /*@Qualifier("upper") */private final Translator translator;


    public Demo(@Qualifier("upper") final Translator translator,  @Value("${Demo.name}") String name ) {
        this.translator = translator;
        this.name = name;
        System.out.println(translator.translate("Hier entsteht Demo"));
        System.out.println(name);
    }


//    public Demo() {
//        System.out.println(translator.translate("Hier entsteht Demo"));
//    }

    @PostConstruct
    public void init() {
        System.out.println(translator.translate("Init"));
        System.out.println(name);
    }

    @PreDestroy // Nicht bei  Prototype
    public void destroy() {
        System.out.println("Destroy");
    }
}
