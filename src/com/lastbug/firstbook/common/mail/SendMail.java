package com.lastbug.firstbook.common.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	private static String user = "clzlckzkch1509@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
	private static String password = "qowlxj12!";   // 패스워드
	
	public static String gmailSend(String email) {
		
		String emailkey = getRandomStr(6);

        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com"); 
        prop.put("mail.smtp.port", 465); 
        prop.put("mail.smtp.auth", "true"); 
        prop.put("mail.smtp.ssl.enable", "true"); 
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 

            // Subject
            message.setSubject("FirstBook Email 인증키"); //메일 제목을 입력

            // Text
            message.setText("인증키 : " + emailkey);    //메일 내용을 입력

            // send the message
            Transport.send(message); ////전송
            System.out.println("message sent successfully...");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return emailkey;
    }
	
	public static String naverMailSend(String email) { 
		
		String emailkey = getRandomStr(6);
		String host = "smtp.naver.com"; 
		
		// SMTP 서버 정보를 설정한다. 
		Properties props = new Properties(); 
		props.put("mail.smtp.host", host); 
		props.put("mail.smtp.port", 587); 
		props.put("mail.smtp.auth", "true"); 
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
			protected PasswordAuthentication getPasswordAuthentication() { 
				return new PasswordAuthentication(user, password); 
			} 
		}); 
		
		try { 
			MimeMessage message = new MimeMessage(session); 
			message.setFrom(new InternetAddress(user)); 
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 
			
			// 메일 제목 
			message.setSubject("FirstBook Email 인증키"); 
			
			// 메일 내용 
			message.setText("인증키 : " + emailkey); 
			
			// send the message 
			Transport.send(message); 
			
			System.out.println("Success Message Send"); 
		} catch (MessagingException e) {
			e.printStackTrace(); 
		} 
		
		return emailkey;
	}
	
	public static String getRandomStr(int size) {

		char[] tmp = new char[size];
		for(int i=0; i<tmp.length; i++) {
			int div = (int) Math.floor( Math.random() * 2 );
			
			if(div == 0) { // 0이면 숫자로
				tmp[i] = (char) (Math.random() * 10 + '0') ;
			}else { //1이면 알파벳
				tmp[i] = (char) (Math.random() * 26 + 'A') ;
			}
		}
		return new String(tmp);
	}

}
