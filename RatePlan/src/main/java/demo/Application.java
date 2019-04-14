package demo;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import sun.security.x509.KeyIdentifier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2019/4/14.
 */
public class Application
{
    public static void main(String[] args) throws ParseException
    {
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("RatePlanKS");

        RatePlan ratePlanA = new RatePlan();
        ratePlanA.setRatePlanCode("A");
        ratePlanA.setBaseRate(100);
        ratePlanA.setDate(simpleDate("11/28/2019"));

        RatePlan ratePlanB = new RatePlan();
        ratePlanB.setRatePlanCode("B");
        ratePlanB.setBaseRate(100);
        ratePlanB.setDate(simpleDate("11/28/2019"));

        RatePlanPeriod ratePlanPeriod = new RatePlanPeriod();
        ratePlanPeriod.setStartDate(simpleDate("11/28/2019"));
        ratePlanPeriod.setEndDate(simpleDate("11/30/2019"));

        kieSession.insert(ratePlanA);
        kieSession.insert(ratePlanB);
        kieSession.insert(ratePlanPeriod);

        kieSession.fireAllRules();
    }

    public static Date simpleDate(String date) throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.parse(date);
    }
}
