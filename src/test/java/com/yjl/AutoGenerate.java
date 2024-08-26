package com.yjl;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/2/11 23:24
 */
public abstract class AutoGenerate {
    static final String CONFIG_FILE = "/conf/gencode/genCodes.properties";
    static final String CONFIG_DIR = "/conf/gencode/template/";
    protected String entityFileName;//实体类文件名(绝对路径)
    protected String entityClassName;//实体类名(含包名)
    protected String entityName;
    protected String entityNameFirstLower;
    protected String basePackage;
    protected String projectBasePath;
    protected String configFilePath;
    protected String templatePath;
    protected String domainBasePath;//实体类存放路径(绝对路径，不含包名)
    protected String databaseType = "jpa";
    protected Properties properties = new Properties();
    public final void doAction(String entityPath) {
        File file = new File("");
        try {
            String curProjectPath = file.getCanonicalPath().replace("\\", "/");
            System.out.println("current project path:" + curProjectPath);
            init(curProjectPath, entityPath);
            doActionAbstract();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public abstract void doActionAbstract();
    public void init(String curProjectPath, String entityFilePath) throws IOException {
        if(!entityFilePath.endsWith(".java")) {
            throw new IllegalArgumentException("必须为java文件");
        }
        entityFileName = entityFilePath.replace("\\", "/");
        projectBasePath = curProjectPath;
        configFilePath = projectBasePath + CONFIG_FILE;
        templatePath = projectBasePath + CONFIG_DIR;
        try(InputStream inputStream = new FileInputStream(configFilePath)) {
            properties.load(inputStream);
        }

        domainBasePath = properties.getProperty("domainModule", "").trim();
        if(domainBasePath.length() > 0) {
            if(!domainBasePath.startsWith("\\") && !domainBasePath.startsWith("/")) {
                domainBasePath = "/" + domainBasePath;
            }
            if(!domainBasePath.endsWith("\\") && !domainBasePath.endsWith("/")) {
                domainBasePath += "/";
            }
        }

          domainBasePath = projectBasePath + (domainBasePath.equals("") ? "/" : domainBasePath) + "src/main/java/";
        if(!entityFileName.startsWith(domainBasePath) || !entityFileName.substring(domainBasePath.length()).contains("domain")) {
            throw new IllegalArgumentException("目标类必须在[ " + domainBasePath + "../domain/.. ]目录下");
        }

        String tmpEntityFile = entityFileName.substring(domainBasePath.length());
        int index = tmpEntityFile.indexOf(".java");
        entityClassName = tmpEntityFile.substring(0, index).replaceAll("/", ".");

        databaseType = properties.getProperty("databaseType", "jpa").toLowerCase();
        entityName = entityClassName.substring(entityClassName.lastIndexOf(".") + 1);
        entityNameFirstLower = Character.toLowerCase(entityName.charAt(0)) + entityName.substring(1);
        String entityPath = entityClassName.substring(0, entityClassName.lastIndexOf("."));
        basePackage = entityPath.substring(0, entityPath.indexOf(".domain"));
    }

    protected void makeSurePath(File file) {
        if(file.getParentFile().mkdir()) {
            System.out.println("create path " + file.getParent());
        }
    }

    protected String getFileContent(String tmpFileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        File tmpFile = new File(templatePath + tmpFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(tmpFile), Charset.forName("UTF-8")));
        String line;
        while((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
    protected static String getFieldType(String line) {
        line = line.trim();
        if(line.startsWith("private")) {
            for(String s : line.substring(7).split(" ")) {
                if (s.trim().length() > 0) {
                    return s.trim();
                }
            }
        }
        return null;
    }
    protected static String getFieldName(String line) {
        //跳过private
        line = line.trim().substring(7);
        int k = line.indexOf(";");
        if (k > 0) {
            line = line.substring(0, k).trim();
        }
        String[] words = line.split(" ");
        return words[words.length - 1];
    }
    protected static boolean isFieldLine(String line) {
        line = line.trim();
        if (!line.startsWith("private")) {
            return false;
        }
        String[] lineWords = line.split(" ");
        for (String lineWord : lineWords) {
            if ("static".equals(lineWord) || "final".equals(lineWord)) {
                return false;
            }
        }
        return true;
    }
    protected String getDtoFileName() {
        String packageName = basePackage + ".domain.dto";
        String className = packageName + "." + entityName + "DTO";
        return domainBasePath + className.replaceAll("\\.", "/") + ".java";
    }
    protected String getEntityFileName() {
        return entityFileName;
    }
}
