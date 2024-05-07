package ru.ScheduleOfEvents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.services.EventsService;
import ru.ScheduleOfEvents.util.InputTextExtractor;

import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping()
public class EventsController {
    private final EventsService eventsService;
    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @GetMapping("/events")
    public String showEvents(@RequestParam(value = "firstParam",required = false,defaultValue = "defaultFirst") String firstParam,
                             @RequestParam(value = "secondParam",required = false,defaultValue = "defaultSecond") String secondParam,
                             @RequestParam(value = "thirdParam",required = false,defaultValue = "defaultThird") String thirdParam,Model model) {
        model.addAttribute("firstParam", firstParam);
        model.addAttribute("secondParam", secondParam);
        model.addAttribute("thirdParam", thirdParam);
        model.addAttribute("search",new InputTextExtractor());
        List<Event> eventList = eventsService.findAll();
        if (!thirdParam.equals("defaultThird")){
            eventList = eventsService.findAllByName(thirdParam);
        }
        if (!firstParam.equals("defaultFirst")){
            eventList = eventList.stream().sorted(new Comparator<Event>() {
                @Override
                public int compare(Event o1, Event o2) {
                    int i = switch (firstParam) {
                        case "age" -> o2.compareAge(o1.getAge());
                        case "date" -> o1.getDate().compareTo(o2.getDate());
                        case "ageReverse" -> o1.compareAge(o2.getAge());
                        default -> o2.getDate().compareTo(o1.getDate());
                    };
                    return i;
                }
            }).toList();
        }
        if (!secondParam.equals("defaultSecond")){
            eventList = eventList.stream().sorted(new Comparator<Event>() {
                @Override
                public int compare(Event o1, Event o2) {
                    if (o1.getDescription().equals(o2.getDescription())) {
                        return o1.getDate().compareTo(o2.getDate());
                    } else {
                        return switch (secondParam) {
                            case "concert" -> o1.getDescription().equals("концерт") ? -1 : 1;
                            case "play" -> o1.getDescription().equals("детский спектакль") ? -1 : 1;
                            case "show" -> o1.getDescription().equals("новогоднее шоу") ? -1 : 1;
                            case "performance" -> o1.getDescription().equals("представление для взрослых") ? -1 : 1;
                            default -> o2.getDate().compareTo(o1.getDate());
                        };
                    }
                }
            }).toList();
        }
        model.addAttribute("event",eventList);
        return "sheduleEvents/Events";
    }

    @PostMapping("/events/{firstParam}/{secondParam}/{thirdParam}")
    public String filterEvent(@PathVariable("firstParam") String firstParam, @PathVariable("secondParam") String secondParam,@PathVariable("thirdParam") String thirdParam){
        return "redirect:/events?firstParam=" + firstParam + "&secondParam=" + secondParam + "&thirdParam=" + thirdParam;
    }
    @PostMapping("/events/{reboot}")
    public String rebootEvent(){
        return "redirect:/events";
    }

    @PostMapping("/events/search")
    public String performSearch(InputTextExtractor inputTextExtractor) {
        return "redirect:/events?thirdParam=" + inputTextExtractor.getName();
    }
}