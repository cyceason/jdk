package com.cyc.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author: 陈奕丞
 * @Date: 2020/1/26 08:52
 * @Description: 插入排序
 * 最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2544, 235445465, 65675, 8, 768, 8, 9, 7897, 87, 9, 87, 97};

        InsertionSort insertionSort = new InsertionSort();
        int[] ints = insertionSort.selectionSort(a);
        System.out.println(JSON.toJSONString(ints));

    }

    private int[] selectionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            int preIndex = i;
            for (int j = i; j > 0; j--) {
                if (array[j - 1] > value) {
                    preIndex = j - 1;
                    array[j] = array[j - 1];
                }
            }
            if (preIndex != i) {
                array[preIndex] = value;
            }

        }
        return array;
    }
}
