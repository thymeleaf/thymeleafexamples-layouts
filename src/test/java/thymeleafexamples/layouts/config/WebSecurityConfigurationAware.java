package thymeleafexamples.layouts.config;

import org.junit.Before;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.inject.Inject;

public abstract class WebSecurityConfigurationAware extends WebAppConfigurationAware {

    @Inject
    private FilterChainProxy springSecurityFilterChain;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilters(this.springSecurityFilterChain).build();
    }
}
