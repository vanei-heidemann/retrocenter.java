package com.javanei.retrocenter.datafile.service;

import com.javanei.retrocenter.common.DatafileCategoryEnum;
import com.javanei.retrocenter.datafile.Datafile;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatafileServiceConfiguration.class})
public class DatafileServiceTest {
    private static Datafile datafile;
    @Autowired
    private DatafileService datafileService;

    @BeforeClass
    public static void initialize() throws Exception {
        datafile = new Datafile("name 01", "no-intro", "1.00",
                "description 01", "author 01", "2017", "teste@teste.com",
                "homepage 01", "http://www.teste.com", "comment 01");
    }

    @Test
    public void create() {
        Datafile d = datafileService.create(datafile);
        Assert.assertEquals("Name", "name 01", d.getName());
        Assert.assertEquals("category", DatafileCategoryEnum.NoIntro.name(), d.getCategory());
        Assert.assertEquals("version", "1.00", d.getVersion());
        Assert.assertEquals("description", "description 01", d.getDescription());
        Assert.assertEquals("author", "author 01", d.getAuthor());
        Assert.assertEquals("date", "2017", d.getDate());
        Assert.assertEquals("email", "teste@teste.com", d.getEmail());
        Assert.assertEquals("homepage", "homepage 01", d.getHomepage());
        Assert.assertEquals("url", "http://www.teste.com", d.getUrl());
        Assert.assertEquals("comment", "comment 01", d.getComment());
    }
}
