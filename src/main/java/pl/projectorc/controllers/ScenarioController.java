package pl.projectorc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/scenario")
@RequiredArgsConstructor
public class ScenarioController {

//    private ScenarioService scenarioService;

    @GetMapping
    public String getScenario() {
        return "scenario";
    }
}
