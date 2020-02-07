package com.itmcs.roo.mirisk.web;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooLinkFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import io.springlets.web.mvc.util.MethodLinkFactory;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

/**
 * = ManageRolesSearchJsonLinkFactory
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooLinkFactory(controller = ManageRolesSearchJsonController.class)
@Component
public class ManageRolesSearchJsonLinkFactory implements MethodLinkFactory<ManageRolesSearchJsonController> {

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<ManageRolesSearchJsonController> getControllerClass() {
        return ManageRolesSearchJsonController.class;
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
