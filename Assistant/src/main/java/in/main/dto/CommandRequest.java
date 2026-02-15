package in.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandRequest {

	 private String command;

	    public String getCommand() {
	        return command;
	    }

	    public void setCommand(String command) {
	        this.command = command;
	    }
}
