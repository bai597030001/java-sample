package com.example.shiroexample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.example.shiroexample.model.MailFileVo;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String fromMail;

    /**
     * 发送普通的邮件
     * 通过JavaMailSender send(SimpleMailMessage simpleMailMessage) 方法发送邮件。
     *
     * @param toMail  被发送人 fromMail 发送人
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleMail(String toMail, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setTo(toMail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            sender.send(simpleMailMessage);
            logger.info("发送给" + toMail + "简单邮件已经发送。 subject：" + subject);
        } catch (Exception e) {
            logger.info("发送给" + toMail + "send mail error subject：" + subject);
            e.printStackTrace();
        }
    }

    /**
     * 发送 html 格式邮件
     * (1)：通过JavaMailSender 的 createMimeMessage() 创建 MimeMessage  对象实例
     * (2)：将 MimeMessage 放入到MimeMessageHelper  构造函数中，并通过MimeMessageHelper 设置发送邮件信息。（发送人， 被发送人，主题，内容）
     * (3)：通过JavaMailSender send(MimeMessage  mimeMessage)发送邮件。
     *
     * @param toMail  被发送人 fromMail 发送人
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlMail(String toMail, String subject, String content) {
        MimeMessage mimeMessage = sender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(toMail);
            mimeMessageHelper.setFrom(fromMail);
            mimeMessageHelper.setText(content, true);
            mimeMessageHelper.setSubject(subject);
            sender.send(mimeMessage);
            logger.info("发送给" + toMail + "html邮件已经发送。 subject：" + subject);
        } catch (MessagingException e) {
            logger.info("发送给" + toMail + "html send mail error subject：" + subject);
            e.printStackTrace();
        }
    }

    static class InlineResource {
        private String cid;
        private String path;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public InlineResource(String cid, String path) {
            super();
            this.cid = cid;
            this.path = path;
        }
    }

    /**
     * 发送静态资源（一般是图片）的邮件
     * (发送 html 中带图片的邮件和发送 html邮件操作基本一致，不同的是需要额外在通过MimeMessageHelper addInline 的方法去设置图片信息。)
     * (1)定义html 嵌入的 image标签中 src 属性 id 例如 <img src=“cid:image1”/>
     * (2)设置MimeMessageHelper通过addInline 将cid 和文件资源进行指定即可
     *
     * @param to           被发送人
     * @param subject      主题
     * @param content      邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:image\" >
     * @param resourceList 静态资源list
     */
    public void sendInlineResourceMail(String to, String subject, String content, List<InlineResource> resourceList) {
        MimeMessage message = sender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromMail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            for (InlineResource inlineResource : resourceList) {
                FileSystemResource res = new FileSystemResource(new File(inlineResource.getPath()));
                helper.addInline(inlineResource.getCid(), res);
            }
            sender.send(message);
            logger.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }

    /**
     * 发送带附件的邮件
     * 发送带附件的邮件和发送html 操作基本一致，通过MimeMessageHelper设置邮件信息的时候，
     * 将附件通过FileSystemResource 进行包装，然后再通过 MimeMessageHelper addAttachment 设置到发送邮件信息中即可。
     *
     * @param toMail   被发送人 fromMail 发送人
     * @param subject  主题
     * @param content  内容
     * @param filePath 文件路径
     */
    public void sendAttachmentsMail(String toMail, String subject, String content, String filePath) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromMail);
            helper.setTo(toMail);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf("/"));
            helper.addAttachment(fileName, file);
            sender.send(message);
            logger.info("发送给" + toMail + "带附件的邮件已经发送。");
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("发送给" + toMail + "带附件的邮件时发生异常！", e);
        }
    }

    /****************************************************************************/

    /**
     * 发送邮件的方法
     *
     * 三种收件人模式的解释
     * 1，Message.RecipientType.TO  直接接收人
     * 2，Message.RecipientType.CC  明抄送收件人
     * 3，Message.RecipientType.BCc 暗抄送收件人（不会 被直接收件人 和 明抄送收件人 看见的收件人）
     *
     * @param toAddr  收件人的 邮件地址
     * @param ccAddr  抄送人的 邮件地址
     * @param subject 邮件标题
     * @param html    邮件内容
     * @param srcVos  静态资源类
     * @param athVos  附件资源类
     */
    public void sendMail(String[] toAddr, String[] ccAddr, String subject, String html, List<MailFileVo> srcVos, List<MailFileVo> athVos) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromMail);
            helper.setSubject(subject);
            helper.setText(html, true);
            if (toAddr != null && toAddr.length > 0) {
                Address[] addresses = new Address[toAddr.length];
                for (int i = 0; i < toAddr.length; i ++) {
                    addresses[i] = new InternetAddress(toAddr[i]);
                }
                message.setRecipients(Message.RecipientType.TO, addresses);
            }
            if (ccAddr != null && ccAddr.length > 0) {
                Address[] addresses = new Address[ccAddr.length];
                for (int i = 0; i < ccAddr.length; i ++) {
                    addresses[i] = new InternetAddress(ccAddr[i]);
                }
                message.setRecipients(Message.RecipientType.CC, addresses);
            }
            if (srcVos != null && !srcVos.isEmpty()) {
                for (MailFileVo srcVo : srcVos) {
                    helper.addInline(srcVo.getName(), srcVo.getFile());
                }
            }
            if (athVos != null && !athVos.isEmpty()) {
                for (MailFileVo athVo : athVos) {
                    helper.addAttachment(athVo.getName(), athVo.getFile());
                }
            }
            sender.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

}
