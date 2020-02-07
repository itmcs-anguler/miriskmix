package com.itmcs.roo.mirisk.web;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooLinkFactory;
import io.springlets.web.mvc.util.MethodLinkFactory;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

/**
 * = RiskRegistersSearchJsonLinkFactory
 TODO Auto-generated class documentation
 *
 */
@RooLinkFactory(controller = RiskRegistersSearchJsonController.class)
@Component
public class RiskRegistersSearchJsonLinkFactory implements MethodLinkFactory<RiskRegistersSearchJsonController> {

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<RiskRegistersSearchJsonController> getControllerClass() {
        return RiskRegistersSearchJsonController.class;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param methodName
     * @param parameters
     * @param pathVariables
     * @return UriComponents
     */
    public UriComponents toUri(String methodName, Object[] parameters, Map<String, Object> pathVariables) {
        throw new IllegalArgumentException("Invalid method name: " + methodName);
    }
}
