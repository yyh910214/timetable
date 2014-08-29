/*
 * @(#)ThreadTestBO.java $version 2014. 8. 26.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.bo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;

/**
 * @author younghan
 */
public class ThreadTestBO {
	public static void main(String[] args) {
		ThreadTestBO test = new ThreadTestBO();  
		test.threadTest1();  
		
		test.threadTest2();  
		
		test.threadTest3();  
	}
	
	
	public void threadTest1()	{
		ParamClass param = new ParamClass();
		Thread threads[] = new Thread[10];
		
		
		long startTime = System.nanoTime();
		for(int i = 0; i < 10; i++)	{
			threads[i] = new Thread(new RunnableTest(param));
			threads[i].start();
		}
		
		for(int i = 0; i < 10; i++)	{
			try {
				threads[i].join();
			} catch (InterruptedException e) {
			}
		}
		long endTime = System.nanoTime();
		
		System.out.println("Execution1 Time : " + (endTime - startTime));
	}
	
	public void threadTest2()	{
		ExecutorService service = Executors.newFixedThreadPool(10);
		ParamClass param = new ParamClass();
		int threadNum;
		long startTime = System.nanoTime();
		while((threadNum = param.getNext()) != -1)	{
			service.submit(new RunnableTest2(threadNum));
		}
		
		service.shutdown();
		try {
			service.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		}
		long endTime = System.nanoTime();
		System.out.println("Execution2 Time : " + (endTime - startTime));
	}
	
	public void threadTest3()	{
		List<Integer[]> param = Lists.newArrayList();
		
		int t;
		Integer[] temp = null;
		for(int i = 0; i < 100; i++)	{
			if((t=i % 10) == 0)	{
				temp = new Integer[10];
			}
			temp[t] = i;
			if (t == 9)	{
				param.add(temp);
			}
		}
		Thread threads[] = new Thread[10];
		
		long startTime = System.nanoTime();
		for(int i = 0; i < 10; i++)	{
			threads[i] = new Thread(new RunnableTest3(param.get(i)));
			threads[i].start();
		}
		
		for(int i = 0; i < 10; i++)	{
			try {
				threads[i].join();
			} catch (InterruptedException e) {
			}
		}
		long endTime = System.nanoTime();
		
		System.out.println("Execution3 Time : " + (endTime - startTime));
	}
	
	public class RunnableTest implements Runnable	{
		private int threadNum;
		private ParamClass param;
		
		public RunnableTest()	{
			
		}
		
		public RunnableTest(int threadNum)	{
			this.threadNum = threadNum;
		}
		
		public RunnableTest(ParamClass param)	{
			this.param = param;
		}

		/**
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			System.out.println("Start Thread : " + Thread.currentThread().getId());  
			
			while(true)	{
				synchronized(param)	{
					threadNum = param.getNext();
				}
				if(threadNum == -1)	{
					break;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					
				}
				System.out.println("ThreadNum : " + threadNum);
			}
			
			System.out.println("End Thread : " + Thread.currentThread().getId()); 
		}
		
	}
	
	class RunnableTest2 implements Runnable	{
		private int threadNum;
		
		public RunnableTest2()	{
			
		}
		
		public RunnableTest2(int threadNum)	{
			this.threadNum = threadNum;
		}

		public void run()	{
			System.out.println("Start Thread : " + Thread.currentThread().getId()); 
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
			}
			System.out.println("ThreadNum : " + threadNum);
			System.out.println("End Thread : " + Thread.currentThread().getId()); 
		}
		
	}
	
	class RunnableTest3 implements Runnable	{
		private Integer[] threadNum;
		
		public RunnableTest3()	{
			
		}
		
		public RunnableTest3(Integer[] threadNum)	{
			this.threadNum = threadNum;
		}

		public void run()	{
			System.out.println("Start Thread : " + Thread.currentThread().getId());
			for(int i = 0; i < 1000; i++)	{
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					
				}
				System.out.println("ThreadNum : " +  i );
			}
			System.out.println("End Thread : " + Thread.currentThread().getId()); 
		}
	}
	
	class ParamClass	{
		Integer paramArray[];
		int count;

		public ParamClass()	{
			paramArray = new Integer[10000];
			for(int i = 0; i < 10000; i++)	{
				paramArray[i] = i;
			}
		}
		
		public int getNext()	{
			if(count < 10000)	{
				return paramArray[count++];
			}
			return -1;
		}
	}

}
