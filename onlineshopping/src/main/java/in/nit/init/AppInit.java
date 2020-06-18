package in.nit.init;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import in.nit.config.AppConfig;

public class AppInit 
			extends AbstractAnnotationConfigDispatcherServletInitializer {

	//provide spring configure file as input to web.xml(FC)
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[] {AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return null;
	}
//Url Pattern
	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"/"}; 
	}
	
	

}
