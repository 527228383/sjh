package com.sjh.config;

import net.hasor.core.ApiBinder;
import net.hasor.core.DimModule;
import net.hasor.db.JdbcModule;
import net.hasor.db.Level;
import net.hasor.spring.SpringModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * ClassName: DatawayModule <br/>
 * Description: <br/>
 * date: 2020/8/20 9:34<br/>
 *
 * @author ex-sujh<br/>
 * @since JDK 12
 */
@DimModule
@Component
public class DatawayModule implements SpringModule {
    @Autowired
     private DataSource dataSource = null;
     @Override
     public void loadModule(ApiBinder apiBinder) throws Throwable {
         apiBinder.installModule(new JdbcModule(Level.Full, this.dataSource));
     }
}
