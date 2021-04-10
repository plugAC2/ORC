package pl.projectorc.controllers;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.projectorc.entities.Actor;
import pl.projectorc.models.ActorModel;
import pl.projectorc.services.ActorService;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/character")
public class ActorController {

    private ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ModelAndView getAllCharacters() {
        List<Actor> actors = actorService.getAll();
        return new ModelAndView("actor", "actors", actors);
    }

    @GetMapping("/add")
    public ModelAndView addNewCharacter() {
        return new ModelAndView("newActor", "addActorModel", new ActorModel());
    }

    @PostMapping("/add")
    public String saveActor(@Valid ActorModel actorModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newActor";
        }
        actorService.newRecord(actorService.setActorFromModel(actorModel));

        return "redirect:/character";
    }

    @GetMapping(value = "/edit", params = "id")
    public ModelAndView editCharacter(@RequestParam Long id) {
        try {
            return new ModelAndView("editActor", "editActorModel", actorService.setModelFromActorId(id));
        } catch (NoSuchElementException e) {
            return new ModelAndView("redirect:/error");
        }
    }

    @PostMapping(value = "/edit", params = "id")
    public String updateCharacter(@ModelAttribute("editActorModel") ActorModel actorModel) {
        Actor actor = actorService.setActorFromModel(actorModel);
        return "redirect:/character";
    }

}
