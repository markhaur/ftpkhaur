package com.ftp.ftpkhaur.ftp;

import com.ftp.ftpkhaur.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class SFTPScheduler {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private Configuration configuration;
    private SFTPTransmitter sftpTransmitter;

    @Autowired
    public SFTPScheduler(Configuration configuration, SFTPTransmitter sftpTransmitter){
        this.sftpTransmitter = sftpTransmitter;
    }



    @Scheduled(fixedRateString = "${sftp.schedule}", timeUnit = TimeUnit.HOURS)
    public void reportCurrentTime(){
        System.out.println("Starting SFTP Transmitter at " + dateFormat.format(new Date()));
        try {
            sftpTransmitter.process();
        } catch (IOException e) {
            System.out.println("Error while processing files: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
