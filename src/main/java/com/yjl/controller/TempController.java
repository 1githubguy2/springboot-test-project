package com.yjl.controller;

import com.alibaba.fastjson.JSON;
import com.yjl.annotation.RecordAnnotation;
import com.yjl.domain.enums.OperationType;
import com.yjl.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author unbroken
 * @Description AOP和自定义注解
 * @Version 1.0
 * @date 2024/2/11 23:36
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/temp")
public class TempController {

    @RecordAnnotation(operationType = OperationType.ADD)
    @PostMapping
    public void create(@RequestBody User user) {
        System.out.println("记录日志");

    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(nums));
        System.out.println(JSON.toJSONString(nums));
    }
    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return len;
        }
        int slow = 2, fast = 2;
        while(fast < len) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
