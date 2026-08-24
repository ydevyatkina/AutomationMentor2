package com.sandbox.auto.selenium04;

import com.sandbox.auto.selenium04.tests.WebBaseTests;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.qameta.allure.util.ResultsUtils.getStatus;
import static io.qameta.allure.util.ResultsUtils.getStatusDetails;

@Aspect
public class CustomAllureAspectJ {
    private static List<String> failedSteps = new ArrayList<>();

    @Pointcut("execution(public * org.assertj.core.api.AbstractAssert+.*(..))")
    public void anyAssert() {
    //pointcut body, should be empty
    }

    @AfterThrowing(pointcut = "anyAssert()", throwing = "e")
    public void stepFailed(final Throwable e) {
        Optional<String> stepID = Allure.getLifecycle().getCurrentTestCaseOrStep();
        if (stepID.isPresent() && !failedSteps.contains(stepID.get())) {
            failedSteps.add(stepID.get());
            Allure.getLifecycle().updateStep(s -> {
                System.out.println("Hi");
                s.setStatus(getStatus(e).orElse(Status.BROKEN))
                        .setStatusDetails(getStatusDetails(e).orElse(null));
            });
            AttachmentListener.saveScreenshot(WebBaseTests.getWebDriver());
            Allure.getLifecycle().stopStep();
        }
    }
}
