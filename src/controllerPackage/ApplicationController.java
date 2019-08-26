package controllerPackage;

import BusinessPackage.EventManager;
import BusinessPackage.Task;
import ExceptionPackage.*;
import ModelPackage.Event;
import ModelPackage.Player;
import ModelPackage.ResultTaskModel;
import ModelPackage.SearchModel;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ApplicationController {
    private EventManager eventManager;
    private Task task;

    public ApplicationController(){
        eventManager = new EventManager();
        task = new Task();
    }

    public ArrayList<Event> getAllEvents() throws AllEventsException, ConnectionException, CashPrizeException, EndDateException, NbTeamsException, IdException {
        return eventManager.getAllEvents();
    }

    public ArrayList<Player> getAllPlayers() throws ConnectionException, AllPlayersException {
        return eventManager.getAllPlayers();
    }

    public void addEvent(Event newEvent) throws ConnectionException, AddEventException {
        eventManager.addEvent(newEvent);
    }

    public void modifyEvent(Event event) throws ConnectionException, UpdateException {
        eventManager.modifyEvent(event);
    }

    public void closingProcedure() throws CloseException{
        eventManager.closingProcedure();
    }

    public void deleteEvent(int eventId) throws ConnectionException, DeleteException {
        eventManager.deleteEvent(eventId);
    }

    public ArrayList<ResultTaskModel> startTask() throws ConnectionException, TaskException {
        return task.startTask();
    }

    public ArrayList<SearchModel> search(int id, GregorianCalendar date) throws ConnectionException, SearchException {
        return eventManager.search(id, date);
    }

}
