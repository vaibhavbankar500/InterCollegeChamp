package com.example.vaibhav.intercollegechamp.model;

/**
 * Created by vaibhav on 19/4/17.
 */

public class Event {

private String EventId,EventName,EventDate,EventVenue,EventType,EventLastDate,EventRules,EventTime,EventCoordinator,EventCoordinatorNumber;


    public String getEventTime() {
        return EventTime;
    }

    public void setEventTime(String eventTime) {
        EventTime = eventTime;
    }

    public String getEventRules() {
        return EventRules;
    }

    public void setEventRules(String eventRules) {
        EventRules = eventRules;
    }

    public String getEventLastDate() {
        return EventLastDate;
    }

    public void setEventLastDate(String eventLastDate) {
        EventLastDate = eventLastDate;
    }

    public String getEventType() {
        return EventType;
    }

    public void setEventType(String eventType) {
        EventType = eventType;
    }

    public String getEventCoordinator() {
        return EventCoordinator;
    }

    public void setEventCoordinator(String eventCoordinator) {
        EventCoordinator = eventCoordinator;
    }

    public String getEventCoordinatorNumber() {
        return EventCoordinatorNumber;
    }

    public void setEventCoordinatorNumber(String eventCoordinatorNumber) {
        EventCoordinatorNumber = eventCoordinatorNumber;
    }

    public Event(String EventId, String EventName, String EventDate, String EventVenue,
                 String EventType, String EventLastDate, String EventRules, String EventTime, String EventCoordinator, String EventCoordinatorNumber) {

        this.EventId=EventId;
        this.EventName=EventName;
        this.EventType=EventType;
        this.EventRules=EventRules;
        this.EventDate=EventDate;
        this.EventTime=EventTime;
        this.EventLastDate=EventLastDate;
        this.EventVenue=EventVenue;
        this.EventCoordinator=EventCoordinator;

        this.EventCoordinatorNumber=EventCoordinatorNumber;



    }
    public Event() {    }

    public String getEventId() {
        return EventId;
    }

    public void setEventId(String eventId) {
        EventId = eventId;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String eventDate) {
        EventDate = eventDate;
    }

    public String getEventVenue() {
        return EventVenue;
    }

    public void setEventVenue(String eventVenue) {
        EventVenue = eventVenue;
    }


}