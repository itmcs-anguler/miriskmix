package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.EventStatus;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.EventStatusService;
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
 * = EventStatusesItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = EventStatus.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/eventstatuses/{eventStatus}", name = "EventStatusesItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventStatusesItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private EventStatusService eventStatusService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param eventStatusService
     */
    @Autowired
    public EventStatusesItemJsonController(EventStatusService eventStatusService) {
        this.eventStatusService = eventStatusService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return EventStatusService
     */
    public EventStatusService getEventStatusService() {
        return eventStatusService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatusService
     */
    public void setEventStatusService(EventStatusService eventStatusService) {
        this.eventStatusService = eventStatusService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return EventStatus
     */
    @ModelAttribute
    public EventStatus getEventStatus(@PathVariable("eventStatus") Long id) {
        EventStatus eventStatus = eventStatusService.findOne(id);
        if (eventStatus == null) {
            throw new NotFoundException(String.format("EventStatus with identifier '%s' not found", id));
        }
        return eventStatus;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute EventStatus eventStatus) {
        return ResponseEntity.ok(eventStatus);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @return UriComponents
     */
    public static UriComponents showURI(EventStatus eventStatus) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(EventStatusesItemJsonController.class).show(eventStatus)).buildAndExpand(eventStatus.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedEventStatus
     * @param eventStatus
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute EventStatus storedEventStatus, @Valid @RequestBody EventStatus eventStatus, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        eventStatus.setId(storedEventStatus.getId());
        getEventStatusService().save(eventStatus);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute EventStatus eventStatus) {
        getEventStatusService().delete(eventStatus);
        return ResponseEntity.ok().build();
    }
}
