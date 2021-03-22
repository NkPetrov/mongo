package com.uib.mongo.mongo.configuration

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomizeAuthenticationSuccessHandler : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(request: HttpServletRequest,
                                         response: HttpServletResponse, authentication: Authentication) {
        //set our response to OK status
        response.status = HttpServletResponse.SC_OK
        for (auth in authentication.authorities) {
            if ("ADMIN" == auth.authority) {
                response.sendRedirect("/dashboard")
            }else response.sendRedirect("/start")
        }
    }
}
