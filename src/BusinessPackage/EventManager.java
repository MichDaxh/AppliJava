package BusinessPackage;

import DataAccessPackage.EventDBAccess;
import DataAccessPackage.EventDataAccess;
import ExceptionPackage.*;
import ModelPackage.Event;
import ModelPackage.Player;
import ModelPackage.SearchModel;
import ModelPackage.DataTaskModel;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class EventManager {
    private EventDataAccess dao;

    public EventManager(){
        setDao(new EventDBAccess());
    }

    public ArrayList<Event> getAllEvents() throws AllEventsException, ConnectionException, CashPrizeException, EndDateException, NbTeamsException, IdException {
            return dao.getAllEvents();
    }

    public ArrayList<Player> getAllPlayers() throws ConnectionException, AllPlayersException {
        return dao.getAllPlayers();
    }

    public ArrayList<SearchModel> search(int id, GregorianCalendar date) throws ConnectionException, SearchException {
        return dao.search(id, date);
    }

    public void addEvent(Event newEvent) throws ConnectionException, AddEventException {
        //check
        dao.addEvent(newEvent);
    }

    public void modifyEvent(Event event) throws ConnectionException, UpdateException {
        dao.modifyEvent(event);
    }

    public void deleteEvent(int eventId) throws ConnectionException, DeleteException {
        dao.deleteEvent(eventId);
    }
    public ArrayList<DataTaskModel> taskSearch() throws TaskException, ConnectionException{
        return dao.taskSearch();
    }

    public void setDao(EventDataAccess dao) {
        this.dao = dao;
    }

    public void closingProcedure() throws CloseException{
        dao.disconnect();
    }
}
