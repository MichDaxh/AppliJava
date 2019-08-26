package ModelPackage;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataTaskModel {
    private int cashPrize;
    private Date startDate;

    public DataTaskModel(Date startDate, int cashPrize) {
        this.cashPrize = cashPrize;
        this.startDate = startDate;
    }

    public int getCashPrize() {
        return cashPrize;
    }

    public int getYear(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        int year = calendar.get(Calendar.YEAR);
        return year;
    }
}
