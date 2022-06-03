package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;

import static com.codeborne.selenide.Selenide.sleep;

public class Waiter {
    private static Logger log = LogManager.getLogger();

    public static void waitForCondition(ICondition condition, int timeOutInSeconds, int sleepMillis, String errorMessage)
    {
        long startTime = System.currentTimeMillis();
        long finishTime = startTime + timeOutInSeconds * 1000L;
        boolean fullfilled;
        do
        {
            log.debug("Waiting for condition fulfill: ");
            fullfilled = condition.checkCondition();
            if (!fullfilled)
            {
                sleep(sleepMillis);
            }
        }
        while ((!fullfilled) && System.currentTimeMillis() < finishTime);
        if (!fullfilled) throw new RuntimeException(errorMessage);
    }

    public static void waitForCondition(ICondition condition, int timeoutInSeconds, String errorMessage)
    {
        waitForCondition(condition, timeoutInSeconds, 500, errorMessage);
    }

    public static boolean isCondition(Callable func, int timeoutInSec, boolean condition)
    {
        boolean result = !condition;
        for (int i = 0; i < timeoutInSec && result != condition; i++)
        {
            log.debug("Waiting while condition is: " + condition);
            try
            {
                result = (boolean) func.call();
            } catch (Throwable ignored)
            {

            }
            if(result != condition)
            {
                sleep(500);
            }
        }
        return result;
    }

}
