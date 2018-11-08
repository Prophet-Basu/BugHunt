package setgen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import com.utility.DBUtil;

public class FillTable {

	static Connection conn=null;
	
	
// Fisher–Yates shuffle---------------------------------------------------------------------------------
	private static void shuffleArray(int[] array)
	{
	    int index, temp;
	    Random random = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = array[index];
	        array[index] = array[i];
	        array[i] = temp;
	    }
	}
//----------------------------------------------------------------------------------------------------	
	
	
	public static boolean fillTable(int n,int tq, int q){
		boolean status = false;
		
		int[] array = new int[tq];
		for(int i=0;i<tq;i++)
		{
			array[i]=i+1;
		}
		
		
		//-------------------------------------------------------------
		StringBuffer sb = new StringBuffer();
		
		sb.append("insert into "+CreateTable.TBNAME2+" values (?,");
		for(int i=0;i<q;i++)
		{
			sb.append("?,");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(")");
		//-------------------------------------------------------------
		
		String query3 = sb.toString();
		System.out.println(query3);
		
		conn=DBUtil.getConnection("bughunt");
		if(conn!=null){
		
			try {
				PreparedStatement ps=conn.prepareStatement(query3);
				int n2=1;
				while(n2<=n){
					int c=1;
					ps.setInt(c++, n2++);
					shuffleArray(array);
					for(int i=0;i<q;i++){
						ps.setInt(c++, array[i]);
					}
					if(ps.executeUpdate()>0)
						System.out.println("Set inserted !!!");
				}
				status=true;
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
		
		}
		return status;
	}
	
}
