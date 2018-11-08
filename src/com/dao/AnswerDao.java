package com.dao;

import java.sql.Timestamp;

public interface AnswerDao {

	public boolean recordAnswer(int teamID, String teamname, int qno, int givenAns,int lang, Timestamp ts,long diff);

	public boolean updateAnswer(int teamID, String teamname, int qno, int givenAns,int lang, Timestamp ts,long diff);
}
