package com.uib.mongo.controller

import com.uib.mongo.repository.entity.user.User
import com.uib.mongo.service.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class LoginController {
    @Autowired
    private val userService: CustomUserDetailsService? = null

    @GetMapping("/start")
    fun getStartingPage(): String{
        return "start"
    }

    @RequestMapping(value = ["/login"], method = [RequestMethod.GET])
    fun login(): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "login"
        return modelAndView
    }

    @RequestMapping(value = ["/signup"], method = [RequestMethod.GET])
    fun signup(): ModelAndView {
        val modelAndView = ModelAndView()
        val user = User()
        modelAndView.addObject("user", user)
        modelAndView.viewName = "signup"
        return modelAndView
    }

    @RequestMapping(value = ["/registration"], method = [RequestMethod.GET])
    fun registration(): ModelAndView {
        val modelAndView = ModelAndView()
        val user = User()
        modelAndView.addObject("user", user)
        modelAndView.viewName = "registration"
        return modelAndView
    }

    @RequestMapping(value = ["/signup"], method = [RequestMethod.POST])
    fun createNewUser(@Validated user: User, bindingResult: BindingResult): ModelAndView {
        val modelAndView = ModelAndView()
        val userExists = userService!!.findUserByName(user.name)
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided")
        }
        if (bindingResult.hasErrors()) {
            modelAndView.viewName = "signup"
        } else {
            userService.saveUser(user)
            modelAndView.addObject("successMessage", "User has been registered successfully")
            modelAndView.addObject("user", User())
            modelAndView.viewName = "login"
        }
        return modelAndView
    }

    @RequestMapping(value = ["/registration"], method = [RequestMethod.POST])
    fun createNewUserReg(@Validated user: User, bindingResult: BindingResult): ModelAndView {
        val modelAndView = ModelAndView()
        val userExists = userService!!.findUserByName(user.name)
        if (userExists != null) {
            bindingResult
                    .rejectValue("name", "error.user",
                            "There is already a user registered with the username provided")
        }
        if (bindingResult.hasErrors()) {
            modelAndView.viewName = "signup"
        } else {
            userService.saveUser(user)
            modelAndView.addObject("successMessage", "User has been registered successfully")
            modelAndView.addObject("user", User())
            modelAndView.viewName = "login"
        }
        return modelAndView
    }

    @RequestMapping(value = ["/dashboard"], method = [RequestMethod.GET])
    fun dashboard(): ModelAndView {
        val modelAndView = ModelAndView()
        val auth = SecurityContextHolder.getContext().authentication
        val user = userService!!.findUserByName(auth.name)
        modelAndView.addObject("currentUser", user)
        modelAndView.addObject("name", "Welcome " + user!!.name)
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role")
        modelAndView.viewName = "dashboard"
        return modelAndView
    }

    @RequestMapping(value = ["/", "/home"], method = [RequestMethod.GET])
    fun home(): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "home"
        return modelAndView
    }
}
