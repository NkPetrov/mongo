package com.uib.mongo.controller

import com.uib.mongo.repository.entity.document.PartQuestionnaire
import com.uib.mongo.service.QuestionnaireService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/part")
class PartController(
        private val questionnaireService: QuestionnaireService
) {
    @PostMapping("/addPart/{listId}")
    fun editPart(@RequestParam("partPartId") partPartId: String,
                 @PathVariable("listId") listId: String,
                 part: PartQuestionnaire): String {
        var parentPart = questionnaireService.getPartQuestionnaireByPartId(partPartId)
        parentPart?.children?.add(questionnaireService.saveEditPart(part))
        if (parentPart != null) {
            questionnaireService.saveEditPart(parentPart)
        }
        return "redirect:/main/editList/${listId}"
    }

    @GetMapping("/deletePart/{listId}/{partId}")
    fun deleteList(@PathVariable("partId") partId: String,
                   @PathVariable("listId") listId: String
    ): String {
        questionnaireService.deletePart(partId)
        return "redirect:/main/editList/${listId}"
    }
}
