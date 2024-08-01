//package com.bti.ui.admin.region;
//
//import com.bti.controllers.RegionController;
//import com.bti.model.Region;
//import com.bti.services.RegionService;
//import jakarta.faces.component.UIComponent;
//import jakarta.faces.context.FacesContext;
//import jakarta.faces.convert.Converter;
//import jakarta.faces.convert.FacesConverter;
//import org.springframework.login.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//import java.util.logging.Logger;
//
//@Component
//@FacesConverter(forClass = Region.class)
//public class RegionConverter implements Converter<Region> {
//
//    @Autowired
//    private RegionController regionController;
//
//    private static final Logger LOGGER = Logger.getLogger(RegionConverter.class.getName());
//    @Autowired
//    private RegionService regionService;
//
//    @Override
//    public Region getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
//        if (value == null || value.isEmpty()) {
//            return null;
//        }
//        try {
//            Long id = Long.valueOf(value);
//            Optional<Region> region = regionService.findRegionById(id);
//            System.out.println("orked");
//            LOGGER.info("Converted string to region: " + region.orElse(null));
//            return region.orElse(null);
//        } catch (NumberFormatException e) {
//            LOGGER.severe("Conversion error: " + e.getMessage());
//            System.out.println(e.getMessage());
//            throw new IllegalArgumentException("Invalid region ID: " + value, e);
//        }
//    }
//
//    @Override
//    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Region region) {
//        if (region == null) {
//            return "";
//        }
//        String id = String.valueOf(region.getId());
//        LOGGER.info("Converted region to string: " + id);
//        return id;
//    }
//}
