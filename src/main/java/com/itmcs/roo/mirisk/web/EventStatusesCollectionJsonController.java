package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.EventStatus;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.EventStatusService;
import io.springlets.data.domain.GlobalSearch;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * = EventStatusesCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = EventStatus.class, pathPrefix = "/mirisk", type = ControllerType.COLLECTION)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/eventstatuses", name = "EventStatusesCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventStatusesCollectionJsonController {

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
    public EventStatusesCollectionJsonController(EventStatusService eventStatusService) {
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
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<EventStatus>> list(GlobalSearch globalSearch, Pageable pageable) {
        Page<EventStatus> eventStatuses = getEventStatusService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(eventStatuses);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(EventStatusesCollectionJsonController.class).list(null, null)).build().encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody EventStatus eventStatus, BindingResult result) {
        if (eventStatus.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        EventStatus newEventStatus = getEventStatusService().save(eventStatus);
        UriComponents showURI = EventStatusesItemJsonController.showURI(newEventStatus);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatuses
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<EventStatus> eventStatuses, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getEventStatusService().save(eventStatuses);
        return ResponseEntity.created(listURI().toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatuses
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<EventStatus> eventStatuses, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getEventStatusService().save(eventStatuses);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        getEventStatusService().delete(ids);
        return ResponseEntity.ok().build();
    }
}
