package com.appsfreedom.utils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {
	
	private static final String SMPT_HOST = "smtp.gmail.com";
	private static final String USERNAME = "info.appsfreedom";
	private static final String PASSWORD = "Seal1234";
	private static final String PORT = "587";
	private static final String TO_EMAIL = "kannan.dharani@appsfreedom.com";
	private static final String FROM_EMAIL = "info.appsfreedom@gmail.com";
	private static final String REPORT_SUBJECT = "Automation Report";
	private static final String REPORT_BODY = "Find attached report!!. Please download the attachment and open it";
	private static final String ATTACHMENT_PATH = System.getProperty("user.dir")+"\\log\\report.html";
	private static final String ATTACHMENT_NAME = "report.html";

	public static void execute(String reportFileName) throws Exception {

		final String username = "info.appsfreedom";
		final String password = "Seal1234";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("info.appsfreedom@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("kannan.dharani@appsfreedom.com"));
			message.setSubject("Automation Report!");
			//message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");

			//MimeBodyPart messageBodyPart = new MimeBodyPart();
			//messageBodyPart.setText("Please find attached report!! \\n Please download the attachment and open it");
			Multipart multipart = new MimeMultipart();

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Please find attached report!! \\n Please download the attachment and open it");
			String file = System.getProperty("user.dir") + "\\log\\";
			String fileName = reportFileName;
			DataSource source = new FileDataSource(file + fileName);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
			MyLogger.log.info("Sending email report");

			Transport.send(message);
			MyLogger.log.info("Email report sent");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void sendReport() {
        Properties props = System.getProperties();
        String body = "";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMPT_HOST);
        props.put("mail.smtp.user", FROM_EMAIL);
        props.put("mail.smtp.password", PASSWORD);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
        	//Set from address
            message.setFrom(new InternetAddress(FROM_EMAIL));
             message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO_EMAIL));
           //Set subject
            message.setSubject(REPORT_SUBJECT);
            message.setText(body);
          
            BodyPart objMessageBodyPart = new MimeBodyPart();
            
            objMessageBodyPart.setText(REPORT_BODY);
            
            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(objMessageBodyPart);

            objMessageBodyPart = new MimeBodyPart();

            //Set path to the pdf report file
            //String filename = ATTACHMENT_PATH
            //Create data source to attach the file in mail
            DataSource source = new FileDataSource(ATTACHMENT_PATH);
            
            objMessageBodyPart.setDataHandler(new DataHandler(source));

            objMessageBodyPart.setFileName(ATTACHMENT_NAME);

            multipart.addBodyPart(objMessageBodyPart);

            message.setContent(multipart);
            Transport transport = session.getTransport("smtp");
            transport.connect(SMPT_HOST, FROM_EMAIL, PASSWORD);
            MyLogger.log.info("Sending email report");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            MyLogger.log.info("Email report sent");
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }

	public static void main(String[] args) {

		try {
			//execute("report.html");
			sendReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}