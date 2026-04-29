package com.example.ProTrack.Service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private final Map<String, OtpEntry> otpStorage = new ConcurrentHashMap<>();
    private final long EXPIRATION_TIME_MS = 5 * 60 * 1000; // 5 minutes

    public String generateOtp(String email) {
        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
        otpStorage.put(email, new OtpEntry(otp, Instant.now()));
        return otp;
    }

    public boolean validateOtp(String email, String otp) {
        if (!otpStorage.containsKey(email)) return false;

        OtpEntry entry = otpStorage.get(email);
        if (Instant.now().isAfter(entry.timestamp.plusMillis(EXPIRATION_TIME_MS))) {
            otpStorage.remove(email);
            return false;
        }

        return entry.otp.equals(otp);
    }

    public void clearOtp(String email) {
        otpStorage.remove(email);
    }

    private static class OtpEntry {
        String otp;
        Instant timestamp;

        OtpEntry(String otp, Instant timestamp) {
            this.otp = otp;
            this.timestamp = timestamp;
        }
    }
}