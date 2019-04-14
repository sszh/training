package demo;

import java.util.Date;

/**
 * Created by admin on 2019/4/14.
 */
public class RatePlan
{
    private String ratePlanCode;
    private int baseRate;
    private int rate;
    private Date date;

    public String getRatePlanCode()
    {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode)
    {
        this.ratePlanCode = ratePlanCode;
    }

    public int getBaseRate()
    {
        return baseRate;
    }

    public void setBaseRate(int baseRate)
    {
        this.baseRate = baseRate;
    }

    public int getRate()
    {
        return rate;
    }

    public void setRate(int rate)
    {
        this.rate = rate;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
}
