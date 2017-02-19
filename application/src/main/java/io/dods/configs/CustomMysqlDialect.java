package io.dods.configs;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Extends MySQL5InnoDBDialect and sets the default charset to be UTF-8
 *
 * @author Sorin Postelnicu
 * @since Aug 13, 2007
 */
public class CustomMysqlDialect extends MySQL5InnoDBDialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB CHARSET=utf8";
    }

}