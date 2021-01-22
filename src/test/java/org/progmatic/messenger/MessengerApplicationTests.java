package org.progmatic.messenger;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.progmatic.messenger.controller.HomeController;
import org.progmatic.messenger.controller.MessageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.http.HttpResponse;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MessageController.class)
@AutoConfigureMockMvc(addFilters = false)
@ComponentScan({"org.progmatic.messenger.service"})
class MessengerApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void testHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/greeting"))
                .andExpect(MockMvcResultMatchers.view().name("/home"));
    }

    @Test
    @WithMockUser
    public void testHomePage2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/message/kilistaz")
                .param("from", "user")
                .param("text", "valami"))
                .andExpect(
                        MockMvcResultMatchers.view().name("MessageSearcherandList"));


    }
}

