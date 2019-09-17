package com.vainglory.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 敏感词过滤工具类
 */
public class SensitiveWordEngine {

     //敏感词库
    public static Map sensitiveWordMap = null;
    //只过滤最小敏感词
    //123，1234都是敏感词，过滤到123即返回
    public static int minMatchType = 1;
    //过滤所有敏感词
    //123，1234都是敏感词，过滤到1234才返回
    public static int maxMatchType = 2;

    /**
     * 敏感词库敏感词数量
     * @return
     */
    public static int getWordSize(){
        if (SensitiveWordEngine.sensitiveWordMap == null){
            return 0;
        }
        return SensitiveWordEngine.sensitiveWordMap.size();
    }

    /**
     * 是否包含敏感词
     *
     * @param txt
     * @param matchType
     * @return
     */
    public static boolean isContainSensitiveWord(String txt, int matchType) {
        //大家好我是私家侦探啦啦
        boolean flag = false;
        for (int i = 0; i < txt.length(); i++){
            int matchFlag = checkSensitiveWord(txt, i, matchType);
            if (matchFlag > 0){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取敏感词内容
     *
     * @param txt
     * @param matchType
     * @return 敏感词内容
     */
    public static Set<String> getSensitiveWord(String txt, int matchType){
        //set集合，不重复存储敏感词
        Set<String> sensitiveWordList = new HashSet<String>();
        for (int i = 0; i < txt.length(); i++){
            //i表示敏感词开始的位置，length表示该敏感词的长度
            int length = checkSensitiveWord(txt, i, matchType);
            if (length > 0){
                // 将检测出的敏感词保存到集合中
                sensitiveWordList.add(txt.substring(i, i + length));
                i = i + length - 1;
            }
        }
        return sensitiveWordList;
    }

    /**
     * 替换敏感词
     *
     * @param txt
     * @param matchType
     * @param replaceChar
     * @return
     */
    public static String replaceSensitiveWord(String txt, int matchType, String replaceChar){
        String resultTxt = txt;
        Set<String> set = getSensitiveWord(txt, matchType);
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString;
        while (iterator.hasNext()){
            word = iterator.next();
            System.out.println("word:"+word);
            replaceString = getReplaceChars(replaceChar, word.length());
            System.out.println(replaceString);
            //存在问题，私家，和私家侦探都是敏感词，第一遍把私家全部换成**，第二遍替换私家侦探时会导致替换不成功
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }
        return resultTxt;
    }

    /**
     * 用于代替敏感词的内容
     *
     * @param replaceChar
     * @param length
     * @return
     */
    private static String getReplaceChars(String replaceChar, int length){
        String resultReplace = replaceChar;
        for (int i = 1; i < length; i++){
            resultReplace += replaceChar;
        }
        return resultReplace;
    }

    /**
     * 检查是否包含敏感词，返回找到的该敏感词的长度
     *
     * @param txt
     * @param beginIndex
     * @param matchType
     * @return
     */
    public static int checkSensitiveWord(String txt, int beginIndex, int matchType){
        //大家好我是私家侦探啦啦
        boolean flag = false;
        // 匹配标记，记录敏感词的长度
        int matchFlag = 0;
        char word;
        Map nowMap = SensitiveWordEngine.sensitiveWordMap;
        for (int i = beginIndex; i < txt.length(); i++){
            word = txt.charAt(i);
            // 判断该字是否存在于敏感词库中
            nowMap = (Map) nowMap.get(word);
            if (nowMap != null) {
                System.out.println(word+" = "+nowMap);
                matchFlag++;
                // 判断是否是敏感词的结尾字，如果是结尾字则判断是否继续检测
                if ("1".equals(nowMap.get("isEnd"))) {
                    flag = true;
                    // 判断过滤类型，如果是最小过滤则跳出循环，否则继续循环
                    if (SensitiveWordEngine.minMatchType == matchType){
                        break;
                    }
                }
            }else{
                break;
            }
        }
        if (!flag){
            matchFlag = 0;
        }
        //System.out.println("matchFlag:"+matchFlag);
        return matchFlag;
    }
}
