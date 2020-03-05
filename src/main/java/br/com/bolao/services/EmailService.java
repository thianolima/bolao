package br.com.bolao.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
    @Autowired
    public JavaMailSender emailSender;
    
	public void enviar(String destinario, String assunto, String mensagem) {
		try {
			MimeMessage email = emailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(email, "UTF-8");
			
			helper.setTo(destinario);
			helper.setSubject(assunto); 
			helper.setText(mensagem, true);
			
			emailSender.send(email);
		} catch (MessagingException e) {
			throw new RuntimeException("Erro ao tentar enviar email !", e);
		} 
	}
}
