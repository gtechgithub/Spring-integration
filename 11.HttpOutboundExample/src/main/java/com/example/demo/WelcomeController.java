package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@RequestMapping(value="/serverTime")
	public ResponseEntity<?> getServerTime() {
		DateTimeFormatter datetimeformat= DateTimeFormatter.ofPattern("dd-MM-YYYY HH:MM:SS");
		LocalDateTime currentTime = LocalDateTime.now();
		
		String servermsgTime = datetimeformat.format(currentTime);
	    ServerMsg servermsg = new ServerMsg();
	    servermsg.setCurrentTime(servermsgTime);
	    
	    return new ResponseEntity<ServerMsg>(servermsg,HttpStatus.OK);
	}
}	