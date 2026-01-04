package com.api.constant;
//Add Constant

public enum OEM {

	GOOGLE(1), APPLES(2);
	int code;

	private OEM(int code) {
		this.code=code;
	}
	public int getCode()
	{
		return code;
	}

}
