package com.example.genshinimpactjunit5springboot.controllers;

import com.example.genshinimpactjunit5springboot.models.Region;
import com.example.genshinimpactjunit5springboot.services.impl.RegionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/genshin-regions")
public class RegionsController {

    private final RegionsServiceImpl genshinRegionsService;

    @Autowired
    public RegionsController(RegionsServiceImpl genshinRegionsService) {
        this.genshinRegionsService = genshinRegionsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("regions", genshinRegionsService.findAll());
        return "genshin-regions/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("region", genshinRegionsService.findOne(id));
        return "genshin-regions/show";
    }

    @GetMapping("/new")
    public String newHero(@ModelAttribute("region") Region genshinRegion) {
        return "genshin-regions/new";
    }

    @GetMapping("/index")
    public String index(@RequestParam(value = "myVariable", required = false) String myVariable,
                        Model model) {
        model.addAttribute("regions", genshinRegionsService.findAll());
        model.addAttribute("listOfHeroes", genshinRegionsService.getGenshinHeroesNamesFromSomeLocationAndSomeWeapon(myVariable));
        return "genshin-regions/index";
    }

    @PostMapping("/my-form")
    public String find(@RequestParam("myVariable") String myVariable,
                       RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("myVariable", myVariable);
        return "redirect:/genshin-regions/index";
    }


    @PostMapping()
    public String create(@ModelAttribute("region") @Valid Region genshinRegion,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "genshin-regions/new";

        genshinRegionsService.save(genshinRegion);
        return "redirect:/genshin-regions";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("region", genshinRegionsService.findOne(id));
        return "genshin-regions/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("region") @Valid Region genshinRegion, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "genshin-regions/edit";

        genshinRegionsService.update(id, genshinRegion);
        return "redirect:/genshin-regions";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        genshinRegionsService.delete(id);
        return "redirect:/genshin-regions";
    }
}
