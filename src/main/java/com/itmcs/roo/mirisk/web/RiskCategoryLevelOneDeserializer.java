package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import com.itmcs.roo.mirisk.service.api.RiskCategoryLevelOneService;
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
 * = RiskCategoryLevelOneDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = RiskCategoryLevelOne.class)
@JsonComponent
public class RiskCategoryLevelOneDeserializer extends JsonObjectDeserializer<RiskCategoryLevelOne> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskCategoryLevelOneService riskCategoryLevelOneService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskCategoryLevelOneService
     * @param conversionService
     */
    @Autowired
    public RiskCategoryLevelOneDeserializer(@Lazy RiskCategoryLevelOneService riskCategoryLevelOneService, ConversionService conversionService) {
        this.riskCategoryLevelOneService = riskCategoryLevelOneService;
        this.conversionService = conversionService;
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
     * @return RiskCategoryLevelOne
     */
    public RiskCategoryLevelOne deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        RiskCategoryLevelOne riskCategoryLevelOne = riskCategoryLevelOneService.findOne(id);
        if (riskCategoryLevelOne == null) {
            throw new NotFoundException("RiskCategoryLevelOne not found");
        }
        return riskCategoryLevelOne;
    }
}
