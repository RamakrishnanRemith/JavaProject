package model.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import common.DataBaseConnection;
import common.User;
import controller.batch.BatchListAction;

public class BatchActionListDAO {


	public static  String Batchview(BatchListAction batchListAction) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session=batchListAction.request.getSession();
		session=batchListAction.request.getSession(true);
		String status="loginRedirectFail";
		String sessionbatchId=(String)session.getAttribute("sessionbatchId");
	String sql=null;
		//select process
	
		Connection con= DataBaseConnection.getConnection();
		sql="SELECT*FROM tbl_batch Where".concat(batchListAction.condition).concat(batchListAction.searchCondition).concat(batchListAction.sortCondition)+" LIMIT "+(batchListAction.pageid-1)+" , "+batchListAction.recordsPerPage;
		java.sql.PreparedStatement ps=con.prepareStatement(sql);
		
		
		ResultSet getValue=ps.executeQuery();
		int sno =0;
		batchListAction.list=new ArrayList<User>();
		while(getValue.next()){
			User user = new User();
			sno++;
			user.setSno(sno);
			user.setBatchName(getValue.getString("batchName"));
			user.setBatchId(getValue.getString("batchId"));
			user.setStartDate(getValue.getString("startDate"));
			user.setEndDate(getValue.getString("endDate"));
			user.setDelflag(getValue.getInt("delflag"));
			//set user in list
			batchListAction.list.add(user);
		}
		if(!batchListAction.list.equals(null)){
			status="loginRedirectSuccess";
		}else{
			System.out.println("loginRedirectFail");
		}
		
		return status;
}
	

	public static String BatchlistUse(BatchListAction batchListAction) {
		String status ="UseFail";
	
		try {
			Connection con= DataBaseConnection.getConnection();
			PreparedStatement useUpdate = con.prepareStatement("Update tbl_batch set delflag=0 where batchId = ?");
			useUpdate.setString(1, batchListAction.getHdnuserid());
			int delUpdate=useUpdate.executeUpdate();
			if(delUpdate==1) {
				status ="UseSuccess";
			}
		} catch(Exception e1) {
			System.out.println(e1);
		}
		return status;
	}

	public static String BatchnotlistUse(BatchListAction batchListAction) {
		// TODO Auto-generated method stub
		String status ="NotUseFail";
		try {
			Connection con= DataBaseConnection.getConnection();
			PreparedStatement notUseUpdate = con.prepareStatement("Update tbl_batch set delflag=1 where batchId = ?");
			notUseUpdate.setString(1, batchListAction.getHdnuserid());
			int delUpdate=notUseUpdate.executeUpdate();
			if(delUpdate==1){
				status ="NotUseSuccess";
			}
			
		} catch(Exception e1) {
			System.out.println(e1);
		}
		return status;
	}


}
	
	
	

