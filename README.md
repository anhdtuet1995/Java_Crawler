# Hệ thống lấy dữ liệu trang quancafe.vn

### Clone project

### Copy thư mục chromedriver vào ổ C, rồi thay đổi dòng sau trong hàm setUp()
```
System.setProperty("webdriver.chrome.driver", "C:\\Users\\Anh\\Desktop\\chromedriver_win32\\chromedriver.exe"); 
```
Thành
```
System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe"); 
```

### Add các thư viện trong mục library vào project

### Chạy chương trình, chờ cho đến khi hoàn thành tiến trình. Trên trình duyệt Chrome, ấn F12 chọn Console, chuột phải vào nội dung console, chọn Save As rồi lưu lại vào project với tên là logcf2.log.

### Mở file ScrollDownUp.java
Comment lại đoạn sau :
```
HandleLogFile h = new HandleLogFile();
h.setUp();
h.setForDriver(web);
h.infiniteScrollDown(350);
```

Và Uncomment đoạn sau: 
```
//String a = new Scanner(new File("logcf2.log")).useDelimiter("\\Z").next();
```
và 
```
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
```

### Ổ D xuất hiện 1 file output.xls. Đây là dữ liệu mà ta cần get
