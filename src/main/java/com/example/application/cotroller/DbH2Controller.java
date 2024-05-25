package com.example.application.cotroller;

import com.example.application.db.DbH2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("test")
/**
 * class used to assist in controlling the database, and verifying that db operations through vaadin are completed correctly
 */
public class DbH2Controller {


    @Autowired
    DbH2Service dbH2Service;

    @GetMapping("create")
    public void setup() {
        dbH2Service.createTable();
    }

    @GetMapping("insert")
    public void insert() {
        dbH2Service.insertData("nik", "nik@vaadin-presentation");
    }

    @GetMapping("fetch")
    public void fetch() {
        dbH2Service.fetch();
    }

}
