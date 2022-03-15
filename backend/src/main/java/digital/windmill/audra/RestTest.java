package ch.windmill.audra;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTest {
	
	@GetMapping("echo")
	public String echo() {
		return "echo";
	}

}
