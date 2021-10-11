package org.noear.solon.serialization.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.noear.solon.core.handle.Render;
import org.noear.solon.serialization.RenderFactory;
import org.noear.solon.serialization.StringSerializerRender;

/**
 * @author noear 2021/10/11 created
 */
public class JacksonRenderFactory extends JacksonCustomizer implements RenderFactory {
    public static final JacksonRenderFactory global = new JacksonRenderFactory();

    ObjectMapper config = new ObjectMapper();

    private JacksonRenderFactory(){
        config.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


    @Override
    public Render create() {
        return new StringSerializerRender(false, new JacksonSerializer(config));
    }

    @Override
    protected ObjectMapper config() {
        return config;
    }
}
