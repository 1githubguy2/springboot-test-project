package com.yjl;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/2/24 11:28
 */
public class GenerateDto extends AutoGenerate{


    @Override
    public void doActionAbstract() {
        try {
            genDto();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void genDto() throws IOException {
        String packageName = basePackage + ".domain.dto";
        String className = packageName + "." + entityName + "DTO";
        String fileName = domainBasePath + className.replaceAll("\\.", "/") + ".java";
        File outFile = new File(fileName);
        if (outFile.exists()) {
            System.err.println(className + "已存在");
            return ;
        }
        makeSurePath(outFile);
        String fileContent = getFileContent("EntityDTO.tmp");
        fileContent = fileContent.replaceAll("\\$\\{EntityName}", entityName);
        fileContent = fileContent.replaceAll("\\$\\{basePackage}", basePackage);
        String entityFileName = domainBasePath + entityClassName.replaceAll("\\.", "/") + ".java";
        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(entityFileName)), StandardCharsets.UTF_8));

        List<String> packageList = new ArrayList<>();
        List<String> needPackageList = new ArrayList<>();
        StringBuilder propertiesBuilder = new StringBuilder();
        boolean start = false;
        String line;
        while((line = reader.readLine()) != null) {
            if(line.contains("class") && line.contains(entityName)) {
                start = true;
                continue;
            }
            if(line.contains("@")) {
                if(line.contains("@api") && start) {
                    propertiesBuilder.append(line).append("\n");
                }
                continue;
            }
            if(start && (line.startsWith("}") || (line.contains("(") && line.contains(")")) )) {
                //类结束，或者遇到方法
                break;
            }
            if(start) {
                if(check(line)) {
                    propertiesBuilder.append(line).append("\n");
                    String fieldType = getFieldType(line);
                    if(fieldType != null) {
                        packageList.stream().filter(p -> {
                            int end = p.indexOf(";");
                            return p.substring(0, end).trim().endsWith(fieldType);
                        }).findAny().ifPresent(needPackageList::add);
                    }
                }
            }else {
                line = line.trim();
                if(line.startsWith("import")) {
                    String p = line.split(" ", 2)[1].trim();
                    packageList.add(p);
                }
            }
        }
        reader.close();

        StringBuilder importPackages = new StringBuilder();
        for(String s : needPackageList) {
            importPackages.append("import ").append(s).append("\n");
        }

        fileContent = fileContent.replaceAll("\\$\\{importPackages}", importPackages.toString());
        fileContent = fileContent.replaceAll("\\$\\{dtoProperties}", propertiesBuilder.toString());

        FileOutputStream out = new FileOutputStream(outFile);
        out.write(fileContent.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();

        System.out.println("已创建");
    }
    private boolean check(String line) {
        line = line.trim();
        for(String s : line.split(" ")) {
            if("static".equals(s) || "final".equals(s)) {
                return false;
            }
        }
        return true;
    }
}
