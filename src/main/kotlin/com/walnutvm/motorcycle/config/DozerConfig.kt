package com.walnutvm.motorcycle.config

import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DozerConfig {

    @Bean
    fun dozerMapper(): Mapper = DozerBeanMapperBuilder.buildDefault()
}