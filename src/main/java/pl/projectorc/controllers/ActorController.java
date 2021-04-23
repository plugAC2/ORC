package pl.projectorc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.projectorc.factories.ActorEntityModelFactory;
import pl.projectorc.models.ActorModel;
import pl.projectorc.security.UserSecurityUtil;
import pl.projectorc.services.ActorService;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/character")
public class ActorController {

    private final ActorService actorService;
    private final UserSecurityUtil userSecurityUtil;
    private final ActorEntityModelFactory factory;

    public ActorController(ActorService actorService, UserSecurityUtil userSecurityUtil, ActorEntityModelFactory factory) {
        this.actorService = actorService;
        this.userSecurityUtil = userSecurityUtil;
        this.factory = factory;
    }

    @GetMapping
    public ModelAndView getAllCharacters() {
        List<ActorModel> actors = actorService.getAllModel();
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
        actorService.newRecord(actorModel);

        return "redirect:/character";
    }

    @GetMapping(value = "/edit", params = "id")
    public ModelAndView editCharacter(@RequestParam Long id) {
        try {
            if(!actorService.checkIfActorGeneral(id)) {
                userSecurityUtil.userOwnsCharacter(id);
            }
            return new ModelAndView("editActor", "editActorModel", factory.createModelFromEntityId(id));
        } catch (NoSuchElementException e) {
            return new ModelAndView("redirect:/error");
        }
    }

    @PostMapping(value = "/edit", params = "id")
    public String updateCharacter(@RequestParam Long id, @ModelAttribute("editActorModel") ActorModel actorModel) {
        actorService.changeRecord(id, actorModel);
        return "redirect:/character";
    }

    @GetMapping(value = "/delete", params = "id")
    public String deleteCharacter(@RequestParam Long id) {
        if(!actorService.checkIfActorGeneral(id)) {
            userSecurityUtil.userOwnsCharacter(id);
        }
        actorService.deleteRecordById(id);
        return "redirect:/character";
    }

}
