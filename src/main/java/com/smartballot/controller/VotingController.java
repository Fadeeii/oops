package com.smartballot.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.smartballot.model.Candidate;
import com.smartballot.model.User;
import com.smartballot.model.VoteRecord;
import com.smartballot.repo.CandidateRepository;
import com.smartballot.repo.UserRepository;
import com.smartballot.repo.VoteRepository;

import java.util.List;

@Controller
public class VotingController {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/";

        model.addAttribute("user", user);
        return "dashboard";
    }

    @GetMapping("/vote")
    public String showVotePage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/";

        if (user.isHasVoted()) {
            model.addAttribute("message", "You have already voted.");
            return "dashboard";
        }

        List<Candidate> candidates = candidateRepository.findAll();
        model.addAttribute("candidates", candidates);
        return "vote";
    }

    @PostMapping("/vote")
    public String castVote(@RequestParam Long candidateId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/";

        if (user.isHasVoted()) {
            model.addAttribute("message", "You already voted.");
            return "dashboard";
        }

        Candidate candidate = candidateRepository.findById(candidateId).orElse(null);
        if (candidate != null) {
            VoteRecord vote = new VoteRecord(user, candidate);
            voteRepository.save(vote);
            user.setHasVoted(true);
            userRepository.save(user);
        }

        return "redirect:/results";
    }
}