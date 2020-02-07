package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.RiskCategoryLevelOneService;
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
 * = RiskCategoryLevelOnesItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = RiskCategoryLevelOne.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/riskcategorylevelones/{riskCategoryLevelOne}", name = "RiskCategoryLevelOnesItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class RiskCategoryLevelOnesItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskCategoryLevelOneService riskCategoryLevelOneService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskCategoryLevelOneService
     */
    @Autowired
    public RiskCategoryLevelOnesItemJsonController(RiskCategoryLevelOneService riskCategoryLevelOneService) {
        this.riskCategoryLevelOneService = riskCategoryLevelOneService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskCategoryLevelOneService
     */
    public RiskCategoryLevelOneService getRiskCategoryLevelOneService() {
        return riskCategoryLevelOneService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOneService
     */
    public void setRiskCategoryLevelOneService(RiskCategoryLevelOneService riskCategoryLevelOneService) {
        this.riskCategoryLevelOneService = riskCategoryLevelOneService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskCategoryLevelOne
     */
    @ModelAttribute
    public RiskCategoryLevelOne getRiskCategoryLevelOne(@PathVariable("riskCategoryLevelOne") Long id) {
        RiskCategoryLevelOne riskCategoryLevelOne = riskCategoryLevelOneService.findOne(id);
        if (riskCategoryLevelOne == null) {
            throw new NotFoundException(String.format("RiskCategoryLevelOne with identifier '%s' not found", id));
        }
        return riskCategoryLevelOne;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOne
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute RiskCategoryLevelOne riskCategoryLevelOne) {
        return ResponseEntity.ok(riskCategoryLevelOne);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOne
     * @return UriComponents
     */
    public static UriComponents showURI(RiskCategoryLevelOne riskCategoryLevelOne) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(RiskCategoryLevelOnesItemJsonController.class).show(riskCategoryLevelOne)).buildAndExpand(riskCategoryLevelOne.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedRiskCategoryLevelOne
     * @param riskCategoryLevelOne
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute RiskCategoryLevelOne storedRiskCategoryLevelOne, @Valid @RequestBody RiskCategoryLevelOne riskCategoryLevelOne, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        riskCategoryLevelOne.setId(storedRiskCategoryLevelOne.getId());
        getRiskCategoryLevelOneService().save(riskCategoryLevelOne);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOne
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute RiskCategoryLevelOne riskCategoryLevelOne) {
        getRiskCategoryLevelOneService().delete(riskCategoryLevelOne);
        return ResponseEntity.ok().build();
    }
}
