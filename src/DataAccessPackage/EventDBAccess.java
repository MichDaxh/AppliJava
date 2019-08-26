package DataAccessPackage;

import ExceptionPackage.*;
import ModelPackage.Event;
import ModelPackage.Player;
import ModelPackage.SearchModel;
import ModelPackage.DataTaskModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class EventDBAccess implements EventDataAccess{

    private Connection connection;
    private String sql;
    private PreparedStatement statement;
    private ResultSet data;
    private ArrayList<Event> allEvents;
    private ArrayList<Player> allPlayers;
    private ArrayList<SearchModel> searchResults;
    private ArrayList<DataTaskModel> taskResults;

    @Override
    public ArrayList<Event> getAllEvents() throws ConnectionException, AllEventsException, CashPrizeException, EndDateException, NbTeamsException, IdException {
        connect();
        sql = "select * from event";
        try{
            Event event;
            allEvents = new ArrayList<Event>();
            statement = connection.prepareStatement(sql);
            data =  statement.executeQuery();
            while(data.next()){
                String[] casters = new String[5];
                String[] sponsors = new String[5];
                casters[0] = data.getString("caster1_name");
                casters[1] = data.getString("caster2_name");
                casters[2] = data.getString("caster3_name");
                casters[3] = data.getString("caster4_name");
                casters[4] = data.getString("caster5_name");

                sponsors[0] = data.getString("sponsor1");
                sponsors[1] = data.getString("sponsor2");
                sponsors[2] = data.getString("sponsor3");
                sponsors[3] = data.getString("sponsor4");
                sponsors[4] = data.getString("sponsor5");
                event = new Event(data.getInt("id_event"),data.getInt("nb_team"), data.getInt("cash_prize"),data.getString("event_name"), data.getString("locality"), casters, sponsors,data.getDate("start_date"),data.getDate("end_date"),data.getBoolean("isMajor"));
                allEvents.add(event);
            }
        }
        catch(SQLException | CastersException | SponsorsException e){
            throw new AllEventsException(e.getMessage());
        }
        return allEvents;
    }
    @Override
    public ArrayList<Player> getAllPlayers() throws ConnectionException, AllPlayersException {
        connect();
        sql = "select * from player";
        try{
            Player player;
            allPlayers = new ArrayList<Player>();
            statement = connection.prepareStatement(sql);
            data = statement.executeQuery();
            while(data.next()){
                player = new Player(data.getInt("id_player"), data.getString("first_name"), data.getString("last_Name"), data.getString("pseudo"), data.getString("nationality"), data.getBoolean("isBench"));
                allPlayers.add(player);
            }
        }
        catch( SQLException e){
            throw new AllPlayersException(e.getMessage());
        }
        return allPlayers;
    }


    public ArrayList<SearchModel> search(int id, GregorianCalendar date) throws ConnectionException, SearchException {
        connect();
        sql = " select t.team_name, e.event_name, e.start_date, e.end_date from player pl join membership m on (pl.id_player = m.id_player) join team t on m.id_team = t.id_team join participation p on t.id_team = p.id_team join event e on p.id_event = e.id_event WHERE pl.id_player = ? AND e.start_date > ?";
        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setDate(2, new java.sql.Date(date.getTimeInMillis()));
            data = statement.executeQuery();
            searchResults = new ArrayList<SearchModel>();
            while(data.next()){
                SearchModel searchModel = new SearchModel(data.getString("team_name"), data.getString("event_name"), data.getDate("start_date"), data.getDate("end_date"));
                searchResults.add(searchModel);
            }
            if(searchResults.size() ==0)
                throw new SearchException("No results");
        }
        catch (SQLException e){
            throw new SearchException(e.getMessage());
        }
        return searchResults;
    }
    @Override
    public void addEvent(Event newEvent) throws ConnectionException, AddEventException{
        String eventName, locality;
        String[] sponsors, casters;
        Integer nbTeams, cashPrize;
        java.sql.Date sqlStartDate, sqlEndDate;
        boolean isMajor;
        connect();
        eventName = newEvent.getName();
        locality = newEvent.getLocality();
        sponsors = newEvent.getSponsor();
        casters = newEvent.getCaster();
        nbTeams = newEvent.getNbTeams();
        cashPrize = newEvent.getCashPrize();
        sqlStartDate = new java.sql.Date(newEvent.getStartDate().getTimeInMillis());
        sqlEndDate = new java.sql.Date(newEvent.getEndDate().getTimeInMillis());
        isMajor = newEvent.isMajor();
        sql = "INSERT INTO event (event_name, nb_team, cash_prize, locality, start_date, end_date, isMajor)VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try{
            statement = connection.prepareStatement(sql);
            statement.setString(1 , eventName);
            statement.setInt(2, nbTeams);
            statement.setInt(3, cashPrize);
            statement.setString(4, locality);
            statement.setDate(5, sqlStartDate);
            statement.setDate(6, sqlEndDate);
            statement.setBoolean(7, isMajor);
            int insertedLinesNumber = statement.executeUpdate();
            if (insertedLinesNumber == 0) throw new AddEventException("Unable to add line.");
            //retrieve event id
            int id;
            sql = "SELECT LAST_INSERT_ID() as id";
            statement.clearParameters();
            statement = connection.prepareStatement(sql);
            data = statement.executeQuery();
            data.next();
            id = data.getInt("id");

            //update of non required columns
            for(int i = 0; i < 5; i++){
                if ( sponsors[i] != null ) {
                    sql = "update event set sponsor"+ (i+1)+" = ? where id_event =  " + id;
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, sponsors[i]);
                    statement.executeUpdate( );
                }
                if(casters[i] != null){
                    sql = "update event set caster"+ (i+1) +"_name = ? where id_event = ' " + id  + " ' ";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, casters[i]);
                    statement.executeUpdate( );
                }
            }
        }
        catch (SQLException e){
            throw new AddEventException(e.getMessage());
        }
    }

    @Override
    public void deleteEvent(int eventId) throws DeleteException, ConnectionException {
        connect();
        sql ="DELETE from event where id_event = ? ";
        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1, eventId);
            statement.executeUpdate();
        }
        catch(SQLException e){
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public void modifyEvent(Event event) throws ConnectionException, UpdateException {
        connect();
        sql = "update event set event_name = ?, nb_team = ? , cash_prize = ? , locality = ? , start_date = ? , end_date = ? , isMajor= ? where id_event = ?";

        try{
            statement = connection.prepareStatement(sql);
            statement.setString(1 , event.getName());
            statement.setInt(2, event.getNbTeams());
            statement.setInt(3, event.getCashPrize());
            statement.setString(4, event.getLocality());
            java.sql.Date sqlStartDate = new Date(event.getStartDate().getTimeInMillis());
            java.sql.Date sqlEndDate = new java.sql.Date(event.getEndDate().getTimeInMillis());
            statement.setDate(5, sqlStartDate);
            statement.setDate(6, sqlEndDate);
            statement.setBoolean(7, event.isMajor());
            statement.setInt(8, event.getId());
            statement.executeUpdate();
            String[] sponsors = event.getSponsor();
            String[] casters = event.getCaster();
            for(int i = 0; i < 5; i++){
                if ( sponsors[i] != null && !sponsors[i].isEmpty() ) {
                    sql = "update event set sponsor"+ (i+1)+" = ? where id_event = ? ";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, sponsors[i]);
                    statement.setInt(2, event.getId());
                    statement.executeUpdate( );
                }
                if(casters[i] != null && !casters[i].isEmpty()){
                    sql = "update event set caster"+ (i+1) +"_name = ? where id_event = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, casters[i]);
                    statement.setInt(2, event.getId());
                    statement.executeUpdate( );
                }
            }

        }
        catch(SQLException e){
            throw new UpdateException(e.getMessage());
        }
    }

    public ArrayList<DataTaskModel> taskSearch() throws TaskException, ConnectionException {
        connect();
        sql="Select start_date, cash_prize from event order by start_date";
        try{
            statement = connection.prepareStatement(sql);
            taskResults = new ArrayList<DataTaskModel>();
            data = statement.executeQuery();
            while(data.next()){
                DataTaskModel result = new DataTaskModel(data.getDate("start_date"), data.getInt("cash_prize"));
                taskResults.add(result);
            }
        }
        catch (SQLException e){
            throw new TaskException(e.getMessage());
        }
        return taskResults;
    }

    private void connect() throws ConnectionException{
            try{
                connection = SingletonConnexion.getInstance();
            }
            catch (SQLException e){
                throw new ConnectionException(e.getMessage());
            }
    }

    public void disconnect() throws CloseException{
        try{
            if(connection != null) connection.close();
        }
        catch(SQLException e){
            throw new CloseException(e.getMessage());
        }
    }

}