package com.yjl;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/2/24 16:03
 */
public class GenerateAll {
    static String entityPath = "F:\\assitant\\IDEA\\springboot-test-project\\src\\main\\java\\com\\yjl\\domain\\model\\ApprovalCallback.java";
    public static void main(String[] args) {
        new GenerateDto().doAction(entityPath);
        new GenerateDtoMapper().doAction(entityPath);
        new GenerateRepository().doAction(entityPath);
        new GenerateService().doAction(entityPath);
        new GenerateSpecification().doAction(entityPath);
        new GenerateController().doAction(entityPath);
    }
}
