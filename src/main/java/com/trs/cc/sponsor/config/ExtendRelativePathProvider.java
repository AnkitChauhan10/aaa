package com.trs.cc.sponsor.config;

import springfox.documentation.spring.web.paths.AbstractPathProvider;

public class ExtendRelativePathProvider extends AbstractPathProvider {
    public static final String ROOT = "/sponsor";
    @Override
    protected String applicationPath() {
        return ROOT;
    }


    @Override
    protected String getDocumentationPath() {
        return "/api/docs";
    }
}
