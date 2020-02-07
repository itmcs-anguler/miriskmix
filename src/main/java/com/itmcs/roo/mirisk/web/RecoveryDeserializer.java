package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.Recovery;
import com.itmcs.roo.mirisk.service.api.RecoveryService;
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
 * = RecoveryDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Recovery.class)
@JsonComponent
public class RecoveryDeserializer extends JsonObjectDeserializer<Recovery> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RecoveryService recoveryService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param recoveryService
     * @param conversionService
     */
    @Autowired
    public RecoveryDeserializer(@Lazy RecoveryService recoveryService, ConversionService conversionService) {
        this.recoveryService = recoveryService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RecoveryService
     */
    public RecoveryService getRecoveryService() {
        return recoveryService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param recoveryService
     */
    public void setRecoveryService(RecoveryService recoveryService) {
        this.recoveryService = recoveryService;
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
     * @return Recovery
     */
    public Recovery deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Recovery recovery = recoveryService.findOne(id);
        if (recovery == null) {
            throw new NotFoundException("Recovery not found");
        }
        return recovery;
    }
}
