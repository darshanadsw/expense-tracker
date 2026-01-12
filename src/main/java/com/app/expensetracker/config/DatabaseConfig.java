package com.app.expensetracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Database configuration class for profile-specific data access setup.
 * 
 * This class provides profile-specific configurations:
 * 
 * - Local profile: Uses JDBC with H2
 *   - JPA auto-configuration is disabled via application-local.properties
 *   - JDBC auto-configuration is enabled (JdbcTemplate, NamedParameterJdbcTemplate)
 *   - Uses UserJDBCDao (@Profile("local"))
 * 
 * - Staging profile: Uses JPA with PostgreSQL
 *   - Full JPA auto-configuration enabled
 *   - JDBC-specific auto-configuration is disabled (JdbcTemplate, etc.)
 *   - DataSource is still configured (required by JPA)
 *   - Uses UserJpaRepository (@Profile("staging"))
 * 
 * Auto-configuration exclusions are handled via application-{profile}.properties:
 * - application-local.properties: Excludes HibernateJpaAutoConfiguration and JpaRepositoriesAutoConfiguration
 * - application-staging.properties: Excludes JdbcTemplateAutoConfiguration, NamedParameterJdbcTemplateAutoConfiguration
 */
@Configuration
public class DatabaseConfig {
    
    // Profile-specific auto-configuration exclusions are handled via:
    // 1. application-local.properties: Excludes JPA/Hibernate
    // 2. application-staging.properties: Excludes JDBC-specific beans (JdbcTemplate, etc.)
    // 3. Repository implementations use @Profile annotations for activation
}
