package cn.wgt.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class UtilTools {
	//照片尺寸
	private static int[] sizeArr = new int[]{200,400,800};

	public static String generateId(){
		StringBuilder orderNo = new StringBuilder();
		orderNo.append(new SimpleDateFormat("yyMMddHHmm").format(new Date()));
		orderNo.append(String.valueOf(Math.random()).substring(2, 13));
		return orderNo.toString();
	}

	@SuppressWarnings("Duplicates")
	public static String storagePhoto(MultipartFile photo){
		String photoUrl="";
		String fileName=photo.getOriginalFilename();
//            File f=new File("C:\\Program Files\\Tomcat\\webapps\\ROOT\\imgs",fileName);
//                        "C:\\Program Files\\Tomcat\\webapps\\ROOT\\imgs"
		try{
			String suffix=fileName.substring(fileName.lastIndexOf("."));
			String imgName= UUID.randomUUID().toString()+suffix;
			File localFile=new File("C:\\Program Files\\Tomcat\\webapps\\ROOT\\imgs",imgName);
			localFile.createNewFile();
			photoUrl+= Config.SERVER_IP+"imgs/"+imgName+",";
			FileOutputStream outputStream=new FileOutputStream(localFile);
			outputStream.write(photo.getBytes());
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return photoUrl;
	}



	public static void CompressImg(String filePath){
		Image img;
		BufferedImage bi;
		String newFliePath;
		FileOutputStream fos;
		try {
			File file = new File(filePath);
			if(file.exists() == false) {
				return ;
			}
			img = ImageIO.read(file);
			for(int i=0; i < sizeArr.length; ++i) {
				bi= new BufferedImage(sizeArr[i], sizeArr[i], BufferedImage.TYPE_3BYTE_BGR);
				bi.createGraphics().drawImage(img.getScaledInstance(sizeArr[i], sizeArr[i], Image.SCALE_SMOOTH), 0, 0, null);
				newFliePath = filePath.substring(0,filePath.lastIndexOf("\\") + 1) + sizeArr[i] + "x" + sizeArr[i] + "_" + filePath.substring(filePath.lastIndexOf("\\") + 1);
				fos = new FileOutputStream(newFliePath);
				ImageIO.write(bi, "JPG", fos);
				fos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	public static String generateId() {
//		StringBuilder id = new StringBuilder();
//		id.append(new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
//		id.append(String.valueOf(Math.random()).substring(2, 6));
//		return id.toString();
//	}

	/**
	 * @param concerningUser 要删去的子串
	 * @param users			 原有的字符串
	 * @param index			 子串所在的位置
	 * @return
	 */
	public static String getString(String concerningUser, String users, int index) {
		String str;
		if(index+concerningUser.length()==users.length()){
			if(index-1<=0){
				str="";
			}else{
				str=users.substring(0,index-1);
			}
		}else{
			str= users.substring(0,index)+users.substring(index+concerningUser.length()+1,users.length()-1);
		}
		return str;
	}

	/**通过年月日获取今天是今年的第几天
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
    public static int getDayOfYear(int year,int month,int day) {
		int days = 0;
		switch (month) {
			case 12:
				days += 31;
			case 11:
				days += 30;
			case 10:
				days += 31;
			case 9:
				days += 30;
			case 8:
				days += 31;
			case 7:
				days += 31;
			case 6:
				days += 30;
			case 5:
				days += 31;
			case 4:
				days += 30;
			case 3:
				days += 31;
			case 2:
				if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
					days += 29;
				else
					days += 28;
			case 1:
				days += day;
		}
		return days;
	}
	public static List generateRandomList(int size){
    	List<Integer> list=new ArrayList();
		Random random=new Random(System.currentTimeMillis());
    	while(list.size()!=size){
    		int index=random.nextInt(size);
    		if(list.indexOf(index)>-1){
    			continue;
			}else{
    			list.add(index);
			}
		}
		return list;
	}

}