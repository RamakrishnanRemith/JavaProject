package controller.Student;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.mysql.jdbc.Connection;

import common.DataBaseConnection;

/**
 * ExcelDownloadAction
 * @author Prem kumar
 *
 */
public class ExcelDownloadAction implements ServletRequestAware, ServletResponseAware {
	HttpSession session;
	HttpServletRequest request;
	HttpServletResponse  response;
	private String setCellValue;
	/**
	 * templateExcelDownload
	 * @throws IOException
	 */
	public void templateExcelDownload()throws IOException{
		String INVOICE_FILE = "static/template/template.xls";
		String filepath = ServletActionContext.getServletContext().getRealPath("/")+INVOICE_FILE;
		POIFSFileSystem filein = new POIFSFileSystem(new FileInputStream(filepath));
		// new workbook in the above path
		HSSFWorkbook wb = new HSSFWorkbook(filein);
		//get sheet with name Sheet1 
		HSSFSheet sheet = wb.getSheet("Sheet1");
		try {
			Connection con=(Connection) DataBaseConnection.getConnection();
			String sql = null;int rowIndex = 12;
			//select process
			sql = "SELECT * FROM tbl_student WHERE delflag = '0' LIMIT 15 ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int sno =1;
			while(rs.next()) {
				HSSFRow detailsRowStyle = sheet.getRow(rowIndex);
				HSSFCell snoDetailsCellStyle = detailsRowStyle.getCell((short) 2);
				HSSFCell studentIdDetailsCellStyle = detailsRowStyle.getCell((short) 3);
				HSSFCell studentNameDetailsCellStyle = detailsRowStyle.getCell((short) 4);
				HSSFCell genderDetailsCellStyle = detailsRowStyle.getCell((short) 5);
				HSSFCell dobDetailsCellStyle = detailsRowStyle.getCell((short) 6);
				HSSFCell mailIdDetailsCellStyle = detailsRowStyle.getCell((short) 7);
				HSSFCell contactNoDetailsCellStyle = detailsRowStyle.getCell((short) 8);
				
				HSSFRow detailsRow = sheet.getRow(rowIndex);
				if(detailsRow == null) {
					sheet.createRow(rowIndex);
				}
				HSSFCell snoDetailsCellValue = detailsRow.getCell((short) 2);
				HSSFCellStyle snoStyle = snoDetailsCellStyle.getCellStyle();
				if(snoDetailsCellValue == null) {
					snoDetailsCellValue = detailsRow.createCell((short)2);
					}
				double temp = snoDetailsCellValue.getNumericCellValue();
				
				if(temp ==0.0) {
					snoDetailsCellValue.setCellStyle(snoStyle);
					snoDetailsCellValue.setCellValue(sno);
				}		
				
				HSSFCell studentIdDetailsCellValue = detailsRow.getCell((short) 3);
				HSSFCellStyle studentIdStyle = studentIdDetailsCellStyle.getCellStyle();
				if(studentIdDetailsCellValue == null) {
					studentIdDetailsCellValue = detailsRow.createCell((short)3);
				}
				studentIdDetailsCellValue.setCellStyle(studentIdStyle);
				studentIdDetailsCellValue.setEncoding(HSSFCell.ENCODING_UTF_16);
				studentIdDetailsCellValue.setCellValue(rs.getString("studentId"));
				
				HSSFCell studentNameDetailsCellValue = detailsRow.getCell((short) 4);
				HSSFCellStyle studentNameStyle = studentNameDetailsCellStyle.getCellStyle();
				if(studentNameDetailsCellValue == null) {
					studentNameDetailsCellValue = detailsRow.createCell((short)4);
				}
				studentNameDetailsCellValue.setCellStyle(studentNameStyle);
				studentNameDetailsCellValue.setEncoding(HSSFCell.ENCODING_UTF_16);
				studentNameDetailsCellValue.setCellValue(rs.getString("studentName"));
				
				HSSFCell genderDetailsCellValue = detailsRow.getCell((short) 5);
				HSSFCellStyle genderStyle = genderDetailsCellStyle.getCellStyle();
				if(genderDetailsCellValue == null) {
					genderDetailsCellValue = detailsRow.createCell((short)5);
				}
				genderDetailsCellValue.setCellStyle(genderStyle);
				genderDetailsCellValue.setEncoding(HSSFCell.ENCODING_UTF_16);
				String gender;
				if(rs.getInt("gender")==1){
					gender="M";
				} else {
					gender="F";
				}
				genderDetailsCellValue.setCellValue(gender);
				HSSFCell dobDetailsCellValue = detailsRow.getCell((short) 6);
				HSSFCellStyle dobStyle = dobDetailsCellStyle.getCellStyle();
				if(dobDetailsCellValue == null) {
					dobDetailsCellValue = detailsRow.createCell((short)6);
				}
				dobDetailsCellValue.setCellStyle(dobStyle);
				dobDetailsCellValue.setEncoding(HSSFCell.ENCODING_UTF_16);
				dobDetailsCellValue.setCellValue(rs.getString("dob"));
				
				HSSFCell mailIdDetailsCellValue = detailsRow.getCell((short) 7);
				HSSFCellStyle mailIdStyle = mailIdDetailsCellStyle.getCellStyle();
				if(mailIdDetailsCellValue == null) {
					mailIdDetailsCellValue = detailsRow.createCell((short)7);
				}
				mailIdDetailsCellValue.setCellStyle(mailIdStyle);
				mailIdDetailsCellValue.setEncoding(HSSFCell.ENCODING_UTF_16);
				mailIdDetailsCellValue.setCellValue(rs.getString("mailId"));
				
				HSSFCell contactNoDetailsCellValue = detailsRow.getCell((short) 8);
				HSSFCellStyle contactNoStyle = contactNoDetailsCellStyle.getCellStyle();
				if(contactNoDetailsCellValue == null) {
					contactNoDetailsCellValue = detailsRow.createCell((short)8);
				}
				contactNoDetailsCellValue.setCellStyle(contactNoStyle);
				contactNoDetailsCellValue.setEncoding(HSSFCell.ENCODING_UTF_16);
				contactNoDetailsCellValue.setCellValue(rs.getString("contactNo"));
				sno++;
				rowIndex++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String assets = ServletActionContext.getServletContext().getRealPath("/").concat("static/excel").concat("/");
		String excelFile = "Student.xls";
		File f = new File(assets + excelFile);
		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);
		wb.write(baos);
		response.addHeader("Content-Disposition", "attachment; filename="+excelFile);
		response.setContentLength((int) f.length());
		response.setContentLength(baos.size());
		ServletOutputStream sos;
		sos = response.getOutputStream();
		baos.writeTo(sos);
		sos.flush();
	}
	
	/**
	 * 
	 * method Import
	 */
	public String Import(){
		int id= 1;
		String label="STU",sqls;
		String status = "success" ,sql,csvFilePath = "static/template/csv_import.csv";
		String filepath = ServletActionContext.getServletContext().getRealPath("/")+csvFilePath;
		int batch = 20; Connection con =null;
		try {
			con=(Connection) DataBaseConnection.getConnection();
			//insert Process
			sql = "insert into tbl_student(studentName,dob,mailId,contactNo,address,pincode,gender,studentId) values (?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			String lineText=null; int count =0;
			BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
			lineReader.readLine();
			while ((lineText = lineReader.readLine())!=null){
				sqls = "SELECT id FROM tbl_student ORDER BY id DESC Limit 1";
				java.sql.PreparedStatement pselect1=con.prepareStatement(sqls);
				ResultSet rs1=pselect1.executeQuery();
				if(rs1.next()) {
					id=rs1.getInt("id") + 1;
				}
				String studentId=String.format(label+"%05d",id);
				String [] data = lineText.split(",");
				String Name =data[1];
				ps.setString(1, Name);
				String Dob =data[2];
				ps.setString(2, Dob);
				String MailId =data[3];
				ps.setString(3, MailId);
				String ContactNo =data[4];
				ps.setString(4, ContactNo);
				String Address =data[5];
				ps.setString(5, Address);
				String Pincode =data[6];
				ps.setString(6, Pincode);
				int Gender =Integer.parseInt(data[7]);
				ps.setInt(7, Gender);
				ps.setString(8, studentId);
				ps.addBatch();
				if(count%batch==0){
					ps.executeBatch();
				}
			}
			lineReader.close();
			int [] result = ps.executeBatch();
			for (int i=0;i<result.length;i++) {
				if(result[i]!=1) {
					status="fail";
				}
			}
			con.commit();
			con.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return the setCellValue
	 */
	public String getSetCellValue() {
		return setCellValue;
	}

	/**
	 * @param setCellValue the setCellValue to set
	 */
	public void setSetCellValue(String setCellValue) {
		this.setCellValue = setCellValue;
	}
}