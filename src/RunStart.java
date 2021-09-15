import javax.swing.*;

/**
 * @author luqiwei
 * @version 1.0
 * @date 2021/9/15 11:34
 */
public class RunStart {

        private JFrame frame;
        public static void main(String[] args) {
            // write your code here
            try {
                ping form1 = new ping();   //定义我们用JFormDesigner 生成的窗口 form1的实例
                form1.setVisible(true);
                // form1.setBounds(400, 200, 636, 561);
                form1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //form1.getContentPane().setLayout(null);
                //form1.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
