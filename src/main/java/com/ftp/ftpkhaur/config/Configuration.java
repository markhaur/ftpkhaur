package com.ftp.ftpkhaur.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")
public class Configuration {

    @Value("${sftp.host}")
    private String sftpHost;

    @Value("${sftp.port}")
    private Integer sftpPort;

    @Value("${sftp.username}")
    private String sftpUsername;

    @Value("${sftp.password}")
    private String sftpPassword;

    @Value("${sftp.local.source}")
    private String sftpLocalSource;

    @Value("${sftp.remote.source}")
    private String sftpRemoteSource;

    @Value("${sftp.local.destination}")
    private String sftpLocalDestination;

    @Value("${sftp.remote.destination}")
    private String sftpRemoteDestination;

    @Value("${sftp.schedule}")
    private Integer sftpSchedule;

    public Integer getSchedule(){
        return sftpSchedule;
    }

    public String getSftpHost() {
        return sftpHost;
    }


    public Integer getSftpPort() {
        return sftpPort;
    }

    public String getSftpUsername() {
        return sftpUsername;
    }

    public String getSftpPassword() {
        return sftpPassword;
    }

    public String getSftpLocalSource() {
        return sftpLocalSource;
    }

    public String getSftpRemoteSource() {
        return sftpRemoteSource;
    }

    public String getSftpLocalDestination() {
        return sftpLocalDestination;
    }

    public String getSftpRemoteDestination() {
        return sftpRemoteDestination;
    }
}
