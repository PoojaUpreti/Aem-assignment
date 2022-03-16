package com.mysite.core.pk2;

import com.mysite.core.pk1.authpub;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Component(service = authpub.class)
@Designate(ocd = OsgiConfig.Config.class)

public class OsgiConfig implements authpub{
    private boolean author;




    @ObjectClassDefinition(name = "servletauthpub", description = "This is our first servlet")
    public @interface Config {
        @AttributeDefinition(name = "author", description = "This is author definition", type = AttributeType.BOOLEAN)
        boolean isAuthor() default true;

    }
    @Activate
    @Modified
    public void active(Config config) {
        this.author = config.isAuthor();
    }
    public boolean isAuthor() {
        return author;
    }

    @Override
    public String author() {
        if (isAuthor())
            return "This servlet is executed from author environment";
        else
            return "This servlet is executed from publisher environment";
    }
    @Override
    public String publisher() {
        return null;
    }
}