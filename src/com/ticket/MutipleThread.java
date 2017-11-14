package com.ticket;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@SuppressWarnings("unchecked")
public class MutipleThread extends Thread {

	private ThreadCallback call;
	private static Map<Integer, String> location = new HashMap<Integer, String>();
	private static int first_array[][];
	private static int second_array[][];
	private int first;
	private int second;
	private int grade;
	//private static ArrayList<Ticket> list = new ArrayList<Ticket>();
	private static Map<String,LinkedList<Ticket>> map = new HashMap<String,LinkedList<Ticket>>();
	private static Map<String,LinkedList<Ticket>> second_map = new HashMap<String,LinkedList<Ticket>>();
	static{
		LinkedList<Ticket> list = new LinkedList<Ticket>();
		for(int i=1;i<=10;i++) {
			for(int j=1;j<=10;j++) {
				Ticket ticket = new Ticket();
				ticket.setCarriage(i);
				ticket.setGrade(1);
				ticket.setNumber(j);
				list.add(ticket);
			}
		}
		LinkedList<Ticket> second_list = new LinkedList<Ticket>();
		for(int i=1;i<=10;i++) {
			for(int j=1;j<=20;j++) {
				Ticket ticket = new Ticket();
				ticket.setCarriage(i);
				ticket.setGrade(2);
				ticket.setNumber(j);
				second_list.add(ticket);
			}
		}
		location.put(0, "成都");
		location.put(1, "德阳");
		location.put(2, "宝鸡");
		location.put(3, "西安");
		location.put(4, "太行山");
		location.put(5, "太原");
		location.put(6, "洛阳");
		location.put(7, "郑州");
		location.put(8, "邯郸");
		location.put(9, "秦皇岛");
		location.put(10, "石家庄");
		location.put(11, "北京");
		first_array=new int[location.keySet().size()-1][];
		second_array = new int[location.keySet().size()-1][];
		for(int i=0;i<first_array.length;i++) {
			first_array[i] = new int[first_array.length-i];
		}
		for(int i=0;i<second_array.length;i++) {
			second_array[i] = new int[second_array.length-i];
		}
		for(int i=0;i<first_array.length;i++) {  
            for(int j=0;j<first_array[i].length;j++) {
            	String s = String.valueOf(i);
            	String s1 = String.valueOf(j+i+1);
            	String temp = s+s1;
            	//int number = Integer.valueOf(temp);
            	LinkedList<Ticket> ticket_list = (LinkedList<Ticket>) list.clone(); //必须用clone方法赋值，不然要改变原有对象，因为指向同一地址
            	map.put(temp, ticket_list);
            }  
        }
		for(int i=0;i<second_array.length;i++) {  
            for(int j=0;j<second_array[i].length;j++) {
            	String s = String.valueOf(i);
            	String s1 = String.valueOf(j+i+1);
            	String temp = s+s1;
            	//int number = Integer.valueOf(temp);
            	LinkedList<Ticket> ticket_list = (LinkedList<Ticket>) second_list.clone(); //必须用clone方法赋值，不然要改变原有对象，因为指向同一地址
            	second_map.put(temp, ticket_list);
            }  
        }
//		ArrayList<Ticket> ticket_list = new ArrayList<Ticket>();
//		ticket_list.add(new Ticket());
		//map.put("05", ticket_list);
//		Set set = map.keySet();
//		System.out.println(set.size());
//		System.out.println(map.get("01").size());
//		map.get("05").remove(0);
//		System.out.println(map.get("01").size());
//		System.out.println(map.get("03").size());
//		System.out.println(map.get("05").size());
//		System.out.println("end init");
	}

	public MutipleThread(ThreadCallback call, int first, int second, int grade) {
		this.call = call;
		this.first = first;
		this.second = second;
		this.grade = grade;
	}

	public static int getCurrentSeatByTrip(int first, int second, int grade) {
		if (second<=first) {
			return -99;
		}
		String s = String.valueOf(first);
    	String s1 = String.valueOf(second);
    	String temp = s+s1;
		int minValue = 0;
		if (grade==1) {
			minValue = map.get(temp).size();
			for(int i=first;i<second;i++) {
				for(int j = 0;j<=first_array[i].length-1;j++) {
					String s3 = String.valueOf(i);
			    	String s4 = String.valueOf(j+i+1);
			    	String temp1 = s3+s4;
					if (minValue>map.get(temp1).size()) {
						minValue = map.get(temp1).size();
					}
				}
			}
		} else {
			minValue = second_map.get(temp).size();
			for(int i=first;i<second;i++) {
				for(int j = 0;j<=second_array[i].length-1;j++) {
					String s3 = String.valueOf(i);
			    	String s4 = String.valueOf(j+i+1);
			    	String temp1 = s3+s4;
					if (minValue>second_map.get(temp1).size()) {
						minValue = second_map.get(temp1).size();
					}
				}
			}
		}
		return minValue;
	}

	/**
	 * 多线程购买火车票，多个线程锁，lock为ticket对象
	 */
	@Override
	public void run() {
		super.run();
		System.out.println(Thread.currentThread().getName());
		for (int i = first; i < second; i++) {
			for (int j = 0; j <= first_array[i].length - 1; j++) {
				String s = String.valueOf(i);
				String s1 = String.valueOf(j+i+1);
				String temp = s+s1;
				if (grade == 1) {
					if (map.get(temp).size() > 0) {
						Ticket ticket = map.get(temp).get(0);
						System.out.println("车厢号：" + ticket.getCarriage()+ ",座位号：" + ticket.getNumber());
						synchronized (ticket) {
							map.get(temp).remove(ticket);
						}
					}
				} else {
					if (second_map.get(temp).size() > 0) {
						Ticket ticket = second_map.get(temp).get(0);
						System.out.println("车厢号：" + ticket.getCarriage()+ ",座位号：" + ticket.getNumber());
						synchronized (ticket) {
							second_map.get(temp).remove(ticket);
						}
					}
				}
			}
		}
		int n = getCurrentSeatByTrip(first,second,grade);
		call.getCurrentSeatNumber(n);
		
	}
}
