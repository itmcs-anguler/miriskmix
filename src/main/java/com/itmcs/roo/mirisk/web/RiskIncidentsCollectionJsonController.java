package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.RiskIncidentService;
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
 * = RiskIncidentsCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = RiskIncident.class, pathPrefix = "/mirisk", type = ControllerType.COLLECTION)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/riskincidents", name = "RiskIncidentsCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class RiskIncidentsCollectionJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskIncidentService riskIncidentService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskIncidentService
     */
    @Autowired
    public RiskIncidentsCollectionJsonController(RiskIncidentService riskIncidentService) {
        this.riskIncidentService = riskIncidentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskIncidentService
     */
    public RiskIncidentService getRiskIncidentService() {
        return riskIncidentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncidentService
     */
    public void setRiskIncidentService(RiskIncidentService riskIncidentService) {
        this.riskIncidentService = riskIncidentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<RiskIncident>> list(GlobalSearch globalSearch, Pageable pageable) {
        Page<RiskIncident> riskIncidents = getRiskIncidentService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(riskIncidents);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(RiskIncidentsCollectionJsonController.class).list(null, null)).build().encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncident
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody RiskIncident riskIncident, BindingResult result) {
        if (riskIncident.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        RiskIncident newRiskIncident = getRiskIncidentService().save(riskIncident);
        UriComponents showURI = RiskIncidentsItemJsonController.showURI(newRiskIncident);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncidents
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<RiskIncident> riskIncidents, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getRiskIncidentService().save(riskIncidents);
        return ResponseEntity.created(listURI().toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncidents
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<RiskIncident> riskIncidents, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getRiskIncidentService().save(riskIncidents);
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
        getRiskIncidentService().delete(ids);
        return ResponseEntity.ok().build();
    }
}
