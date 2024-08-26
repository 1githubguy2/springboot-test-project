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
public class GenerateController extends AutoGenerate{


    @Override
    public void doActionAbstract() {
        try {
            genController();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void genController() throws IOException {
        String packageName = basePackage + ".controller";
        String className = packageName + "." + entityName + "Controller";
        String fileName = domainBasePath + className.replace(".", "/") + ".java";
        File outFile = new File(fileName);
        if (outFile.exists()) {
            System.err.println(className + "已存在");
            return ;
        }
        makeSurePath(outFile);
        String fileContent = getFileContent("/Controller.tmp");
        fileContent = fileContent.replaceAll("\\$\\{EntityName}", entityName);
        fileContent = fileContent.replaceAll("\\$\\{basePackage}", basePackage);
        fileContent = fileContent.replaceAll("\\$\\{entityNameFirstLower}", entityNameFirstLower);

        FileOutputStream out = new FileOutputStream(outFile);
        out.write(fileContent.getBytes(StandardCharsets.UTF_8));
        out.close();

        System.out.println("已创建");
    }
}
