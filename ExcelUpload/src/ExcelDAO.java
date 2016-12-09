import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ExcelDAO {
	
	private Connection conn;
	
	private void getConn() throws SQLException
	{
		conn=ConnUtil.getUtil().getConn();
	}
	
	public boolean insertAll(List<ExcelVO> list) throws SQLException
	{
		getConn();
		
		int resultCount=0;
		
		StringBuilder sb=new StringBuilder();
		sb.append(" insert into columnMapping (t_dbName, t_topic, t_tableName, t_tableNameKo, t_attrName ");
		sb.append("  , t_colName, t_dataType, t_note, s_dbName, s_tableName, s_tableNameKo  ");
		sb.append("  , s_attrName, s_colName, s_dataType, s_note, m_terms, m_note) ");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "); 
		
		conn.setAutoCommit(false);
		PreparedStatement pstmt=conn.prepareStatement(sb.toString());
		
		for(int i=0; i<list.size(); i++)
		{
			pstmt.setString(1, list.get(i).getT_dbName());
			pstmt.setString(2, list.get(i).getT_topic());
			pstmt.setString(3, list.get(i).getT_tableName());
			pstmt.setString(4, list.get(i).getT_tableNameKo());
			pstmt.setString(5, list.get(i).getT_attrName());
			pstmt.setString(6, list.get(i).getT_colName());
			pstmt.setString(7, list.get(i).getT_dataType());
			pstmt.setString(8, list.get(i).getT_note());
			pstmt.setString(9, list.get(i).getS_dbName());
			pstmt.setString(10, list.get(i).getS_tableName());
			pstmt.setString(11, list.get(i).getS_tableNameKo());
			pstmt.setString(12, list.get(i).getS_attrName());
			pstmt.setString(13, list.get(i).getS_colName());
			pstmt.setString(14, list.get(i).getS_dataType());
			pstmt.setString(15, list.get(i).getS_note());
			pstmt.setString(16, list.get(i).getM_terms());
			pstmt.setString(17, list.get(i).getM_note());
			
			pstmt.addBatch();
			
			//1000개마다 executeBatch()
			if(i%1000==0)
			{
				pstmt.executeBatch();
				pstmt.clearBatch();
				conn.commit();
				resultCount++;
			}
		}
		
		//executeBatch 실행&Statement 닫기
		int[] batch=pstmt.executeBatch();
		conn.commit();
		pstmt.close();
		
		if(batch.length>0)
			resultCount++;

		if(resultCount<=0)
			return false;
		else
			return true;
	}
	
	public boolean deleteAll() throws SQLException
	{
		getConn();
		
		int resultCount=0;
		
		StringBuilder sb=new StringBuilder();
		sb.append(" truncate table columnMapping ");
		
		conn.setAutoCommit(false);
		Statement stmt=conn.createStatement();
		resultCount=stmt.executeUpdate(sb.toString());
		
		conn.commit();
		stmt.close();
		
		if(resultCount<=0)
			return false;
		else
			return true;
		
	}
	
}
