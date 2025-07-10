package com.luhach.algorithms.commands;


import com.luhach.algorithms.kds.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class Kds {
    private static final Logger LOG = LoggerFactory.getLogger(Kds.class);

    @Autowired
    private Table table;

    @ShellMethod("create Table")
    public void create(String name, String[] columns) {
        table.create(name,columns);
    }

    @ShellMethod("Describe Table")
    public void desc(String name) {
        table.desc(name);
    }

    @ShellMethod("Describe Table")
    public void in(String name,String columns,String values) {
        table.insert(name,columns,values);
    }
}
