package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.RiskCategoryLevelOneService;
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
 * = RiskCategoryLevelOnesCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = RiskCategoryLevelOne.class, pathPrefix = "/mirisk", type = ControllerType.COLLECTION)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/riskcategorylevelones", name = "RiskCategoryLevelOnesCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class RiskCategoryLevelOnesCollectionJsonController {

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
    public RiskCategoryLevelOnesCollectionJsonController(RiskCategoryLevelOneService riskCategoryLevelOneService) {
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
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<RiskCategoryLevelOne>> list(GlobalSearch globalSearch, Pageable pageable) {
        Page<RiskCategoryLevelOne> riskCategoryLevelOnes = getRiskCategoryLevelOneService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(riskCategoryLevelOnes);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(RiskCategoryLevelOnesCollectionJsonController.class).list(null, null)).build().encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOne
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody RiskCategoryLevelOne riskCategoryLevelOne, BindingResult result) {
        if (riskCategoryLevelOne.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        RiskCategoryLevelOne newRiskCategoryLevelOne = getRiskCategoryLevelOneService().save(riskCategoryLevelOne);
        UriComponents showURI = RiskCategoryLevelOnesItemJsonController.showURI(newRiskCategoryLevelOne);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOnes
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<RiskCategoryLevelOne> riskCategoryLevelOnes, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getRiskCategoryLevelOneService().save(riskCategoryLevelOnes);
        return ResponseEntity.created(listURI().toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOnes
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<RiskCategoryLevelOne> riskCategoryLevelOnes, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getRiskCategoryLevelOneService().save(riskCategoryLevelOnes);
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
        getRiskCategoryLevelOneService().delete(ids);
        return ResponseEntity.ok().build();
    }
}
