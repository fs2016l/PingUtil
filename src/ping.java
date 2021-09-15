import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Wed Sep 15 13:02:59 CST 2021
 */



/**
 * @author aaa
 */
public class ping extends JFrame {
    public ping() {
        initComponents();
    }
    JFileChooser jfc=new  JFileChooser(new File("."));
    FileUtils fileUtils = new FileUtils();


    private void selectFile(ActionEvent e) {
        JButton jbt= (JButton) e.getSource();
        System.err.println(jbt);
        //1.点击bOpen要做的事为
        //打开文件选择器对话框
        int status = jfc.showOpenDialog(jfc);
        //没有选打开按钮结果提示
        if (status != JFileChooser.APPROVE_OPTION) {
            filePathIn.setText("没有选中文件");
        } else {
            try {
                //被选中的文件保存为文件对象
                File file = jfc.getSelectedFile();
                Scanner scanner = new Scanner(file);
                String info = "";
                while (scanner.hasNextLine()) {

                    String str = scanner.nextLine();
                    info += str + "\r\n";
                }
                //把读取的数据存到文本框中
                filePathIn.setText(file.getPath());

            } catch (FileNotFoundException e1) {
                System.out.println("系统没有找到此文件");
                e1.printStackTrace();
            }
        }
    }

    private void readFile(ActionEvent e) {
        ipInText.setText("");
        FileUtils.readFile(filePathIn.getText());
        java.util.List<String> strings = FileUtils.readFile(filePathIn.getText());
        for (String string : strings) {
            ipInText.append(string);
            ipInText.append("\r\n");
        }

    }

    private void clean(ActionEvent e) {
        ipInText.setText("");
        ipOutText.setText("");
    }

    private void run(ActionEvent e) {
        System.err.println("123");
        // TODO add your code here
        ipOutText.setText("");
        String[] split = ipInText.getText().split("\\s+");
        Executor executor = Executors.newCachedThreadPool();// 启用多线程
        for (String s : split) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.err.println(s);
                        PingEntity pingEntity = PingUtils.ping1(s);
                        ipOutText.append(pingEntity.getIp()+":   "+pingEntity.isState()+"\r\n");
                    } catch (Exception e) {
                    }
                }
            });
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        buttonBar = new JPanel();
        filePathIn = new JTextField();
        seleltFileBtn = new JButton();
        readBtn = new JButton();
        cleanBtn = new JButton();
        runBtn = new JButton();
        panel1 = new JPanel();
        scrollPane5 = new JScrollPane();
        ipInText = new JTextArea();
        scrollPane1 = new JScrollPane();
        ipOutText = new JTextArea();

        //======== this ========
        setMinimumSize(new Dimension(516, 339));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setLayout(new BorderLayout());

            //======== buttonBar ========
            {
                buttonBar.setPreferredSize(new Dimension(59, 89));
                buttonBar.setLayout(new MigLayout(
                    "fillx,insets dialog,alignx right",
                    // columns
                    "[fill]" +
                    "[fill]" +
                    "[button,fill]" +
                    "[button,fill]",
                    // rows
                    "[]" +
                    "[]"));
                buttonBar.add(filePathIn, "cell 0 0 4 1");

                //---- seleltFileBtn ----
                seleltFileBtn.setText("\u9009\u62e9\u6587\u4ef6");
                seleltFileBtn.addActionListener(e -> selectFile(e));
                buttonBar.add(seleltFileBtn, "cell 0 1,growy");

                //---- readBtn ----
                readBtn.setText("\u8bfb\u53d6");
                readBtn.addActionListener(e -> readFile(e));
                buttonBar.add(readBtn, "cell 1 1,growy");

                //---- cleanBtn ----
                cleanBtn.setText("\u6e05\u7a7a");
                cleanBtn.addActionListener(e -> clean(e));
                buttonBar.add(cleanBtn, "cell 2 1,growy");

                //---- runBtn ----
                runBtn.setText("\u6267\u884c");
                runBtn.setPreferredSize(new Dimension(178, 130));
                runBtn.addActionListener(e -> run(e));
                buttonBar.add(runBtn, "cell 3 1");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            //======== panel1 ========
            {
                panel1.setLayout(new MigLayout(
                    "fillx,hidemode 3,aligny center",
                    // columns
                    "[fill]" +
                    "[fill]",
                    // rows
                    "[150]"));

                //======== scrollPane5 ========
                {
                    scrollPane5.setViewportView(ipInText);
                }
                panel1.add(scrollPane5, "cell 0 0,growy");

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(ipOutText);
                }
                panel1.add(scrollPane1, "cell 1 0,growy");
            }
            dialogPane.add(panel1, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel buttonBar;
    private JTextField filePathIn;
    private JButton seleltFileBtn;
    private JButton readBtn;
    private JButton cleanBtn;
    private JButton runBtn;
    private JPanel panel1;
    private JScrollPane scrollPane5;
    private JTextArea ipInText;
    private JScrollPane scrollPane1;
    private JTextArea ipOutText;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
