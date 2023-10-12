package com.metalsa.utils;

import com.metalsa.aprobacion.service.MailNotificationService;
import com.metalsa.core.model.NvcTblEmailToSend;
import com.metalsa.core.repository.EmailToSendRepository;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author APOMR
 */
@RestController
public class ScheduledProcess {

    private static final Logger LOG = Logger.getLogger(ScheduledProcess.class);

    @Autowired
    private EmailToSendRepository mailToSendService;

    @Autowired
    private MailNotificationService mailService;

    @Getter
    @Setter
    @Value("${wsEndPoint}")
    private String wsEndPoint;
    @Getter
    @Setter
    @Value("${sch.sendMails}")
    private Boolean sendMails;
//    @Getter
//    @Setter
//    @Value("${sch.sendMailDelay}")
//    private Integer sendMailDelay;
//    @Scheduled(fixedDelayString = "${sch.sendMailDelay}", initialDelayString = "${sch.sendMailDelay}")

    //@Scheduled(fixedDelayString = "90000", initialDelayString = "90000")
    @RequestMapping("/sendEmails/")
    public String sendEmails() {
        if (sendMails!=null && wsEndPoint!=null && !wsEndPoint.contains("localhost")) {
            List<NvcTblEmailToSend> emailsToSend = mailToSendService.findByStatus("SPX_PENDIENTE");
            if (emailsToSend.size() > 0) {
                emailsToSend.forEach((e) -> {
                    e.setStatus("SPX_ENVIANDO");
                    mailToSendService.save(e);
                    if (mailService.sendSpxMail(e)) {
                        e.setStatus("SPX_OK");
                        mailToSendService.save(e);
                    } else {
                        e.setStatus("SPX_ERROR");
                        mailToSendService.save(e);
                    }
                    mailToSendService.flush();
                });
            }
        }
        return "DONE!";
    }

    @RequestMapping("/resendEmails/")
    public String resendEmails() {
        List<NvcTblEmailToSend> emailsToSend = mailToSendService.findByStatus("SPX_ERROR");
        if (emailsToSend.size() > 0) {
            emailsToSend.forEach((e) -> {
                if (mailService.sendSpxMail(e)) {
                    e.setStatus("SPX_OK");
                    mailToSendService.save(e);
                } else {
                    e.setStatus("SPX_ERROR");
                    mailToSendService.save(e);
                }
            });
        }
        return "DONE!";
    }
}
