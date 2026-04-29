package com.example.ProTrack.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Send OTP email for login or sensitive actions
     */
    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("ProTrack | One-Time Password (OTP)");

        String body =
                "🔐 ProTrack – Secure Access\n\n" +

                        "🇬🇧 English:\n" +
                        "Your One-Time Password (OTP) is: " + otp + "\n" +
                        "This code is valid for a short time. Do not share it with anyone.\n\n" +

                        "🇫🇷 Français:\n" +
                        "Votre mot de passe à usage unique (OTP) est : " + otp + "\n" +
                        "Ce code est valable pour une courte durée. Ne le partagez avec personne.\n\n" +

                        "🇸🇦 العربية:\n" +
                        "رمز التحقق الخاص بك هو: " + otp + "\n" +
                        "هذا الرمز صالح لفترة قصيرة. لا تشاركه مع أي شخص.\n\n" +

                        "— ProTrack System";

        message.setText(body);
        mailSender.send(message);
    }

    /**
     * Send signup confirmation email
     */
    public void sendSignupConfirmation(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Welcome to ProTrack | Bienvenue sur ProTrack");

        String body =
                "🎉 ProTrack – Account Created Successfully\n\n" +

                        "🇬🇧 English:\n" +
                        "Your ProTrack account has been successfully created.\n" +
                        "You can now log in and start managing production, materials, and quality control.\n\n" +

                        "🇫🇷 Français:\n" +
                        "Votre compte ProTrack a été créé avec succès.\n" +
                        "Vous pouvez maintenant vous connecter et gérer la production, les matières premières et le contrôle qualité.\n\n" +

                        "🇸🇦 العربية:\n" +
                        "تم إنشاء حسابك على ProTrack بنجاح.\n" +
                        "يمكنك الآن تسجيل الدخول وإدارة الإنتاج والمواد ومراقبة الجودة.\n\n" +

                        "— ProTrack Team";

        message.setText(body);
        mailSender.send(message);
    }
}
