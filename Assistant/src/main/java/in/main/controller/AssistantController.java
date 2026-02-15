package in.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import in.main.dto.CommandRequest;
import in.main.dto.CommandResponse;
import in.main.service.AssistantService;

@RestController
@RequestMapping
@CrossOrigin
public class AssistantController {

	   private final AssistantService assistantService;

	    public AssistantController(AssistantService assistantService) {
	        this.assistantService = assistantService;
	    }

	    @PostMapping("/command")
	    public ResponseEntity<CommandResponse> processCommand(
	            @RequestBody CommandRequest request) {

	        String response = assistantService.handleCommand(request.getCommand());
	        return ResponseEntity.ok(new CommandResponse(response));
	    }
}
