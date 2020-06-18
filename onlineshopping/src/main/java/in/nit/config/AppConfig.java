package in.nit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@PropertySource("classpath:app.properties")
@ComponentScan(basePackages="in.nit")
public class AppConfig implements WebMvcConfigurer{

	@Autowired
	private Environment env;

	@Bean
	public DataSource ds() {
		BasicDataSource d= new BasicDataSource();
		d.setDriverClassName(env.getProperty("db.driver"));
		d.setUrl(env.getProperty("db.url"));
		d.setUsername(env.getProperty("db.user"));
		d.setPassword(env.getProperty("db.password"));

		return d;
	}

	//2. LocalSessionFactoryBean
	@Bean
	public LocalSessionFactoryBean sf() {
		LocalSessionFactoryBean s=new LocalSessionFactoryBean();
		s.setDataSource(ds());
		s.setHibernateProperties(props());
		s.setPackagesToScan("in.nit.model");
		return s;
	}
	@Bean
	public Properties props() {
		Properties p =new Properties();
		p.put("hibernate.dialect",env.getProperty("orm.dialect"));
		p.put("hibernate.show_sql", env.getProperty("orm.showsql"));
		p.put("hibernate.format_sql", env.getProperty("orm.frmtsql"));
		p.put("hibernate.hbm2ddl.auto",env.getProperty("orm.ddlauto"));
		return p;
	}

	//3. Hibernate Template
	@Bean
	public HibernateTemplate ht() {
		HibernateTemplate h =new HibernateTemplate();
		h.setSessionFactory(sf().getObject());
		return h;
	}

	//4. HiberanetTranscationManagement
	@Bean
	public HibernateTransactionManager htx() {
		HibernateTransactionManager htm=new HibernateTransactionManager();
		htm.setSessionFactory(sf().getObject());

		return htm;
	}
	@Bean
	public InternalResourceViewResolver irvr() {
		InternalResourceViewResolver ir= new InternalResourceViewResolver();
		ir.setPrefix(env.getProperty("mvc.prefix"));
		ir.setSuffix(env.getProperty("mvc.suffix"));
		return ir;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/assets/");
	}

}
