package com.example.application.views.personel;

import com.example.application.backend.entity.Personel;
import com.example.application.repository.PersonelRepository;
import com.example.application.service.AppService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Personel")
@Route(value = "personel", layout = MainLayout.class)
public class PersonelView extends VerticalLayout {
    Grid<Personel> grid = new Grid<>(Personel.class);

    TextField filterText = new TextField();
    private AppService appService;
    private PersonelRepository personelRepository;

    public PersonelView(AppService appService, PersonelRepository personelRepository){
        this.appService = appService;
        this.personelRepository = personelRepository;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureFilter();

        // filtre componentini gridden önce ekle
        add(filterText, grid);

        updateList();
    }

    private void configureGrid(){
        grid.addClassName("personel-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "tckn");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter by ..............");
        filterText.setClearButtonVisible(true);

        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }
    private void updateList(){
        grid.setItems(appService.findAll(filterText.getValue()));
    }
}
