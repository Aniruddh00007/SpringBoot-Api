package in.main.service;

import org.springframework.stereotype.Service;

@Service
public class AssistantService {

	
	 private final CommandProcessor commandProcessor;

	    public AssistantService(CommandProcessor commandProcessor) {
	        this.commandProcessor = commandProcessor;
	    }

	    public String handleCommand(String command) {
	        if (command == null || command.isEmpty()) {
	            return "Please say something.";
	        }
	        return commandProcessor.process(command.toLowerCase());
	    }
}
