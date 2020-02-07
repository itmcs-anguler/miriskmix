package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import com.itmcs.roo.mirisk.service.api.RiskCategoryLevelTwoService;
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
 * = RiskCategoryLevelTwoDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = RiskCategoryLevelTwo.class)
@JsonComponent
public class RiskCategoryLevelTwoDeserializer extends JsonObjectDeserializer<RiskCategoryLevelTwo> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskCategoryLevelTwoService riskCategoryLevelTwoService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskCategoryLevelTwoService
     * @param conversionService
     */
    @Autowired
    public RiskCategoryLevelTwoDeserializer(@Lazy RiskCategoryLevelTwoService riskCategoryLevelTwoService, ConversionService conversionService) {
        this.riskCategoryLevelTwoService = riskCategoryLevelTwoService;
        this.conversionService = conversionService;
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
     * @return RiskCategoryLevelTwo
     */
    public RiskCategoryLevelTwo deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        RiskCategoryLevelTwo riskCategoryLevelTwo = riskCategoryLevelTwoService.findOne(id);
        if (riskCategoryLevelTwo == null) {
            throw new NotFoundException("RiskCategoryLevelTwo not found");
        }
        return riskCategoryLevelTwo;
    }
}
