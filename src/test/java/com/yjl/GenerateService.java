package com.yjl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/2/24 11:28
 */
public class GenerateService extends AutoGenerate{


    @Override
    public void doActionAbstract() {
        try {
            genService();
            genServiceImpl();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void genService() throws IOException {
        String packageName = basePackage + ".service.facade";
        String className = packageName + "." + entityName + "Service";
        String fileName = domainBasePath + className.replaceAll("\\.", "/") + ".java";
        File outFile = new File(fileName);
        if (outFile.exists()) {
            System.err.println(className + "已存在");
            return ;
        }
        makeSurePath(outFile);
        String fileContent = getFileContent("/jpa/Service.tmp");
        fileContent = fileContent.replaceAll("\\$\\{EntityName}", entityName);
        fileContent = fileContent.replaceAll("\\$\\{basePackage}", basePackage);

        FileOutputStream out = new FileOutputStream(outFile);
        out.write(fileContent.getBytes(StandardCharsets.UTF_8));
        out.close();

        System.out.println("已创建");
    }
    private void genServiceImpl() throws IOException {
        String packageName = basePackage + ".service";
        String className = packageName + "." + entityName + "ServiceImpl";
        String fileName = domainBasePath + className.replaceAll("\\.", "/") + ".java";
        File outFile = new File(fileName);
        if (outFile.exists()) {
            System.err.println(className + "已存在");
            return ;
        }
        makeSurePath(outFile);
        String fileContent = getFileContent("/jpa/ServiceImpl.tmp");
        fileContent = fileContent.replaceAll("\\$\\{EntityName}", entityName);
        fileContent = fileContent.replaceAll("\\$\\{basePackage}", basePackage);

        FileOutputStream out = new FileOutputStream(outFile);
        out.write(fileContent.getBytes(StandardCharsets.UTF_8));
        out.close();

        System.out.println("已创建");
    }
}
