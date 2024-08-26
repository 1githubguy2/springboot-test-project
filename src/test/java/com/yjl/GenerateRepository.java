package com.yjl;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/2/24 11:28
 */
public class GenerateRepository extends AutoGenerate{


    @Override
    public void doActionAbstract() {
        try {
            genRepository();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void genRepository() throws IOException {
        String packageName = basePackage + ".repository";
        String className = packageName + "." + entityName + "Repository";
        String fileName = domainBasePath + className.replaceAll("\\.", "/") + ".java";
        File outFile = new File(fileName);
        if (outFile.exists()) {
            System.err.println(className + "已存在");
            return ;
        }
        makeSurePath(outFile);
        String fileContent = getFileContent("/jpa/Repository.tmp");
        fileContent = fileContent.replaceAll("\\$\\{EntityName}", entityName);
        fileContent = fileContent.replaceAll("\\$\\{basePackage}", basePackage);

        FileOutputStream out = new FileOutputStream(outFile);
        out.write(fileContent.getBytes(StandardCharsets.UTF_8));
        out.close();

        System.out.println("已创建");
    }
}
