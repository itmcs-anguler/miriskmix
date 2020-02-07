package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.RiskCategoryLevelTwoService;
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
 * = RiskCategoryLevelTwoesItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = RiskCategoryLevelTwo.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/riskcategoryleveltwoes/{riskCategoryLevelTwo}", name = "RiskCategoryLevelTwoesItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class RiskCategoryLevelTwoesItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskCategoryLevelTwoService riskCategoryLevelTwoService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskCategoryLevelTwoService
     */
    @Autowired
    public RiskCategoryLevelTwoesItemJsonController(RiskCategoryLevelTwoService riskCategoryLevelTwoService) {
        this.riskCategoryLevelTwoService = riskCategoryLevelTwoService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskCategoryLevelTwoService
     */
    public RiskCategoryLevelTwoService getRiskCategoryLevelTwoService() {
        return riskCategoryLevelTwoService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwoService
     */
    public void setRiskCategoryLevelTwoService(RiskCategoryLevelTwoService riskCategoryLevelTwoService) {
        this.riskCategoryLevelTwoService = riskCategoryLevelTwoService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskCategoryLevelTwo
     */
    @ModelAttribute
    public RiskCategoryLevelTwo getRiskCategoryLevelTwo(@PathVariable("riskCategoryLevelTwo") Long id) {
        RiskCategoryLevelTwo riskCategoryLevelTwo = riskCategoryLevelTwoService.findOne(id);
        if (riskCategoryLevelTwo == null) {
            throw new NotFoundException(String.format("RiskCategoryLevelTwo with identifier '%s' not found", id));
        }
        return riskCategoryLevelTwo;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwo
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute RiskCategoryLevelTwo riskCategoryLevelTwo) {
        return ResponseEntity.ok(riskCategoryLevelTwo);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwo
     * @return UriComponents
     */
    public static UriComponents showURI(RiskCategoryLevelTwo riskCategoryLevelTwo) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(RiskCategoryLevelTwoesItemJsonController.class).show(riskCategoryLevelTwo)).buildAndExpand(riskCategoryLevelTwo.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedRiskCategoryLevelTwo
     * @param riskCategoryLevelTwo
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute RiskCategoryLevelTwo storedRiskCategoryLevelTwo, @Valid @RequestBody RiskCategoryLevelTwo riskCategoryLevelTwo, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        riskCategoryLevelTwo.setId(storedRiskCategoryLevelTwo.getId());
        getRiskCategoryLevelTwoService().save(riskCategoryLevelTwo);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwo
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute RiskCategoryLevelTwo riskCategoryLevelTwo) {
        getRiskCategoryLevelTwoService().delete(riskCategoryLevelTwo);
        return ResponseEntity.ok().build();
    }
}
