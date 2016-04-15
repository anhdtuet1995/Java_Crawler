/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrolldownup;

/**
 *
 * @author Anh
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
  
public class ScrollDownUp {  
    public static String web = "http://www.quancafe.vn/tim-quan-cafe?k=&pro=01&dist=&street=&a=&ser=&cate=&pur=&rad=&type=1";
    
    public ArrayList<String> getOneData(String a){
        ArrayList<String> data = new ArrayList<>();
        int indexName = 0;
        int indexAddress = 0;
        int indexMap = 0;
        
        for(int i = a.indexOf("\"Name\""); i < a.length(); i++){
            if(a.charAt(i) == ','){
                indexName = i;
                break;
            }
        }
        
        data.add(nameCafe(a.substring(a.indexOf("\"Name\"") + 9, indexName)));
        
        for(int i = a.indexOf("\"Name\""); i < a.length(); i++){
            if(a.charAt(i) == '}'){
                indexAddress = i;
                break;
            }
        }
        
        data.add(addressCafe(a.substring(a.indexOf("\"Address\"") + 14, indexAddress)));
        
        for(int i = a.indexOf("\"Map\""); i < a.length(); i++){
            if(a.charAt(i) == '}'){
                indexMap = i;
                break;
            }
        }
        String latlong = a.substring(a.indexOf("\"Map\"") + 10,indexMap - 1);
        data.add(longCafe(latlong) + "");
        data.add(latCafe(latlong) + "");
        
        return data;
    }
    
    public String splitString(String a){
        int index = 0;
        for(int i = a.indexOf("\"SlideEffect\""); i < a.length(); i++){
            if(a.charAt(i) == '}'){
                index = i;
                break;
            }
        }
        return a.substring(index);
    }
    
    public String nameCafe(String name){
        name = name.replace("+", " ");
        name = name.replace("\"", "");
        return name.trim();
    }
    
    public String addressCafe(String ad){
        String s = "";
        int index = 0;
        ad = ad.replace("+", " ");
        if(ad.indexOf("\"HouseNo\"") > 0){
            for(int i = ad.indexOf("\"HouseNo\""); i < ad.length(); i++){
                if(ad.charAt(i) == ','){
                    index = i;
                    break;
                }
            }
            s = s + ad.substring(ad.indexOf("\"HouseNo\"")+13, index) + " ";
            s = s.replace("\"", "");
        }
        
        
        for(int i = ad.indexOf("\"Street\""); i < ad.length(); i++){
            if(ad.charAt(i) == ','){
                index = i;
                break;
            }
        }
        s = s + ad.substring(ad.indexOf("\"Street\"")+12, index - 1) + ", ";
        
        for(int i = ad.indexOf("\"District\""); i < ad.length(); i++){
            if(ad.charAt(i) == ','){
                index = i;
                break;
            }
        }
        s = s + ad.substring(ad.indexOf("\"District\"")+14, index - 1) + ", ";
        
        for(int i = ad.indexOf("\"Province\""); i < ad.length(); i++){
            if(ad.charAt(i) == ','){
                index = i;
                break;
            }
        }
        s = s + ad.substring(ad.indexOf("\"Province\"")+14, index - 1) + ", ";
        
        for(int i = ad.indexOf("\"National\""); i < ad.length(); i++){
            if(ad.charAt(i) == ','){
                index = i;
                break;
            }
        }
        s = s + ad.substring(ad.indexOf("\"National\"")+14, index - 1);
        return s.trim();
    }
    
    public float latCafe(String latlong){
        int index = 0;
        float s = Float.parseFloat(latlong.substring(latlong.indexOf("\"Latitude\"")+12));
        return s;
    }
    
    public float longCafe(String latlong){
        int index = 0;
        for(int i = latlong.indexOf("\"Longitude\""); i < latlong.length(); i++){
            if(latlong.charAt(i) == ','){
                index = i;
                break;
            }
        }
        float s = Float.parseFloat(latlong.substring(latlong.indexOf("\"Longitude\"")+13, index - 1));
        return s;
    }
    
    public static void main(String[] args) throws Exception{  
        ScrollDownUp s = new ScrollDownUp();
        HandleLogFile h = new HandleLogFile();
        h.setUp();
        h.setForDriver(web);
        h.infiniteScrollDown(350);
        //String a = new Scanner(new File("logcf2.log")).useDelimiter("\\Z").next();
        
        WritableWorkbook workbook = Workbook.createWorkbook(new File("D:\\output.xls"));
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        Label label = new Label(0, 0, "STT"); 
        sheet.addCell(label);
        Label label2 = new Label(1, 0, "Tên quán"); 
        sheet.addCell(label2);
        Label label3 = new Label(2, 0, "Địa chỉ"); 
        sheet.addCell(label3);
        Label label4 = new Label(3, 0, "Kinh độ"); 
        sheet.addCell(label4);
        Label label5 = new Label(4, 0, "Vĩ độ"); 
        sheet.addCell(label5);
        Integer i = 1;
        // do{
        //     sheet.addCell(new Number(0, i, i));
        //     for(int j = 0; j< s.getOneData(a).size(); j++){
        //         sheet.addCell(new Label(j+1, i, s.getOneData(a).get(j) + ""));
        //     }
        //     a = s.splitString(a);
        //     i++;
        //     System.out.println(i);
        // }
        // while(a.indexOf("\"Name\"") > 0);
        workbook.write(); 
        workbook.close();
        
    }
}  
