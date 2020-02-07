package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.ResidualImpactAssessmentService;
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
 * = ResidualImpactAssessmentsCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = ResidualImpactAssessment.class, pathPrefix = "/mirisk", type = ControllerType.COLLECTION)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/residualimpactassessments", name = "ResidualImpactAssessmentsCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResidualImpactAssessmentsCollectionJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ResidualImpactAssessmentService residualImpactAssessmentService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param residualImpactAssessmentService
     */
    @Autowired
    public ResidualImpactAssessmentsCollectionJsonController(ResidualImpactAssessmentService residualImpactAssessmentService) {
        this.residualImpactAssessmentService = residualImpactAssessmentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ResidualImpactAssessmentService
     */
    public ResidualImpactAssessmentService getResidualImpactAssessmentService() {
        return residualImpactAssessmentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessmentService
     */
    public void setResidualImpactAssessmentService(ResidualImpactAssessmentService residualImpactAssessmentService) {
        this.residualImpactAssessmentService = residualImpactAssessmentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<ResidualImpactAssessment>> list(GlobalSearch globalSearch, Pageable pageable) {
        Page<ResidualImpactAssessment> residualImpactAssessments = getResidualImpactAssessmentService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(residualImpactAssessments);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(ResidualImpactAssessmentsCollectionJsonController.class).list(null, null)).build().encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody ResidualImpactAssessment residualImpactAssessment, BindingResult result) {
        if (residualImpactAssessment.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        ResidualImpactAssessment newResidualImpactAssessment = getResidualImpactAssessmentService().save(residualImpactAssessment);
        UriComponents showURI = ResidualImpactAssessmentsItemJsonController.showURI(newResidualImpactAssessment);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessments
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<ResidualImpactAssessment> residualImpactAssessments, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getResidualImpactAssessmentService().save(residualImpactAssessments);
        return ResponseEntity.created(listURI().toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessments
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<ResidualImpactAssessment> residualImpactAssessments, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getResidualImpactAssessmentService().save(residualImpactAssessments);
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
        getResidualImpactAssessmentService().delete(ids);
        return ResponseEntity.ok().build();
    }
}
