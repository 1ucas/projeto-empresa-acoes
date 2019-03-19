package self.manobray.rabbitmq.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import self.manobray.rabbitmq.domain.Message;

@Service
public class EmailServiceImpl implements EmailService{
    
	static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
    @Override
    public void sendEmail(Message message) {
    	logger.info("Email Processor Ouviu:");
        logger.info("Subject:" + message.getSubject());
        logger.info("Body:" + message.getBody());
    }
}
