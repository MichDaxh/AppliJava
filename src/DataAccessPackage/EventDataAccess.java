package DataAccessPackage;

import ExceptionPackage.*;
import ModelPackage.Event;
import ModelPackage.Player;
import ModelPackage.SearchModel;
import ModelPackage.DataTaskModel;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface EventDataAccess {

    public ArrayList<Event> getAllEvents() throws ConnectionException, AllEventsException, CashPrizeException, EndDateException, NbTeamsException, IdException;
    public void addEvent(Event newEvent) throws ConnectionException, AddEventException;
    public void deleteEvent(int eventId) throws DeleteException, ConnectionException;
    public void modifyEvent(Event event) throws ConnectionException, UpdateException;
    public void disconnect() throws CloseException;
    public ArrayList<Player> getAllPlayers() throws ConnectionException, AllPlayersException;
    public ArrayList<SearchModel> search(int id, GregorianCalendar date)throws ConnectionException, SearchException;
    public ArrayList<DataTaskModel> taskSearch() throws TaskException, ConnectionException;
}

