package ModelPackage;

import java.util.Date;

public class SearchModel {
    private String teamName, eventName;
    private Date eventStartDate, eventEndDate;

    public SearchModel(String teamName, String eventName, Date eventStartDate, Date eventEndDate) {
        this.teamName = teamName;
        this.eventName = eventName;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getEventName() {
        return eventName;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }
}
