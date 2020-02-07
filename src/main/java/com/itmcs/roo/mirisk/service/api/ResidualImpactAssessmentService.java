package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = ResidualImpactAssessmentService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = ResidualImpactAssessment.class)
public interface ResidualImpactAssessmentService extends EntityResolver<ResidualImpactAssessment, Long>, ValidatorService<ResidualImpactAssessment> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ResidualImpactAssessment
     */
    public abstract ResidualImpactAssessment findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     */
    public abstract void delete(ResidualImpactAssessment residualImpactAssessment);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<ResidualImpactAssessment> save(Iterable<ResidualImpactAssessment> entities);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    public abstract void delete(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return ResidualImpactAssessment
     */
    public abstract ResidualImpactAssessment save(ResidualImpactAssessment entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ResidualImpactAssessment
     */
    public abstract ResidualImpactAssessment findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<ResidualImpactAssessment> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<ResidualImpactAssessment> findAll();

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public abstract long count();

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<ResidualImpactAssessment> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<ResidualImpactAssessment> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @param inheritImpactAssessmentToAdd
     * @return ResidualImpactAssessment
     */
    public abstract ResidualImpactAssessment addToInheritImpactAssessment(ResidualImpactAssessment residualImpactAssessment, Iterable<Long> inheritImpactAssessmentToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @param inheritImpactAssessmentToRemove
     * @return ResidualImpactAssessment
     */
    public abstract ResidualImpactAssessment removeFromInheritImpactAssessment(ResidualImpactAssessment residualImpactAssessment, Iterable<Long> inheritImpactAssessmentToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @param inheritImpactAssessment
     * @return ResidualImpactAssessment
     */
    public abstract ResidualImpactAssessment setInheritImpactAssessment(ResidualImpactAssessment residualImpactAssessment, Iterable<Long> inheritImpactAssessment);
}
