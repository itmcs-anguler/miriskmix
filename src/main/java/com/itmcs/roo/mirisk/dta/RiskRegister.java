package com.itmcs.roo.mirisk.dta;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import io.springlets.format.EntityFormat;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.util.Objects;
import javax.persistence.Entity;

/**
 * = RiskRegister
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class RiskRegister {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * TODO Auto-generated attribute documentation
     *
     @Version
     private Integer version;*/
    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String inheritRiskAssessmentScore;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String currentControls;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String riskOwner;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String furthurAction;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String residualRiskAssessmentScore;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @EntityFormat
    private Category category;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @EntityFormat
    private ResidualRiskLikelihood residualRiskLikelihood;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @EntityFormat
    private ResidualImpactAssessment residualImpactAssessment;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @EntityFormat
    private RiskStatus riskStatus;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @EntityFormat
    private ResidualRiskLikelihood inheritRiskLikelihood;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @EntityFormat
    private ResidualImpactAssessment inheritImpactAssessment;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Size(max = 600)
    private String riskDescription;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String uniqueId;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

    /**
     * Gets id value
     *
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets id value
     *
     * @param id
     * @return RiskRegister
     */
    public RiskRegister setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets inheritRiskAssessmentScore value
     *
     * @return String
     */
    public String getInheritRiskAssessmentScore() {
        return this.inheritRiskAssessmentScore;
    }

    /**
     * Sets inheritRiskAssessmentScore value
     *
     * @param inheritRiskAssessmentScore
     * @return RiskRegister
     */
    public RiskRegister setInheritRiskAssessmentScore(String inheritRiskAssessmentScore) {
        this.inheritRiskAssessmentScore = inheritRiskAssessmentScore;
        return this;
    }

    /**
     * Gets currentControls value
     *
     * @return String
     */
    public String getCurrentControls() {
        return this.currentControls;
    }

    /**
     * Sets currentControls value
     *
     * @param currentControls
     * @return RiskRegister
     */
    public RiskRegister setCurrentControls(String currentControls) {
        this.currentControls = currentControls;
        return this;
    }

    /**
     * Gets riskOwner value
     *
     * @return String
     */
    public String getRiskOwner() {
        return this.riskOwner;
    }

    /**
     * Sets riskOwner value
     *
     * @param riskOwner
     * @return RiskRegister
     */
    public RiskRegister setRiskOwner(String riskOwner) {
        this.riskOwner = riskOwner;
        return this;
    }

    /**
     * Gets furthurAction value
     *
     * @return String
     */
    public String getFurthurAction() {
        return this.furthurAction;
    }

    /**
     * Sets furthurAction value
     *
     * @param furthurAction
     * @return RiskRegister
     */
    public RiskRegister setFurthurAction(String furthurAction) {
        this.furthurAction = furthurAction;
        return this;
    }

    /**
     * Gets residualRiskAssessmentScore value
     *
     * @return String
     */
    public String getResidualRiskAssessmentScore() {
        return this.residualRiskAssessmentScore;
    }

    /**
     * Sets residualRiskAssessmentScore value
     *
     * @param residualRiskAssessmentScore
     * @return RiskRegister
     */
    public RiskRegister setResidualRiskAssessmentScore(String residualRiskAssessmentScore) {
        this.residualRiskAssessmentScore = residualRiskAssessmentScore;
        return this;
    }

    /**
     * Gets category value
     *
     * @return Category
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Sets category value
     *
     * @param category
     * @return RiskRegister
     */
    public RiskRegister setCategory(Category category) {
        this.category = category;
        return this;
    }

    /**
     * Gets residualRiskLikelihood value
     *
     * @return ResidualRiskLikelihood
     */
    public ResidualRiskLikelihood getResidualRiskLikelihood() {
        return this.residualRiskLikelihood;
    }

    /**
     * Sets residualRiskLikelihood value
     *
     * @param residualRiskLikelihood
     * @return RiskRegister
     */
    public RiskRegister setResidualRiskLikelihood(ResidualRiskLikelihood residualRiskLikelihood) {
        this.residualRiskLikelihood = residualRiskLikelihood;
        return this;
    }

    /**
     * Gets residualImpactAssessment value
     *
     * @return ResidualImpactAssessment
     */
    public ResidualImpactAssessment getResidualImpactAssessment() {
        return this.residualImpactAssessment;
    }

    /**
     * Sets residualImpactAssessment value
     *
     * @param residualImpactAssessment
     * @return RiskRegister
     */
    public RiskRegister setResidualImpactAssessment(ResidualImpactAssessment residualImpactAssessment) {
        this.residualImpactAssessment = residualImpactAssessment;
        return this;
    }

    /**
     * Gets riskStatus value
     *
     * @return RiskStatus
     */
    public RiskStatus getRiskStatus() {
        return this.riskStatus;
    }

    /**
     * Sets riskStatus value
     *
     * @param riskStatus
     * @return RiskRegister
     */
    public RiskRegister setRiskStatus(RiskStatus riskStatus) {
        this.riskStatus = riskStatus;
        return this;
    }

    /**
     * Gets inheritRiskLikelihood value
     *
     * @return ResidualRiskLikelihood
     */
    public ResidualRiskLikelihood getInheritRiskLikelihood() {
        return this.inheritRiskLikelihood;
    }

    /**
     * Sets inheritRiskLikelihood value
     *
     * @param inheritRiskLikelihood
     * @return RiskRegister
     */
    public RiskRegister setInheritRiskLikelihood(ResidualRiskLikelihood inheritRiskLikelihood) {
        this.inheritRiskLikelihood = inheritRiskLikelihood;
        return this;
    }

    /**
     * Gets inheritImpactAssessment value
     *
     * @return ResidualImpactAssessment
     */
    public ResidualImpactAssessment getInheritImpactAssessment() {
        return this.inheritImpactAssessment;
    }

    /**
     * Sets inheritImpactAssessment value
     *
     * @param inheritImpactAssessment
     * @return RiskRegister
     */
    public RiskRegister setInheritImpactAssessment(ResidualImpactAssessment inheritImpactAssessment) {
        this.inheritImpactAssessment = inheritImpactAssessment;
        return this;
    }

    /**
     * Gets riskDescription value
     *
     * @return String
     */
    public String getRiskDescription() {
        return this.riskDescription;
    }

    /**
     * Sets riskDescription value
     *
     * @param riskDescription
     * @return RiskRegister
     */
    public RiskRegister setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
        return this;
    }

    /**
     * Gets uniqueId value
     *
     * @return String
     */
    public String getUniqueId() {
        return this.uniqueId;
    }

    /**
     * Sets uniqueId value
     *
     * @param uniqueId
     * @return RiskRegister
     */
    public RiskRegister setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    /**
     * This `equals` implementation is specific for JPA entities and uses
     * the entity identifier for it, following the article in
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @param obj
     * @return Boolean
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        // instanceof is false if the instance is null
        if (!(obj instanceof RiskRegister)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((RiskRegister) obj).getId());
    }

    /**
     * This `hashCode` implementation is specific for JPA entities and uses a fixed `int` value to be able
     * to identify the entity in collections after a new id is assigned to the entity, following the article in
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @return Integer
     */
    public int hashCode() {
        return 31;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String toString() {
        return "RiskRegister {" + "id='" + id + '\'' + ", inheritRiskAssessmentScore='" + inheritRiskAssessmentScore + '\'' + ", currentControls='" + currentControls + '\'' + ", riskOwner='" + riskOwner + '\'' + ", furthurAction='" + furthurAction + '\'' + ", residualRiskAssessmentScore='" + residualRiskAssessmentScore + '\'' + ", riskDescription='" + riskDescription + '\'' + ", uniqueId='" + uniqueId + '\'' + "}" + super.toString();
    }
}
