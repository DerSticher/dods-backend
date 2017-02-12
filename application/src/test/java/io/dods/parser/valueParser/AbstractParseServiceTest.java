package io.dods.parser.valueParser;

import org.junit.ClassRule;
import org.junit.Rule;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

/**
 * @author Richard Gottschalk
 */
public abstract class AbstractParseServiceTest {

    // -------------------------------------------
    //  spring test support requirement from 4.2
    // -------------------------------------------
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
}
