package com.cyc.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author: 陈奕丞
 * @Date: 2020/1/26 08:52
 * @Description: 选择排序
 * 最佳情况：T(n) = O(n2)  最差情况：T(n) = O(n2)  平均情况：T(n) = O(n2)
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2544, 235445465, 65675, 8, 768, 8, 9, 7897, 87, 9, 87, 97};

        SelectionSort selectionSort = new SelectionSort();
        int[] ints = selectionSort.selectionSort(a);
        System.out.println(JSON.toJSONString(ints));

    }

    private int[] selectionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
