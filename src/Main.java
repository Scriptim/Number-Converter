import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

/**
 * @author scriptim
 */
public class Main {

  private static JFrame frame = new JFrame();

  private static JLabel label2 = new JLabel("Binary");
  private static JLabel label8 = new JLabel("Octal");
  private static JLabel label10 = new JLabel("Decimal");
  private static JLabel label16 = new JLabel("Hexadecimal");

  private static JTextField text2 = new JTextField();
  private static JTextField text8 = new JTextField();
  private static JTextField text10 = new JTextField();
  private static JTextField text16 = new JTextField();

  public static void main(String[] args) {

    label2.setToolTipText("Base 2");
    label8.setToolTipText("Base 8");
    label10.setToolTipText("Base 10");
    label16.setToolTipText("Base 16");

    label2.setLabelFor(text2);
    label8.setLabelFor(text8);
    label10.setLabelFor(text10);
    label16.setLabelFor(text16);

    frame.setTitle("Number Converter by Scriptim");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLayout(new GridLayout(8, 1));
    frame.setSize(500, 300);
    frame.setLocationRelativeTo(null);

    frame.add(label2);
    frame.add(text2);
    frame.add(label8);
    frame.add(text8);
    frame.add(label10);
    frame.add(text10);
    frame.add(label16);
    frame.add(text16);

    frame.setVisible(true);

  }

}
