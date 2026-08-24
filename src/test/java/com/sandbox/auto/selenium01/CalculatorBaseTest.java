package com.sandbox.auto.selenium01;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.*;


public abstract class CalculatorBaseTest {

    protected Calculator calculator;

    @BeforeSuite
    public void setUpSuite() {
        System.out.println(CalculatorBaseTest.class.getSimpleName() + " before suite method\n");
    }

    @BeforeClass
    public void setUpClass() {
        System.out.println(this.getClass().getSimpleName() + " before class method\n");

    }

    @BeforeMethod
    public void setUp() {
        System.out.println("before method\n");
        this.calculator = new Calculator();
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("after method\n");
    }

    @AfterClass
    public void tearDownClass() {
        System.out.println(this.getClass().getSimpleName() + " after class method\n");
        this.calculator = null;
    }

    @AfterSuite
    public void tearDownSuite() {
        System.out.println(CalculatorBaseTest.class.getSimpleName() + " after suite method\n");
    }
}
