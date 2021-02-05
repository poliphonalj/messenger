package org.progmatic.messenger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.progmatic.messenger.DTO.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest//integracios teszt
@AutoConfigureMockMvc
@ActiveProfiles("mytest_1")
class MessengerApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @WithMockUser(username = "user")
    void userShouldBeAbleToCreateMessageWitRest() throws Exception {
        MessageDTO mr = new MessageDTO();
        mr.setText("Helló");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/message")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mr)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        /*MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/rest/listmessages"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        List<MessageDTO> dtolist =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")),
                        new TypeReference<List<MessageDTO>>() {
                        });
        Assertions.assertFalse(dtolist.isEmpty());*/
    }

}
