package ru.onemore.vtbhack.back.config;

import org.jooq.conf.RenderQuotedNames;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqConfig {

    @Bean
    public DefaultConfiguration configuration(DataSourceConnectionProvider connectionProvider) {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(connectionProvider);
//        jooqConfiguration.set(new DefaultExecuteListenerProvider(exceptionTransformer()));

//        String sqlDialectName = environment.getRequiredProperty("jooq.sql.dialect");
//        SQLDialect dialect = SQLDialect.valueOf(sqlDialectName);
//        jooqConfiguration.set(dialect);
        jooqConfiguration.settings().setRenderQuotedNames(RenderQuotedNames.NEVER);

        return jooqConfiguration;
    }

    @Bean
    public DefaultDSLContext dsl(DataSourceConnectionProvider connectionProvider) {
        return new DefaultDSLContext(configuration(connectionProvider));
    }

}
