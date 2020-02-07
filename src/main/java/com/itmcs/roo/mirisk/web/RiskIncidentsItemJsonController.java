package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.RiskIncidentService;
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
 * = RiskIncidentsItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = RiskIncident.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/riskincidents/{riskIncident}", name = "RiskIncidentsItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class RiskIncidentsItemJsonController {

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
    public RiskIncidentsItemJsonController(RiskIncidentService riskIncidentService) {
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
     * @param id
     * @return RiskIncident
     */
    @ModelAttribute
    public RiskIncident getRiskIncident(@PathVariable("riskIncident") Long id) {
        RiskIncident riskIncident = riskIncidentService.findOne(id);
        if (riskIncident == null) {
            throw new NotFoundException(String.format("RiskIncident with identifier '%s' not found", id));
        }
        return riskIncident;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncident
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute RiskIncident riskIncident) {
        return ResponseEntity.ok(riskIncident);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncident
     * @return UriComponents
     */
    public static UriComponents showURI(RiskIncident riskIncident) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(RiskIncidentsItemJsonController.class).show(riskIncident)).buildAndExpand(riskIncident.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedRiskIncident
     * @param riskIncident
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute RiskIncident storedRiskIncident, @Valid @RequestBody RiskIncident riskIncident, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        riskIncident.setId(storedRiskIncident.getId());
        getRiskIncidentService().save(riskIncident);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncident
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute RiskIncident riskIncident) {
        getRiskIncidentService().delete(riskIncident);
        return ResponseEntity.ok().build();
    }
}
