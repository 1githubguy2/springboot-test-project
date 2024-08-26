package com.yjl;

import java.io.*;
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
public class GenerateDtoMapper extends AutoGenerate{


    @Override
    public void doActionAbstract() {
        try {
            genDtoMapper();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void genDtoMapper() throws IOException {
        String packageName = basePackage + ".domain.mapper";
        String className = packageName + "." + entityName + "DTOMapper";
        String fileName = domainBasePath + className.replaceAll("\\.", "/") + ".java";
        File outFile = new File(fileName);
        if (outFile.exists()) {
            System.err.println(className + "已存在");
            return ;
        }
        makeSurePath(outFile);
        String fileContent = getFileContent("EntityDTOMapper.tmp");
        fileContent = fileContent.replaceAll("\\$\\{EntityName}", entityName);
        fileContent = fileContent.replaceAll("\\$\\{basePackage}", basePackage);

        String dtoFileName = getDtoFileName();
        File dtoFile = new File(dtoFileName);
        if (!dtoFile.exists()) {
            throw new IllegalArgumentException("必须先生成DTO类后才能生成DTOMapper类");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(dtoFileName)), StandardCharsets.UTF_8));

        StringBuilder setters = new StringBuilder();
        boolean start = false;
        String line;
        while((line = reader.readLine()) != null) {
            if(line.contains("class") && line.contains(entityName)) {
                start = true;
                continue;
            }
            if(line.contains("@")) {
                continue;
            }
            if(start && (line.startsWith("}") || (line.contains("(") && line.contains(")")) )) {
                //类结束，或者遇到方法
                break;
            }
            if(start) {
                if(!isFieldLine(line)) {
                    continue;
                }
                String fieldName = getFieldName(line);
                if ("id".equals(fieldName)) {
                    continue;
                }
                String firstUpper = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                String setLine = "target.set" + firstUpper + "(source.get" + firstUpper + "());";
                setters.append(setLine).append("\n        ");
            }
        }
        reader.close();

        fileContent = fileContent.replaceAll("\\$\\{setters}", setters.toString());

        FileOutputStream out = new FileOutputStream(outFile);
        out.write(fileContent.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();

        System.out.println("已创建");
    }
}
