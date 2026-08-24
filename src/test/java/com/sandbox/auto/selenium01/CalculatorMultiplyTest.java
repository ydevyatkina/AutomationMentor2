package com.sandbox.auto.selenium01;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorMultiplyTest extends CalculatorBaseTest {

    @Test(dataProviderClass = CalculatorDataProvider.class, dataProvider = "Calculator Data")
    public void multiplyTest(long a, long b) {
        assertThat(calculator.mult(a, b))
                .as("Calculator multiplication: %d * %d is not equal to expected", a, b)
                .isEqualTo(a * b);
    }
}
