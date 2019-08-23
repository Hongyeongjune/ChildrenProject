package org.zerock.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.zerock.model.dto.CIRequest;
import org.zerock.model.dto.CIResponse;
import org.zerock.model.dto.NHRequest;
import org.zerock.model.dto.NHResponse;
import org.zerock.model.entity.UserCI;
import org.zerock.model.entity.UserNH;
import org.zerock.repository.UserCIRepository;
import org.zerock.repository.UserNHRepository;

@Service
public class UserService {

	public final UserCIRepository userCIRepository;
	public final UserNHRepository userNHRepository;
	
	public UserService(UserCIRepository userCIRepository, UserNHRepository userNHRepository) {
		this.userCIRepository = userCIRepository;
		this.userNHRepository = userNHRepository;
		
	}
	
	public void createUserCI(CIRequest request) {
		
		UserCI user = request.ToEntity();
		
		List<UserCI> userCI = userCIRepository.findByNumber(request.getNumber());
		
		for(int i=0; i<userCI.size(); i++) {
			if(user.getNumber() == userCI.get(i).getNumber() && user.getType().equals(userCI.get(i).getType()) &&
					user.getName().equals(userCI.get(i).getName()))
				userCIRepository.delete(userCI.get(i));
		}
		
		userCIRepository.save(user);
		 
	}
	
	public void createUserNH(NHRequest request) {
		
		UserNH user = request.ToEntity();
		
		List<UserNH> userNH = userNHRepository.findByNumber(request.getNumber());
		
		for(int i=0; i<userNH.size(); i++) {
			if(user.getNumber() == userNH.get(i).getNumber() && user.getType().equals(userNH.get(i).getType()) &&
					user.getName().equals(userNH.get(i).getName()))
				userNHRepository.delete(userNH.get(i));
		}
		
		userNHRepository.save(user);
	}
	
	public void createCIFile(String name) {
		
		List<UserCI> userCI = userCIRepository.findByName(name);
		
		String[] columns1 = {"문항번호", "정답", "기쁨","슬픔","점수","오답분석","반응시간"};
		String[] columns2 = {"문항번호", "정답", "기쁨","슬픔","무서움","화남","점수","오답분석","반응시간"};
		
		List<CIResponse> response = new ArrayList<CIResponse>();
		List<CIResponse> musicResponse = new ArrayList<CIResponse>();
		List<CIResponse> voiceResponse = new ArrayList<CIResponse>();
		
		int musicIndex = 0;
		int voiceIndex = 0;
		
		for(int i=0; i<userCI.size(); i++) {
			
			UserCI user = userCI.get(i);
			CIResponse find = new CIResponse();
			find.setName(user.getName());
			find.setAge(user.getAge());
			find.setSex(user.getSex());
			find.setLeftDate(user.getLeftDate());
			find.setLeftDevice(user.getLeftDevice());
			find.setLeftStart(user.getLeftStart());
			find.setRightDate(user.getRightDate());
			find.setRightDevice(user.getRightDevice());
			find.setRightStart(user.getRightStart());
			find.setType(user.getType());
			find.setChoice(user.getChoice());
			find.setIncorrect(user.getIncorrect());
			find.setNumber(user.getNumber());
			find.setScore(user.getScore());
			find.setTimer(user.getTimer());
			
			response.add(i, find);
		}
		
		for(int i=0; i<userCI.size(); i++) {
			
			if(response.get(i).getType().equals("음악")) {
				CIResponse musicFind = new CIResponse();
				musicFind.setName(response.get(i).getName());
				musicFind.setAge(response.get(i).getAge());
				musicFind.setSex(response.get(i).getSex());
				musicFind.setLeftDate(response.get(i).getLeftDate());
				musicFind.setLeftDevice(response.get(i).getLeftDevice());
				musicFind.setLeftStart(response.get(i).getLeftStart());
				musicFind.setRightDate(response.get(i).getRightDate());
				musicFind.setRightDevice(response.get(i).getRightDevice());
				musicFind.setRightStart(response.get(i).getRightStart());
				musicFind.setType(response.get(i).getType());
				musicFind.setChoice(response.get(i).getChoice());
				musicFind.setIncorrect(response.get(i).getIncorrect());
				musicFind.setNumber(response.get(i).getNumber());
				musicFind.setScore(response.get(i).getScore());
				musicFind.setTimer(response.get(i).getTimer());
				
				musicResponse.add(musicIndex, musicFind);
				
				musicIndex++;
				
			}
			else if(response.get(i).getType().equals("음성")) {
				CIResponse voiceFind = new CIResponse();
				voiceFind.setName(response.get(i).getName());
				voiceFind.setAge(response.get(i).getAge());
				voiceFind.setSex(response.get(i).getSex());
				voiceFind.setLeftDate(response.get(i).getLeftDate());
				voiceFind.setLeftDevice(response.get(i).getLeftDevice());
				voiceFind.setLeftStart(response.get(i).getLeftStart());
				voiceFind.setRightDate(response.get(i).getRightDate());
				voiceFind.setRightDevice(response.get(i).getRightDevice());
				voiceFind.setRightStart(response.get(i).getRightStart());
				voiceFind.setType(response.get(i).getType());
				voiceFind.setChoice(response.get(i).getChoice());
				voiceFind.setIncorrect(response.get(i).getIncorrect());
				voiceFind.setNumber(response.get(i).getNumber());
				voiceFind.setScore(response.get(i).getScore());
				voiceFind.setTimer(response.get(i).getTimer());
				
				voiceResponse.add(voiceIndex, voiceFind);
				
				voiceIndex++;
			}
		}
		
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */

        // Create a Sheet
        Sheet sheet1 = workbook.createSheet(musicResponse.get(0).getType() + " 개인 결과지 ");
        Sheet sheet2 = workbook.createSheet(voiceResponse.get(0).getType() + " 개인 결과지 ");
        
        Row infoRow1 = sheet1.createRow(0);
        Row infoRow2 = sheet2.createRow(0);
        
        Cell cell1 = infoRow1.createCell(0);
        
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        cell1.setCellStyle(style);
        
        cell1.setCellValue("이름 : " + musicResponse.get(0).getName() + "\n" + "나이 : " 
        + musicResponse.get(0).getAge() + "\n" + "성별 : " + musicResponse.get(0).getSex() + "\n" +
        		"인공와우 이식 시기 : 좌측 " + musicResponse.get(0).getLeftDate() + " 우측 " + musicResponse.get(0).getRightDate());
        
        Cell cell2 = infoRow2.createCell(0);
        
        cell2.setCellStyle(style);
        
        cell2.setCellValue("이름 : " + voiceResponse.get(0).getName() + "\n" + "나이 : " 
        + voiceResponse.get(0).getAge() + "\n" + "성별 : " + voiceResponse.get(0).getSex() + "\n" +
        		"인공와우 이식 시기 : 좌측 " + voiceResponse.get(0).getLeftDate() + " 우측 " + voiceResponse.get(0).getRightDate());
        
        // Create a Row
        Row headerRow1 = sheet1.createRow(1);
        Row headerRow2 = sheet2.createRow(1);

        // Create cells
        for(int i = 0; i < columns1.length; i++) {
            cell1 = headerRow1.createCell(i);
            cell1.setCellValue(columns1[i]);
        }
        
        for(int i = 0; i < columns2.length; i++) {
            cell2 = headerRow2.createCell(i);
            cell2.setCellValue(columns2[i]);
        }

        int rowNum1 = 2;
        for(int i=0; i<musicResponse.size(); i++) {
        	
            Row row = sheet1.createRow(rowNum1++);

            row.createCell(0).setCellValue(musicResponse.get(i).getNumber());

            row.createCell(1).setCellValue(musicResponse.get(i).getAnswer());

           
            if(musicResponse.get(i).getChoice().equals("기쁨"))
            	row.createCell(2).setCellValue("O");
            else if(musicResponse.get(i).getChoice().equals("슬픔"))
            	row.createCell(3).setCellValue("O");
            
            row.createCell(4).setCellValue(musicResponse.get(i).getScore());
            
            row.createCell(5).setCellValue(musicResponse.get(i).getIncorrect());
            
            row.createCell(6).setCellValue(musicResponse.get(i).getTimer());
            
        }
        
        int rowNum2 = 2;
        for(int i=0; i<voiceResponse.size(); i++) {
        	
            Row row = sheet2.createRow(rowNum2++);

            row.createCell(0).setCellValue(voiceResponse.get(i).getNumber());

            row.createCell(1).setCellValue(voiceResponse.get(i).getAnswer());

           
            if(voiceResponse.get(i).getChoice().equals("기쁨"))
            	row.createCell(2).setCellValue("O");
            else if(voiceResponse.get(i).getChoice().equals("슬픔"))
            	row.createCell(3).setCellValue("O");
            else if(voiceResponse.get(i).getChoice().equals("무서움"))
            	row.createCell(4).setCellValue("O");
            else if(voiceResponse.get(i).getChoice().equals("화남"))
            	row.createCell(5).setCellValue("O");
            
            row.createCell(6).setCellValue(voiceResponse.get(i).getScore());
            
            row.createCell(7).setCellValue(voiceResponse.get(i).getIncorrect());
            
            row.createCell(8).setCellValue(voiceResponse.get(i).getTimer());
            
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns1.length; i++) {
            sheet1.autoSizeColumn(i);
            sheet1.setColumnWidth(i, 4096);
        }
        for(int i = 0; i < columns2.length; i++) {
            sheet2.autoSizeColumn(i);
            sheet2.setColumnWidth(i, 4096);
        }

        // Write the output to a file
        FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(musicResponse.get(0).getName() + musicResponse.get(0).getAge() + "정서검사결과지.xlsx");
			try {
				workbook.write(fileOut);
				fileOut.close();
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void createNHFile(String name) {
		
		List<UserNH> userNH = userNHRepository.findByName(name);
		
		String[] columns1 = {"문항번호", "정답", "기쁨","슬픔","점수","오답분석","반응시간"};
		String[] columns2 = {"문항번호", "정답", "기쁨","슬픔","무서움","화남","점수","오답분석","반응시간"};
		
		List<NHResponse> response = new ArrayList<NHResponse>();
		List<NHResponse> musicResponse = new ArrayList<NHResponse>();
		List<NHResponse> voiceResponse = new ArrayList<NHResponse>();
		
		int musicIndex = 0;
		int voiceIndex = 0;
		
		for(int i=0; i<userNH.size(); i++) {
			UserNH user = userNH.get(i);
			NHResponse find = new NHResponse();
			find.setName(user.getName());
			find.setAge(user.getAge());
			find.setSex(user.getSex());
			find.setType(user.getType());
			find.setChoice(user.getChoice());
			find.setIncorrect(user.getIncorrect());
			find.setNumber(user.getNumber());
			find.setScore(user.getScore());
			find.setTimer(user.getTimer());
			
			response.add(i, find);
		}
		
		for(int i=0; i<userNH.size(); i++) {
			
			if(response.get(i).getType().equals("음악")) {
				NHResponse musicFind = new NHResponse();
				musicFind.setName(response.get(i).getName());
				musicFind.setAge(response.get(i).getAge());
				musicFind.setSex(response.get(i).getSex());
				musicFind.setType(response.get(i).getType());
				musicFind.setChoice(response.get(i).getChoice());
				musicFind.setIncorrect(response.get(i).getIncorrect());
				musicFind.setNumber(response.get(i).getNumber());
				musicFind.setScore(response.get(i).getScore());
				musicFind.setTimer(response.get(i).getTimer());
				
				musicResponse.add(musicIndex, musicFind);
				
				musicIndex++;
				
			}
			else if(response.get(i).getType().equals("음성")) {
				NHResponse voiceFind = new NHResponse();
				voiceFind.setName(response.get(i).getName());
				voiceFind.setAge(response.get(i).getAge());
				voiceFind.setSex(response.get(i).getSex());
				voiceFind.setType(response.get(i).getType());
				voiceFind.setChoice(response.get(i).getChoice());
				voiceFind.setIncorrect(response.get(i).getIncorrect());
				voiceFind.setNumber(response.get(i).getNumber());
				voiceFind.setScore(response.get(i).getScore());
				voiceFind.setTimer(response.get(i).getTimer());
				
				voiceResponse.add(voiceIndex, voiceFind);
				
				voiceIndex++;
			}
		}
		
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */

        // Create a Sheet
        Sheet sheet1 = workbook.createSheet(musicResponse.get(0).getType() + " 개인 결과지 ");
        Sheet sheet2 = workbook.createSheet(voiceResponse.get(0).getType() + " 개인 결과지 ");
        
        Row infoRow1 = sheet1.createRow(0);
        Row infoRow2 = sheet2.createRow(0);
        
        Cell cell1 = infoRow1.createCell(0);
        
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        cell1.setCellStyle(style);
        
        cell1.setCellValue("이름 : " + musicResponse.get(0).getName() + "\n" + "나이 : " 
        + musicResponse.get(0).getAge() + "\n" + "성별 : " + musicResponse.get(0).getSex());
        
        
        Cell cell2 = infoRow2.createCell(0);
        
        cell2.setCellStyle(style);
        
        cell2.setCellValue("이름 : " + voiceResponse.get(0).getName() + "\n" + "나이 : " 
        + voiceResponse.get(0).getAge() + "\n" + "성별 : " + voiceResponse.get(0).getSex());
        
        // Create a Row
        Row headerRow1 = sheet1.createRow(1);
        Row headerRow2 = sheet2.createRow(1);

        // Create cells
        for(int i = 0; i < columns1.length; i++) {
            cell1 = headerRow1.createCell(i);
            cell1.setCellValue(columns1[i]);
        }
        
        for(int i = 0; i < columns2.length; i++) {
            cell2 = headerRow2.createCell(i);
            cell2.setCellValue(columns2[i]);
        }

        int rowNum1 = 2;
        for(int i=0; i<musicResponse.size(); i++) {
        	
            Row row = sheet1.createRow(rowNum1++);

            row.createCell(0).setCellValue(musicResponse.get(i).getNumber());

            row.createCell(1).setCellValue(musicResponse.get(i).getAnswer());

           
            if(musicResponse.get(i).getChoice().equals("기쁨"))
            	row.createCell(2).setCellValue("O");
            else if(musicResponse.get(i).getChoice().equals("슬픔"))
            	row.createCell(3).setCellValue("O");
            
            row.createCell(4).setCellValue(musicResponse.get(i).getScore());
            
            row.createCell(5).setCellValue(musicResponse.get(i).getIncorrect());
            
            row.createCell(6).setCellValue(musicResponse.get(i).getTimer());
            
        }
        
        int rowNum2 = 2;
        for(int i=0; i<voiceResponse.size(); i++) {
        	
            Row row = sheet2.createRow(rowNum2++);

            row.createCell(0).setCellValue(voiceResponse.get(i).getNumber());

            row.createCell(1).setCellValue(voiceResponse.get(i).getAnswer());

           
            if(voiceResponse.get(i).getChoice().equals("기쁨"))
            	row.createCell(2).setCellValue("O");
            else if(voiceResponse.get(i).getChoice().equals("슬픔"))
            	row.createCell(3).setCellValue("O");
            else if(voiceResponse.get(i).getChoice().equals("무서움"))
            	row.createCell(4).setCellValue("O");
            else if(voiceResponse.get(i).getChoice().equals("화남"))
            	row.createCell(5).setCellValue("O");
            
            row.createCell(6).setCellValue(voiceResponse.get(i).getScore());
            
            row.createCell(7).setCellValue(voiceResponse.get(i).getIncorrect());
            
            row.createCell(8).setCellValue(voiceResponse.get(i).getTimer());
            
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns1.length; i++) {
            sheet1.autoSizeColumn(i);
            sheet1.setColumnWidth(i, 4096);
        }
        for(int i = 0; i < columns2.length; i++) {
            sheet2.autoSizeColumn(i);
            sheet2.setColumnWidth(i, 4096);
        }

        // Write the output to a file
        FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(musicResponse.get(0).getName() + musicResponse.get(0).getAge() + "정서검사결과지.xlsx");
			try {
				workbook.write(fileOut);
				fileOut.close();
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
