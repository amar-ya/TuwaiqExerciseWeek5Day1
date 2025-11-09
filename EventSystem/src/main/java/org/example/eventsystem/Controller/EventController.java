package org.example.eventsystem.Controller;

import org.example.eventsystem.Api.EventApiResponse;
import org.example.eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController
{
    public static int id = 1;
    ArrayList<Event> events= new ArrayList<>();

    @PostMapping("/create")
    public EventApiResponse createNewEvent(@RequestBody Event newEvent){
        newEvent.setId(id++);
        events.add(newEvent);
        return new EventApiResponse("event has been added successfully");
    }

    @GetMapping("/get")
    public ArrayList<Event> getAllEvents(){
        return events;
    }

    @PutMapping("/update/{id}")
    public EventApiResponse updateEvent(@PathVariable int id, @RequestBody Event event){
        for (Event e : events){
            if (e.getId() == id){
                if (event.getStartDate()!=null) e.setStartDate(event.getStartDate());
                if (event.getEndDate()!=null) e.setEndDate(event.getEndDate());
                if (event.getDescription()!=null) e.setDescription(event.getDescription());
                return new EventApiResponse("event has been updated successfully");
            }
        }
        return new EventApiResponse("event was not found");
    }

    @DeleteMapping("/delete/{id}")
    public EventApiResponse deleteEvent(@PathVariable int id){
        for (Event e : events){
            if (e.getId() == id){
                events.remove(e);
                return new EventApiResponse("event has been deleted successfully");
            }
        }
        return new EventApiResponse("event was not found");
    }

    //make sure to put the id before the capacity when calling this API
    @PutMapping("/update/capacity/{id}/{capacity}")
    public EventApiResponse changeEventCapacity(@PathVariable int id, @PathVariable int capacity){
        for (Event e : events){
            if (e.getId() == id){
                if (capacity == e.getCapacity()) return new EventApiResponse("capacity was "+e.getCapacity()+" already");
                else if (capacity == 0) return new EventApiResponse("capacity cant be 0");
                else {
                    e.setCapacity(capacity);
                    return new EventApiResponse("event has been updated successfully");
                }
            }
        }
        return new EventApiResponse("event was not found");
    }


    @GetMapping("/get/{id}")
    public Event searchEventById(@PathVariable int id){
        for (Event e : events){
            if (e.getId() == id){
                return e;
            }
        }
        return null;
    }
}
