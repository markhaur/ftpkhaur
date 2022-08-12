package com.ftp.ftpkhaur;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class FtpkhaurApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtpkhaurApplication.class, args);

	}

}
