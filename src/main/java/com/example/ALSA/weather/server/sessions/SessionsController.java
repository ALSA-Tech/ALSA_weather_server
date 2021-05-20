package com.example.ALSA.weather.server.sessions;

import com.example.ALSA.weather.server.clients.Client;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/session")
@SessionAttributes({"user"})
public class SessionsController {

    @GetMapping("/create")
    public String createSession(HttpSession httpSession){
        String message = "";
        httpSession.setAttribute("user", "client-created");//replace with client object
        message = "Session created with value: " + httpSession.getAttribute("user");
        return message;
    }

    @GetMapping("/isValid")
    public Boolean isValid(HttpSession httpSession){
        boolean isValid;
        if(httpSession.getAttribute("user") == null){
            isValid = false;
        }else{
            isValid = true;
        }
        return isValid;
    }

    @GetMapping("/display")
    public Object display(HttpSession httpSession){
        String message = "";
        if(httpSession.getAttribute("user") == null){
            message = "Can't display session, it's empty!";
        }else{
            message = "Session content: " + httpSession.getAttribute("user");
        }
        return message;
    }

    @GetMapping("/destroy")
    public Object destroySession(HttpSession httpSession){
        String message = "";
        if(httpSession.getAttribute("user") == null){
            message = "The sessions is empty, nothing to delete!";
        }else{
           message  = "User deleted: " + httpSession.getAttribute("user");
        }
        httpSession.invalidate();
        return message;
    }

}
