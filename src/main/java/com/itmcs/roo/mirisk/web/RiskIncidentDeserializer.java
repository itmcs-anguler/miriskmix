package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import com.itmcs.roo.mirisk.service.api.RiskIncidentService;
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
 * = RiskIncidentDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = RiskIncident.class)
@JsonComponent
public class RiskIncidentDeserializer extends JsonObjectDeserializer<RiskIncident> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskIncidentService riskIncidentService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskIncidentService
     * @param conversionService
     */
    @Autowired
    public RiskIncidentDeserializer(@Lazy RiskIncidentService riskIncidentService, ConversionService conversionService) {
        this.riskIncidentService = riskIncidentService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskIncidentService
     */
    public RiskIncidentService getRiskIncidentService() {
        return riskIncidentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncidentService
     */
    public void setRiskIncidentService(RiskIncidentService riskIncidentService) {
        this.riskIncidentService = riskIncidentService;
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
     * @return RiskIncident
     */
    public RiskIncident deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        RiskIncident riskIncident = riskIncidentService.findOne(id);
        if (riskIncident == null) {
            throw new NotFoundException("RiskIncident not found");
        }
        return riskIncident;
    }
}
