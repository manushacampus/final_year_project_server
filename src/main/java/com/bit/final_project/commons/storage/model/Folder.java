package com.bit.final_project.commons.storage.model;

import com.bit.final_project.commons.Generator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@Getter
@Setter
public class Folder {

    public static final String DOCUMENT = "documents";
    public static final String PAGES = "pages";
    public static final String SIGNER = "signer";
    public static final String INPUTS = "inputs";
    public static final String TEMP = "temp";
    public static final String APPLICATION = "applications";
    public static final String ASSETS = "assets";
    public static final String ORG = "organization";


    public static String path(String... folders) {
        String folderPath = "";
        for (String folder : folders) {
            folderPath += folder + "/";
        }
        return StringUtils.removeEnd(folderPath, "/");
    }

    public static String pathForPage(String document, String page) {
        return Folder.path(Folder.DOCUMENT, document, Folder.PAGES);
    }

    public static String pathForDocument(String document) {
        return Folder.path(Folder.DOCUMENT, document);
    }

    public static String pathForOutputDocument(String document, String fileName){
        String base = System.getenv().get("VIDITURE_HOME");
        return base + "/" + Folder.path(Folder.DOCUMENT, document) + "/" + fileName;
    }

    public static String pathForSignerInputField(String document, String signer, String field) {
        return Folder.path(Folder.DOCUMENT, document, Folder.SIGNER, signer, Folder.INPUTS, field);
    }

    public static String pathForApplicationSupportAssets(String application , String assetKey) {
        return Folder.path(Folder.APPLICATION, application, Folder.ASSETS, assetKey);
    }

    public static String pathForOrgAssets(String org) {
        return Folder.path(Folder.ORG, org, Folder.ASSETS);
    }

    public static String pathForLocalTempFile(String file) {
        String base = System.getenv().get("VIDITURE_HOME");
        return base + "/_local_temp/" + file;
    }

    public static String pathForLocalTempFileWithExtension(String extension) {
        String base = System.getenv().get("VIDITURE_HOME");
        return base + "/_local_temp/" + Generator.getRandomString() + "." + extension;
    }
}
