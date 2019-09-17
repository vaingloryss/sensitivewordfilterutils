package com.vainglory.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 敏感词库初始化
 */
public class SensitiveWordInit {
    /**
     * 敏感词库
     */
    private static HashMap sensitiveWordMap;


    /**
     * 初始化敏感词库
     * @param sensitiveWordList
     * @return
     */
    public Map initKeyWord(List<String> sensitiveWordList) {
        try {
            // 从敏感词List集合中取出敏感词并封装到Set集合中
            Set<String> sensitiveWordSet = new HashSet<>();
            for (String s : sensitiveWordList) {
                sensitiveWordSet.add(s.trim());
            }
            // 将敏感词库加入到HashMap中
            addSensitiveWordToHashMap(sensitiveWordSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensitiveWordMap;
    }

    /**
     * 封装敏感词库
     * @param sensitiveWordSet
     */
    private void addSensitiveWordToHashMap(Set<String> sensitiveWordSet) {
        // 初始化HashMap对象并控制容器的大小
        sensitiveWordMap = new HashMap(sensitiveWordSet.size());
        // 敏感词
        String key = null;
        // 用来按照相应的格式保存敏感词库数据,
        Map nowMap = null;
        // 用来辅助构建敏感词库
        Map<String, String> newWordMap;
        // 使用一个迭代器来循环敏感词Set集合sensitiveWordSet
        Iterator<String> iterator = sensitiveWordSet.iterator();
        while (iterator.hasNext()){
            key = iterator.next();
            //将sensitiveWordMap集合的引用赋给nowMap，
            nowMap = sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                // 截取敏感词当中的字，在敏感词库中字为HashMap对象的Key值
                char keyChar = key.charAt(i);
                // 判断这个字是否存在于敏感词库中
                Object wordMap = nowMap.get(keyChar);

                if (wordMap != null){
                    nowMap = (Map) wordMap;
                    //System.out.println(i+"nowMap："+nowMap);
                }else{
                    //构建敏感词中关键字的Map
                    newWordMap = new HashMap();
                    newWordMap.put("isEnd", "0");
                    //将敏感词的关键字作为键，敏感词的关键字map作为值，插入到nowMap
                    nowMap.put(keyChar, newWordMap);
                    //nowMap的索引指向最里层的关键字集合
                    nowMap = newWordMap;
                }
                // 如果该字是当前敏感词的最后一个字，则标识为结尾字
                if (i == key.length() - 1) {
                    //修改标记位为1
                    nowMap.put("isEnd", "1");
                }
                System.out.println("封装敏感词库过程"+sensitiveWordMap);
            }

            System.out.println("\n查看敏感词库数据"+sensitiveWordMap+"\n");
        }
    }
}
