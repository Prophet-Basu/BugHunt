package com.dao;

import java.util.ArrayList;

import com.bean.QuoAnsBean;

public interface QuoAnsDao {

	public void insertQandA(QuoAnsBean qabean);

	public QuoAnsBean fetchQandA(int qno);

	public ArrayList<QuoAnsBean> fetchAll();

	public int getTotalQues();

}
