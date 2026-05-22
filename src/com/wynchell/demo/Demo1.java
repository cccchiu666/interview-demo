package com.wynchell.demo;

import java.util.*;

/**
 * Created by Intellij IDEA.
 * <p>
 * 机题题目第一题：Java基本操作
 * 算法名称：字符串压缩与解压缩
 * 压缩规则：
 * 1、仅压缩连续重复出现的字符。比如字符串"abcbc"由于无连续重复字符，压缩后的字符串还是"abcbc"。
 * 2、压缩字段的格式为"大于1的字符重复的次数+字符"。例如：字符串"xxxyyyyyyz"压缩后就成为"3x6yz"。其中x和y的重复次数为3和6，z的重复次数为1
 * <p>
 * 要求：1、仅使用jdk内置包和类库
 * 2、分别编写压缩和解压缩算法
 * 3、读取键盘输入的字符串，进行压缩和解压缩，并测试解压缩的结果和输入字符串是否一致
 * 4、代码具有封装行
 * 5、代码逻辑清晰，格式优美
 * 6、提交源代码和运行结果截图，至少提供2条测试用例
 * <p>
 *
 * @author: Wynchell
 * @version: 1.0
 * <p>
 * To change this template use File | Settings | File Templates
 */
public class Demo1 {

    /**
     * 测试主程序
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== 字符串压缩工具 ===");
        System.out.print("请输入要压缩的字符串: ");

        if (scanner.hasNextLine()) {
            String origin = scanner.nextLine();

            // 1. 执行压缩
            String compressed = compress(origin);
            System.out.println("压缩后结果: " + compressed);

            // 2. 执行解压缩
            String decompressed = decompress(compressed);
            System.out.println("解压还原结果: " + decompressed);

        }
        scanner.close();
    }

    /**
     * 压缩算法
     * 1、仅压缩连续重复出现的字符。比如字符串"abcbc"由于无连续重复字符，压缩后的字符串还是"abcbc"。
     * 2、压缩字段的格式为"大于1的字符重复的次数+字符"。例如：字符串"xxxyyyyyyz"压缩后就成为"3x6yz"。其中x和y的重复次数为3和6，z的重复次数为1
     */
    public static String compress(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        char[] chars = input.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            // 如果当前字符与下一个字符相同，计数增加
            if (i + 1 < chars.length && chars[i] == chars[i + 1]) {
                count++;
            } else {
                // 如果计数大于1，拼接数字
                if (count > 1) {
                    sb.append(count);
                }
                // 拼接字符
                sb.append(chars[i]);
                // 重置计数
                count = 1;
            }
        }
        return sb.toString();
    }

    /**
     * 解压缩算法
     * 规则：解析数字并重复后续字符
     */
    public static String decompress(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder numBuilder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (Character.isDigit(c)) {
                // 如果是数字，可能存在多位数（如10a），先存入临时StringBuilder
                numBuilder.append(c);
            } else {
                // 如果是字符，获取之前的数字
                int repeatCount = 1;
                if (numBuilder.length() > 0) {
                    repeatCount = Integer.parseInt(numBuilder.toString());
                    // 清空数字缓冲区
                    numBuilder.setLength(0);
                }

                // 根据重复次数生成字符串
                for (int j = 0; j < repeatCount; j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }


}
