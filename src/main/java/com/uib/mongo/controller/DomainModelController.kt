package com.uib.mongo.controller

import com.uib.mongo.repository.entity.document.DomainQuestionnaire
import com.uib.mongo.service.CustomUserDetailsService
import com.uib.mongo.service.QuestionnaireService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/domain")
class DomainModelController(
        private val questionnaireService: QuestionnaireService,
        private val customUserDetailsService: CustomUserDetailsService
) {

    @GetMapping("{listId}")
    fun getStartingPage(@PathVariable("listId") listId: String,
                        model: Model): String {
        model.addAttribute("list",
                questionnaireService.getListQuestionnaireByListId(listId))
        return "domain"
    }

    @GetMapping("/editDomain/{listId}/{domainId}")
    fun editList(@PathVariable("domainId") domainId: String,
                 @PathVariable("listId") listId: String,
                 model: Model): String {
        model.addAttribute("domain",
                questionnaireService.getDomainById(domainId))
        return "editDomain"
    }

    @PostMapping("/editDomain/{listId}/{domainId}")
    fun editListName(@PathVariable("listId") listId: String,
                     domain: DomainQuestionnaire): String {
        questionnaireService.saveEditDomain(domain)
        return "redirect:/domain/${listId}"
    }
}
