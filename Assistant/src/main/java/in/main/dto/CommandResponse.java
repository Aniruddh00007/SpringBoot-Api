
package in.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandResponse {


    private String reply;

    public CommandResponse(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }
}
