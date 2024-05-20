package feedback.web.util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;

import feedback.core.models.entities.Code;
import feedback.core.models.entities.Student;
import feedback.core.models.entities.Subject;

public class FacesUtil {
	public static final String EMAIL_STUDENT_EX="@hutech.edu.vn";
	public static final String EMAIL_TEACHER_EX="@hutech.edu.vn";
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static Object getApplicationObject(Object key) {
		return getFacesContext().getExternalContext().getApplicationMap()
				.get(key);
	}

	public static Object getSessionObject(Object key) {
		return getFacesContext().getExternalContext().getSessionMap().get(key);
	}

	public static Object getRequestObject(Object key) {
		return getFacesContext().getExternalContext().getRequestMap().get(key);
	}

	public static Object setApplicationObject(String key, Object value) {
		return getFacesContext().getExternalContext().getApplicationMap()
				.put(key, value);
	}

	public static Object setSessionObject(String key, Object value) {
		return getFacesContext().getExternalContext().getSessionMap()
				.put(key, value);
	}

	public static Object setRequestObject(String key, Object value) {
		return getFacesContext().getExternalContext().getRequestMap()
				.put(key, value);
	}
	 public static String encryptMD5(String input) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(input.getBytes());
	            BigInteger number = new BigInteger(1, messageDigest);
	            String hashtext = number.toString(16);
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	            return hashtext;
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 public static List<Code> readExcelStudentMutliSheet(UploadedFile file) {
   	  List<Code> rs  =new ArrayList<Code>();
   	  try {
		 XSSFWorkbook workbook = new XSSFWorkbook(file.getInputstream());
			Iterator<XSSFSheet> iterator = workbook.iterator();
			while(iterator.hasNext()){
				XSSFSheet sheet = iterator.next();
				List<Student> list = readExcelStudentOneSheet(sheet);
				rs.add(new Code(sheet.getSheetName(),getNameCode(sheet),list));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	  return rs;

 	}
     public static List<XSSFSheet> readExcelStudentMutliSheetToList(UploadedFile file){
   	  List<XSSFSheet> rs  =new ArrayList<XSSFSheet>();
   	  try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputstream());
			Iterator<XSSFSheet> iterator = workbook.iterator();
			while(iterator.hasNext()){
				rs.add(iterator.next());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	  return rs;
     }
     public static List<XSSFSheet> readExcelStudentMutliSheetToList(XSSFWorkbook workbook){
      	  List<XSSFSheet> rs  =new ArrayList<XSSFSheet>();
      	  Iterator<XSSFSheet> iterator = workbook.iterator();
			while(iterator.hasNext()){
				rs.add(iterator.next());
			}
      	  return rs;

        }
     public static List<Code> readExcel(XSSFWorkbook workbook){
    	 List<Code> result = new ArrayList<Code>();
    	 List<XSSFSheet> sheets  = readExcelStudentMutliSheetToList(workbook);
    	 for(XSSFSheet sheet: sheets){
    		Code code = new Code();
    		code.setCodeNo(sheet.getSheetName());
    		code.setCodeName(getNameCode(sheet));
    		code.addStudent(readExcelStudentOneSheet(sheet));
    		result.add(code);
    	 }
		return result;
    	 
     }
     public static String getNameCode(XSSFSheet sheet){
   	  Iterator<Row> rowI = sheet.iterator();
   	  while (rowI.hasNext()) {
 			Row row = rowI.next();
 			Iterator<Cell> iterator = row.cellIterator();
 			while (iterator.hasNext()) {
				Cell cell = iterator.next();
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if (cell.getStringCellValue().trim().equalsIgnoreCase("Lớp")||cell.getStringCellValue().trim().equalsIgnoreCase("code")) {
						Cell cell2 = iterator.next();
						return getStringCellValus(cell2);
					}

				}
   	  }
   	  }
   	  return null;
     }
     public static List<Student> readExcelStudentOneSheet(XSSFSheet sheet) {
 		Boolean read = false;

 		List<Student> rs = new ArrayList<Student>();
 		Iterator<Row> rowI = sheet.iterator();
		int culBeginRead = 0;
		while (rowI.hasNext()) {
			Row row = rowI.next();
			Iterator<Cell> iterator = row.cellIterator();
			if (!read) {
				readCell: while (iterator.hasNext()) {
					Cell cell = iterator.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getStringCellValue().trim()
								.equalsIgnoreCase("stt")) {
							read = true;
							culBeginRead = cell.getColumnIndex();
							break readCell;

						}

					}
				}
			} else {
				while (iterator.hasNext()) {
					Cell cell = iterator.next();
					if (culBeginRead == cell.getColumnIndex()) {
						Cell no = iterator.next();
						Cell name = iterator.next();
						iterator.next();
						Cell gender = iterator.next();
						Cell birthd = iterator.next();
						Cell address = iterator.next();
						Cell phone = iterator.next();
						rs.add(new Student(getStringCellValus(no),
								getStringCellValus(name),
								encryptMD5(getStringCellValus(no)),
								getStringCellValus(no) + EMAIL_STUDENT_EX,
								getGenderCallValue(gender),
								getDateCellValus(birthd),
								getStringCellValus(address),
								getStringCellValus(phone)));
					}
				}

			}

		}
 		return rs;

 	}

 	public static String getStringCellValus(Cell cell) {
 		String rs = null;
 		try {
 			rs= cell.getStringCellValue();
 		} catch (Exception e) {
 		}
 		return rs;
 	}

 	@SuppressWarnings("deprecation")
	public static Date getDateCellValus(Cell cell) {
		Date rs = null;
		try {
			switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			if (cell.getStringCellValue() != null)
				rs = new Date(cell.getStringCellValue());
				break;
		case Cell.CELL_TYPE_NUMERIC:
			rs = cell.getDateCellValue();
			break;
		
		default:
			break;
		}
		
		} catch (Exception e) {
			System.out.println(e);
		}	
		return rs;
	}

 	public static int getGenderCallValue(Cell cell) {
 		switch (cell.getCellType()) {
 		case Cell.CELL_TYPE_BLANK:
 			return 0;
 		case Cell.CELL_TYPE_STRING:
 			if (cell.getStringCellValue() != null)
 				return 1;
 		case Cell.CELL_TYPE_NUMERIC:
 			if (cell.getNumericCellValue() != 0)
 				return 1;
 			break;
 		case Cell.CELL_TYPE_BOOLEAN:
 			if (cell.getBooleanCellValue())
 				return 1;
 			break;
 		default:
 			break;
 		}
 		return 0;

 	}
 	
	public static List<Subject> readExcelSubject(XSSFWorkbook workbook) {
		List<Subject> rs = new ArrayList<Subject>();
		 List<XSSFSheet> sheets  = readExcelStudentMutliSheetToList(workbook);
		 for(XSSFSheet s: sheets){
			 rs.addAll(readExcelSubjectOneSheet(s));
		 }
		 Set<Subject> set = new HashSet<Subject>();
		set.addAll(rs);
		rs.clear();
		rs.addAll(set);
		return rs;
	}
	 public static List<Subject> readExcelSubjectOneSheet(XSSFSheet sheet) {
	 		Boolean read = false;

	 		List<Subject> rs = new ArrayList<Subject>();
	 		Iterator<Row> rowI = sheet.iterator();
			int culBeginRead = 0;
			while (rowI.hasNext()) {
				Row row = rowI.next();
				Iterator<Cell> iterator = row.cellIterator();
				if (!read) {
					readCell: while (iterator.hasNext()) {
						Cell cell = iterator.next();
						if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
							if (cell.getStringCellValue().trim()
									.equalsIgnoreCase("stt")) {
								read = true;
								culBeginRead = cell.getColumnIndex();
								break readCell;
							}
						}
					}
				} else {
					while (iterator.hasNext()) {
						Cell cell = iterator.next();
						if (culBeginRead == cell.getColumnIndex()) {
							Cell no = iterator.next();
							Cell name = iterator.next();
							
							rs.add(new Subject(getStringCellValus(no),getStringCellValus(name)));
						}
					}
				}
			}
	 		return rs;

	 	}
}
