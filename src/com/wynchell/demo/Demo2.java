package com.wynchell.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * 第二题：文件解析与逻辑处理
 * 文件格式：
 * 如下文本文件，文件第一行为表头，使用竖线“|”分割，其他行为表数据，每行为一条记录。
 * <p>
 * 要求：
 * 1、使用java代码读取文本文件并解析
 * 2、按照父子关系生成树形结构
 * 3、将树形结构输出为json格式（可以使用Fastjson等json库）
 * 4、代码要有封装性
 * 5、代码逻辑清晰，格式优美
 * 6、提交源代码和运行结果截图
 * <p>
 * author: Wynchell
 * version: 1.0
 * <p>
 * To change this template use File | Settings | File Templates
 */
public class Demo2 {

    /**
     * 测试主程序
     */
    public static void main(String[] args) {
        // 请确保路径正确：src/com/wynchell/file/data.txt
        String filePath = "src/com/wynchell/file/data.txt";
        try {
            List<MyNode> tree = buildTree(filePath);

            ObjectMapper mapper = new ObjectMapper();
            // 输出 JSON
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree);

            System.out.println("--- 树形 JSON 结果 ---");
            System.out.println(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 要求：
     * 1、使用java代码读取文本文件并解析
     * 2、按照父子关系生成树形结构
     * 3、将树形结构输出为json格式（可以使用Fastjson等json库）
     * 4、代码要有封装性
     * 5、代码逻辑清晰，格式优美
     * 6、提交源代码和运行结果截图
     * <p>
     * param filePath 文件路径
     * return
     * throws IOException
     */
    public static List<MyNode> buildTree(String filePath) throws IOException {
        Map<String, MyNode> nodeMap = new LinkedHashMap<>();
        List<MyNode> roots = new ArrayList<>();

        // 1. 读取并创建对象
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // 1.1 跳过表头
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    // 1.2 封装对象
                    MyNode node = new MyNode(parts[0], parts[1], parts[2]);
                    // 1.3 统一封装到map集合
                    nodeMap.put(node.id, node);
                }
            }
        }

        // 2. 组装父子关系
        for (MyNode node : nodeMap.values()) {
            // 2.1 根据当前节点的fid，获取父节点信息
            MyNode parent = nodeMap.get(node.fid);

            // 2.2 确定根节点
            if ("-1".equals(node.fid) || !nodeMap.containsKey(node.fid)) {
                roots.add(node);
            } else {
                // 2.2 将当前节点挂载到父节点
                parent.children.add(node);
            }
        }

        return roots;
    }

    static class MyNode {
        public String id;
        public String fid;
        public String text;
        public List<MyNode> children = new ArrayList<>();

        public MyNode(String id, String fid, String text) {
            this.id = id;
            this.fid = fid;
            this.text = text;
        }
    }
}




