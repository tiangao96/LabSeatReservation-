package com.lab.common.storage;

import com.lab.common.storage.StorageProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StorageWebConfig
implements WebMvcConfigurer {
    private final StorageProperties props;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = "file:" + this.props.getUploadDirAbsolute() + "/";
        registry.addResourceHandler(new String[]{this.props.getUrlPrefix() + "/**"}).addResourceLocations(new String[]{location}).setCachePeriod(Integer.valueOf(3600));
    }

    public StorageWebConfig(StorageProperties props) {
        this.props = props;
    }
}

