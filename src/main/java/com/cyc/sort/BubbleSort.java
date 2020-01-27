package com.cyc.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author: 陈奕丞
 * @Date: 2020/1/25 16:35
 * @Description: 冒泡排序算法
 * 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2544, 235445465, 65675, 8, 768, 8, 9, 7897, 87, 9, 87, 97};

        BubbleSort bubbleSort = new BubbleSort();
        int[] ints = bubbleSort.bubbleSort(a);
        System.out.println(JSON.toJSONString(ints));

    }

    private int[] bubbleSort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        return array;
    }
}
