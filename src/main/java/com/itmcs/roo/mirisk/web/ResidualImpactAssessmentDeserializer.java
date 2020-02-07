package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import com.itmcs.roo.mirisk.service.api.ResidualImpactAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = ResidualImpactAssessmentDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = ResidualImpactAssessment.class)
@JsonComponent
public class ResidualImpactAssessmentDeserializer extends JsonObjectDeserializer<ResidualImpactAssessment> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ResidualImpactAssessmentService residualImpactAssessmentService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param residualImpactAssessmentService
     * @param conversionService
     */
    @Autowired
    public ResidualImpactAssessmentDeserializer(@Lazy ResidualImpactAssessmentService residualImpactAssessmentService, ConversionService conversionService) {
        this.residualImpactAssessmentService = residualImpactAssessmentService;
        this.conversionService = conversionService;
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
     * @return ConversionService
     */
    public ConversionService getConversionService() {
        return conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param conversionService
     */
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jsonParser
     * @param context
     * @param codec
     * @param tree
     * @return ResidualImpactAssessment
     */
    public ResidualImpactAssessment deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        ResidualImpactAssessment residualImpactAssessment = residualImpactAssessmentService.findOne(id);
        if (residualImpactAssessment == null) {
            throw new NotFoundException("ResidualImpactAssessment not found");
        }
        return residualImpactAssessment;
    }
}
