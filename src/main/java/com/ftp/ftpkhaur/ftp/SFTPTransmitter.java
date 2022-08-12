package com.ftp.ftpkhaur.ftp;

import com.ftp.ftpkhaur.config.Configuration;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.RemoteResourceInfo;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class SFTPTransmitter {
;
    Configuration configuration;

    @Autowired
    public SFTPTransmitter(Configuration configuration) throws IOException {
        this.configuration = configuration;
    }

    private SSHClient setupSSHClient() throws IOException {
        SSHClient sshClient = new SSHClient();
        sshClient.addHostKeyVerifier(new PromiscuousVerifier());
        sshClient.connect(configuration.getSftpHost());
        sshClient.authPassword(configuration.getSftpUsername(), configuration.getSftpPassword());
        return sshClient;
    }

    public void process() throws IOException {
        SSHClient sshClient = setupSSHClient();
        SFTPClient sftpClient = sshClient.newSFTPClient();
        // uploading files
        uploadFiles(sftpClient);
        // downloading files
        downloadFiles(sftpClient);
        sftpClient.close();
        sshClient.disconnect();
    }

    private void uploadFiles (SFTPClient sftpClient) throws IOException {
        File sourceDir = new File(configuration.getSftpLocalSource());
        File[] files = sourceDir.listFiles();

        for (File file : files) {
            // TODO: add support for uploading folders
            if (file.isFile()) {
                System.out.println("uploading file: " + file.getName());
                sftpClient.put(file.getAbsolutePath(), configuration.getSftpRemoteDestination());
                System.out.println("uploaded file: " + file.getName());
            }
        }
    }

    private void downloadFiles(SFTPClient sftpClient) throws IOException {
        List<RemoteResourceInfo> remoteResourceInfoList =  sftpClient.ls(configuration.getSftpRemoteSource());

        for (RemoteResourceInfo remoteResourceInfo : remoteResourceInfoList) {
            // TODO: add support for downloading folders
            if (remoteResourceInfo.isRegularFile()) {
                System.out.println("downloading file: " + remoteResourceInfo.getName());
                System.out.println("path of file : " + remoteResourceInfo.getPath());
                sftpClient.get(remoteResourceInfo.getPath(), configuration.getSftpLocalDestination());
                System.out.println("downloaded file: " + remoteResourceInfo.getName());
            }
        }
    }
}
