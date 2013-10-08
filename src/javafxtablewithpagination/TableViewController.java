/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtablewithpagination;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author bhalepr
 */
public class TableViewController implements Initializable {

  @FXML
  private TableView<Person> personView = new TableView<>();
  @FXML
  private TableColumn<Person, String> personViewIdCol;
  @FXML
  private TableColumn<Person, String> personViewNameCol;
  @FXML
  private TableColumn<Person, Integer> personViewAgeCol;
  @FXML
  Pagination pagination;
  @FXML
  private Button save;
  @FXML
  private TextField idTxt;
  @FXML
  private TextField nameTxt;
  @FXML
  private TextField ageTxt;
  private Button id = new Button("ID");

  private ImageView upImg = new ImageView(new Image("/resources/image/up.png"));
  private ImageView downImg = new ImageView(new Image("/resources/image/down.png"));
  List<Person> rawPersons = PersonFactory.getAllPersons();
  ObservableList<Person> persons = FXCollections.observableArrayList(rawPersons);
  int pageCount = 5;
  int itemsPerPage = 4;
  int currentPageIndex = 0;
  boolean order = true;

  //SimpleIntegerProperty pageCount = new SimpleIntegerProperty(5);
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    System.out.println("initialize");
    sort();
    initializeTable();
    pageCount = getPageCount(persons.size(), itemsPerPage);

    System.out.println("pageCount=" + pageCount);
    pagination.setPageCount(pageCount);

    pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        System.out.println("Pagination Changed from " + oldValue + " , to " + newValue);
        currentPageIndex = newValue.intValue();
        updatePersonView();
      }
    });

    save.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
      @Override
      public void handle(javafx.event.ActionEvent t) {
        persons.add(new Person(idTxt.getText(), nameTxt.getText(), (new Integer(ageTxt.getText())).intValue()));
        sort();
        updatePersonView();
        idTxt.setText(null);
        nameTxt.setText(null);
        ageTxt.setText(null);
      }
    });

    id.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
      @Override
      public void handle(javafx.event.ActionEvent t) {
        sort();
        if (order) {
          Collections.reverse(persons);
        }
        System.out.println(" order = " + order + "; data = " + persons);
        order = !order;
        id.setGraphic((order) ? upImg : downImg);
        updatePersonView();
        System.out.println(" comparator called");
      }
    });
  }

  private void sort() {
    //persons.sort((Person p1, Person p2) -> p1.id.compareTo(p2.id));
    Collections.sort(persons, new Comparator<Person>() {
      @Override
      public int compare(Person t, Person t1) {
        System.out.println(" comparator called");
        return t.getId().compareTo(t1.getId());
      }
    });
  }

  public void updatePersonView() {
    System.out.println("updatePersonView");
    personView.getItems().setAll(persons.subList(currentPageIndex * itemsPerPage, ((currentPageIndex * itemsPerPage + itemsPerPage <= persons.size()) ? currentPageIndex * itemsPerPage + itemsPerPage : persons.size())));
  }

  private void initializeTable() {
    personViewIdCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Id"));
    id.setGraphic(upImg);
    personViewIdCol.setGraphic(id);
    personViewIdCol.setSortable(false);
    personViewNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Name"));
    personViewNameCol.sortTypeProperty().addListener(new ChangeListener<SortType>() {
      @Override
      public void changed(ObservableValue<? extends SortType> paramObservableValue, SortType paramT1, SortType paramT2) {
        System.out.println("NAME Clicked -- sortType = " + paramT1 + ", SortType=" + paramT2);
        id.setGraphic(null);
      }
    });
    personViewAgeCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("Age"));
    personViewAgeCol.setText("AGE");
    personViewAgeCol.setSortable(false);
    personView.getItems().setAll(persons.subList(0, itemsPerPage));
  }

  public int getItemsPerPage() {
    return itemsPerPage;
  }

  public void setItemsPerPage(int itemsPerPage) {
    this.itemsPerPage = itemsPerPage;
  }

  public int getCurrentPageIndex() {
    return currentPageIndex;
  }

  public void setCurrentPageIndex(int currentPageIndex) {
    this.currentPageIndex = currentPageIndex;
  }

  private int getPageCount(int totalCount, int itemsPerPage) {
    float floatCount = Float.valueOf(totalCount) / Float.valueOf(itemsPerPage);
    int intCount = totalCount / itemsPerPage;
    System.out.println("floatCount=" + floatCount + ", intCount=" + intCount);
    return ((floatCount > intCount) ? ++intCount : intCount);
  }
}
