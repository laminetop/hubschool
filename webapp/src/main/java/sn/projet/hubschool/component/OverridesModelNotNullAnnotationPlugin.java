package sn.projet.hubschool.component;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import springfox.bean.validators.plugins.NotNullAnnotationPlugin;

/**
 * https://github.com/springfox/springfox/issues/1231
 */
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class OverridesModelNotNullAnnotationPlugin extends NotNullAnnotationPlugin {
}