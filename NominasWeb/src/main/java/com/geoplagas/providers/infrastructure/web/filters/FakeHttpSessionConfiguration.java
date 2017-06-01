package com.geoplagas.providers.infrastructure.web.filters;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.SessionRepositoryFilter;

@Configuration
@Profile({"dev","beta"})
public class FakeHttpSessionConfiguration {
	@Bean
    public <S extends ExpiringSession> SessionRepositoryFilter<? extends ExpiringSession> springSessionRepositoryFilter(SessionRepository<S> sessionRepository, ServletContext servletContext) {
        SessionRepositoryFilter<S> sessionRepositoryFilter = new SessionRepositoryFilter<S>(sessionRepository);
        sessionRepositoryFilter.setServletContext(servletContext);
        return sessionRepositoryFilter;
    }

    @Bean(name="mapSessionRepository")
    public SessionRepository<?> sessionRepository() {
        MapSessionRepository sessionRepository = new MapSessionRepository();
        return sessionRepository;
    }
}