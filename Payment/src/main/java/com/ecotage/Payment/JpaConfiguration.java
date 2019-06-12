package com.ecotage.Payment;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages= {"com.ecotage.repo","com.ecotage.handler","com.ecotage.dao"})
@EntityScan(basePackages = {"com.ecotage.model"})
public interface JpaConfiguration {

}
