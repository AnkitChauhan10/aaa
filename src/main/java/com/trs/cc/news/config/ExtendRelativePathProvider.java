package com.trs.cc.news.config;

import springfox.documentation.spring.web.paths.AbstractPathProvider;

public class ExtendRelativePathProvider extends AbstractPathProvider {
    public static final String ROOT = "/news";
    @Override
    protected String applicationPath() {
        return ROOT;
    }


    @Override
    protected String getDocumentationPath() {
        return "/api/docs";
    }
}
