package com.col.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.col.util.ConnUtil;
import com.col.vo.ExcelVO;

public class ExcelDAO {
	
	private Connection conn;
	
	private void getConn() throws Exception
	{
		conn=ConnUtil.getUtil().getConn();
	}
	
	public List<ExcelVO> selectAll() throws Exception
	{
		getConn();
		
		List<ExcelVO> result=new ArrayList<ExcelVO>();
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select t_dbName, t_topic, t_tableName, t_tableNameKo, t_attrName  ");
		sql.append("  , t_colName, t_dataType, t_note, s_dbName, s_tableName, s_tableNameKo  ");
		sql.append("  , s_attrName, s_colName, s_dataType, s_note, m_terms, m_note ");
		sql.append(" from columnmapping ");
		
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql.toString());
		rs.setFetchSize(50);
		
		while(rs.next())
		{
			ExcelVO vo=new ExcelVO();
			vo.setT_dbName(rs.getString("t_dbName"));
			vo.setT_topic(rs.getString("t_topic"));
			vo.setT_tableName(rs.getString("t_tableName"));
			vo.setT_tableNameKo(rs.getString("t_tableNameKo"));
			vo.setT_attrName(rs.getString("t_attrName"));
			vo.setT_colName(rs.getString("t_colName"));
			vo.setT_dataType(rs.getString("t_dataType"));
			vo.setT_note(rs.getString("t_note"));
			vo.setS_dbName(rs.getString("s_dbName"));
			vo.setS_tableName(rs.getString("s_tableName"));
			vo.setS_tableNameKo(rs.getString("s_tableNameKo"));
			vo.setS_attrName(rs.getString("s_attrName"));
			vo.setS_colName(rs.getString("s_colName"));
			vo.setS_dataType(rs.getString("s_dataType"));
			vo.setS_note(rs.getString("s_note"));
			vo.setM_terms(rs.getString("m_terms"));
			vo.setM_note(rs.getString("m_note"));
			
			result.add(vo);
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	public List<ExcelVO> search(ExcelVO vo) throws Exception
	{
		getConn();
		
		List<ExcelVO> result=new ArrayList<ExcelVO>();
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select t_dbname, t_topic, t_tablename, t_tablenameko, t_attrname ");
		sql.append("  , t_colname, t_datatype, t_note, s_dbname, s_tableName, s_tablenameko  ");
		sql.append("  , s_attrname, s_colname, s_datatype, s_note, m_terms, m_note ");
		sql.append(" from columnmapping ");
		sql.append(" where 1=1 ");
		
		//조건절 삽입
		if(vo.getT_tableName()!=null)
		{
			sql.append("   and instr(lower(t_tablename), lower('");
			sql.append(vo.getT_tableName());
			sql.append("'))>0 ");
		}
		
		if(vo.getT_colName()!=null)
		{
			sql.append("   and instr(lower(t_colname), lower('");
			sql.append(vo.getT_colName());
			sql.append("'))>0 ");
		}
		
		if(vo.getS_tableName()!=null)
		{
			sql.append("   and instr(lower(s_tablename), lower('");
			sql.append(vo.getS_tableName());
			sql.append("'))>0 ");
		}
		
		if(vo.getS_colName()!=null)
		{
			sql.append("   and instr(lower(s_colname), lower('");
			sql.append(vo.getS_colName());
			sql.append("'))>0 ");
		}
		
		//test log
		System.out.println("search method");
		System.out.println("query log: ");
		System.out.println(sql.toString());
		
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql.toString());
		
		while(rs.next())
		{
			ExcelVO resultVO=new ExcelVO();
			resultVO.setT_dbName(rs.getString("t_dbname"));
			resultVO.setT_topic(rs.getString("t_topic"));
			resultVO.setT_tableName(rs.getString("t_tablename"));
			resultVO.setT_tableNameKo(rs.getString("t_tablenameko"));
			resultVO.setT_attrName(rs.getString("t_attrname"));
			resultVO.setT_colName(rs.getString("t_colname"));
			resultVO.setT_dataType(rs.getString("t_datatype"));
			resultVO.setT_note(rs.getString("t_note"));
			resultVO.setS_dbName(rs.getString("s_dbname"));
			resultVO.setS_tableName(rs.getString("s_tablename"));
			resultVO.setS_tableNameKo(rs.getString("s_tablenameko"));
			resultVO.setS_attrName(rs.getString("s_attrname"));
			resultVO.setS_colName(rs.getString("s_colname"));
			resultVO.setS_dataType(rs.getString("s_datatype"));
			resultVO.setS_note(rs.getString("s_note"));
			resultVO.setM_terms(rs.getString("m_terms"));
			resultVO.setM_note(rs.getString("m_note"));
			
			result.add(resultVO);
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
}





