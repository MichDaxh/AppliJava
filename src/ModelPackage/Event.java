package ModelPackage;

import ExceptionPackage.*;

import java.util.Date;
import java.util.GregorianCalendar;

public class Event {
    private Integer id, nbTeams, cashPrize;
    private String  name, locality;
    private String[]  caster, sponsor;
    private GregorianCalendar startDate, endDate;
    private boolean isMajor;

    public Event(Integer id , int nbTeams, int cashPrize, String name, String locality, String[] caster, String[] sponsor, Date startDate, Date endDate, boolean isMajor) throws NbTeamsException, CashPrizeException, EndDateException, CastersException, SponsorsException, IdException {
        setId(id);
        setNbTeams(nbTeams);
        setCashPrize(cashPrize);
        this.name = name;
        this.locality = locality;
        if(caster.length <= 5)
            this.caster = caster;
        else
            throw new CastersException("More than 5 casters.");
        if(sponsor.length <= 5)
            this.sponsor = sponsor;
        else
            throw new SponsorsException("More than 5 sponsors.");
        setStartDate(startDate);
        setEndDate(endDate);
        this.isMajor = isMajor;
    }

    public Event(int nbTeams, int cashPrize, String name, String locality, String[] caster, String[] sponsor, Date startDate, Date endDate, boolean isMajor) throws NbTeamsException, CashPrizeException, EndDateException, CastersException, SponsorsException, IdException {
        this( null, nbTeams, cashPrize, name, locality, caster, sponsor, startDate, endDate, isMajor);
    }


    public void setStartDate(Date startDate) {
        this.startDate = new GregorianCalendar();
        this.startDate.setTime(startDate);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) throws IdException {
        if(id != null){
            if(id>0)
                this.id = id;
            else
                throw new IdException("Id must be > 0.");
        }
        else
            id = null;
    }

    public void setNbTeams(Integer nbTeams) throws NbTeamsException{
        if(nbTeams > 1) this.nbTeams = nbTeams;
        else throw new NbTeamsException("Must be >= 2.");
    }

    public void setCashPrize(Integer cashPrize) throws CashPrizeException {
        if(cashPrize > 0) this.cashPrize = cashPrize;
        else throw new CashPrizeException("Must be > 0");
    }

    public void setEndDate(Date endDate) throws EndDateException {
        GregorianCalendar temp = new GregorianCalendar();
        temp.setTime(endDate);
        if(temp.compareTo(startDate) > 0) {
            this.endDate = new GregorianCalendar();
            this.endDate.setTime(endDate);
        }
        else throw new EndDateException();
    }

    public int getNbTeams() {
        return nbTeams;
    }

    public int getCashPrize() {
        return cashPrize;
    }

    public String getName() {
        return name;
    }

    public String getLocality() {
        return locality;
    }

    public String[] getCaster() {
        return caster;
    }

    public String[] getSponsor() {
        return sponsor;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public boolean isMajor() {
        return isMajor;
    }
}
