package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskStatus;
import com.itmcs.roo.mirisk.service.api.RiskStatusService;
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
 * = RiskStatusDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = RiskStatus.class)
@JsonComponent
public class RiskStatusDeserializer extends JsonObjectDeserializer<RiskStatus> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskStatusService riskStatusService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskStatusService
     * @param conversionService
     */
    @Autowired
    public RiskStatusDeserializer(@Lazy RiskStatusService riskStatusService, ConversionService conversionService) {
        this.riskStatusService = riskStatusService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskStatusService
     */
    public RiskStatusService getRiskStatusService() {
        return riskStatusService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatusService
     */
    public void setRiskStatusService(RiskStatusService riskStatusService) {
        this.riskStatusService = riskStatusService;
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
     * @return RiskStatus
     */
    public RiskStatus deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        RiskStatus riskStatus = riskStatusService.findOne(id);
        if (riskStatus == null) {
            throw new NotFoundException("RiskStatus not found");
        }
        return riskStatus;
    }
}
