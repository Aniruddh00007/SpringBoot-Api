package in.main.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;
@Component
public class CommandProcessor {

	public String process(String command) {

        if (command.contains("hello")) {
            return "Hello Aniruddh, Jack is online!";
        }

        if (command.contains("time")) {
            return "Current time is " + LocalTime.now();
        }

        if (command.contains("date")) {
            return "Today is " + LocalDate.now();
        }

        if (command.contains("who are you")) {
            return "I am Jack, your personal voice assistant.";
        }

        return "Sorry, I did not understand this command.";
    }
}
