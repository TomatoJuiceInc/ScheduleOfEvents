package ru.ScheduleOfEvents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.services.EventsService;
import ru.ScheduleOfEvents.util.InputTextExtractor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;


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
                             @RequestParam(value = "thirdParam",required = false,defaultValue = "defaultThird") String thirdParam,
                             @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                             @RequestParam(value = "limit", required = false, defaultValue = "5") int limit,
                             Model model) {
        Pageable pageable = PageRequest.of(page, limit);
        List<Event> eventList = eventsService.findAll().stream().filter(event -> event.isStatus()).toList();
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
                    if (o1.getCategory().equals(o2.getCategory())) {
                        return o1.getDate().compareTo(o2.getDate());
                    } else {
                        return switch (secondParam) {
                            case "concert" -> o1.getCategory().equals("концерт") ? -1 : 1;
                            case "play" -> o1.getCategory().equals("детский спектакль") ? -1 : 1;
                            case "show" -> o1.getCategory().equals("новогоднее шоу") ? -1 : 1;
                            case "performance" -> o1.getCategory().equals("представление для взрослых") ? -1 : 1;
                            default -> o2.getDate().compareTo(o1.getDate());
                        };
                    }
                }
            }).toList();
        }
        Page<Event> eventPage = convertListToPage(eventList, pageable);

        model.addAttribute("event",eventPage);
        model.addAttribute ("numbers", IntStream.range(0,eventPage.getTotalPages()).toArray());

        model.addAttribute("firstParam", firstParam);
        model.addAttribute("secondParam", secondParam);
        model.addAttribute("thirdParam", thirdParam);
        model.addAttribute("page",page);
        model.addAttribute("search",new InputTextExtractor());

        return "scheduleEvents/Events";
    }

    @PostMapping("/events/{firstParam}/{secondParam}/{thirdParam}/{page}")
    public String filterEvent(@PathVariable("firstParam") String firstParam, @PathVariable("secondParam") String secondParam,@PathVariable("thirdParam") String thirdParam,@PathVariable("page") int page){
        return "redirect:/events?firstParam=" + firstParam + "&secondParam=" + secondParam + "&thirdParam=" + thirdParam + "&page=" + page;
    }
    @PostMapping("/events/{reboot}")
    public String rebootEvent(){
        return "redirect:/events";
    }

    @PostMapping("/events/search")
    public String performSearch(InputTextExtractor inputTextExtractor) {
        return "redirect:/events?thirdParam=" + inputTextExtractor.getName();
    }
    public Page<Event> convertListToPage(List<Event> eventList, Pageable pageable) {
        int total = eventList.size();
        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.min((start + pageable.getPageSize()), total);
        List<Event> paginatedList = eventList.subList(start, end);
        return new PageImpl<>(paginatedList, pageable, total);
    }
}