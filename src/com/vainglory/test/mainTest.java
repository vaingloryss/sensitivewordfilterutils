package com.vainglory.test;

import com.vainglory.util.SensitiveWordEngine;
import com.vainglory.util.SensitiveWordInit;
import com.vainglory.util.SensitiveWordList;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class mainTest {

    private static String txt;
    @BeforeClass
    public static void initTest(){
        txt ="好哈略略略哈哈我是辉大家好哈哈哈好哈略略略哈哈我大师大家好哈哈哈好哈略略略哈哈我是辉大家大家好哈哈哈大师";
    }

    //初始化敏感词库
    @Test
    public void test05(){
        SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();
        sensitiveWordInit.initKeyWord(SensitiveWordList.getSensWordList());
    }

    /**
     * 检查是否包含敏感词
     */
    @Test
    public void isContainSensitiveWord(){
        String txt = "大家好我是私家侦探啦啦";
        System.out.println("信息内容为:"+txt);
        boolean boo = false;
        try {
            // 初始化敏感词库对象
            SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();
            // 构建敏感词库，将敏感词库sensitiveWordMap传入SensitiveWordEngine类
            SensitiveWordEngine.sensitiveWordMap = sensitiveWordInit.initKeyWord(SensitiveWordList.getSensWordList());
            boo = SensitiveWordEngine.isContainSensitiveWord(txt, SensitiveWordEngine.maxMatchType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(boo);
    }

    /*
     * 获取敏感词内容
     */
    @Test
    public void getSensitiveWord(){
        String txt = "好哈私家哈哈我是辉大私家侦探家好哈哈哈";
        System.out.println("信息内容为:"+txt);
        Set<String> sensitiveWordList;
        try {
            // 初始化敏感词库对象
            SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();
            // 构建敏感词库,传入SensitiveWordEngine类中的敏感词库
            SensitiveWordEngine.sensitiveWordMap = sensitiveWordInit.initKeyWord(SensitiveWordList.getSensWordList());
            sensitiveWordList = SensitiveWordEngine.getSensitiveWord(txt, SensitiveWordEngine.maxMatchType);

            for (String word : sensitiveWordList) {
                System.out.println(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 替换敏感词内容
     */
    @Test
    public void replaceSensitiveWord(){
        String resultTxt;
        try {
            // 初始化敏感词库对象
            SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();
            // 构建敏感词库,传入SensitiveWordEngine类中的敏感词库
            SensitiveWordEngine.sensitiveWordMap = sensitiveWordInit.initKeyWord(SensitiveWordList.getSensWordList());
            long before = System.currentTimeMillis();
            resultTxt = SensitiveWordEngine.replaceSensitiveWord(txt,SensitiveWordEngine.maxMatchType,"*");
            long after = System.currentTimeMillis();
            System.out.println("信息长度："+txt.length());
            System.out.println("词库数量："+SensitiveWordList.getSensWordList().size());
            System.out.println("用时："+(after-before)+"毫秒");
            System.out.println(resultTxt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试不使用敏感词过滤工具耗时
     */
    @Test
    public void testList(){
        List<String> sensWordList = SensitiveWordList.getSensWordList();
        String replaceStr = "";
        String resultTxt = "";
        long before = System.currentTimeMillis();
        for (String s : sensWordList) {
            if(txt.contains(s)){
                for (int i = 0;i<s.length();i++){
                    replaceStr+="*";
                }
                resultTxt = txt.replaceAll(s, replaceStr);
            }
        }
        long after = System.currentTimeMillis();
        System.out.println("信息长度："+txt.length());
        System.out.println("词库数量："+sensWordList.size());
        System.out.println("用时："+(after-before)+"毫秒");
        System.out.println(resultTxt);
    }
}
