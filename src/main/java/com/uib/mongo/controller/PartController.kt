package com.uib.mongo.controller

import com.uib.mongo.repository.entity.document.PartQuestionnaire
import com.uib.mongo.service.QuestionnaireService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/part")
class PartController(
        private val questionnaireService: QuestionnaireService
) {
    @PostMapping("/editPart/{listId}")
    fun editList(@RequestParam("partPartId") partPartId: String,
                 @PathVariable("listId") listId: String,
                 part: PartQuestionnaire): String {
        var parentPart = questionnaireService.getPartQuestionnaireByPartId(partPartId)
        parentPart?.children?.add(questionnaireService.saveEditPart(part))
        if (parentPart != null) {
            questionnaireService.saveEditPart(parentPart)
        }
        return "redirect:/main/editList/${listId}"
    }
}
