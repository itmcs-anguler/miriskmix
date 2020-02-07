package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.EventType;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.EventTypeService;
import io.springlets.web.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * = EventTypesItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = EventType.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/eventtypes/{eventType}", name = "EventTypesItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventTypesItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private EventTypeService eventTypeService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param eventTypeService
     */
    @Autowired
    public EventTypesItemJsonController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return EventTypeService
     */
    public EventTypeService getEventTypeService() {
        return eventTypeService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventTypeService
     */
    public void setEventTypeService(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return EventType
     */
    @ModelAttribute
    public EventType getEventType(@PathVariable("eventType") Long id) {
        EventType eventType = eventTypeService.findOne(id);
        if (eventType == null) {
            throw new NotFoundException(String.format("EventType with identifier '%s' not found", id));
        }
        return eventType;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute EventType eventType) {
        return ResponseEntity.ok(eventType);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     * @return UriComponents
     */
    public static UriComponents showURI(EventType eventType) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(EventTypesItemJsonController.class).show(eventType)).buildAndExpand(eventType.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedEventType
     * @param eventType
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute EventType storedEventType, @Valid @RequestBody EventType eventType, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        eventType.setId(storedEventType.getId());
        getEventTypeService().save(eventType);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute EventType eventType) {
        getEventTypeService().delete(eventType);
        return ResponseEntity.ok().build();
    }
}
