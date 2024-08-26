package com.yjl;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/2/24 11:28
 */
public class GenerateSpecification extends AutoGenerate{


    @Override
    public void doActionAbstract() {
        try {
            genSpecification();
            //genServiceImpl();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void genSpecification() throws IOException {
        if (!"jpa".endsWith(databaseType)) {
            return;
        }
        String packageName = basePackage + ".service.specification";
        String className = packageName + "." + entityName + "Specification";
        String fileName = domainBasePath + className.replaceAll("\\.", "/") + ".java";
        File outFile = new File(fileName);
        if (outFile.exists()) {
            System.err.println(className + "已存在");
            return ;
        }
        makeSurePath(outFile);
        String fileContent = getFileContent("/jpa/Specification.tmp");
        fileContent = fileContent.replaceAll("\\$\\{EntityName}", entityName);
        fileContent = fileContent.replaceAll("\\$\\{basePackage}", basePackage);

        StringBuilder setters = new StringBuilder();

        String entityFileName = getEntityFileName();
        File entityFile = new File(entityFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(entityFile), Charset.forName("UTF-8")));

        String line;
        boolean start = false;
        while ((line = reader.readLine()) != null) {
            if (line.contains("class") && line.contains(entityName)) {
                start = true;
                continue;
            }
            if (line.contains("@")) {
                continue;
            }
            if (line.startsWith("}") || (line.contains("(") && line.contains(")"))) {
                //类结束或遇到方法
                break;
            }
            if (start) {
                if (!isFieldLine(line)) {
                    continue;
                }
                String fieldName = getFieldName(line);
                if ("id".equals(fieldName)) {
                    continue;
                }
                String fieldType = getFieldType(line);
                String firstUpper = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                String ifCode = "if (Objects.nonNull(condition.get" + firstUpper + "())) {\n";
                if (fieldType != null && fieldType.endsWith("String")) {
                    ifCode = "if (StringUtils.hasText(condition.get" + firstUpper + "())) {\n";
                }
                ifCode += "            ands.add(criteriaBuilder.equal(root.get(\"" + fieldName + "\"), condition.get" + firstUpper + "()));\n";
                ifCode += "        }\n";
                setters.append(ifCode).append("\n        ");
            }
        }
        reader.close();
        fileContent  = fileContent.replaceAll("\\$\\{setters}", setters.toString());

        FileOutputStream out = new FileOutputStream(outFile);
        out.write(fileContent.getBytes(StandardCharsets.UTF_8));
        out.close();

        System.out.println("已创建");
    }

}
