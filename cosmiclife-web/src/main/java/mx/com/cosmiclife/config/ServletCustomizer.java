package mx.com.cosmiclife.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.stereotype.Component;

@Component
public class ServletCustomizer implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        mappings.add("woff","application/x-font-woff");
        mappings.add("eot","application/vnd.ms-fontobject");
        mappings.add("ttf","application/x-font-ttf");
        container.setMimeMappings(mappings);
    }
}