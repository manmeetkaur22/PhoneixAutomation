package com.dataprovider;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.utils.CSVReaderUtil;
import com.dataprovider.api.bean.UserBean;

public class DataProviderUtils {
	@DataProvider(name="LoginApiDataProvider")
	public static Iterator<UserBean> loginAPIDataProvider()
	{
		return CSVReaderUtil.loadCSV("testData/loginCreds.csv");
		
	}

}
