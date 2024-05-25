package com.example.application.views.helloworld;

import com.example.application.db.DbH2Service;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("Hello World")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends VerticalLayout {


    private final MultiSelectListBox<String> listBox;
    @Autowired
    private DbH2Service dbH2Service;

    public HelloWorldView() {

        listBox = new MultiSelectListBox<>();

        //  constructing layout insert
        HorizontalLayout layoutInsert = new HorizontalLayout();
        layoutInsert.setSizeFull();
        layoutInsert.setJustifyContentMode(JustifyContentMode.CENTER);

        TextField txtName = new TextField();
        txtName.setValue("my name");

        Button btnInsertData = new Button("insert data");
        btnInsertData.setIcon(VaadinIcon.INSERT.create());
        btnInsertData.addClickListener(e -> {
            String strResult = dbH2Service.insertData(txtName.getValue(), txtName.getValue() + "@server.com");
            Notification.show(strResult, 2400, Notification.Position.MIDDLE);
        });

        // constructing layout operations
        HorizontalLayout layoutOperations = new HorizontalLayout();

        Button btnShow = new Button("show records", VaadinIcon.SEARCH.create());
        btnShow.addClickListener(event -> {
            fetchDataFromDb();
        });

        Button btnDelete = new Button("delete all");
        btnDelete.setIcon(VaadinIcon.DEL.create());
        btnDelete.addClickListener(e -> {
            String strResult = dbH2Service.deleteAllData();
            Notification.show(strResult, 2400, Notification.Position.MIDDLE);
        });

        // adding to sub-layouts
        layoutOperations.add(btnShow, btnDelete);
        layoutInsert.add(txtName, btnInsertData);

        // setting properties of HelloWorldView (VerticalLayout)
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        //adding to total layout
        add(layoutInsert, layoutOperations, listBox);

    }


    private void fetchDataFromDb() {
        List<String> listString = dbH2Service.fetchListString();
        listBox.setItems(listString);
    }


}
