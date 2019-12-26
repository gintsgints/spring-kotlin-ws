package com.balica.ws.springws3.greeting

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class GreetingControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var greetingService: GreetingService

    @Test
    fun `List greetings`() {
        val gr1 = GreetingEntity("gr1", 100)
        val pageReq = PageRequest.of(0, 1, Sort.Direction.fromString("ASC"), "text")
        every { greetingService.findAll(pageReq) } returns PageImpl<GreetingEntity>(listOf(gr1))
        mockMvc.perform(get("/api/greeting/")
                .param("page", "0")
                .param("size", "1")
                .param("sortDir", "ASC")
                .param("sort", "text")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.content.[0].text").value(gr1.text))
    }

    @Test
    fun `List greetings with default page`() {
        val gr1 = GreetingEntity("gr1", 100)
        val pageReq = PageRequest.of(0, 1)
        every { greetingService.findAll(pageReq) } returns PageImpl<GreetingEntity>(listOf(gr1))
        mockMvc.perform(get("/api/greeting/")
                .param("size", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.content.[0].text").value(gr1.text))
    }

// TODO: Check why thease tests does not mock save
// I get error
//    org.springframework.web.util.NestedServletException: Request processing failed; nested exception is io.mockk.MockKException: no answer found for: GreetingService(com.balica.ws.springws3.greeting.GreetingService#0 bean#1).save(com.balica.ws.springws3.greeting.GreetingEntity@737eb41f)
//    at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)
//    at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)

//    @Test
//    fun `Create greeting`() {
//        val gr1 = GreetingEntity("gr1", 0)
//        every { greetingService.save(gr1) } returns GreetingEntity("gr1", 1)
//        mockMvc.perform(post("/api/greeting/")
//                .content("""{"text": "gr1"}""")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful);
//    }

//    @Test
//    fun `Update greeting`() {
//        val gr1 = GreetingEntity("gr1", 1)
//        every { greetingService.save(gr1) } returns GreetingEntity("gr2", 1)
//        mockMvc.perform(post("/api/greeting/")
//                .content("""{"id": 1, "text": "gr1"}""")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful);
//    }

}
