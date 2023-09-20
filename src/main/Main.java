package main;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
 
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.Window;
import util.Connect;
 
public class Main extends Application{
 
	StackPane spLogin = new StackPane();
	StackPane spRegister = new StackPane();
	StackPane spMainMenu = new StackPane();
	StackPane spProductList = new StackPane();
	StackPane sCartList = new StackPane();
	StackPane spManage = new StackPane();
	StackPane spTranHead = new StackPane();
	StackPane spTranDetail = new StackPane();
 
	BorderPane manageBorderPane;
	GridPane mangeFormPane;
 
	Label manageProductIDLabel, manageNameLabel, manageTypeLabel, managePriceLabel, manageStockLabel;
	TextField manageProductIDField, manageNameField, manageTypeField, managePriceField, manageStockField;
	Spinner<Integer> priceSpinner, stockSpinner;
	ComboBox<String> typeCombo;
 
	Button insertBtn = new Button("Insert"); 
	Button updateBtn = new Button("Update");
	Button manageRemoveBtn = new Button("Remove"); 
 
	ScrollPane manageScrollPane;
 
	GridPane gpMainMenu = new GridPane();
	GridPane gridPane = new GridPane();
	GridPane gridPaneReg = new GridPane();
	GridPane gpProductList = new GridPane();
	GridPane formPane = new GridPane();
 
	GridPane manageFormPane = new GridPane();
	
	GridPane transHeadPane = new GridPane();
 
	GridPane transDetailPane = new GridPane();
	
	ScrollPane scrollPane;
	BorderPane borderPane = new BorderPane();
 
	HBox hBox = new HBox();
	HBox hBoxReg = new HBox();
    HBox hBoxRad = new HBox();
 
	Scene sceneLogin = new Scene(spLogin, 450, 500);
	Scene sceneRegister = new Scene(spRegister, 600, 600);
	Scene sceneMainMenu = new Scene(spMainMenu, 1200,750);
	Scene sceneProductList = new Scene(spProductList);
	Scene sceneCartList = new Scene(sCartList);
	Scene sceneManageList = new Scene(spManage);
	Scene sceneTransHead = new Scene(spTranHead);
	Scene sceneTransDetail = new Scene(spTranDetail);
	
	
	Window winProductList = new Window();
	Window winCartList = new Window();
	Window winManageList = new Window();
	Window winTransList = new Window();
	Window winTransDetailList = new Window();
 
	TableView <Products> productTable = new TableView<Products>();
	TableView <Cart> cartTable = new TableView<Cart>();
	TableView <TransactionHeader> tranHeadTable = new TableView<TransactionHeader>();
	TableView <TransactionDetail> tranDetailTable = new TableView<TransactionDetail>();
 
	//Declare
	Label lbEmail;
	Label lbPass;
 
	Label lbName;
    Label lbEmailReg;
    Label lbPassReg;
    Label lbConfirmPass;
    Label lbPhone;
    Label lbGender;
    
    Label lbTransId;
    TextField tfTransId;
    Button btnRemDetail;
    
    
    Label lbCartCustId;
    TextField tfCartCustId;
    
    Label lbTranId;
    TextField tfTranId;
    Label lbTrCustId;
    TextField tfTrCustId;
    Button tranDetail;
    Button findCustId;
    Button remTrans;
    Button viewTrans;
 
    Label lbProductId;
    Label lbQty;
	Label productIdLabel, qtyLabel, totalPriceLabel;	
 
    Button btLoginReg;
    Button btRegReg;
    Button removeBtn = new Button("Remove From Cart"); 
    Button checkOutBtn = new Button("Check Out");
 
    TextField tfEmailReg;
    TextField tfName;
    PasswordField pfPassReg;
    PasswordField pfConfirmPass;
    TextField tfPhone;
    TextField tfProductId;
    TextField tfCProductID;
    
    TextField tfMoney;
    Label lbMoney;
    
 
    Spinner<Integer> spQty;
	Spinner<Integer> qtySpinner;
 
 
	Button btLogin;
	Button btReg;
	Button addCart;
 
	TextField tfEmail;
	PasswordField pfPass;
 
	RadioButton rbMale;
	RadioButton rbFemale;
 
	ToggleGroup tgGender;
 
	MenuBar mbMain;
	Menu mProduct;
	Menu mAccount;
	MenuItem productList;
	MenuItem cart;
	MenuItem manageProduct;
	MenuItem logOut;
	MenuItem regStaff;
	MenuItem transaction;
 
	String role;
	String userID;
	String email;
	String proID;
	String productID;
	String tranID;
	
	
	int currentQty;
	int currentStock;
	Integer totPrice = 0;
 
	int totalPrice = 0;
	int remQtySp;
	int getStocks = 0;
 
 
	private Connect connect = Connect.getInstance();
 
	public static void main(String[] args) {
		launch(args);
	}
	
	
	//generate StaffID
	public String uID() {
		String uID = "";
		Random rand = new Random();
 
		int digit1 = rand.nextInt(10);
	    int digit2 = rand.nextInt(10);
	    int digit3 = rand.nextInt(10);
	    int digit4 = rand.nextInt(10);
 
	    String unique = "S" + digit1 + digit2 + digit3 + digit4;
	    String query = "SELECT StaffID FROM staff WHERE StaffID = '" + unique + "'";
 
		  connect.rs = connect.execQuery(query);
		  try {
				if(connect.rs.next()) {
					uID();
				} else {
					uID = unique;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
 
			}
		  return uID;
	}
	
	
	//generate TransactionID
	public String trID() {
		String trID = "";
		Random rand = new Random();
 
		int digit1 = rand.nextInt(10);
	    int digit2 = rand.nextInt(10);
	    int digit3 = rand.nextInt(10);
	    int digit4 = rand.nextInt(10);
 
	    String unique = "TR" + digit1 + digit2 + digit3 + digit4;
	    String query = "SELECT TransactionID FROM TransactionHeader WHERE StaffID = '" + unique + "'";
 
		  connect.rs = connect.execQuery(query);
		  try {
				if(connect.rs.next()) {
					trID();
				} else {
					trID = unique;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
 
			}
		  return trID;
	}
	
	
	//generate ProductID
	public String pID() {
 
		String firstLet = "";
 
		if(typeCombo.getValue().equals("Food")) {
			firstLet = "F";
		} else if(typeCombo.getValue().equals("Drink")) {
			firstLet = "D";
		}
 
		String pID = "";
		Random rand = new Random();
 
		int digit1 = rand.nextInt(10);
	    int digit2 = rand.nextInt(10);
	    int digit3 = rand.nextInt(10);
	    int digit4 = rand.nextInt(10);
 
	    String unique = firstLet + digit1 + digit2 + digit3 + digit4;
	    String query = "SELECT ProductID FROM products WHERE ProductID = '" + unique + "'";
 
		  connect.rs = connect.execQuery(query);
		  try {
				if(connect.rs.next()) {
					pID();
				} else {
					pID = unique;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
 
			}
		  return pID;
	}
 
	
	//membuat halaman Login
	public void loginPage() {
		//Login Page
		gridPane.setAlignment(Pos.CENTER);
 
		lbEmail = new Label("Email");
		tfEmail = new TextField();
		tfEmail.setPromptText("Email");
 
		lbPass = new Label("Password");
		pfPass = new PasswordField();
		pfPass.setPromptText("Password");
 
		btLogin = new Button("Login");
		btReg = new Button ("Register");
 
		hBox.getChildren().addAll(btLogin);
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER_RIGHT);
 
		gridPane.add(lbEmail, 0, 0);
		gridPane.add(tfEmail, 0, 1);
		gridPane.add(lbPass, 0, 2);
		gridPane.add(pfPass, 0, 3);
		gridPane.add(hBox, 0, 4);
 
		gridPane.setVgap(10);
 
		spLogin.getChildren().addAll(gridPane);
 
	}
 
	//membuat Halaman Register
	public void registerPage() {
		//Register Page
		gridPaneReg.setAlignment(Pos.CENTER);
 
        lbName = new Label("Name");
        tfName = new TextField();
        tfName.setPrefWidth(500);
        tfName.setPromptText("Name");
 
        lbPassReg = new Label("Password");
        pfPassReg = new PasswordField();
        pfPassReg.setPromptText("Password");
 
        lbConfirmPass = new Label("Confirm Password");
        pfConfirmPass = new PasswordField();
        pfConfirmPass.setPromptText("Confirm Password");
 
        lbEmailReg = new Label("Email");
        tfEmailReg = new TextField();
        tfEmailReg.setPromptText("xxxxxx@gmail.com");
 
        lbPhone = new Label("Phone Number");
        tfPhone = new TextField();
        tfPhone.setPromptText("+62XXXXXXXXXXX");
 
        lbGender = new Label("Gender");
 
    	rbMale = new RadioButton("Male");
		rbFemale = new RadioButton("Female");
		tgGender = new ToggleGroup();
		rbMale.setToggleGroup(tgGender);
		rbFemale.setToggleGroup(tgGender);
		hBoxRad.getChildren().addAll(rbMale, rbFemale);
		hBoxRad.setSpacing(10);
 
        btLoginReg = new Button("Back");
        btRegReg = new Button ("Register");
 
        hBoxReg.getChildren().addAll(btLoginReg, btRegReg);
        hBoxReg.setSpacing(10);
        hBoxReg.setAlignment(Pos.BOTTOM_RIGHT);
 
        gridPaneReg.add(lbName, 0, 1);
        gridPaneReg.add(tfName, 0, 2);
        gridPaneReg.add(lbPassReg, 0, 3);
        gridPaneReg.add(pfPassReg, 0, 4);
        gridPaneReg.add(lbConfirmPass, 0, 5);
        gridPaneReg.add(pfConfirmPass, 0, 6);
        gridPaneReg.add(lbEmailReg, 0, 7);
        gridPaneReg.add(tfEmailReg, 0, 8);
        gridPaneReg.add(lbPhone, 0, 9);
        gridPaneReg.add(tfPhone, 0, 10);
        gridPaneReg.add(lbGender, 0, 11);
        gridPaneReg.add(hBoxRad, 0, 12);
        gridPaneReg.add(hBoxReg, 0, 13);
 
 
        gridPaneReg.setVgap(10);
 
        spRegister.getChildren().addAll(gridPaneReg);
 
	}
	
	//membuat Table Product List
	public void addTableProList() {
 
		TableColumn<Products, String> idColumn = new TableColumn<Products, String>("Product Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("id"));
 
		TableColumn<Products, String> typeColumn = new TableColumn<Products, String>("Product Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("type"));
 
		TableColumn<Products, String> nameColumn = new TableColumn<Products, String>("Product Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
		
		TableColumn<Products, Integer> priceColumn = new TableColumn<Products, Integer>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<Products, Integer>("price"));
		
		productTable.getColumns().addAll(idColumn, typeColumn, nameColumn, priceColumn);
		
		productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		productTable.setPrefHeight(300);
		productTable.setPrefWidth(700);
	}
	
	//membuat Table TransactionDetail
	public void addTableTransDetList() {
		
		TableColumn<TransactionDetail, String> idColumn = new TableColumn<TransactionDetail, String>("Transaction ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<TransactionDetail, String>("transactionID"));
		
		TableColumn<TransactionDetail, String> pIdColumn = new TableColumn<TransactionDetail, String>("Product ID");
		pIdColumn.setCellValueFactory(new PropertyValueFactory<TransactionDetail, String>("productID"));
		
		TableColumn<TransactionDetail, Integer> qColumn = new TableColumn<TransactionDetail, Integer>("Quantity");
		qColumn.setCellValueFactory(new PropertyValueFactory<TransactionDetail, Integer>("quantity"));
		
		TableColumn<TransactionDetail, Integer> totPriceColumn = new TableColumn<TransactionDetail, Integer>("Total Price");
		totPriceColumn.setCellValueFactory(new PropertyValueFactory<TransactionDetail, Integer>("totalPrice"));
		
		tranDetailTable.getColumns().addAll(idColumn, pIdColumn, qColumn, totPriceColumn);
		
		tranDetailTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tranDetailTable.setPrefHeight(300);
		tranDetailTable.setPrefWidth(750);
	}
	
	//membuat Table Cart
	public void addTableCartList() {
 
		TableColumn<Cart, String> idColumn = new TableColumn<Cart, String>("Product ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<Cart, String>("id"));

 
		TableColumn<Cart, Integer> qtyColumn = new TableColumn<Cart, Integer>("Quantity");
		qtyColumn.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("qty"));
 
		TableColumn<Cart, Integer> priceColumn = new TableColumn<Cart, Integer>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("price"));
		
 
		TableColumn<Cart, Integer> totalColumn = new TableColumn<Cart, Integer>("Total");
		totalColumn.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("total"));
 
		cartTable.getColumns().addAll(idColumn, qtyColumn, priceColumn, totalColumn);
		
		cartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		cartTable.setPrefHeight(300);
		cartTable.setPrefWidth(700);
	}
	
	//Membuat Table TransactionHeader
	
	public void addTableTranHeadList() {
		
		TableColumn<TransactionHeader, String> idColumn = new TableColumn<TransactionHeader, String>("Transaction ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<TransactionHeader, String>("transactionID"));
		
		TableColumn<TransactionHeader, String> cIdColumn = new TableColumn<TransactionHeader, String>("Customer ID");
		cIdColumn.setCellValueFactory(new PropertyValueFactory<TransactionHeader, String>("customerID"));
		
		TableColumn<TransactionHeader, String> stIdColumn = new TableColumn<TransactionHeader, String>("Staff ID");
		stIdColumn.setCellValueFactory(new PropertyValueFactory<TransactionHeader, String>("staffID"));
		
		TableColumn<TransactionHeader, String> dateColumn = new TableColumn<TransactionHeader, String>("Transaction Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<TransactionHeader, String>("transactionDate"));
		
		TableColumn<TransactionHeader, Integer> priceColumn = new TableColumn<TransactionHeader, Integer>("Total Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<TransactionHeader, Integer>("totalTransactionPrice"));
		
		tranHeadTable.getColumns().addAll(idColumn, cIdColumn, stIdColumn, dateColumn, priceColumn);
		
		tranHeadTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tranHeadTable.setPrefHeight(300);
		tranHeadTable.setPrefWidth(750);
	}
	
	
	//membuat page TransactionDetail
	public void tranDetailList() {
		addTableTransDetList();
		
		transDetailPane.add(tranDetailTable, 0, 0);
	
		transDetailPane.setVgap(10);
		transDetailPane.setPadding(new Insets(10));
	
		spTranDetail.getChildren().add(transDetailPane);
	}

	//membuat page Transaction
	
	public void tranHeaderList() {
		addTableTranHeadList();
		
		lbTranId = new Label("Transaction ID: ");
	    tfTranId = new TextField();
	    lbTrCustId = new Label("Customer ID:");
	    tfTrCustId = new TextField();
	    tranDetail = new Button("View Detail");
	    remTrans = new Button("Remove Transaction");
	    findCustId = new Button("Find Customer Transaction");
	    viewTrans = new Button("View All Transaction");
	    
	    transHeadPane.add(tranHeadTable, 0, 0);
//	    transHeadPane.add(lbTranId, 0, 1);
//	    transHeadPane.add(tfTranId, 0, 2);
	    transHeadPane.add(lbTrCustId, 0, 1);
	    transHeadPane.add(tfTrCustId, 0, 2);
	    transHeadPane.add(findCustId, 1, 2);
	    transHeadPane.add(tranDetail, 1, 3);
	    transHeadPane.add(remTrans, 1, 4);
	    
	    findCustId.setTranslateX(-400);
	    tranDetail.setTranslateX(-400);
	    remTrans.setTranslateX(-400);
	    viewTrans.setTranslateX(-400);
	    
	    tfTranId.setMaxWidth(300);
	    tfTrCustId.setMaxWidth(300);
	    tranDetail.setPrefWidth(300);
	    remTrans.setPrefWidth(300);
	    findCustId.setPrefWidth(300);
	    viewTrans.setPrefWidth(300);
	    
	    transHeadPane.setVgap(10);
	    transHeadPane.setPadding(new Insets(10));
	    spTranHead.getChildren().addAll(transHeadPane);		
	}
	
	
	//membuat halaman cart
	public void cartList () {
		formPane = new GridPane();
		scrollPane = new ScrollPane();
		totalPriceLabel = new Label("Total Price: " + totalPrice );
		productIdLabel = new Label("Product ID");
		qtyLabel = new Label("Qty");
		
		lbCartCustId = new Label("Customer ID");
		tfCartCustId = new TextField();
		tfCartCustId.setMaxWidth(150);
		
//		
//		tfCProductID = new TextField();
//		tfCProductID.setMaxWidth(150);
 
		qtySpinner = new Spinner<>();
		qtySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999));
		qtySpinner.setMaxWidth(170);
		
		tfMoney = new TextField();
		lbMoney = new Label("Customer Money: ");
		tfMoney.setMaxWidth(150);
		
		formPane.add(cartTable, 0, 0);
		formPane.add(totalPriceLabel, 0, 1);
//		formPane.add(productIdLabel, 0, 2);
//		formPane.add(tfCProductID, 0, 3);
 
 
		formPane.add(qtyLabel, 0, 2);
		formPane.add(qtySpinner, 0, 3);
		
		formPane.add(lbCartCustId, 0, 4);
		formPane.add(tfCartCustId, 0, 5);
		
		formPane.add(lbMoney, 0, 6);
		formPane.add(tfMoney, 0, 7);
		
		formPane.add(removeBtn, 0, 8);
		formPane.add(checkOutBtn, 0, 9);	
 
		formPane.setPadding(new Insets (10));
		formPane.setHgap(10); 
		formPane.setVgap(10); 
 
		borderPane.setTop(formPane);
		scrollPane.setContent(borderPane);
		scrollPane.setFitToWidth(true);
 
		sCartList.getChildren().addAll(scrollPane);
 
	}
	
	//membuat halaman manage
 
	public void manageList() {
 
		VBox vBoxPrice = new VBox();
 
		HBox hBoxManage = new HBox();
 
 
		manageBorderPane = new BorderPane();
		manageFormPane = new GridPane();
		manageScrollPane = new ScrollPane();
 
		manageProductIDLabel = new Label("Product ID");
		manageNameLabel = new Label("Product Name");
		manageTypeLabel = new Label("Type");
		managePriceLabel = new Label("Price");
		manageStockLabel = new Label("Stock");
 
		manageProductIDField = new TextField();
		manageNameField = new TextField();
		manageTypeField = new TextField();
		managePriceField = new TextField();
		manageStockField = new TextField();
 
		manageProductIDField.setMaxWidth(400);
		manageNameField.setMaxWidth(400);
 
		typeCombo = new ComboBox<>();
		typeCombo.getItems().addAll("Food", "Drink");
 
		priceSpinner = new Spinner<>(10000, 100000, 10000, 1000);
		
		priceSpinner.setEditable(true);
 
		manageFormPane.add(productTable, 0, 0);
 
//		manageFormPane.add(manageProductIDLabel, 0, 1);
//		manageFormPane.add(manageProductIDField, 0, 2);
//		
		manageFormPane.add(manageNameLabel, 0, 1);
		manageFormPane.add(manageNameField, 0, 2);
 
		manageFormPane.add(manageTypeLabel, 0, 3);
		manageFormPane.add(typeCombo, 0, 4);
 
 
		vBoxPrice.getChildren().addAll(managePriceLabel, priceSpinner);
 
		hBoxManage.getChildren().addAll(vBoxPrice);
 
		hBoxManage.setSpacing(10);
 
		manageFormPane.add(hBoxManage, 0, 7);
 
		insertBtn.setTranslateX(-200);
		updateBtn.setTranslateX(-200);
		manageRemoveBtn.setTranslateX(-200);
		insertBtn.setTranslateY(10);
		updateBtn.setTranslateY(10);
		manageRemoveBtn.setTranslateY(10);
 
		insertBtn.setPrefWidth(200);
		updateBtn.setPrefWidth(200);
		manageRemoveBtn.setPrefWidth(200);
 
		manageFormPane.add(insertBtn, 1, 1);
		manageFormPane.add(updateBtn, 1, 3);
		manageFormPane.add(manageRemoveBtn, 1, 5);
 
		manageFormPane.setPadding(new Insets(10));
		manageFormPane.setHgap(10);
 
		manageBorderPane.setBottom(manageFormPane);
		manageScrollPane.setContent(manageBorderPane);
		spManage.getChildren().addAll(manageScrollPane);
 
	}
	
	//membuat halaman product
 
	public void productList () {
		addTableProList();
 
		lbQty = new Label("Qty");
 
		spQty = new Spinner<>();
		spQty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999));
		spQty.setEditable(true);
		spQty.setMaxWidth(170);
 
		addCart = new Button("Add to Cart");
 
		gpProductList.add(productTable,0,0);
		gpProductList.add(lbQty, 0, 1);
		gpProductList.add(spQty, 0, 2);
		gpProductList.add(addCart, 0, 3);
 
		gpProductList.setVgap(10);
 
		gpProductList.setPadding(new Insets(10));
		spProductList.getChildren().addAll(gpProductList);
	}
	
	
	//menghitung total price
	public void getTotPrice() {
 
	    for (int i = 0; i < cartTable.getItems().size(); i++) {
 
	        int itemTotalPrice = cartTable.getItems().get(i).getTotal();
 
	        totalPrice += itemTotalPrice;
	    }
 
	}
 
	//mengambil data product dari database
	
	public void viewProduct() {
		String query = "SELECT * FROM products";
		connect.rs = connect.execQuery(query);
 
		try {
			while(connect.rs.next()) {
				String id = connect.rs.getString("ProductID");
				String type = connect.rs.getString("ProductType");
				String name = connect.rs.getString("ProductName");
				Integer price = connect.rs.getInt("ProductPrice");
				productTable.getItems().addAll(new Products(id, type, name, price));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//mengambil data cart dari database
 
	public void viewCart() {
		email = tfEmail.getText();
 
		String queryCart = "SELECT * FROM products JOIN carts JOIN staff ON products.ProductID = carts.ProductID AND carts.StaffID = staff.StaffID WHERE staff.StaffEmail = '" + email + "'";
		connect.rs = connect.execQuery(queryCart);
 
		try {
		    while (connect.rs.next()) {
		        String id = connect.rs.getString("ProductID");
		        Integer qty = connect.rs.getInt("Quantity");
		        Integer price = connect.rs.getInt("ProductPrice");
		        totPrice = price * qty;
 
		        Cart cart = new Cart(id, qty, price, totPrice);
 
		        cartTable.getItems().addAll(cart);
 
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	//mengambil data transaction dari database
	public void viewTransHead() {
		String queryTransaction = "SELECT * FROM TransactionHeader";
		connect.rs = connect.execQuery(queryTransaction);
		
		
		try {
			while(connect.rs.next()) {
				String transid = connect.rs.getString("TransactionID");
				String custid = connect.rs.getString("CustomerID");
				String staffid = connect.rs.getString("StaffID");
				String transactionDate = connect.rs.getString("TransactionDate");
				Integer totalTransaction = connect.rs.getInt("TotalTransaction");
				
				TransactionHeader tHeader = new TransactionHeader(transid, custid, staffid, transactionDate, totalTransaction);
				
				tranHeadTable.getItems().addAll(tHeader);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 
	
	//mengambil data transactiondetail dari database
	public void viewDetailHead() {
		String transid = tranHeadTable.getSelectionModel().getSelectedItem().getTransactionID();
		
		String queryTransDetail = "SELECT * FROM TransactionDetail WHERE TransactionID = '" + transid + "'";
		connect.rs = connect.execQuery(queryTransDetail);
		
		try {
			while(connect.rs.next()) {
				String transactionid = connect.rs.getString("TransactionID");
				String productid = connect.rs.getString("ProductID");
				Integer quantity = connect.rs.getInt("Quantity");
				Integer totalPrice = connect.rs.getInt("TotalPrice");
				
				TransactionDetail tDetail = new TransactionDetail(transactionid, productid, quantity, totalPrice);
				tranDetailTable.getItems().addAll(tDetail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//mengambil data transaction customer dari database
	public void viewHeadCustID() {
		String transsid = tfTrCustId.getText();
		
		String queryTransDetail = "SELECT * FROM TransactionHeader WHERE CustomerID = '" + transsid + "'";
		connect.rs = connect.execQuery(queryTransDetail);
		
		try {
			while(connect.rs.next()) {
				String transid = connect.rs.getString("TransactionID");
				String custid = connect.rs.getString("CustomerID");
				String staffid = connect.rs.getString("StaffID");
				String transactionDate = connect.rs.getString("TransactionDate");
				Integer totalTransaction = connect.rs.getInt("TotalTransaction");
				
				TransactionHeader tHeader = new TransactionHeader(transid, custid, staffid, transactionDate, totalTransaction);
				
				tranHeadTable.getItems().addAll(tHeader);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primary) throws Exception {
 
//		viewProduct();
 
//		manageList();
		
		tranHeaderList();
		
		loginPage();
		registerPage();
		productList();
 
		tranDetailList();
		
		addTableCartList();
//		addTableTransDetList();
 
 
		//mainPage
		mProduct = new Menu("Product");
		mAccount = new Menu("Account");
		productList = new MenuItem("Product List");
		cart = new MenuItem("Cart");
		manageProduct = new MenuItem("Manage Product");
		logOut = new MenuItem("Log Out");
		regStaff = new MenuItem("Register a Staff");
		transaction = new MenuItem("Transaction");
		
		//productList
 
 
        //button function
		regStaff.setOnAction(e -> {
			primary.setScene(sceneRegister);
			primary.setTitle("Register Page");
		});
 
		btLogin.setOnAction(e -> {
			email = tfEmail.getText();
			String password = pfPass.getText();
			String query = "SELECT * FROM staff WHERE StaffEmail = '" + email + "'" +"AND StaffPassword = '" + password +"'";
		    connect.rs = connect.execQuery(query);
		    try {
				if(connect.rs.next()) {
 
					email = tfEmail.getText();
 
					String queryMenu = "SELECT StaffRole FROM staff WHERE StaffEmail = '" + email + "'";
 
					ResultSet getRole = connect.execQuery(queryMenu);
					try {
						getRole.next();
						String role = getRole.getString("StaffRole");
						if(role.equals("admin")) {
							mProduct.getItems().addAll(manageProduct,  transaction);
							mAccount.getItems().addAll(regStaff, logOut);
						} else if (role.equals("user")) {
							mProduct.getItems().addAll(productList, cart);
							mAccount.getItems().addAll(logOut);
						}					
						mbMain = new MenuBar(mProduct, mAccount);
						mbMain.setPrefWidth(1200);
						gpMainMenu.add(mbMain,0,0);
						spMainMenu.setAlignment(Pos.CENTER);
						spMainMenu.getChildren().addAll(gpMainMenu);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
										
					primary.setScene(sceneMainMenu);
					primary.setTitle("Main Menu");
				} else {
					System.out.println("Login Failed");
					String queryCheckEmail = "SELECT * FROM staff WHERE StaffEmail = '" + email + "'";
					connect.rs = connect.execQuery(queryCheckEmail);
					if(connect.rs.next()) {
						Alert alert = new Alert(AlertType.WARNING, "Password doesn't match!", ButtonType.OK);
					    alert.showAndWait();
					}else {
						Alert alert = new Alert(AlertType.WARNING, "Email doesn't exist!", ButtonType.OK);
					    alert.showAndWait();
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btLoginReg.setOnAction(e -> {
			primary.setScene(sceneMainMenu);
			primary.setTitle("Main Menu");
		});
 
		btRegReg.setOnAction(e -> { 
			System.out.println("Register");
			String name = tfName.getText();
			String pass = pfPassReg.getText();
			String passConf = pfConfirmPass.getText();
			String email = tfEmailReg.getText();
			String phone = tfPhone.getText();
			String gender = null;
			String role = "user";
			String id = uID();
			//Radio Button
			try {
				RadioButton rbGender = (RadioButton) tgGender.getSelectedToggle();
				gender = rbGender.getText();
			} catch (Exception e1) {
				Alert alert = new Alert(AlertType.WARNING, "Gender must be choosed.", ButtonType.OK);
			    alert.showAndWait();
 
			}
 
			String query = "INSERT INTO staff (StaffID, StaffName, StaffRole, StaffPhone, StaffEmail, StaffPassword, StaffGender) VALUES ('" + id + "', '" + name + "', '" + role + "', '" + phone + "', '" + email + "', '" + pass + "', '" + gender + "')";
 
			String checkEmail = "SELECT StaffEmail FROM staff WHERE StaffEmail = '" + email + "'";
			
			connect.rs = connect.execQuery(checkEmail);
 
			try {
				if(name.length() < 5 || name.length() > 20) {
					Alert alert = new Alert(AlertType.WARNING, "Name must be between 5-20 characters", ButtonType.OK);
				    alert.showAndWait();
				} else if(pass.length() < 8 && !pass.matches("^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]*$")){
					Alert alert = new Alert(AlertType.WARNING, "Password must contain minimal 8 characters and alphanumeric", ButtonType.OK);
				    alert.showAndWait();
				} else if(!passConf.equals(pass)) {
					Alert alert = new Alert(AlertType.WARNING, "Confirm Password must same as Password", ButtonType.OK);
				    alert.showAndWait();
				} else if (!email.endsWith("@gmail.com") || !email.matches("^[^@]+@[^@]+$") || connect.rs.next()) {
					Alert alert = new Alert(AlertType.WARNING, "Email must end with ‘@gmail.com’, contains 1 ‘@’ and not in front, and unique.", ButtonType.OK);
				    alert.showAndWait();	
				}else if(phone.length() < 10 || !phone.matches("^\\+62\\d+$") ) {
					Alert alert = new Alert(AlertType.WARNING, "Phone Number must contain at least 10 characters, numeric, and starts with ‘+62’", ButtonType.OK);
				    alert.showAndWait();
				} else {
					connect.executeUpdate(query);
					Alert alert = new Alert(AlertType.INFORMATION, "Account Has Been Succesfully Registered.", ButtonType.OK);
					alert.setHeaderText("Register Success!");
				    alert.showAndWait();
				    primary.setScene(sceneMainMenu);
				    primary.setTitle("Main Menu");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
 
		//log out
		logOut.setOnAction(e -> {
			primary.close();
			try {
				new Main().start(new Stage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
 
 
		manageProduct.setOnAction(e -> {
			productTable.getItems().clear();
 
			viewProduct();
 
			manageList();
 
			winManageList.setPrefHeight(750);
			winManageList.setPrefWidth(1200);
			winManageList.setTitle("Manage Product");
			winManageList.setContentPane(spManage);
 
			if (gpMainMenu.getChildren().contains(winTransList)) {
				gpMainMenu.getChildren().remove(winTransList);
 
			}
			
			if(gpMainMenu.getChildren().contains(winTransDetailList)) {
				gpMainMenu.getChildren().remove(winTransDetailList);
			}
 
			if (!gpMainMenu.getChildren().contains(winManageList)) {
				gpMainMenu.add(winManageList, 0, 1);
			} else {
				gpMainMenu.getChildren().remove(winManageList);
 
			}
		});
		
 
		productList.setOnAction(e -> {
			productTable.getItems().clear();
 
			viewProduct();
 
			winProductList.setPrefHeight(750);
			winProductList.setPrefWidth(1200);
			winProductList.setTitle("Product List");
			winProductList.setContentPane(spProductList);
 
			if (gpMainMenu.getChildren().contains(winCartList)) {
				gpMainMenu.getChildren().remove(winCartList);
 
			}
 
			if (!gpMainMenu.getChildren().contains(winProductList)) {
				gpMainMenu.add(winProductList, 0, 1);
			} else {
				gpMainMenu.getChildren().remove(winProductList);
 
			}
		});
 
 
		cart.setOnAction(e -> {
 
			cartTable.getItems().clear();
 
			viewCart();
 
			totalPrice = 0;
 
			getTotPrice();
 
			cartList();
 
			winCartList.setPrefHeight(750);
			winCartList.setPrefWidth(1200);
			winCartList.setTitle("Cart");
			winCartList.setContentPane(sCartList);
 
			if (gpMainMenu.getChildren().contains(winProductList)) {
				gpMainMenu.getChildren().remove(winProductList);
			}
 
 
			if (!gpMainMenu.getChildren().contains(winCartList)) {
				gpMainMenu.add(winCartList, 0, 1);
			} else {
				gpMainMenu.getChildren().remove(winCartList);
			}
		});
 
		addCart.setOnAction(e -> {
			String email = tfEmail.getText();
			
			String productId = productTable.getSelectionModel().getSelectedItem().getId();
			
//			String productId = tfProductId.getText();
			int qtyCart = spQty.getValue();
 
			String query1 = "SELECT StaffID FROM staff WHERE StaffEmail = '" + email + "'";
			ResultSet getUserID = connect.execQuery(query1);
			try {
				getUserID.next();
				 userID = getUserID.getString("StaffID");
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
 
			String queryQty = "SELECT Quantity FROM carts WHERE StaffID = '" + userID + "'AND ProductID ='" + productId + "'";
			ResultSet getQty = connect.execQuery(queryQty);
			try {
				if(getQty.next()) {
					currentQty = getQty.getInt("Quantity");
				} else {
					currentQty = 0;
				}
			} catch (SQLException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
 
 
			String query = "SELECT ProductID FROM products WHERE ProductID = '" + productId + "'";
 
			connect.rs = connect.execQuery(query);
 
			int totalQty = currentQty + qtyCart;
 
			try {
				if(connect.rs.next()) {
 
					String query2 = "SELECT StaffID, ProductID FROM carts WHERE StaffID = '" + userID + "'AND ProductID ='" + productId + "'"; 
					connect.rs = connect.execQuery(query2);
 
					if(connect.rs.next()) {
						String queryUpdate = "UPDATE carts SET Quantity = '" + totalQty + "' WHERE StaffID = '" + userID + "' AND ProductID = '" + productId + "'";
						connect.executeUpdate(queryUpdate);
						Alert alert = new Alert(AlertType.INFORMATION, "Item Succesfully Added to Cart.", ButtonType.OK);
						alert.setHeaderText("Add to Cart Success!");
					    alert.showAndWait();
					} else {
						String queryInsert = "INSERT INTO carts (StaffID, ProductID, Quantity) VALUES ('" + userID + "', '" + productId + "', '" + qtyCart + "')";
						connect.executeUpdate(queryInsert);
						Alert alert = new Alert(AlertType.INFORMATION, "Item Succesfully Added to Cart.", ButtonType.OK);
						alert.setHeaderText("Add to Cart Success!");
					    alert.showAndWait();
					}		
				} else {
					Alert alert = new Alert(AlertType.ERROR, "Item not Found!", ButtonType.OK);
				    alert.showAndWait();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
 
		removeBtn.setOnAction(e -> {
			String email = tfEmail.getText();
 
			String productId = cartTable.getSelectionModel().getSelectedItem().getId();
 
			String query1 = "SELECT StaffID FROM staff WHERE StaffEmail = '" + email + "'";
			ResultSet getUserID = connect.execQuery(query1);
			try {
				getUserID.next();
				 userID = getUserID.getString("StaffID");
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
 
			String queryQty = "SELECT Quantity FROM carts WHERE StaffID = '" + userID + "'AND ProductID ='" + productId + "'";
			ResultSet getQty = connect.execQuery(queryQty);
			try {
				if(getQty.next()) {
					currentQty = getQty.getInt("Quantity");
				} else {
					currentQty = 0;
				}
			} catch (SQLException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
 
			String query = "SELECT ProductID FROM carts WHERE ProductID = '" + productId + "'";
 
			connect.rs = connect.execQuery(query);
 
			int remQty = qtySpinner.getValue();
 
			int remainQty = currentQty - remQty;
 
			try {
				if(connect.rs.next()) {
					String query2 = "SELECT StaffID, ProductID FROM carts WHERE StaffID = '" + userID + "'AND ProductID ='" + productId + "'"; 
					connect.rs = connect.execQuery(query2);
					if(connect.rs.next()) {
						if(remQty == currentQty) {
							String queryUpdate = "DELETE FROM carts WHERE StaffID = '" + userID + "' AND ProductID = '" + productId + "'";
							connect.executeUpdate(queryUpdate);
							Alert alert = new Alert(AlertType.INFORMATION, "Item Succesfully Removed From Cart.", ButtonType.OK);
							alert.setHeaderText("Remove From Cart Success!");
						    alert.showAndWait();
							cart.fire();
							cart.fire();	
						}else if(remainQty < currentQty){
							Alert alert = new Alert(AlertType.WARNING, "Item is not enough!", ButtonType.OK);
							alert.setHeaderText("Remove From Cart Failed!");
						    alert.showAndWait();
						}else {
							String queryUpdate = "UPDATE carts SET Quantity = '" + remainQty + "' WHERE StaffID = '" + userID + "' AND ProductID = '" + productId + "'";
							connect.executeUpdate(queryUpdate);
							Alert alert = new Alert(AlertType.INFORMATION, "Item Succesfully Removed From Cart.", ButtonType.OK);
							alert.setHeaderText("Remove From Cart Success!");
						    alert.showAndWait();
							cart.fire();
							cart.fire();
						}	
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR, "Item not Found!", ButtonType.OK);
				    alert.showAndWait();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		});
 
		checkOutBtn.setOnAction(e -> {
			
			int money = 0;
			try {
				money = Integer.parseInt(tfMoney.getText());
			} catch (NumberFormatException e3) {
				// TODO Auto-generated catch block
				Alert alert = new Alert(AlertType.WARNING, "Input amount of money!", ButtonType.OK);
				alert.setHeaderText("Warning!");
			    alert.showAndWait();
			}
			
			String cID = tfCartCustId.getText();
			String email = tfEmail.getText();
			String trID = trID();
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String date = sdf.format(new Date());
 
			String query1 = "SELECT StaffID FROM staff WHERE StaffEmail = '" + email + "'";
			ResultSet getUserID = connect.execQuery(query1);
			try {
				getUserID.next();
				 userID = getUserID.getString("StaffID");
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
 
			String query = "SELECT * FROM carts WHERE StaffID = '" + userID + "'";
 
			connect.rs = connect.execQuery(query);
 
			try {
 
				if(connect.rs.next()) {
					
					String checkCustomer = "SELECT * FROM customer WHERE CustomerID = '" + cID + "'";
					
					connect.rs = connect.execQuery(checkCustomer);
					
					if(connect.rs.next()) {					
						if(totalPrice == money) {
							Alert alert = new Alert(AlertType.INFORMATION, "Check Out Success! No change needed!", ButtonType.OK);
							alert.setHeaderText("Check Out Success!");
						    alert.showAndWait();
						    String queryDelete = "DELETE FROM carts WHERE StaffID = '" + userID + "'";
							connect.executeUpdate(queryDelete);	
							
							 String insertTrans = "INSERT INTO TransactionHeader (TransactionID, CustomerID, StaffID, TransactionDate, TotalTransaction) VALUES ('" + trID +"', '" + cID + "', '" + userID + "', '" + date + "', '" + totalPrice + "')";
							    
							    connect.rs = connect.executeUpdate(insertTrans);
							    
							    for(int i = 0; i < cartTable.getItems().size();  i++) {
									System.out.println(cartTable.getItems().size());
									
									String productID = cartTable.getItems().get(i).getId();
									Integer quantity = cartTable.getItems().get(i).getQty();
									Integer price = cartTable.getItems().get(i).getPrice();
							        totPrice = price * quantity;
							        System.out.println(productID);	
							        
							    	String insertTrDetail = "INSERT INTO TransactionDetail (TransactionID, ProductID, Quantity, TotalPrice) VALUES ('" + trID + "', '" + productID + "', '" + quantity + "', '" + totPrice + "')";
							    	connect.rs = connect.executeUpdate(insertTrDetail);
							    }
							    
							    cart.fire();
								cart.fire();
							
						} else if (money > totalPrice) {
							Alert alert = new Alert(AlertType.INFORMATION, "Check Out Success! Amount of change is " + (money - totalPrice) , ButtonType.OK);
							alert.setHeaderText("Check Out Success!");
						    alert.showAndWait();
						    String queryDelete = "DELETE FROM carts WHERE StaffID = '" + userID + "'";
							connect.executeUpdate(queryDelete);	
							
							 String insertTrans = "INSERT INTO TransactionHeader (TransactionID, CustomerID, StaffID, TransactionDate, TotalTransaction) VALUES ('" + trID +"', '" + cID + "', '" + userID + "', '" + date + "', '" + totalPrice + "')";
							    
							    connect.rs = connect.executeUpdate(insertTrans);
							    
							    for(int i = 0; i < cartTable.getItems().size();  i++) {
									System.out.println(cartTable.getItems().size());
									
									String productID = cartTable.getItems().get(i).getId();
									Integer quantity = cartTable.getItems().get(i).getQty();
									Integer price = cartTable.getItems().get(i).getPrice();
							        totPrice = price * quantity;
							        System.out.println(productID);	
							        
							    	String insertTrDetail = "INSERT INTO TransactionDetail (TransactionID, ProductID, Quantity, TotalPrice) VALUES ('" + trID + "', '" + productID + "', '" + quantity + "', '" + totPrice + "')";
							    	connect.rs = connect.executeUpdate(insertTrDetail);
							    }
							    
							    cart.fire();
								cart.fire();
							
						} else if (totalPrice > money) {
							Alert alert = new Alert(AlertType.WARNING, "The amount of money is not sufficient!", ButtonType.OK);
							alert.setHeaderText("Warning!");
						    alert.showAndWait();
						}    
						
					} else {
						Alert alert = new Alert(AlertType.WARNING, "Customer ID Not Found!", ButtonType.OK);
						alert.setHeaderText("Wrong Customer ID!");
					    alert.showAndWait();
					}
					
				}else {
					Alert alert = new Alert(AlertType.WARNING, "Cart is Empty!", ButtonType.OK);
					alert.setHeaderText("Cart is Empty!");
				    alert.showAndWait();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 
		});
 
		manageRemoveBtn.setOnAction(e -> {
 
			productID = productTable.getSelectionModel().getSelectedItem().getId();
			
			String query = "SELECT ProductID FROM products WHERE ProductID = '" + productID + "'";
 
			connect.rs = connect.execQuery(query);
 
			try {
				if(connect.rs.next()) {
					String queryUpdate = "DELETE FROM products WHERE ProductID = '" + productID + "'";
					connect.executeUpdate(queryUpdate);
					Alert alert = new Alert(AlertType.INFORMATION, "Product has been removed!", ButtonType.OK);
					alert.showAndWait();
					manageProduct.fire();
					manageProduct.fire();
				} else {
					Alert alert = new Alert(AlertType.ERROR, "Product not Found!", ButtonType.OK);
				    alert.showAndWait();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		});
 
		insertBtn.setOnAction(e -> {
			String id = pID();
			String type = typeCombo.getValue();
			String name = manageNameField.getText();
			Integer price = priceSpinner.getValue();

			String query = "INSERT INTO products (ProductID, ProductType, ProductName, ProductPrice) VALUES ('" + id + "', '" + type + "', '" + name + "', '" + price + "')"; 
 
			if(name.length() < 5 || name.length() > 20) {
				Alert alert = new Alert(AlertType.WARNING, "Product name must be between 5-20 characters", ButtonType.OK);
			    alert.showAndWait();
			} else if(price < 10000 || price > 100000) {
				Alert alert = new Alert(AlertType.WARNING, "Price must be between 10000-100000", ButtonType.OK);
			    alert.showAndWait();
			} else {
				connect.executeUpdate(query);
				Alert alert = new Alert(AlertType.INFORMATION, "Product Has Been Succesfully Inserted!", ButtonType.OK);
				alert.setHeaderText("Insert Success!");
			    alert.showAndWait();
			    manageProduct.fire();
				manageProduct.fire();
			}
		});
 
		updateBtn.setOnAction(e -> {
			
			Integer price = priceSpinner.getValue();
			
			productID = productTable.getSelectionModel().getSelectedItem().getId();
			
			String query = "SELECT ProductID FROM products WHERE ProductID = '" + productID + "'";
 
			connect.rs = connect.execQuery(query);
 
			try {
				if(connect.rs.next()) {
					String queryUpdate = "UPDATE products SET ProductPrice = '" + price + "' WHERE ProductID = '" + productID + "'";
					connect.executeUpdate(queryUpdate);
					Alert alert = new Alert(AlertType.INFORMATION, "Product has been updated!", ButtonType.OK);
					alert.showAndWait();
					manageProduct.fire();
					manageProduct.fire();
				} else {
					Alert alert = new Alert(AlertType.ERROR, "Product not Found!", ButtonType.OK);
				    alert.showAndWait();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		});
		
		transaction.setOnAction(e -> {
			tranHeadTable.getItems().clear();
			viewTransHead();
			
			winTransList.setPrefHeight(750);
			winTransList.setPrefWidth(1200);
			winTransList.setTitle("Transaction");
			winTransList.setContentPane(spTranHead);
 
			if (gpMainMenu.getChildren().contains(winManageList)) {
				gpMainMenu.getChildren().remove(winManageList);
			}
			
			if(gpMainMenu.getChildren().contains(winTransDetailList)) {
				gpMainMenu.getChildren().remove(winTransDetailList);
			}
 
			if (!gpMainMenu.getChildren().contains(winTransList)) {
				gpMainMenu.add(winTransList, 0, 1);
			} else {
				gpMainMenu.getChildren().remove(winTransList);
 
			}
		});
		
		tranDetail.setOnAction(e -> {
			
			String tranId = tranHeadTable.getSelectionModel().getSelectedItem().getTransactionID();
			
			String selectTrID = "SELECT TransactionID FROM TransactionDetail WHERE TransactionID = '" + tranId + "'";
			connect.rs = connect.execQuery(selectTrID);
			
			try {
				if(connect.rs.next()) {
					tranDetailTable.getItems().clear();
					viewDetailHead();
					
					winTransDetailList.setPrefHeight(750);
					winTransDetailList.setPrefWidth(1200);
					winTransDetailList.setTitle("Transaction Detail");
					winTransDetailList.setContentPane(spTranDetail);
 
					if (gpMainMenu.getChildren().contains(winTransList)) {
						gpMainMenu.getChildren().remove(winTransList);
					}
 
					if (!gpMainMenu.getChildren().contains(winTransDetailList)) {
						gpMainMenu.add(winTransDetailList, 0, 1);
					} else {
						gpMainMenu.getChildren().remove(winTransDetailList);
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR, "Transaction ID Not Found!", ButtonType.OK);
				    alert.showAndWait();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		});
		
		
		findCustId.setOnAction(e -> {
			
			String getCustID = tfTrCustId.getText();
			
			String selectTrID = "SELECT TransactionID FROM TransactionHeader WHERE CustomerID = '" + getCustID + "'";
			connect.rs = connect.execQuery(selectTrID);
			
			try {
				if(tfTrCustId.getText().isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR, "Fill The CustomerID Field", ButtonType.OK);
				    alert.showAndWait();
				}else if(connect.rs.next()) {
					tranHeadTable.getItems().clear();
					viewHeadCustID();
					transHeadPane.add(viewTrans, 1, 5);
				} else {
					Alert alert = new Alert(AlertType.ERROR, "Customer ID Not Found!", ButtonType.OK);
				    alert.showAndWait();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		viewTrans.setOnAction(e -> {
			tranHeadTable.getItems().clear();
			viewTransHead();
			
			transHeadPane.getChildren().remove(viewTrans);
		});
		
		remTrans.setOnAction(e -> {
			
			String tranId = tranHeadTable.getSelectionModel().getSelectedItem().getTransactionID();
			
			
			String selectTrID = "SELECT TransactionID FROM TransactionHeader WHERE TransactionID = '" + tranId + "'";
			connect.rs = connect.execQuery(selectTrID);
			
			try {
				if(connect.rs.next()) {
					String delete = "DELETE FROM TransactionHeader WHERE TransactionId = '" + tranId + "'";
					String deleteDetail = "DELETE FROM TransactionDetail WHERE TransactionId = '" + tranId + "'";		
					connect.executeUpdate(delete);
					connect.executeUpdate(deleteDetail);
					transaction.fire();
					transaction.fire();
				} else {
					Alert alert = new Alert(AlertType.ERROR, "Transaction ID Not Found!", ButtonType.OK);
				    alert.showAndWait();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		
		//show
		primary.getIcons().add(new Image("https://i.ibb.co/B6wXb8d/logo.png"));
		primary.setScene(sceneLogin);
		primary.setTitle("Login Page");
		primary.show();
	}
 
}