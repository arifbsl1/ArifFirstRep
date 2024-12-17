package com.sms.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoRestController {

	@GetMapping("/message/{msg}")
	public ResponseEntity<?> getMessage(@PathVariable String msg){
		return ResponseEntity.ok("Hello Mr/Mrs : "+msg);
	}
}
