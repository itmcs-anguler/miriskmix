package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.ResidualImpactAssessmentService;
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
 * = ResidualImpactAssessmentsItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = ResidualImpactAssessment.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/residualimpactassessments/{residualImpactAssessment}", name = "ResidualImpactAssessmentsItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResidualImpactAssessmentsItemJsonController {

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
    public ResidualImpactAssessmentsItemJsonController(ResidualImpactAssessmentService residualImpactAssessmentService) {
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
     * @param id
     * @return ResidualImpactAssessment
     */
    @ModelAttribute
    public ResidualImpactAssessment getResidualImpactAssessment(@PathVariable("residualImpactAssessment") Long id) {
        ResidualImpactAssessment residualImpactAssessment = residualImpactAssessmentService.findOne(id);
        if (residualImpactAssessment == null) {
            throw new NotFoundException(String.format("ResidualImpactAssessment with identifier '%s' not found", id));
        }
        return residualImpactAssessment;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute ResidualImpactAssessment residualImpactAssessment) {
        return ResponseEntity.ok(residualImpactAssessment);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @return UriComponents
     */
    public static UriComponents showURI(ResidualImpactAssessment residualImpactAssessment) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(ResidualImpactAssessmentsItemJsonController.class).show(residualImpactAssessment)).buildAndExpand(residualImpactAssessment.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedResidualImpactAssessment
     * @param residualImpactAssessment
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute ResidualImpactAssessment storedResidualImpactAssessment, @Valid @RequestBody ResidualImpactAssessment residualImpactAssessment, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        residualImpactAssessment.setId(storedResidualImpactAssessment.getId());
        getResidualImpactAssessmentService().save(residualImpactAssessment);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute ResidualImpactAssessment residualImpactAssessment) {
        getResidualImpactAssessmentService().delete(residualImpactAssessment);
        return ResponseEntity.ok().build();
    }
}
