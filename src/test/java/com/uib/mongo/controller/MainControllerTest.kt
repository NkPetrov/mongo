package com.uib.mongo.controller

import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.status
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.client.match.MockRestRequestMatchers.content
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest(var mockMvc: MockMvc) {


//    @Test
//    fun exampleTest1(wireMockServer: WireMockServer) {
//        wireMockServer.stubFor(
//            get(urlEqualTo("/main"))
//                .willReturn(
//                    aResponse()
//                        .withStatus(200)
//                )
//        )
//
//    }

//    @Test
//    fun exampleTest1() {
//        mockMvc.perform(get("/main"))
//            .andDo(print())
//            .andExpect(status().isOk())
//            .andExpect(content().string(containsString("Hello, Mock")))
//
//    }

}