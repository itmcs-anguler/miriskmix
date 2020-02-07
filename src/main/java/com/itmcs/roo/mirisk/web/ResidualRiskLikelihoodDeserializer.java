package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import com.itmcs.roo.mirisk.service.api.ResidualRiskLikelihoodService;
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
 * = ResidualRiskLikelihoodDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = ResidualRiskLikelihood.class)
@JsonComponent
public class ResidualRiskLikelihoodDeserializer extends JsonObjectDeserializer<ResidualRiskLikelihood> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ResidualRiskLikelihoodService residualRiskLikelihoodService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param residualRiskLikelihoodService
     * @param conversionService
     */
    @Autowired
    public ResidualRiskLikelihoodDeserializer(@Lazy ResidualRiskLikelihoodService residualRiskLikelihoodService, ConversionService conversionService) {
        this.residualRiskLikelihoodService = residualRiskLikelihoodService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ResidualRiskLikelihoodService
     */
    public ResidualRiskLikelihoodService getResidualRiskLikelihoodService() {
        return residualRiskLikelihoodService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihoodService
     */
    public void setResidualRiskLikelihoodService(ResidualRiskLikelihoodService residualRiskLikelihoodService) {
        this.residualRiskLikelihoodService = residualRiskLikelihoodService;
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
     * @return ResidualRiskLikelihood
     */
    public ResidualRiskLikelihood deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        ResidualRiskLikelihood residualRiskLikelihood = residualRiskLikelihoodService.findOne(id);
        if (residualRiskLikelihood == null) {
            throw new NotFoundException("ResidualRiskLikelihood not found");
        }
        return residualRiskLikelihood;
    }
}
