package base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {

    // Before Each Single Scenario


    @Before()
    public void intTitle(Scenario scenario){

    System.out.println("START. SCENARIO NAME = " + scenario.getName());
    }


    @After
    public void tearDown(Scenario scenario){

    System.out.println("AFTER. SCENARIO NAME = " + scenario.getName() +
                ". STATUS = " + scenario.getStatus());
    }
}
