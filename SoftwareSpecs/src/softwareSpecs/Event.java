package src.softwareSpecs;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 4/12/15
 * Time: 8:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Event {

    private String eventName;
    private int eventTime;
    private ArrayList<String> eventDates;

    public Event(){

        eventDates = new ArrayList<String>();

    }

    public ArrayList<String> getEventDates() {
        return eventDates;
    }

    public void setEventDates(ArrayList<String> eventDates) {
        this.eventDates = eventDates;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }



    public int getEventTime() {
        return eventTime;
    }

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }




}
