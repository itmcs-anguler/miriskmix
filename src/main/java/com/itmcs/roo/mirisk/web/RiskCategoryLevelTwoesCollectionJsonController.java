package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.RiskCategoryLevelTwoService;
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
 * = RiskCategoryLevelTwoesCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = RiskCategoryLevelTwo.class, pathPrefix = "/mirisk", type = ControllerType.COLLECTION)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/riskcategoryleveltwoes", name = "RiskCategoryLevelTwoesCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class RiskCategoryLevelTwoesCollectionJsonController {

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
    public RiskCategoryLevelTwoesCollectionJsonController(RiskCategoryLevelTwoService riskCategoryLevelTwoService) {
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
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<RiskCategoryLevelTwo>> list(GlobalSearch globalSearch, Pageable pageable) {
        Page<RiskCategoryLevelTwo> riskCategoryLevelTwoes = getRiskCategoryLevelTwoService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(riskCategoryLevelTwoes);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(RiskCategoryLevelTwoesCollectionJsonController.class).list(null, null)).build().encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwo
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody RiskCategoryLevelTwo riskCategoryLevelTwo, BindingResult result) {
        if (riskCategoryLevelTwo.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        RiskCategoryLevelTwo newRiskCategoryLevelTwo = getRiskCategoryLevelTwoService().save(riskCategoryLevelTwo);
        UriComponents showURI = RiskCategoryLevelTwoesItemJsonController.showURI(newRiskCategoryLevelTwo);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwoes
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<RiskCategoryLevelTwo> riskCategoryLevelTwoes, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getRiskCategoryLevelTwoService().save(riskCategoryLevelTwoes);
        return ResponseEntity.created(listURI().toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwoes
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<RiskCategoryLevelTwo> riskCategoryLevelTwoes, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getRiskCategoryLevelTwoService().save(riskCategoryLevelTwoes);
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
        getRiskCategoryLevelTwoService().delete(ids);
        return ResponseEntity.ok().build();
    }
}
