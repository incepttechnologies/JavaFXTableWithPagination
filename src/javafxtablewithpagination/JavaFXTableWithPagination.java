package javafxtablewithpagination;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author bhalepr
 */
public class JavaFXTableWithPagination extends Application {
  private Stage stage;
  
  @Override
  public void start(Stage primaryStage) {
    stage = primaryStage;
    stage.setTitle("Table View!");
    gotoTableView();
    stage.show();
  }
  
   private void gotoTableView() {
    try {
      TableViewController tableView = (TableViewController) replaceSceneContent("TableView.fxml");
    } catch (Exception ex) {
      Logger.getLogger(JavaFXTableWithPagination.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private Initializable replaceSceneContent(String fxml) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    InputStream in = JavaFXTableWithPagination.class.getResourceAsStream(fxml);
    loader.setBuilderFactory(new JavaFXBuilderFactory());
    loader.setLocation(JavaFXTableWithPagination.class.getResource(fxml));
    AnchorPane page;
    try {
      page = (AnchorPane) loader.load(in);
    } finally {
      in.close();
    }
    Scene scene = new Scene(page, 800, 600);
    stage.setScene(scene);
    stage.sizeToScene();
    return (Initializable) loader.getController();
  }

  /**
   * The main() method is ignored in correctly deployed JavaFX application. main() serves only as fallback in case the
   * application can not be launched through deployment artifacts, e.g., in IDEs with limited FX support. NetBeans
   * ignores main().
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
  
}
