package de.atruvia.simplespring.client;


import de.atruvia.simplespring.math.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CalcClient {

    @Qualifier("secure") private final Calculator calculator;



    public void go() {

        System.out.println(calculator.add(3.0,4.0));
    }
}
