package com.theironyard.controllers;

import com.theironyard.entities.Run;
import com.theironyard.entities.User;
import com.theironyard.services.RunRepository;
import com.theironyard.services.UserRespository;
import com.theironyard.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by MacLap on 3/14/16.
 */

@Controller
public class SpeedrunsController {
    @Autowired
    UserRespository users;

    @Autowired
    RunRepository runs;

    @RequestMapping(path="/", method = RequestMethod.GET)
    public String home (HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByName(username);
        if (user !=null) {
            model.addAttribute("user", users.findFirstByName(username));
        }
        else {
            model.addAttribute("runs", runs.findAll());
        }

        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username, String password) throws Exception {
        User user = users.findFirstByName(username);
        if (user == null) {
            user = new User(username, PasswordStorage.createHash(password));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(password, user.getPasswordHash())){
            throw new Exception("Incorrect password");
        }
        session.setAttribute("username", username);
        return "redirect:/";
    }

    @RequestMapping(path="/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/add-run", method = RequestMethod.POST)
    public String addGame(HttpSession session, String gameName, int timeSeconds) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByName(username);
        Run run = new Run(gameName, timeSeconds, user);
        runs.save(run);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-run", method = RequestMethod.POST)
    public String delete(int runId) {
        runs.delete(runId);
        return "redirect:/";
    }

    @RequestMapping(path = "/edit-run", method = RequestMethod.GET)
    public String updateMessage(Model model, int runId) {
        Run run = runs.findOne(runId);
        model.addAttribute("run", run);
        return "edit";
    }

    @RequestMapping(path="/edit-message", method=RequestMethod.POST)
    public String editMessage(int runId, String newName, int newTime) {
        Run run = runs.findOne(runId);
        run.gameName = newName;
        run.timeSeconds = newTime;
        runs.save(run);
        return "redirect:/";
    }
}
