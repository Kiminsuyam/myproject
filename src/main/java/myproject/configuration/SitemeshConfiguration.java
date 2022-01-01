package myproject.configuration;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SitemeshConfiguration extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
//		builder.addDecoratorPath("/community","/WEB-INF/views/decorator/default-layout.jsp");
//		builder.addDecoratorPath("/notice","/WEB-INF/views/decorator/default-layout.jsp");
//		builder.addDecoratorPath("/faq","/WEB-INF/views/decorator/default-layout.jsp");
//		builder.addDecoratorPath("/inquiry","/WEB-INF/views/decorator/default-layout.jsp");

		//최상단 공통 layout나오게 설정 **추가
		builder.addDecoratorPath("/community**","/WEB-INF/views/decorator/default-layout.jsp");
		builder.addDecoratorPath("/notice**","/WEB-INF/views/decorator/default-layout.jsp");
		builder.addDecoratorPath("/faq**","/WEB-INF/views/decorator/default-layout.jsp");
		builder.addDecoratorPath("/inquiry**","/WEB-INF/views/decorator/default-layout.jsp");
		super.applyCustomConfiguration(builder);
	}
}
