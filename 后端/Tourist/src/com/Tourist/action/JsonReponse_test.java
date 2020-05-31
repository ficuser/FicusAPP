package com.Tourist.action;

import java.util.List;

public class JsonReponse_test {



	private int nums;
	
	private List<test> classdata;
	
	public class test{
		private int status;
		
		private String string;

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}
	}
	
	public void setClass(test data)
	{
		classdata.add(data);
	}

	public int getNums(){
		return nums;
	}

	public void setNums(int nums){
		this.nums = nums;
	}
}
