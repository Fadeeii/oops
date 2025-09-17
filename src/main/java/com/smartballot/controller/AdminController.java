package com.smartballot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.smartballot.model.Candidate;
import com.smartballot.repo.CandidateRepository;
import com.smartballot.repo.VoteRepository;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private VoteRepository voteRepository;

    @GetMapping
    public String adminHome(Model model) {
        List<Candidate> candidates = candidateRepository.findAll();
        model.addAttribute("candidates", candidates);
        return "admin";
    }

    @PostMapping("/addCandidate")
    public String addCandidate(@RequestParam String name, @RequestParam String party) {
        candidateRepository.save(new Candidate(name, party));
        return "redirect:/admin";
    }

    @GetMapping("/results")
    public String showResults(Model model) {
        List<Candidate> candidates = candidateRepository.findAll();
        model.addAttribute("candidates", candidates);
        model.addAttribute("voteRepo", voteRepository);
        return "results";
    }
}