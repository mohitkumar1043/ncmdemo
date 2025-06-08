package ncmControllers;

import java.util.Properties;
import java.util.Random;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.ShopsDao;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import model.Admin;

@Controller
public class OtpController {
	private static final String SENDER_EMAIL = "mohitsaini12943@gmail.com";
    private static final String SENDER_PASSWORD = "";

    @GetMapping("/sendOtp")
    @ResponseBody
    public String sendOtp(@RequestParam("email") String email, HttpSession session) {
        String otp = generateOtp();
        ShopsDao shopdao=new ShopsDao();
        Admin admin=shopdao.getAdmin(email);
        if(admin.getEmail()!=null) {
        	return "admin already present try with new email";
        }
        boolean sent = sendOtpToEmail(email, otp);

        if (sent) {
            session.setAttribute("otp", otp);
            session.setAttribute("otpEmail", email);
            return "OTP sent to " + email;
        } else {
            return "Failed to send OTP";
        }
    }
    private String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }
    private boolean sendOtpToEmail(String recipient, String otp) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session mailSession = Session.getInstance(props,
            new jakarta.mail.Authenticator() {
        	 
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
                }
            });

        try {
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP is: " + otp);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return false;
    }
}
