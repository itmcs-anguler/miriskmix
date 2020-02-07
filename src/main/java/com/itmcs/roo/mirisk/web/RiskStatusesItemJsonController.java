package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskStatus;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.RiskStatusService;
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
 * = RiskStatusesItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = RiskStatus.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/riskstatuses/{riskStatus}", name = "RiskStatusesItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class RiskStatusesItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskStatusService riskStatusService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskStatusService
     */
    @Autowired
    public RiskStatusesItemJsonController(RiskStatusService riskStatusService) {
        this.riskStatusService = riskStatusService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskStatusService
     */
    public RiskStatusService getRiskStatusService() {
        return riskStatusService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatusService
     */
    public void setRiskStatusService(RiskStatusService riskStatusService) {
        this.riskStatusService = riskStatusService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskStatus
     */
    @ModelAttribute
    public RiskStatus getRiskStatus(@PathVariable("riskStatus") Long id) {
        RiskStatus riskStatus = riskStatusService.findOne(id);
        if (riskStatus == null) {
            throw new NotFoundException(String.format("RiskStatus with identifier '%s' not found", id));
        }
        return riskStatus;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute RiskStatus riskStatus) {
        return ResponseEntity.ok(riskStatus);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     * @return UriComponents
     */
    public static UriComponents showURI(RiskStatus riskStatus) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(RiskStatusesItemJsonController.class).show(riskStatus)).buildAndExpand(riskStatus.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedRiskStatus
     * @param riskStatus
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute RiskStatus storedRiskStatus, @Valid @RequestBody RiskStatus riskStatus, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        riskStatus.setId(storedRiskStatus.getId());
        getRiskStatusService().save(riskStatus);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute RiskStatus riskStatus) {
        getRiskStatusService().delete(riskStatus);
        return ResponseEntity.ok().build();
    }
}
