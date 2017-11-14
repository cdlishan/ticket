package com.ticket;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		Map<Integer, String> location = new HashMap<Integer, String>();
		int array[][];
		location.put(1, "成都");
		location.put(2, "德阳");
		location.put(3, "宝鸡");
		location.put(4, "西安");
		location.put(5, "太行山");
		location.put(6, "太原");
		location.put(7, "洛阳");
		location.put(8, "郑州");
		location.put(9, "邯郸");
		location.put(10, "秦皇岛");
		location.put(11, "石家庄");
		location.put(12, "北京");
		System.out.println(location.keySet().size()-1);
		array=new int[location.keySet().size()-1][];
		System.out.println(array.length);
		for(int i=0;i<array.length;i++) {
			array[i] = new int[array.length-i];
		}
		for(int i=0;i<array.length;i++) {  
            for(int j=0;j<array[i].length;j++) {  
            	array[i][j]=400;
            }  
        }  
		System.out.println("end init");
	}
}
