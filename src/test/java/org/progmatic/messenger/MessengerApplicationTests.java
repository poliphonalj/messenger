package org.progmatic.messenger;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.progmatic.messenger.controller.SearchAndListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SearchAndListController.class)
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

