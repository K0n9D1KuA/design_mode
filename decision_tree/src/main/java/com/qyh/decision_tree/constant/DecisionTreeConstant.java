package com.qyh.decision_tree.constant;

/**
 * @author 空德夸
 * @version 1.0
 * @description: 决策树常量类
 * @date 2022/11/24 23:23
 */

public class DecisionTreeConstant {

    //叶子节点：1 非叶子节点：0
    //代表是叶子节点
    public static Integer LEAF_NODE_TYPE = 1;
    //代表是非叶子节点
    public static Integer NOT_LEAF_NODE_TYPE = 0;

    //树枝判断类型  1:=  2:> 3:< 4:>= 5:<= 6:可以扩展的其他类型 枚举
    public static Integer LIMIT_TYPE_EQUAL = 1;
    public static Integer LIMIT_TYPE_GREATER = 2;
    public static Integer LIMIT_TYPE_LESS = 3;
    public static Integer LIMIT_TYPE_GREATER_EQUAL = 4;
    public static Integer LIMIT_TYPE_LESS_EQUAL = 5;

    //性别决策
    public static String SEX_DECISION = "sex";
    //年龄
    public static String AGE_DECISION = "age";
    //人群分布决策树
    public static String CROWD_DISTRIBUTION_DECISION_TREE = "CROWD_DISTRIBUTION_DECISION_TREE";


}
