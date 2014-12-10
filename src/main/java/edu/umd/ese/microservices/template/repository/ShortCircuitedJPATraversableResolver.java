package edu.umd.ese.microservices.template.repository;

import org.hibernate.validator.internal.engine.resolver.DefaultTraversableResolver;

/**
 * JPA detects orientDB as a JPA implementation but it's not a full impl.
 * We have to add a couple resolvers see:
 * http://stackoverflow.com/questions/25862444/how-to-fix-compatibility-issue-with-jsr-303-validation-and-orientdb
 */
public class ShortCircuitedJPATraversableResolver extends DefaultTraversableResolver {

    public ShortCircuitedJPATraversableResolver() {
        //we simply override the constructor to disable jpa detection
    }

    @Override
    public boolean isReachable(java.lang.Object traversableObject, javax.validation.Path.Node traversableProperty, java.lang.Class<?> rootBeanType, javax.validation.Path pathToTraversableObject, java.lang.annotation.ElementType elementType) {
        return true;
    }

    @Override
    public boolean isCascadable(java.lang.Object traversableObject, javax.validation.Path.Node traversableProperty, java.lang.Class<?> rootBeanType, javax.validation.Path pathToTraversableObject, java.lang.annotation.ElementType elementType) {
        return true;
    }
}
