package com.yyp.java;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.lang.Thread.sleep;

public class mainForm {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel leftTitlePanel;
    private JPanel leftMainPanel;
    private JLabel leftTitleLabel;
    private JPanel rightTitlePanel;
    private JPanel rightMainPanel;
    private JLabel rightTitleLabel;
    private JPanel menuPanel;
    private JPanel userOpPanel;
    private JScrollPane menuScrollPane;
    private JPanel userOpUpPanel;
    private JPanel userOpDownPanel;
    private JPanel cartControlPanel;
    private JPanel orderListPanel;
    private JButton increaseDishBtn;
    private JButton submitCartBtn;
    private JButton decreaseDishBtn;
    private JPanel cartPanel;
    private JScrollPane cartScrollPane;
    private JTable tableCart;
    private JPanel rightTopPanel;
    private JPanel rightDownPanel;
    private JPanel dishEditPanel;
    private JPanel dashBoardPanel;
    private JPanel dishEditControlPanel;
    private JPanel dishEditAttributesPanel;
    private JButton conferDishButton;
    private JButton newDishBtn;
    private JButton editDishBtn;
    private JPanel labelPanel;
    private JPanel inputTextPanel;
    private JLabel dishIDLable;
    private JLabel dishNameLable;
    private JLabel dishPriceLable;
    private JLabel dishCostLable;
    private JTextField dishIDTextField;
    private JTextField dishCostTextField;
    private JTextField dishNameTextField;
    private JTextField dishPriceTextField;
    private JTable menuTable;
    private JPanel selectPicPanel;
    private JPanel selectBtnPanel;
    private JButton selectButton;
    private JPanel picReviewPanel;
    private JLabel picReviewLabel;
    private JPanel menuControlPanel;
    private JButton increaseCartBtn;
    private JButton decreaseCartBtn;
    private JTextField orderTipsText;
    private JTextField orderQuantityText;
    private JTextField orderPriceText;
    private JTextField orderIDText;
    private JButton submitOrderButton;
    private JButton submitOrderButton1;
    private JButton removeOrderButton1;
    private JTable orderQueueTable;
    private JTextField orderQueueIfmTextField;
    private JTable orderListTable;
    private JTextField totalRevenueTextField;
    private JTextField totalOrderTextField;
    private JTextField totalCostTextField;
    private JTextField totalTipsTextField;
    private JTextField totalProfitTextField;
    private JButton lilySRestaurantClosedButton;


    private static String openDialogAndSelectFile(Component parent) {
        // 创建一个默认的文件选取器
        JFileChooser fileChooser = new JFileChooser();

        // 设置默认显示的文件夹为当前文件夹
        fileChooser.setCurrentDirectory(new File("./img/"));

        // 设置文件选择的模式（只选文件、只选文件夹、文件和文件均可选）
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        // 设置是否允许多选
        fileChooser.setMultiSelectionEnabled(true);

        // 添加可用的文件过滤器（FileNameExtensionFilter 的第一个参数是描述, 后面是需要过滤的文件扩展名 可变参数）
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));
        // 设置默认使用的文件过滤器
        fileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));

        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            // 如果点击了"确定", 则获取选择的文件路径
            File file = fileChooser.getSelectedFile();

            // 如果允许选择多个文件, 则通过下面方法获取选择的所有文件
            // File[] files = fileChooser.getSelectedFiles();
            System.out.println(file.getAbsolutePath());
            return file.getAbsolutePath();
        }
        return "";
    }

    public class myTableCellRenderer implements TableCellRenderer{
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
            return (Component)value;
        }
    }

    public String getSelectedDishId(JTable table) {
        int rowIndex = table.getSelectedRow();
        if (rowIndex != -1) {
            return table.getModel().getValueAt(rowIndex, 0).toString();
        }
        return "";
    }
    public static String[] ReadSelectedRow(JTable table, int colNum) {
        String [] results = new String[colNum];
        int row = table.getSelectedRow();
        if (row == -1) {
            return results;
        }
        for (int column = 0; column < colNum; column++) {
            results[column] =  table.getModel().getValueAt(row, column).toString();
        }
        return results;
    }

    public int findRow(JTable table, String id){
        for(int row = 0; row < table.getRowCount(); row++){
            String curId =  table.getModel().getValueAt(row, 0).toString();
            if(curId.equals(id)){
                return row;
            }
        }
        return -1;
    }


    public ImageIcon scaleImage(ImageIcon icon, int maxHeight) {
//        int realHeight = (int) ((double)maxWidth / (double) icon.getIconWidth() * icon.getIconHeight());
        int realWidth = (int) ((double) maxHeight / (double) icon.getIconHeight() * icon.getIconWidth());
        return new ImageIcon(icon.getImage().getScaledInstance(realWidth, maxHeight , Image.SCALE_DEFAULT));
    }

    public void cleanDishEditAttributeText(){
        dishIDTextField.setText("");
        dishCostTextField.setText("");
        dishNameTextField.setText("");
        dishPriceTextField.setText("");
    }

    public void cleanOrderText(){
        orderIDText.setText("");
        orderPriceText.setText("");
        orderQuantityText.setText("");
        orderTipsText.setText("");
    }


    public void SetTableColor(JTable table) {
        table.setGridColor(Color.BLACK);
        table.setBackground(Color.lightGray);       // 背景颜色
        table.setSelectionBackground(Color.blue);   // 选中高亮颜色
        table.setShowGrid(false);
        table.setShowHorizontalLines(true);
    }



    public mainForm() {

        DishFactory dishFactory = new DishFactory();
        Menu menuList = new Menu();
        Cart cart = new Cart();
        Order order = new Order();
        OrderFactory orderFactory = new OrderFactory();
        OrderQueue orderQueue = new OrderQueue();

        final CartEntry[] cartEntry = new CartEntry[1];

        ////////Initialization the cartTable/////////
        String[] columnNames = {"DishID","Name", "Price","Quality","Picture"};
        DefaultTableModel cartTableModel = new DefaultTableModel(0, 0);
        cartTableModel.setColumnIdentifiers(columnNames);
        tableCart.setModel(cartTableModel);
        SetTableColor(tableCart);
        tableCart.setRowHeight(70);
        tableCart .getColumn("Picture").setCellRenderer(new myTableCellRenderer());

        ////////Initialization the menuTable/////////
        String[] menuNames = {"Dish ID","Name", "Price","Picture"};
        DefaultTableModel menuTableModel = new DefaultTableModel(0, 0);
        menuTableModel.setColumnIdentifiers(menuNames);
        menuTable.setModel(menuTableModel);
        SetTableColor(menuTable);
        //get picture column and override TableCellRenderer class component methods
        menuTable.setRowHeight(70);
        menuTable .getColumn("Picture").setCellRenderer(new myTableCellRenderer());

        ////////Initialization the orderQueue/////////
        String[] orderQueueTableNames ={"Order Id"} ;
        DefaultTableModel orderQueueTextAreaModel = new DefaultTableModel();
        orderQueueTextAreaModel.setColumnIdentifiers(orderQueueTableNames);
        orderQueueTable.setModel(orderQueueTextAreaModel);

        TimerThread timerThread = new TimerThread(orderQueueTextAreaModel, orderQueue);
        timerThread.start();


//        for (int i = 0; i < 10; i++) {
//            orderQueue.addToQueue(Integer.parseInt(orderFactory.createOrderID()));
//
//        }

        ////////Initialization the orderListTable/////////
        String[] orderListHeader = {"orderID","Total Quantity","Tips","Total Price"};
        DefaultTableModel orderListTableModel = new DefaultTableModel(0, 0);
        orderListTableModel.setColumnIdentifiers(orderListHeader);
        orderListTable.setModel(orderListTableModel);
        SetTableColor(orderListTable);



        //////////// Initialization of dish added text////////////
        dishIDTextField.setText(dishFactory.nextId() + "");
        dishIDTextField.setEditable(false);
        dishIDTextField.setBackground(Color.lightGray);

        /////////// Initialization of order information text ////////////
        orderIDText.setEditable(false);
        orderPriceText.setEditable(false);
        orderQuantityText.setEditable(false);
        orderIDText.setBackground(Color.LIGHT_GRAY);
        orderPriceText.setBackground(Color.LIGHT_GRAY);
        orderQuantityText.setBackground(Color.LIGHT_GRAY);

        /////////////button in cart///////////
        increaseDishBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //find dishID
                if (tableCart.getSelectedRow() != -1) {
                    String dishID = getSelectedDishId(tableCart);
                    //find dish in menulist
                    Dish dish = menuList.getDish(dishID);
                    //add dish and quantity to the cart
                    cart.addToCart(dish);
                    cartTableModel.setValueAt(cart.getDishQuantity(dishID) + "", findRow(tableCart, dishID), 3);
                    System.out.println("Add to cart.");
                }
            }
        });

        decreaseDishBtn.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableCart.getSelectedRow() != -1) {
                    //find dishID
                    String dishID = getSelectedDishId(tableCart);
                    //find dish in menulist
                    Dish dish = menuList.getDish(dishID);
                    //remove dish or reduce dish quantity from the cart
                    if (cart.getDishQuantity(dishID) > 1) {
                        cart.removeFromCart(dish);
                        cartTableModel.setValueAt(cart.getDishQuantity(dishID), findRow(tableCart, dishID), 3);
                    } else if (cart.getDishQuantity(dishID) == 1) {
                        cart.removeFromCart(dish);
                        cartTableModel.removeRow(findRow(tableCart, dishID));
                    }
                }
            }
        });


        newDishBtn.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                    //input dish information
                    String dishID = dishIDTextField.getText();
                    String dishName = dishNameTextField.getText();
                    String dishPrice = dishPriceTextField.getText();
                    String dishCost = dishCostTextField.getText();

                    //get dish picture from JLable
                    ImageIcon imageIcon = scaleImage((ImageIcon) picReviewLabel.getIcon(), 70);
                    JLabel imageLabel = new JLabel();
                    imageLabel.setIcon(imageIcon);

                    //pass dish information into dishFactory
                    Dish dish = dishFactory.createDish(dishName, dishPrice, dishCost, imageIcon);

                    //display dish information in menu panel
                    menuTableModel.addRow(new Object[]{dishID, dishName, dishPrice, imageLabel});
                    //add dishes to dishHashMap in Menu Class
                    menuList.addDishToMenu(dish);
                    cleanDishEditAttributeText();
                    dishIDTextField.setText(dishFactory.nextId() + "");
                    dishIDTextField.setEditable(false);
            }
        });




        selectButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = openDialogAndSelectFile(selectButton);
                if (!filePath.equals("")) {
                    ImageIcon icon=new ImageIcon(filePath);
                    picReviewLabel.setIcon(scaleImage(icon, 100));
                    picReviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
                }

            }
        });

        /////////////button in menu panel///////////
        increaseCartBtn.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(menuTable.getSelectedRow() != -1) {
                    //find dishID
                    String dishID = getSelectedDishId(menuTable);
                    //find dish in menulist
                    Dish dish = menuList.getDish(dishID);
                    //add dish and quantity to the cart
                    boolean isNewDish = cart.addToCart(dish);

                    System.out.println(isNewDish);

                    if (isNewDish) {
                        JLabel jLabel = new JLabel();
                        ImageIcon icon = dish.getIcon();
                        jLabel.setIcon(dish.getIcon());

                        cartTableModel.addRow(new Object[]{dish.getID(), dish.getName(), dish.getPrice(), cart.getDishQuantity(dishID) + "", jLabel});
                    } else {
                        cartTableModel.setValueAt(cart.getDishQuantity(dishID) + "", findRow(tableCart, dishID), 3);
                    }

                    System.out.println("Add to cart.");
                }
            }
        });


        decreaseCartBtn.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(menuTable.getSelectedRow() != -1) {
                    //find dishID
                    String dishID = getSelectedDishId(menuTable);
                    //find dish in menulist
                    Dish dish = menuList.getDish(dishID);
                    //remove dish or reduce dish quantity from the cart
                    if (cart.getDishQuantity(dishID) > 1) {
                        cart.removeFromCart(dish);
                        cartTableModel.setValueAt(cart.getDishQuantity(dishID), findRow(tableCart, dishID), 3);
                    } else if (cart.getDishQuantity(dishID) == 1) {
                        cart.removeFromCart(dish);
                        cartTableModel.removeRow(findRow(tableCart, dishID));
                    }
                }
            }
        });


        submitCartBtn.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                orderIDText.setText(orderFactory.createOrderID());
                orderQuantityText.setText(cart.getOrderQuantity() + "");
                orderPriceText.setText(cart.getOrderPrice()+"");

                //add cart data to order, including orderID, orderPrice, orderQuantity, orderCost,orderHashMap from the cart
                orderFactory.createOrderID(Integer.parseInt(orderIDText.getText()),cart.getOrderPrice(),cart.getOrderQuantity(),cart.getOrderCost(),cart.getIdToCartEntryMap());

                //clear the cart data in hashMap
                cart.clearCart();

                //clear the cartTable
                cartTableModel.setRowCount(0);
            }
        });



        submitOrderButton1.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String tips = orderTipsText.getText();
                int orderID = Integer.parseInt(orderIDText.getText());
                orderFactory.updateOrderTips(orderID,Integer.parseInt(orderTipsText.getText()));
                orderQueue.addToQueue(orderID);
                cleanOrderText();
                int orderBeforeYours = orderQueue.size() -1;
                orderQueueIfmTextField.setText("Your orderID is " + orderID +  ". There are " + orderBeforeYours+ " orders before you.");
                System.out.println("click: " + orderQueue.size());

                String[] orderInf = new String[4];
                orderInf[0] = orderID + "";
                orderInf[1] = orderFactory.getOrderHashMap().get(orderID).getQuantity() + "";
                orderInf[2] = orderFactory.getOrderHashMap().get(orderID).getTips() + "";
                orderInf[3] = orderFactory.getOrderHashMap().get(orderID).getOrderPriceAndTips() + "";

                orderListTableModel.addRow(orderInf);

            }
        });

        removeOrderButton1.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanOrderText();
            }
        });

        editDishBtn.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderID = getSelectedDishId(menuTable);
                dishIDTextField.setText(orderID);
                dishNameTextField.setText(menuList.getDish(orderID).getName());
                dishPriceTextField.setText(menuList.getDish(orderID).getPrice());
                dishCostTextField.setText(menuList.getDish(orderID).getCost());
            }
        });


        conferDishButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String dishID = dishIDTextField.getText();
                String dishName = dishNameTextField.getText();
                String dishPrice = dishPriceTextField.getText();
                String dishCost = dishCostTextField.getText();


                ImageIcon imageicon = (ImageIcon) picReviewLabel.getIcon();
                JLabel imageLabel = new JLabel();
                imageLabel.setIcon(imageicon);


                Dish dish = dishFactory.editDish(dishID,dishName, dishPrice, dishCost,imageicon);
                menuList.addDishToMenu(dish);
                menuTableModel.removeRow(findRow(menuTable, dishID));
                menuTableModel.addRow(new Object[]{dishID, dishName, dishPrice, imageLabel});
                cleanDishEditAttributeText();
                dishIDTextField.setText(dishFactory.nextId() + "");
                dishIDTextField.setEditable(false);


            }
        });

        lilySRestaurantClosedButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                totalOrderTextField.setText(orderFactory.getTotalOrderQuantity() + "");
                totalCostTextField.setText(orderFactory.getTotalOrderCost() + "");
                totalRevenueTextField.setText(orderFactory.getTotalOrderPrice() + "");
                totalProfitTextField.setText(orderFactory.getTotalOrderProfit() + "");
                totalTipsTextField.setText(orderFactory.getTotalOrderTips() + "");

            }
        });
    }




    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new mainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.pack();
        frame.setVisible(true);

    }


}
