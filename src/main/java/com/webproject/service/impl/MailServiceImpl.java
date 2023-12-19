package com.webproject.service.impl;

import com.webproject.entity.Code;
import com.webproject.entity.User;
import com.webproject.mapper.CodeMapper;
import com.webproject.mapper.UserMapper;
import com.webproject.service.MailService;
import com.webproject.utils.Result;
import com.webproject.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CodeMapper codeMapper;
    @Override
    public Result sendMimeMail(UserVo userVo) {
        User user = userMapper.selectByUsername(userVo.getUsername());
        if (user == null){
            return Result.error("请确认用户名是否正确");
        }
        if (!user.getEmail().equals(userVo.getEmail())){
            return Result.error("邮箱不匹配");
        }

        try {
            // 生成随机数
            String code = randomCode();
            //邮箱设置
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("验证码邮件");//主题
            mailMessage.setText("您的验证码是："+code);//内容
            System.out.println("email="+userVo.getEmail());
            mailMessage.setTo(userVo.getEmail());//发给谁
            mailMessage.setFrom("dmy1024@qq.com");//你自己的邮箱
            mailSender.send(mailMessage);//发送

            // 将随机数放置到sql中
            if (codeMapper.selectCode(userVo.getUsername()) == null){
                codeMapper.insertCode(user.getUsername(), user.getEmail(), code);
            }else {
                codeMapper.updateCode(user.getUsername(), user.getEmail(), code);
            }

            return Result.successMsg("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("发送失败");
        }
    }

    @Override
    public String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for(int i = 0;i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    @Override
    public boolean registered(UserVo userVo) {
        //获取session中的验证信息
        Code dbCode = codeMapper.selectCode(userVo.getUsername());
        String email = dbCode.getEmail();
        String code = dbCode.getCode();
        LocalDateTime oldTime = dbCode.getTime();
        //获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();

        //计算时间差，单位为分钟
        long minutesDiff = ChronoUnit.MINUTES.between(oldTime, currentTime);

        System.out.println("email="+email+"\ncode="+code);
        //获取表单中的提交的验证信息
        String voCode = userVo.getCode();

        //如果email数据为空，或者不一致，失败
        if (email == null || email.isEmpty()){
            return false;
        }else if (!code.equals(voCode)){
            return false;
        }else if (minutesDiff > 10) {
            //如果时间差大于10分钟，返回false
            return false;
        }

        return true;
    }

}
