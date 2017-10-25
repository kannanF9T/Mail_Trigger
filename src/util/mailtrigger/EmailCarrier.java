package util.mailtrigger;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*; 
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
  
class EmailCarrier{  
public static String mailProcess(){
	boolean connectionExist = false;
	String createBkup = null;
	final String filename = "F:\\Mysql\\Backup.sql";//change accordingly 
	String result = null;
	try{
	connectionExist = checkConnection();
	if(connectionExist){
		createBkup = createBkup(filename);
		if(createBkup!=null&&createBkup.equals("SUCCESS")){
			result = triggerMail(filename);
		}else{
			result = "BKUP_FAIL";
		}
		}else{
			result = "NO_INT_CONN";
		}
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		System.out.println("Inside mailProcess method::result::"+result);
		return result;
	}
	}
 public static String triggerMail(String filename){  
  String result = null;
  String to="kannaninfo93@gmail.com";//change accordingly  
  final String user="kannaninfo93@gmail.com";//change accordingly  
  final String password="Torres@8421";//change accordingly  
   
  //1) get the session object     
  Properties properties = System.getProperties();  
  properties.setProperty("mail.smtp.host", "smtp.gmail.com");  
  properties.put("mail.smtp.starttls.enable","true"); 
  properties.put("mail.smtp.auth", "true");  
  properties.put("mail.smtp.port", "587");
  Session session = Session.getInstance(properties,  
   new javax.mail.Authenticator() {  
   protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication(user,password);  
   }  
  });  

  try{  
    MimeMessage message = new MimeMessage(session);  
    message.setFrom(new InternetAddress(user));  
    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
    message.setSubject("Backup for the data::"+new java.util.Date());  
    BodyPart messageBodyPart1 = new MimeBodyPart();  
    messageBodyPart1.setText("This is message body");  
      
     MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
    
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName(filename);  
     
    Multipart multipart = new MimeMultipart();  
    multipart.addBodyPart(messageBodyPart1);  
    multipart.addBodyPart(messageBodyPart2);  
 
    message.setContent(multipart );  
   
    Transport.send(message);
    result = "MAIL_TRIGGERED";
    System.out.println("message sent....");  
  
   }catch (MessagingException ex) {
	   ex.printStackTrace();
	   result = "MAIL_TRIGGER_FAIL";
	}
  return result;
 }  
 public static String createBkup(String fileName){
	 Process p = null;
	 String result = null;
     try {
         Runtime runtime = Runtime.getRuntime();
         p = runtime.exec("C:\\Program Files (x86)\\MySQL\\MySQL Server 5.0\\bin\\mysqldump -uroot -p1234 --add-drop-database -B TEST_DE -r " +fileName);
         int processComplete = p.waitFor();

         if (processComplete == 0) {
        	 result = "SUCCESS";
             System.out.println("Backup created successfully!");

         } else {
        	 result = "FAIL";
             System.out.println("Could not create the backup");
         }


     } catch (Exception e) {
    	 result = "FAIL";
         e.printStackTrace();
     }
     return result;
 }
 
 public static boolean checkConnection(){
	 boolean flag = false;
	 try{
		 URL url = new URL("http://www.goal.com");
		 URLConnection connection = url.openConnection();
		 connection.connect();		 
		 flag = true;
		 System.out.println("internet connection exist..!");
	 }catch(MalformedURLException ex){
		 ex.printStackTrace();
		 flag = false;
	 }catch(IOException ex){
		 ex.printStackTrace();
		 flag = false;
	 }catch(Exception ex){
		 ex.printStackTrace();
		 flag = false;
	 }finally{
		 System.out.println("checkConnection::flag::"+flag);
		 return flag;
	 }
 }
}  